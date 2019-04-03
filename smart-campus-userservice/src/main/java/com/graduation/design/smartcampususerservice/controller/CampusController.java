package com.graduation.design.smartcampususerservice.controller;

import com.graduation.design.smartcampususerservice.entity.Campus;
import com.graduation.design.smartcampususerservice.service.CampusService;
import com.graduation.design.smartcampususerservice.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CampusController {
    private final CampusService campusService;

    @Autowired
    public CampusController(CampusService campusService) {
        this.campusService = campusService;
    }

    @PostMapping("/campus")
    public Result addCampus(Result rs, @RequestBody Campus campus){
        if(this.campusService.addCampus(campus)==1){
            rs.setSuccess(true);
            rs.setMsg("新增成功！");
        }else{
            rs.setSuccess(false);
            rs.setMsg("新增失败！");
        }
        return rs;
    }
}
