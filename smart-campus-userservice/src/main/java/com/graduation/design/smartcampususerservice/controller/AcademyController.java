package com.graduation.design.smartcampususerservice.controller;

import com.graduation.design.smartcampususerservice.entity.Academy;
import com.graduation.design.smartcampususerservice.service.AcademyService;
import com.graduation.design.smartcampususerservice.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AcademyController {
    private final AcademyService academyService;

    @Autowired
    public AcademyController(AcademyService academyService) {
        this.academyService = academyService;
    }

    @PostMapping("/academy")
    public Result addAcademy(Result rs, @RequestBody Academy academy) {
        if (this.academyService.addAcademy(academy) == 1) {
            rs.setSuccess(true);
            rs.setMsg("新增成功！");
        } else {
            rs.setSuccess(false);
            rs.setMsg("新增失败！");
        }
        return rs;
    }

    @DeleteMapping("/academy")
    public Result delAcademyById(Result rs, int id) {
        if (this.academyService.delAcademyById(id) == 1) {
            rs.setSuccess(true);
            rs.setMsg("删除成功！");
        } else {
            rs.setSuccess(false);
            rs.setMsg("删除失败！");
        }
        return rs;
    }
    @PutMapping("/academy")
    public Result editAcademy(Result rs,Academy academy){
        if(this.academyService.editAcademy(academy)==1){
            rs.setSuccess(true);
            rs.setMsg("修改成功！");
        }else{
            rs.setSuccess(false);
            rs.setMsg("修改失败！");
        }
        return rs;
    }
    @GetMapping("/academy")
    public Result findAcademyById(Result rs,int id){
        Academy academy=this.academyService.findAcademyById(id);
        if(academy!=null){
            rs.setSuccess(true);
            rs.setMsg("查找成功（存在符合条件数据）！");
            rs.setData(academy);
        }else {
            rs.setSuccess(false);
            rs.setMsg("查找失败（不存在符合条件的数据）！");
        }
        return rs;
    }
    @GetMapping("/academies")
    public Result findAcademyByCampusId(Result rs,int campusId){
        List<Academy> academyList=this.academyService.findAcademyByCampusId(campusId);
        if(academyList!=null){
            rs.setSuccess(true);
            rs.setMsg("查找成功（存在符合条件数据）！");
            rs.setData(academyList);
        }else{
            rs.setSuccess(false);
            rs.setMsg("查找失败（不存在符合条件数据）！");
        }
        return rs;
    }
}
