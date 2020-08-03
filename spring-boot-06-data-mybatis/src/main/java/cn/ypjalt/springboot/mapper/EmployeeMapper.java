package cn.ypjalt.springboot.mapper;

import cn.ypjalt.springboot.bean.Employee;
import org.apache.ibatis.annotations.Mapper;

// @Mapper 或者 @MapperScan 将接口扫描装配到容器中
//@Mapper
public interface EmployeeMapper {
    public Employee getEmpById(Integer id);

    public void insertEmp(Employee employee);
}
