package com.graduation.design.smartcmapusadminservice.controller;

import com.graduation.design.smartcmapusadminservice.entity.Column;
import com.graduation.design.smartcmapusadminservice.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class InterestExchangePlatformController {
    private final ColumnService columnService;

    @Autowired
    public InterestExchangePlatformController(ColumnService columnService) {
        this.columnService = columnService;
    }

    @GetMapping("/columnPage")
    public String columnPage(ModelMap map) {
        List<Column> columnList = this.columnService.getAllColumn();
        map.addAttribute("columnList", columnList);
        return "dailyLife/column";
    }

    @GetMapping("/addColumnPage")
    public String addColumnPage() {
        return "dailyLife/addColumn";
    }

    @PostMapping("/addColumn")
    public String addColumn(Column column, ModelMap map) {
        if (this.columnService.addColumn(column) == 1) {
            map.addAttribute("msg", "添加成功！");
        } else {
            map.addAttribute("msg", "添加失败！");
        }
        return "system/result";
    }

    @GetMapping("/editColumnPage")
    public String editColumnPage(int id, ModelMap map) {
        Column column;
        if ((column = this.columnService.findColumnById(id)) != null) {
            map.addAttribute("column", column);
            return "dailyLife/editColumn";
        } else {
            map.addAttribute("msg", "该板块不存在！");
            return "system/result";
        }
    }

    @PostMapping("/editColumn")
    public String editColumn(Column column, ModelMap map) {
        if (this.columnService.findColumnById(column.getId()) != null) {
            if (this.columnService.editColumn(column) == 1) {
                map.addAttribute("msg", "修改成功！");
            } else {
                map.addAttribute("msg", "修改失败！");
            }
        } else {
            map.addAttribute("msg", "修改失败！版块不存在！");
        }
        return "system/result";
    }

    @DeleteMapping("/column/{id}")
    @ResponseBody
    public String delColumn(@PathVariable("id") int id) {
        if (this.columnService.delColumn(id) == 1) {
            return "true";
        } else {
            return "false";
        }
    }
}
