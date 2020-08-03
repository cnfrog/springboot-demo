package cn.ypjalt.springboot.controller;

import cn.ypjalt.springboot.bean.Department;
import cn.ypjalt.springboot.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {

    @Autowired
    DeptService deptService;

    @GetMapping("/dept/{id}")
    public Department getDeptById(@PathVariable("id") Integer id) {
        Department deptById = deptService.getDeptById(id);
        return deptById;
    }
}
