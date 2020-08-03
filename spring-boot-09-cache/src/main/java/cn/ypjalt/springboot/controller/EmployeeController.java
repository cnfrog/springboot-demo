package cn.ypjalt.springboot.controller;

import cn.ypjalt.springboot.bean.Employee;
import cn.ypjalt.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    public Employee getEmp(@PathVariable("id") Integer id) {
        Employee emp = employeeService.getEmp(id);
        return emp;
    }

    @GetMapping("/emp")
    public Employee update(Employee employee) {
        Employee employee1 = employeeService.updateEmp(employee);
        return employee1;
    }

    @GetMapping("/delemp/{id}")
    public String del(@PathVariable("id") Integer id) {
        employeeService.deleteEmp(id);
        return "success";
    }

    @GetMapping("/emp/lastname/{lastname}")
    public Employee getEmpByLastName(@PathVariable("lastname") String lastName) {
        return employeeService.getEmpByLastName(lastName);

    }


}
