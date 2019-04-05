package com.graduation.design.smartcampususerservice.controller;

import com.graduation.design.smartcampususerservice.entity.User;
import com.graduation.design.smartcampususerservice.service.UserService;
import com.graduation.design.smartcampususerservice.thread.ClearCheckCodeThread;
import com.graduation.design.smartcampususerservice.util.EmailUtil;
import com.graduation.design.smartcampususerservice.util.RandomCodeUtil;
import com.graduation.design.smartcampususerservice.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 新用户注册第一步（发送验证码）
     *
     * @param user
     * @return
     */
    @GetMapping("/register")
    public Result signUpFirstStep(User user, Result rs) {
        int active = 3;//设置有效时间
        if (user.getEmail() != null) {
            User user1 = this.userService.findUserByEmail(user.getEmail());
            if (user1 != null) {//该邮箱提交过注册申请
                if (user1.getPassword() != null) {//该邮箱提交过注册申请且完成了注册
                    rs.setSuccess(false);
                    rs.setMsg("该邮箱已被注册！");
                } else {//该邮箱提交过注册申请但未完成注册（未设置登录密码）
                    //*****************************************************//
                    //****暂时未考虑多用户用同个邮箱同时提交注册申请的情况****//
                    //****************************************************//
                    //生成6位数字随机码
                    String checkCode = RandomCodeUtil.getRandomCode(6);
                    if (EmailUtil.sendEmail("smtp.qq.com", "utf-8", user.getEmail(), "2248950919@qq.com", "xiongsimin", "dqkiqyickjrqecfc", "欢迎注册智慧校园", "亲爱的用户，感谢您注册智慧校园App，这是您的专属验证码：" + checkCode + "，请在" + active + "分钟之内完成进行验证，请勿将验证码泄露给他人。")) {//邮件发送成功
                        user1.setCheckCode(checkCode);
                        Date date = new Date();
                        long time = date.getTime();
                        Timestamp activeTime = new Timestamp(time + active * 60 * 1000);
                        user1.setActiveTime(activeTime);
                        this.userService.renewCheckCode(user1);
                        rs.setSuccess(true);
                        rs.setMsg("邮件发送成功！");
                    } else {
                        rs.setSuccess(false);
                        rs.setMsg("邮件发送失败！");
                    }
                }
            } else {//该邮箱未提交过注册申请
                //生成6位数字随机码
                String checkCode = RandomCodeUtil.getRandomCode(6);
                if (EmailUtil.sendEmail("smtp.qq.com", "utf-8", user.getEmail(), "2248950919@qq.com", "xiongsimin", "dqkiqyickjrqecfc", "欢迎注册智慧校园", "亲爱的用户，感谢您注册智慧校园App，这是您的专属验证码：" + checkCode + "，请在" + active + "分钟之内完成进行验证，请勿将验证码泄露给他人。")) {//邮件发送成功
                    user.setCheckCode(checkCode);
                    Date date = new Date();
                    long time = date.getTime();
                    Timestamp activeTime = new Timestamp(time + active * 60 * 1000);
                    user.setActiveTime(activeTime);
                    this.userService.newUser(user);
                    rs.setSuccess(true);
                    rs.setMsg("邮件发送成功！");
                } else {
                    rs.setSuccess(false);
                    rs.setMsg("邮件发送失败！");
                }
            }
        } else {
            rs.setSuccess(false);
            rs.setMsg("邮箱不能为空！");
        }
        return rs;
    }

    /**
     * 新用户注册第二步（验证码验证）
     *
     * @param user
     * @param rs
     * @return
     */
    @PostMapping("/register")
    public Result signUpSecondStep(User user, Result rs) {
        if (user.getEmail() != null) {
            User user1 = this.userService.findUserByEmail(user.getEmail());
            if (user1 != null) {
                Date date = new Date();
                if (date.getTime() <= user1.getActiveTime().getTime()) {//验证码未过期
                    if (user.getCheckCode().equals(user1.getCheckCode())) {//验证码正确
                        rs.setSuccess(true);
                        rs.setMsg("验证成功！");
                    } else {//验证码错误
                        rs.setSuccess(false);
                        rs.setMsg("验证失败！验证码错误！");
                    }
                } else {//验证码已过期
                    rs.setSuccess(false);
                    rs.setMsg("验证失败！验证码已过期！");
                }
            } else {
                rs.setSuccess(false);
                rs.setMsg("邮箱未验证，请先获取验证码！");
            }
        } else {
            rs.setSuccess(false);
            rs.setMsg("验证失败！邮箱不能为空！");
        }
        return rs;
    }

    /**
     * 新用户注册第三步（设置密码）
     * 目前简单实现功能，后期考虑安全性需要在每一步生成一个序列码并存入数据库，下一步操作的参数中序列码正确才允许继续操作
     *
     * @param user
     * @param rs
     * @return
     */
    @PutMapping("/register")
    public Result signUpThirdStep(User user, Result rs) {
        if (user.getEmail() != null) {
            User user1 = this.userService.findUserByEmail(user.getEmail());
            if (user1 != null) {
                user1.setPassword(user.getPassword());
                this.userService.setPassword(user1);
                rs.setSuccess(true);
                rs.setMsg("密码设置成功！");
            } else {
                rs.setSuccess(false);
                rs.setMsg("密码设置失败！邮箱未验证！请先获取验证码！");
            }
        } else {
            rs.setSuccess(false);
            rs.setMsg("密码设置失败！邮箱不能为空！");
        }
        return rs;
    }

    @PostMapping("/login")
    public Result login(User user, Result rs) {
        if(user.getEmail()!=null){
            if(user.getPassword()!=null){
                User user1=this.userService.findUserByEmail(user.getEmail());
                if(user1!=null){
                    if(user1.getPassword()!=null){
                        if(user1.getPassword().equals(user.getPassword())){
                            rs.setSuccess(true);
                            rs.setMsg("登录成功！");
                        }else {
                            rs.setSuccess(false);
                            rs.setMsg("登录失败！密码错误！");
                        }
                    }else {
                        rs.setSuccess(false);
                        rs.setMsg("登录失败！请先完成注册！");
                    }
                }else {
                    rs.setSuccess(false);
                    rs.setMsg("登录失败！用户不存在！请注册！");
                }
            }else{
                rs.setSuccess(false);
                rs.setMsg("登录失败！密码不能为空！");
            }
        }else {
            rs.setSuccess(false);
            rs.setMsg("登录失败！邮箱不能为空！");
        }
        return rs;
    }
//    @PostMapping("/clearCheckCode")
//    public void clearCheckCode(User user){
//        //开启一个线程，3分钟后将数据库中的验证码清除
//        ClearCheckCodeThread clearCheckCodeThread=new ClearCheckCodeThread();
//        clearCheckCodeThread.init(user.getEmail());
//        clearCheckCodeThread.run();
//    }
}
