package com.graduation.design.smartcampuszhengfangservice;

import com.by_syk.graphiccr.core.GraphicC2Translator;

import java.io.File;

public class Test {
    public static void main(String[] args){
        File file=new File("D:/temp/test/code.gif");
        GraphicC2Translator translator = GraphicC2Translator.getInstance();
        String result = translator.translate(file);
        System.out.println("TRANSLATE " + result);
    }
}
