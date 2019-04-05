package com.graduation.design.smartcampususerservice.thread;

import com.graduation.design.smartcampususerservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class ClearCheckCodeThread extends Thread {
    private String email;
    @Autowired
    private UserService userService;
    @Override
    public void run(){
        try {
            Thread.sleep(5000);//
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
//            System.out.println(11+this.email);
//            this.userService.clearCheckCode(this.email);
        }
    }
    public void init(String email){
        this.email=email;
    }
}
