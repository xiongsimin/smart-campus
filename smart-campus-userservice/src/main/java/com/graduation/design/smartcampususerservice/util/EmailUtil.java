package com.graduation.design.smartcampususerservice.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailUtil {
    public static boolean sendEmail(String hostName,String charset,String receiver,String sender,String senderName,String AuthCode,String subject,String msg){
        HtmlEmail htmlEmail=new HtmlEmail();
        htmlEmail.setHostName(hostName);
        htmlEmail.setCharset(charset);
        try {
            htmlEmail.addTo(receiver);
            htmlEmail.setFrom(sender,senderName);
            htmlEmail.setAuthentication(sender,AuthCode);//这是授权码
            htmlEmail.setSubject(subject);
            htmlEmail.setMsg(msg);
            htmlEmail.send();
            return true;
        } catch (EmailException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void main(String[] args){
        if(sendEmail("smtp.qq.com","utf-8","m15032327795@163.com","2248950919@qq.com","xiong","dqkiqyickjrqecfc","测试主题","这是测试内容")){
            System.out.println("发送成功！");
        }else {
            System.out.println("发送失败！");
        }
    }
}
