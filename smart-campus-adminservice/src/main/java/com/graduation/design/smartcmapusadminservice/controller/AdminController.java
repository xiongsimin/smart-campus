package com.graduation.design.smartcmapusadminservice.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.graduation.design.smartcmapusadminservice.entity.Admin;
import com.graduation.design.smartcmapusadminservice.entity.Campus;
import com.graduation.design.smartcmapusadminservice.entity.EducationSystem;
import com.graduation.design.smartcmapusadminservice.service.AdminService;
import com.graduation.design.smartcmapusadminservice.util.Result;

import java.net.URI;

import com.sun.jndi.toolkit.url.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    private final AdminService adminService;
    private final RestTemplate restTemplate;

    @Autowired
    public AdminController(AdminService adminService, RestTemplate restTemplate) {
        this.adminService = adminService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/login")
    public String login(Admin admin) {
        return "login/login";
    }

    @PostMapping("/doLogin")
    public String doLogin(String account, String password, ModelMap map, HttpSession session) {
        if (account != null && password != null) {
            Admin admin = this.adminService.findAdminByAccount(account);
            if (admin != null && admin.getPassword().equals(password)) {
                session.setAttribute("admin", admin);
                return "index/index";
            } else {
                map.addAttribute("msg", "登录失败！");
                return "login/login";
            }
        } else {
            map.addAttribute("msg", "账号或密码不能为空！");
            return "login/login";
        }
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "index/welcome";
    }

    @GetMapping("/quite")
    public void quite(HttpSession session, HttpServletResponse response) {
        session.removeAttribute("admin");
        try {
            response.sendRedirect("/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/campus")
    public String listCampus(ModelMap map) {
        String url = "http://smart-campus-userservice/campus";
        Result result = restTemplate.getForObject(url, Result.class);
        Gson gson = new Gson();
        Type campusListType = new TypeToken<List<Campus>>() {
        }.getType();
        List<Campus> campusList = new ArrayList<>();
        campusList = gson.fromJson(gson.toJson(result.getData()), campusListType);
        map.addAttribute("campusList", campusList);
        return "study/campus";
    }

    @GetMapping("/addCampusPage")
    public String addCampusPage(ModelMap map) {
        String url = "http://smart-campus-userservice/educationSystem";
        Result result = restTemplate.getForObject(url, Result.class);
        Gson gson = new Gson();
        Type educationSystemType = new TypeToken<List<EducationSystem>>() {
        }.getType();
        List<EducationSystem> educationSystemList = new ArrayList<>();
        educationSystemList = gson.fromJson(gson.toJson(result.getData()), educationSystemType);
        map.addAttribute("educationSystemList", educationSystemList);
        return "study/addCampus";
    }

    @PostMapping("/addCampus")
    public String addCampus(Campus campus, ModelMap map) {
        if (campus.getCampusName() != null) {
            Gson gson = new Gson();
            String requestBody = gson.toJson(campus);
            System.out.println(requestBody);
            HttpEntity requestEntity = null;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf("application/json"));
            requestEntity = new HttpEntity(requestBody, headers);
            String url = "http://smart-campus-userservice/campus";
            Result result = restTemplate.postForObject(url, requestEntity, Result.class);
            map.addAttribute("msg", result.getMsg());
        } else {
            map.addAttribute("msg", "添加失败！学校名不能为空！");
        }
        return "system/result";
    }

    @PostMapping("/editCampus")
    public String editCampus(Campus campus, ModelMap map) {
        if (campus.getCampusName() != null) {
            Gson gson = new Gson();
            String requestBody = gson.toJson(campus);
            System.out.println(requestBody);
            HttpEntity requestEntity = null;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf("application/json"));
            requestEntity = new HttpEntity(requestBody, headers);
            String url = "http://smart-campus-userservice/editCampus";
            Result result = restTemplate.postForObject(url, requestEntity, Result.class);
            map.addAttribute("msg", result.getMsg());
        } else {
            map.addAttribute("msg", "修改失败！学校名不能为空！");
        }
        return "system/result";
    }

    @DeleteMapping("/campus/{id}")
    @ResponseBody
    public String delCampus(@PathVariable("id") String id) {
        String baseUrl = "http://smart-campus-userservice";
        URI url = UriComponentsBuilder.fromUriString(baseUrl)
                .path("/delCampus")
                .queryParam("id", id)
                .build()
                .toUri();
        Result result = restTemplate.getForObject(url, Result.class);
        if (result.isSuccess()) {
            return "true";
        } else {
            return "false";
        }
    }

    @GetMapping("/editCampusPage")
    public String editCampusPage(int id, ModelMap map) {
        String baseUrl = "http://smart-campus-userservice";
        URI url = UriComponentsBuilder.fromUriString(baseUrl)
                .path("/findCampusById")
                .queryParam("id", id)
                .build()
                .toUri();
        Result result = restTemplate.getForObject(url, Result.class);
        Gson gson = new Gson();
        Campus campus = gson.fromJson(gson.toJson(result.getData()), Campus.class);
        String url1 = "http://smart-campus-userservice/educationSystem";
        Result result1 = restTemplate.getForObject(url1, Result.class);
        Type educationSystemType = new TypeToken<List<EducationSystem>>() {
        }.getType();
        List<EducationSystem> educationSystemList = new ArrayList<>();
        educationSystemList = gson.fromJson(gson.toJson(result1.getData()), educationSystemType);
        map.addAttribute("campus", campus);
        map.addAttribute("educationSystemList", educationSystemList);
        return "study/editCampus";
    }

    @GetMapping("/educationSystem")
    public String listEducationSystem(ModelMap map) {
        String url = "http://smart-campus-userservice/educationSystem";
        Result result = restTemplate.getForObject(url, Result.class);
        Gson gson = new Gson();
        Type educationSystemListType = new TypeToken<List<EducationSystem>>() {
        }.getType();
        List<EducationSystem> educationSystemList = new ArrayList<>();
        educationSystemList = gson.fromJson(gson.toJson(result.getData()), educationSystemListType);
        map.addAttribute("educationSystemList", educationSystemList);
        return "study/educationSystem";
    }

    @GetMapping("/addEducationSystemPage")
    public String addEducationSystemPage() {
        return "study/addEducationSystem";
    }

    @PostMapping("/addEducationSystem")
    public String addEducationSystem(EducationSystem educationSystem, ModelMap map) {
        if (educationSystem.getEducationSystemName() != null) {
            Gson gson = new Gson();
            String requestBody = gson.toJson(educationSystem);
            HttpEntity requestEntity = null;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf("application/json"));
            requestEntity = new HttpEntity(requestBody, headers);
            String url = "http://smart-campus-userservice/addEducationSystem";
            Result result = restTemplate.postForObject(url, requestEntity, Result.class);
            map.addAttribute("msg", result.getMsg());
        } else {
            map.addAttribute("msg", "添加失败！教务系统名不能为空！");
        }
        return "system/result";
    }
    @GetMapping("/editEducationSystemPage")
    public String editEducationSystemPage(int id,ModelMap map) {
        String baseUrl = "http://smart-campus-userservice";
        URI url = UriComponentsBuilder.fromUriString(baseUrl)
                .path("/findEducationSystemById")
                .queryParam("id", id)
                .build()
                .toUri();
        Result result = restTemplate.getForObject(url, Result.class);
        Gson gson = new Gson();
        EducationSystem educationSystem = gson.fromJson(gson.toJson(result.getData()), EducationSystem.class);
        map.addAttribute("educationSystem", educationSystem);
        return "study/editEducationSystem";
    }
    @PostMapping("/editEducationSystem")
    public String editEducationSystem(EducationSystem educationSystem, ModelMap map) {
        if (educationSystem.getEducationSystemName() != null) {
            Gson gson = new Gson();
            String requestBody = gson.toJson(educationSystem);
            HttpEntity requestEntity = null;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf("application/json"));
            requestEntity = new HttpEntity(requestBody, headers);
            String url = "http://smart-campus-userservice/editEducationSystem";
            Result result = restTemplate.postForObject(url, requestEntity, Result.class);
            map.addAttribute("msg", result.getMsg());
        } else {
            map.addAttribute("msg", "修改失败！教务系统名不能为空！");
        }
        return "system/result";
    }
    @DeleteMapping("/educationSystem/{id}")
    @ResponseBody
    public String delEducationSystem(@PathVariable("id") String id) {
        String baseUrl = "http://smart-campus-userservice";
        URI url = UriComponentsBuilder.fromUriString(baseUrl)
                .path("/delEducationSystem")
                .queryParam("id", id)
                .build()
                .toUri();
        Result result = restTemplate.getForObject(url, Result.class);
        if (result.isSuccess()) {
            return "true";
        } else {
            return "false";
        }
    }
}
