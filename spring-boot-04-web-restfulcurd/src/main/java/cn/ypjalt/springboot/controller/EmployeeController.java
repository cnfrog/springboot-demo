package cn.ypjalt.springboot.controller;

import cn.ypjalt.springboot.dao.DepartmentDao;
import cn.ypjalt.springboot.dao.EmployeeDao;
import cn.ypjalt.springboot.entities.Department;
import cn.ypjalt.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    // 查询所有员工返回列表页面
    @GetMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();
        // 放在请求域中
        model.addAttribute("emps", employees);
        // thymeleaf默认会拼串
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model) {
        // 来到添加页面,查出所有的部门,在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        // 来到添加页面
        return "emp/add";
    }

    // 员工添加
    // springMvc 自动将请求参数和入参对象的属性进行一一绑定,要求了请求参数的
    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        // 来到员工类别页面

        System.out.println("保存的员工信息" + employee);
        employeeDao.save(employee);
        // redirect : 表示重定向都一个地址
        // forward:表示转发到一个地址
        return "redirect:/emps";
    }

    // 来到修改页面,查出当前员工,在页面回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);
        // 回到修改界面
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    // 员工修改
    @PutMapping("/emp")
    public String updateEmp(Employee employee) {
        System.out.println("修改的员工数据" + employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    // 员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";

    }

}
