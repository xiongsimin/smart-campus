package com.graduation.design.smartcampususerservice.controller;

import com.graduation.design.smartcampususerservice.entity.*;
import com.graduation.design.smartcampususerservice.service.CampusService;
import com.graduation.design.smartcampususerservice.service.UserCampusService;
import com.graduation.design.smartcampususerservice.service.UserService;
import com.graduation.design.smartcampususerservice.thread.ClearCheckCodeThread;
import com.graduation.design.smartcampususerservice.util.EmailUtil;
import com.graduation.design.smartcampususerservice.util.RandomCodeUtil;
import com.graduation.design.smartcampususerservice.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@RestController
public class UserController {
    private final UserService userService;
    private final UserCampusService userCampusService;
    private final CampusService campusService;

    @Autowired
    public UserController(UserService userService, UserCampusService userCampusService, CampusService campusService) {
        this.userService = userService;
        this.userCampusService = userCampusService;
        this.campusService = campusService;
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
    public Result signUpSecondStep(@RequestBody User user, Result rs) {
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
    public Result signUpThirdStep(@RequestBody User user, Result rs) {
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

    /**
     * 登录
     *
     * @param user
     * @param rs
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user, Result rs) {
        if (user.getEmail() != null) {
            if (user.getPassword() != null) {
                User user1 = this.userService.findUserByEmail(user.getEmail());
                if (user1 != null) {
                    if (user1.getPassword() != null) {
                        if (user1.getPassword().equals(user.getPassword())) {
                            rs.setSuccess(true);
                            rs.setMsg("登录成功！");
                        } else {
                            rs.setSuccess(false);
                            rs.setMsg("登录失败！密码错误！");
                        }
                    } else {
                        rs.setSuccess(false);
                        rs.setMsg("登录失败！请先完成注册！");
                    }
                } else {
                    rs.setSuccess(false);
                    rs.setMsg("登录失败！用户不存在！请注册！");
                }
            } else {
                rs.setSuccess(false);
                rs.setMsg("登录失败！密码不能为空！");
            }
        } else {
            rs.setSuccess(false);
            rs.setMsg("登录失败！邮箱不能为空！");
        }
        return rs;
    }

    /**
     * 设置个人信息（昵称、性别、学校、教务系统学号、教务系统登录密码）
     *
     * @param userUserCampus
     * @param rs
     * @return
     */
    @PostMapping("/personalDetail")
    public Result setPersonalDetail(@RequestBody UserUserCampus userUserCampus, Result rs) {
        User user = userUserCampus.getUser();
        UserCampus userCampus = userUserCampus.getUserCampus();
        if (user.getEmail() != null) {
            User user1 = this.userService.findUserByEmail(user.getEmail());
            if (user1 != null) {
                if (user1.getPassword() != null) {
                    user1.setNickname(user.getNickname());
                    user1.setSex(user.getSex());
                    userCampus.setUserId(user1.getId());
                    if (this.userService.setPersonalDetail(user1, userCampus) == 2) {
                        rs.setSuccess(true);
                        rs.setMsg("用户信息设置成功！");
                    } else {
                        rs.setSuccess(false);
                        rs.setMsg("用户信息设置失败！");
                    }
                } else {
                    rs.setSuccess(false);
                    rs.setMsg("用户信息设置失败！请先完成注册！");
                }
            } else {
                rs.setSuccess(false);
                rs.setMsg("用户信息设置失败！该邮箱尚未注册！");
            }
        } else {
            rs.setSuccess(false);
            rs.setMsg("用户信息设置失败！邮箱不能为空！");
        }
        return rs;
    }

    /**
     * 获取用户信息
     *
     * @param user
     * @param rs
     * @return
     */
    @GetMapping("/personalDetail")
    public Result getPersonalDetail(User user, Result rs) {
        if (user.getEmail() != null) {
            user = userService.findUserByEmail(user.getEmail());
            if (user != null) {
                rs.setSuccess(true);
                rs.setData(user);
            } else {
                rs.setSuccess(false);
                rs.setMsg("用户信息获取失败！用户不存在！");
            }
        } else {
            rs.setSuccess(false);
            rs.setMsg("用户信息获取失败！邮箱不能为空！");
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

    /**
     * 获取当前时间年-月-日
     *
     * @return
     */
    @GetMapping("/getDate")
    public Result getDate(Result rs) {
        Calendar calendar = Calendar.getInstance();
        MyDate myDate = new MyDate();
        myDate.setYear(calendar.get(Calendar.YEAR));
        myDate.setMonth(calendar.get(Calendar.MONTH) + 1);
        myDate.setDay(calendar.get(Calendar.DATE));
        System.out.println(myDate);
        rs.setSuccess(true);
        rs.setData(myDate);
        return rs;
    }

    @PutMapping("/nickname")
    public Result setNickname(@RequestBody User user, Result rs) {
        if (userService.setNickname(user) == 1) {
            rs.setSuccess(true);
            rs.setMsg("更新昵称成功！");
        } else {
            rs.setSuccess(false);
            rs.setMsg("更新昵称失败！");
        }
        return rs;
    }

    @PutMapping("/sex")
    public Result setSex(@RequestBody User user, Result rs) {
        if (userService.setSex(user) == 1) {
            rs.setSuccess(true);
            rs.setMsg("更新性别成功！");
        } else {
            rs.setSuccess(false);
            rs.setMsg("更新性别失败！");
        }
        return rs;
    }

    /**
     * 新增或更新用户-学校关联记录
     *
     * @param user
     * @param rs
     * @return
     */
    @PostMapping("/userCampus")
    public Result setUserCampus(@RequestBody User user, Result rs) {
        if (user.getEmail() != null) {
            if ((userService.findUserByEmail(user.getEmail())) != null) {
                if (userCampusService.findUserCampusByUserId(user) != null) {//已存在记录，更新
                    UserCampus userCampus = userCampusService.findUserCampusByUserId(user);
                    Campus campus = campusService.findCampusByCampusName(user.getCampus().getCampusName());
                    System.out.println(campus);
                    userCampus.setCampusId(campus.getId());
                    if (userCampusService.updateUserCampus(userCampus) == 1) {
                        rs.setSuccess(true);
                        rs.setMsg("设置学校成功！");
                    } else {
                        rs.setSuccess(false);
                        rs.setMsg("设置学校失败！更新用户-学校关系失败！");
                    }
                } else {//不存在记录，新增
                    UserCampus userCampus = new UserCampus();
                    Campus campus = campusService.findCampusByCampusName(user.getCampus().getCampusName());
                    userCampus.setUserId(user.getId());
                    userCampus.setCampusId(campus.getId());
                    if (userCampusService.addUserCampus(userCampus) == 1) {
                        rs.setSuccess(true);
                        rs.setMsg("设置学校成功！");
                    } else {
                        rs.setSuccess(false);
                        rs.setMsg("设置学校失败！新增用户-学校关系失败！");
                    }
                }
            } else {
                rs.setSuccess(false);
                rs.setMsg("设置学校失败！用户不存在！");
            }
        } else {
            rs.setSuccess(false);
            rs.setMsg("设置学校失败！请求中不包含正确的用户邮箱信息！");
        }
        return rs;
    }

    @PutMapping("/academy")
    public Result setAcademy(@RequestBody User user, Result rs) {
        if (user.getEmail() != null) {
            if (userService.findUserByEmail(user.getEmail()) != null) {
                if (userCampusService.findUserCampusByUserId(user) != null) {//中间表存在
                    UserCampus userCampus = user.getUserCampus();
                    userCampus.setUserId(userService.findUserByEmail(user.getEmail()).getId());
                    if (userCampusService.setAcademy(userCampus) == 1) {
                        rs.setSuccess(true);
                        rs.setMsg("设置成功！");
                    }
                } else {
                    rs.setSuccess(false);
                    rs.setMsg("设置学院失败！请先设置学校！");
                }
            } else {
                rs.setSuccess(false);
                rs.setMsg("设置学院失败！用户不存在！");
            }
        } else {
            rs.setSuccess(false);
            rs.setMsg("设置学院失败！邮箱为空！");
        }
        return rs;
    }

    @PutMapping("/degree")
    public Result setDegree(@RequestBody User user, Result rs) {
        if (user.getEmail() != null) {
            if (userService.findUserByEmail(user.getEmail()) != null) {
                if (userCampusService.findUserCampusByUserId(user) != null) {//中间表存在
                    UserCampus userCampus = user.getUserCampus();
                    userCampus.setUserId(userService.findUserByEmail(user.getEmail()).getId());
                    if (userCampusService.setDegree(userCampus) == 1) {
                        rs.setSuccess(true);
                        rs.setMsg("设置成功！");
                    }
                } else {
                    rs.setSuccess(false);
                    rs.setMsg("设置学历失败！请先设置学校！");
                }
            } else {
                rs.setSuccess(false);
                rs.setMsg("设置学历失败！用户不存在！");
            }
        } else {
            rs.setSuccess(false);
            rs.setMsg("设置学历失败！邮箱为空！");
        }
        return rs;
    }

    @PutMapping("/major")
    public Result setMajor(@RequestBody User user, Result rs) {
        if (user.getEmail() != null) {
            if (userService.findUserByEmail(user.getEmail()) != null) {
                if (userCampusService.findUserCampusByUserId(user) != null) {//中间表存在
                    UserCampus userCampus = user.getUserCampus();
                    userCampus.setUserId(userService.findUserByEmail(user.getEmail()).getId());
                    if (userCampusService.setMajor(userCampus) == 1) {
                        rs.setSuccess(true);
                        rs.setMsg("设置成功！");
                    }
                } else {
                    rs.setSuccess(false);
                    rs.setMsg("设置专业失败！请先设置学校！");
                }
            } else {
                rs.setSuccess(false);
                rs.setMsg("设置专业失败！用户不存在！");
            }
        } else {
            rs.setSuccess(false);
            rs.setMsg("设置专业失败！邮箱为空！");
        }
        return rs;
    }

    @PutMapping("/attendCampusTime")
    public Result setAttendCampusTime(@RequestBody User user, Result rs) {
        if (user.getEmail() != null) {
            if (userService.findUserByEmail(user.getEmail()) != null) {
                if (userCampusService.findUserCampusByUserId(user) != null) {//中间表存在
                    UserCampus userCampus = user.getUserCampus();
                    userCampus.setUserId(userService.findUserByEmail(user.getEmail()).getId());
                    if (userCampusService.setAttendCampusTime(userCampus) == 1) {
                        rs.setSuccess(true);
                        rs.setMsg("设置成功！");
                    }
                } else {
                    rs.setSuccess(false);
                    rs.setMsg("设置入学时间失败！请先设置学校！");
                }
            } else {
                rs.setSuccess(false);
                rs.setMsg("设置入学时间失败！用户不存在！");
            }
        } else {
            rs.setSuccess(false);
            rs.setMsg("设置入学时间失败！邮箱为空！");
        }
        return rs;
    }

    /**
     * 设置/更新用户头像
     *
     * @param email
     * @param encodeImg base64转码后的图片
     * @param imgSuffix 原图片文件后缀
     * @param rs
     * @return
     */
    @PutMapping("/userImage")
    public Result setUserImage(String email, String encodeImg, String imgSuffix, @RequestParam("userImage") MultipartFile multipartFile, Result rs) {
        if (email != null) {
            User user = userService.findUserByEmail(email);
            if (user != null) {
                File fileDir = new File("D:\\nginx-1.15.8\\html\\smart_campus\\user_images\\" + user.getId());
                if (!fileDir.exists()) {
                    fileDir.mkdir();
                }
                File filePath = new File("D:\\nginx-1.15.8\\html\\smart_campus\\user_images\\" + user.getId() + "\\1" + imgSuffix);//用户头像文件，如：1.jpg 1.png
                if (!filePath.exists()) {
                    try {
                        filePath.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                        rs.setSuccess(false);
                        rs.setMsg("新建文件失败！");
                        return rs;
                    }
                }
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream("D:\\nginx-1.15.8\\html\\smart_campus\\user_images\\" + user.getId() + "\\1" + imgSuffix, false);
                    fos.write(multipartFile.getBytes());
                    fos.close();
                    rs.setSuccess(true);
                    rs.setMsg("上传头像成功！");
                } catch (IOException e) {
                    e.printStackTrace();
                    rs.setSuccess(false);
                    rs.setMsg("写入文件失败！");
                    return rs;
                }
            } else {
                rs.setSuccess(false);
                rs.setMsg("用户不存在！");
            }
        } else {
            rs.setSuccess(false);
            rs.setMsg("邮箱不能为空！");
        }
        return rs;
    }
    /*public static void main(String[] args){
        Calendar calendar = Calendar.getInstance();
        MyDate myDate=new MyDate();
        myDate.setYear(calendar.get(Calendar.YEAR));
        myDate.setMonth(calendar.get(Calendar.MONTH+1));
        myDate.setDay(calendar.get(Calendar.DATE));
        System.out.println(myDate);
    }*/
}
