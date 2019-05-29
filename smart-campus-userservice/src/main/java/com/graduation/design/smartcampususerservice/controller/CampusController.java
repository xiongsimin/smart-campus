package com.graduation.design.smartcampususerservice.controller;

import com.graduation.design.smartcampususerservice.entity.Campus;
import com.graduation.design.smartcampususerservice.service.CampusService;
import com.graduation.design.smartcampususerservice.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CampusController {
    private final CampusService campusService;

    @Autowired
    public CampusController(CampusService campusService) {
        this.campusService = campusService;
    }

    @PostMapping("/campus")
    public Result addCampus(Result rs, @RequestBody Campus campus) {
        System.out.println(campus);
        if (this.campusService.addCampus(campus) == 1) {
            rs.setSuccess(true);
            rs.setMsg("新增成功！");
        } else {
            rs.setSuccess(false);
            rs.setMsg("新增失败！");
        }
        return rs;
    }

    @PostMapping("/editCampus")
    public Result editCampus(Result rs, @RequestBody Campus campus) {
        if(this.campusService.findCampusById(campus.getId())!=null){
            if(this.campusService.editCampusDetail(campus)==1){
                rs.setSuccess(true);
                rs.setMsg("修改成功！");
            }else {
                rs.setSuccess(false);
                rs.setMsg("修改失败！");
            }
        }else {
            rs.setSuccess(false);
            rs.setMsg("修改失败！学校信息不能为空！");
        }
        return rs;
    }

    /**
     * 删除大学（慎用，删除大学后，属于该大学的学生依旧存在，此时查询可能会出现异常，后续再考虑这种情况）
     *
     * @param id
     * @param rs
     * @return
     */
    @GetMapping("/delCampus")
    public Result delCampusById(@RequestParam("id") String id, Result rs) {
        if (this.campusService.findCampusById(Integer.parseInt(id)) != null) {
            if (this.campusService.delCampusById(Integer.parseInt(id)) == 1) {
                rs.setSuccess(true);
                rs.setMsg("删除成功！");
            } else {
                rs.setSuccess(false);
                rs.setMsg("删除失败！请重试！");
            }
        } else {
            rs.setSuccess(false);
            rs.setMsg("删除失败！不存在该大学！");
        }
        return rs;
    }

    @GetMapping("/campus")
    public Result getAllCampus(Result rs) {
        List<Campus> campusList = campusService.getAllCampus();
        if (campusList != null) {
            rs.setSuccess(true);
            rs.setData(campusList);
        } else {
            rs.setSuccess(false);
            rs.setMsg("获取所有大学信息失败！");
        }
        return rs;
    }

    @PutMapping("/editCampusEducationSystemAddress")
    public Result editCampusEducationSystemAddress(int id, String educationSystemAddress, Result rs) {
        if (this.campusService.editCampusEducationSystemAddress(id, educationSystemAddress) == 1) {
            rs.setSuccess(true);
            rs.setMsg("修改成功！");
        } else {
            rs.setSuccess(false);
            rs.setMsg("修改失败！");
        }
        return rs;
    }

    @PutMapping("/editCampusEducationSystemId")
    public Result editCampusEducationSystemId(int id, int educationSystemId, Result rs) {
        if (this.campusService.editCampusEducationSystemId(id, educationSystemId) == 1) {
            rs.setSuccess(true);
            rs.setMsg("修改成功！");
        } else {
            rs.setSuccess(false);
            rs.setMsg("修改失败！");
        }
        return rs;
    }

    @GetMapping("/findCampusById")
    public Result findCampusById(@RequestParam("id") int id, Result rs) {
        Campus campus = this.campusService.findCampusById(id);
        if (campus != null) {
            rs.setSuccess(true);
            rs.setMsg("查找成功！");
            rs.setData(campus);
        } else {
            rs.setSuccess(false);
            rs.setMsg("查找失败！");
        }
        return rs;
    }
}
