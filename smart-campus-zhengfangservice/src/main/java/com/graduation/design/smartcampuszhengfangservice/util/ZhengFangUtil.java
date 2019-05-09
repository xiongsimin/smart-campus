package com.graduation.design.smartcampuszhengfangservice.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ZhengFangUtil {
    private String url;
    private Map<String, String> Cookie;//存储当前学生模拟访问时的cookie
    private byte[] checkCodeBytes;//验证码图片的字节数组
    private String UserAgent;//请求头参数UserAgent
    private int timeout;//访问时超时时间
    private String __VIEWSTATE;//从登录页获取的__VIEWSTATE，用于登录时的参数
    private String __VIEWSTATE1;//默认个人课表页参数
    private String __VIEWSTATE2;//默认成绩页参数
    private String __VIEWSTATE3;//默认考试信息页参数
    private Document document;//
    private String stuId;
    private String stuPwd;
    private String stuName;//学生名字
    private String sex;//性别
    private String academy;//学院
    private String major;//专业
    private String attenCampusTime;//入学时间（当前所在级）
    private String idCard;//身份证号
    private String birthday;//出生日期
    private Document personalClassSchedule;//个人课表（按学期查）
    private Document personalGrade;//成绩（按学期查）
    private Document examInfo;//考试信息

    /**
     * 初始化
     *
     * @param zf
     */
    public void init(ZhengFang zf) {
        this.stuId = zf.getStuId();
        this.stuPwd = zf.getStuPwd();
        this.url = zf.getUrl();
    }

    /**
     * 获取__VIEWSTATE
     *
     * @param i 由于多个请求都需要前一个页面的__VIEWSTATE，所以用i区分是哪个
     */
    public void get__VIEWSTATE(int i) {
        for (Element el : this.document.getElementsByTag("input")) {
            if (el.attr("name").equals("__VIEWSTATE")) {
                if (i == 0)
                    this.__VIEWSTATE = el.val();
                else if (i == 1)
                    this.__VIEWSTATE1 = el.val();
                else if (i == 2) {
                    this.__VIEWSTATE2 = el.val();
                } else if (i == 3) {
                    this.__VIEWSTATE3 = el.val();
                }
                break;
            }
        }
    }

    /**
     * 根据ip访问学校教务系统获取Cookie并且获取下一步所需参数__VIEWSTATE
     */
    public boolean getCookieAnd__VIEWSTATE() {
        Connection connection = Jsoup.connect(this.url);
        try {
            Connection.Response response = connection.timeout(timeout).method(Connection.Method.GET).ignoreContentType(true).execute();
            this.Cookie = response.cookies();
            this.document = Jsoup.parse(response.body());
            get__VIEWSTATE(0);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 获取验证码（字节数组），测试时写入路径D://temp//yan.gif
     *
     * @return
     */
    public boolean getCheckCode() {
        String subUrl = "/CheckCode.aspx?";
        Connection connection = Jsoup.connect(this.url + subUrl);
        try {
            Connection.Response response = connection.cookies(this.Cookie).method(Connection.Method.GET).ignoreContentType(true).timeout(this.timeout).execute();
            this.checkCodeBytes = response.bodyAsBytes();
            /*h1 此处为测试用，最后应删除*/
            File img = new File("D://temp//yan.gif");
            if (!img.exists()) {
                img.createNewFile();
            } else {
                img.delete();
                img.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(img);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            bos.write(this.checkCodeBytes);
            bos.flush();
            bos.close();
            fos.close();
            /*h1 此处为测试用，最后应删除*/
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 模拟登陆（只有登陆后才能查询成绩、课表等信息），同时获取学生名字
     */
    public boolean login() {
        System.out.println("请输入验证码！");
        Scanner scanner = new Scanner(System.in);
        String yan = scanner.next();
        scanner.close();
        String subUrl = "/default2.aspx";
        Map<String, String> data = new HashMap<>();
        data.put("txtUserName", this.stuId);
        data.put("TextBox2", this.stuPwd);
        data.put("__VIEWSTATE", this.__VIEWSTATE);
        data.put("txtSecretCode", yan);
        data.put("Textbox1", "");
        data.put("RadioButtonList1", "学生");
        data.put("Button1", "");
        data.put("lbLanguage", "");
        data.put("hidPdrs", "");
        data.put("hidsc", "");
        try {
            //登陆
            Connection.Response response = Jsoup.connect(this.url + subUrl).cookies(this.Cookie).data(data).method(Connection.Method.POST).ignoreContentType(true).timeout(this.timeout).execute();
            //获取学生名字
            this.document = Jsoup.parse(response.body());
            Element el = this.document.getElementById("xhxm");
            this.stuName = el.text().substring(0, el.text().indexOf("同学"));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取学生详细个人信息
     */
    public boolean getPersonalDetails() {
        String subUrl = "/xsgrxx.aspx?xh=" + this.stuId + "&xm=" + this.stuName + "&gnmkdm=N121501";
        String Referer = this.url + "/xs_main.aspx?xh=" + this.stuId;
        try {
            Connection connection = Jsoup.connect(this.url + subUrl);
            Connection.Response response = connection.cookies(this.Cookie).header("Referer", Referer).method(Connection.Method.GET).ignoreContentType(true).timeout(this.timeout).execute();
            this.document = Jsoup.parse(response.body());
            List<Element> elementList = this.document.getElementsByTag("td");
            for (int i = 0; i < elementList.size(); i++) {
                if (elementList.get(i).text().equals("性别：")) {
                    this.sex = elementList.get(i + 1).text();
                    continue;
                }
                if (elementList.get(i).text().equals("出生日期：")) {
                    this.birthday = elementList.get(i + 1).text();
                    continue;
                }
                if (elementList.get(i).text().equals("身份证号：")) {
                    this.idCard = elementList.get(i + 1).text();
                    continue;
                }
                if (elementList.get(i).text().equals("学院：")) {
                    this.academy = elementList.get(i + 1).text();
                    continue;
                }
                if (elementList.get(i).text().equals("专业名称：")) {
                    this.major = elementList.get(i + 1).text();
                    continue;
                }
                if (elementList.get(i).text().equals("当前所在级：")) {
                    this.attenCampusTime = elementList.get(i + 1).text();
                    break;
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取默认个人课表（当前学期）并且获取下一步（按学年和学期获取课表）必要参数__VIEWSTATE1
     *
     * @return
     */
    public boolean getDefaultPersonalClassSchedule() {
        String subUrl = "/xskbcx.aspx?xh=" + this.stuId + "&xm=" + this.stuName + "&gnmkdm=N121603";
        String Referer = this.url + "/xs_main.aspx?xh=" + this.stuId;
        Connection connection = Jsoup.connect(this.url + subUrl);
        try {
            Connection.Response response = connection.method(Connection.Method.GET).ignoreContentType(true).cookies(this.Cookie).header("Referer", Referer).timeout(this.timeout).execute();
            //获取默认课表【暂时未做数据清洗】
            this.personalClassSchedule = Jsoup.parse(response.body());
            this.document = this.personalClassSchedule;
            get__VIEWSTATE(1);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据学年和学期查课表
     *
     * @param xnd 学年 eg:"2017-2018"
     * @param xqd 学期 eg:"1"
     * @return
     */
    public boolean getPersonalClassSchedule(String xnd, String xqd) {
        String Referer = this.url + "/xskbcx.aspx?xh=" + this.stuId + "&xm=" + this.stuName + "&gnmkdm=N121603";
        Map<String, String> data = new HashMap<>();
        data.put("__EVENTTARGET", "xnd");
        data.put("__EVENTARGUMENT", "");
        data.put("__VIEWSTATE", this.__VIEWSTATE1);
        data.put("xnd", xnd);
        data.put("xqd", xqd);
        Connection connection = Jsoup.connect(this.url + "/xskbcx.aspx?xh=" + this.stuId + "&xm=" + this.stuName + "&gnmkdm=N121603");
        try {
            Connection.Response response = connection.method(Connection.Method.POST).ignoreContentType(true).header("Referer", Referer).cookies(this.Cookie).data(data).timeout(this.timeout).execute();
            //根据学年和学期查课表【暂时未做数据清洗】
            this.personalClassSchedule = Jsoup.parse(response.body());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取成绩第一步（该步骤是为了获得下一步请求必要的参数__VIEWSTATE2）
     *
     * @return
     */
    public boolean getGradeFirstStep() {
        String Referer = this.url + "/xs_main.aspx?xh=" + this.stuId;
        Connection connection = Jsoup.connect(this.url + "/xscj_gc2.aspx?xh=" + this.stuId + "&xm=" + this.stuName + "&gnmkdm=N121605");
        try {
            Connection.Response response = connection.method(Connection.Method.GET).ignoreContentType(true).cookies(this.Cookie).header("Referer", Referer).timeout(this.timeout).execute();
            this.document = Jsoup.parse(response.body());
            get__VIEWSTATE(2);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取成绩第二步，根据学年和学期获取成绩
     *
     * @param ddlXN 学年 eg:2018-2019
     * @param ddlXQ 学期 eg:1
     * @return
     */
    public boolean getGradeSecondStep(String ddlXN, String ddlXQ) {
        String subUrl = "/xscj_gc2.aspx?xh=" + this.stuId + "&xm=" + this.stuName + "&gnmkdm=N121605";
        String Referer = this.url + "/xscj_gc2.aspx?xh=" + this.stuId + "&xm=" + this.stuName + "&gnmkdm=N121605";
        Map<String, String> data = new HashMap<>();
        data.put("__VIEWSTATE", this.__VIEWSTATE2);
        data.put("ddlXN", ddlXN);
        data.put("ddlXQ", ddlXQ);
        data.put("Button1", "按学期查询");
        Connection connection = Jsoup.connect(this.url + subUrl);
        try {
            Connection.Response response = connection.method(Connection.Method.POST).ignoreContentType(true).cookies(this.Cookie).header("Referer", Referer).data(data).timeout(this.timeout).execute();
            //根据学年和学期查成绩【暂时未做数据清洗】
            this.personalGrade = Jsoup.parse(response.body());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取默认学期考试信息，并获取该页参数__VIEWSTATE3，为下一步操作使用//
     *
     * @return
     */
    public boolean getDefaultExamInfo() {
        String Referer = this.url + "/xs_main.aspx?xh=" + this.stuId;
        Connection connection = Jsoup.connect(this.url + "/xskscx.aspx?xh=" + this.stuId + "&xm=" + this.stuName + "&gnmkdm=N121604");
        try {
            Connection.Response response = connection.method(Connection.Method.GET).ignoreContentType(true).cookies(this.Cookie).header("Referer", Referer).timeout(this.timeout).execute();
            this.document = Jsoup.parse(response.body());
            get__VIEWSTATE(3);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    //http://202.206.243.3/xskscx.aspx?xh=150104010145&xm=%D0%DC%CB%BC%C3%F4&gnmkdm=N121604
    public boolean getExamInfo(String xnd,String xqd){
        String subUrl = "/xskscx.aspx?xh=" + this.stuId + "&xm=" + this.stuName + "&gnmkdm=N121604";
        String Referer = this.url + "/xskscx.aspx?xh=" + this.stuId + "&xm=" + this.stuName + "&gnmkdm=N121604";
        Map<String, String> data = new HashMap<>();
        data.put("__VIEWSTATE", this.__VIEWSTATE3);
        data.put("__EVENTARGUMENT", "");
        data.put("xnd", xnd);
        data.put("xqd", xqd);
        data.put("__EVENTTARGET", "xnd");
        Connection connection = Jsoup.connect(this.url + subUrl);
        try {
            Connection.Response response = connection.method(Connection.Method.POST).ignoreContentType(true).cookies(this.Cookie).header("Referer", Referer).data(data).timeout(this.timeout).execute();
            //根据学年和学期查成绩【暂时未做数据清洗】
            this.examInfo = Jsoup.parse(response.body());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void printf() {
        System.out.println(this.examInfo);
    }

    //    /**
//     * 封装获取个人详细信息操作
//     *
//     * @return
//     */
//    public boolean doGetPersonalDetails(ZhengFang zf) {
//        //初始化
//        init(zf);
//        //获取cookie和参数__VIEWSTATE
//        if(!getCookieAnd__VIEWSTATE()){
//            return false;
//        }
//        //获取验证码
//        if(!getCheckCode()){
//            return false;
//        }
//        //登录
//        if(!login()){
//            return false;
//        }
//        //获取个人信息
//        if(!getPersonalDetails()){
//            return false;
//        }
//        return true;
//    }
    public static void main(String[] args) {

        ZhengFang zf = new ZhengFang();
        zf.setStuId("150104010145");
        zf.setStuPwd("xxxxx00000qq");
        zf.setUrl("http://202.206.243.6");
        ZhengFangUtil zfu = new ZhengFangUtil();
        zfu.init(zf);
        zfu.getCookieAnd__VIEWSTATE();
        zfu.getCheckCode();
        zfu.login();
        zfu.getDefaultExamInfo();
        zfu.getExamInfo("2016-2017","1");
        /*zfu.getDefaultPersonalClassSchedule();
        zfu.getPersonalClassSchedule("2016-2017", "1");*/
        zfu.printf();
    }
}
