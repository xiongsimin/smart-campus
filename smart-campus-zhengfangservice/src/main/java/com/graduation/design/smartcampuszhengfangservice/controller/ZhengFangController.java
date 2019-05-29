package com.graduation.design.smartcampuszhengfangservice.controller;

import com.graduation.design.smartcampuszhengfangservice.entity.CookieViewState;
import com.graduation.design.smartcampuszhengfangservice.entity.Result;
import com.graduation.design.smartcampuszhengfangservice.util.ZhengFang;
import com.graduation.design.smartcampuszhengfangservice.util.ZhengFangUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ZhengFangController {
    @GetMapping("/checkCode")
    @ResponseBody
    public Result renewCheckCodePicture(int userId, Result rs) {
        ZhengFang zhengFang = new ZhengFang();
        zhengFang.setUrl("http://202.206.243.6");
        ZhengFangUtil zhengFangUtil = new ZhengFangUtil();
        zhengFangUtil.init(zhengFang);
        zhengFangUtil.getCookieAnd__VIEWSTATE();
        zhengFangUtil.getCheckCode(userId);//执行完这一步，会将最新验证码图片写入Nginx服务器下对应用户的文件夹下
        CookieViewState cookieViewState = new CookieViewState(zhengFangUtil.getCookie(), zhengFangUtil.get__VIEWSTATE());
        System.out.println(cookieViewState);
        rs.setSuccess(true);
        rs.setData(cookieViewState);
        return rs;
    }
}
