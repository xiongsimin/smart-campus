package com.graduation.design.smartcampususerservice.util;

import java.util.Random;

public class RandomCodeUtil {
    /**
     * 生成随机码
     * @param length 随机码长度
     * @return
     */
    public static String getRandomCode(int length){
        String baseCode="0123456789";
        Random random=new Random();
        String randomCode="";
        for(int i=0;i<length;i++){
            int r;
            r=random.nextInt(baseCode.length());
            randomCode+=baseCode.charAt(r);
        }
        return randomCode;
    }
    public static void main(String[] args){
        System.out.println(getRandomCode(6));
    }
}
