package com.graduation.design.smartcampususerservice.controller;

import com.graduation.design.smartcampususerservice.entity.EducationSystem;
import com.graduation.design.smartcampususerservice.service.EducationSystemService;
import com.graduation.design.smartcampususerservice.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class EducationSystemController {
    private final EducationSystemService educationSystemService;

    @Autowired
    public EducationSystemController(EducationSystemService educationSystemService) {
        this.educationSystemService = educationSystemService;
    }

    @GetMapping("/educationSystem")
    @ResponseBody
    public Result getAllEducationSystem(Result rs) {
        rs.setData(this.educationSystemService.getAllEducationSystem());
        return rs;
    }
    @PostMapping("/addEducationSystem")
    @ResponseBody
    public Result addEducationSystem(@RequestBody EducationSystem educationSystem,Result rs){
        if(educationSystem.getEducationSystemName()!=null){
            if(this.educationSystemService.addEducationSystem(educationSystem)==1){
                rs.setSuccess(true);
                rs.setMsg("添加成功！");
            }else {
                rs.setSuccess(false);
                rs.setMsg("新增失败！插入数据库失败！");
            }
        }else {
            rs.setSuccess(false);
            rs.setMsg("新增失败！教务系统名不能为空！");
        }
        return rs;
    }
    @GetMapping("/findEducationSystemById")
    @ResponseBody
    public Result findEducationSystemById(@RequestParam("id") int id,Result rs){
        EducationSystem educationSystem = this.educationSystemService.findEducationSystemById(id);
        if (educationSystem != null) {
            rs.setSuccess(true);
            rs.setMsg("查找成功！");
            rs.setData(educationSystem);
        } else {
            rs.setSuccess(false);
            rs.setMsg("查找失败！");
        }
        return rs;
    }
    @PostMapping("/editEducationSystem")
    @ResponseBody
    public Result editEducationSystem(@RequestBody EducationSystem educationSystem,Result rs){
        if(educationSystem.getEducationSystemName()!=null){
            if(this.educationSystemService.findEducationSystemById(educationSystem.getId())!=null){
                if(this.educationSystemService.editEducationSystem(educationSystem)==1){
                    rs.setSuccess(true);
                    rs.setMsg("修改成功！");
                }else {
                    rs.setSuccess(false);
                    rs.setMsg("修改失败！请重试！");
                }
            }else {
                rs.setSuccess(false);
                rs.setMsg("修改失败！教务系统不存在！");
            }
        }else {
            rs.setSuccess(false);
            rs.setMsg("修改失败！教务系统名不能为空！");
        }
        return rs;
    }
    @GetMapping("/delEducationSystem")
    @ResponseBody
    public Result delEducationSystem(@RequestParam("id") int id,Result rs){
        if(this.educationSystemService.findEducationSystemById(id)!=null){
            if(this.educationSystemService.delEducationSystem(id)==1){
                rs.setSuccess(true);
            }else {
                rs.setSuccess(false);
                rs.setMsg("删除失败！请重试！");
            }
        }else {
            rs.setSuccess(false);
            rs.setMsg("删除失败！不存在该教务系统！");
        }
        return rs;
    }
}
