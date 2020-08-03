package cn.ypjalt.springboot.mapper;

import cn.ypjalt.springboot.bean.Employee;
import org.apache.ibatis.annotations.*;


public interface EmployeeMapper {

    @Select("select * from employee where id = #{id}")
    public Employee getEmpById(Integer id);

    @Update("update employee set lastName=#{lastName},email=#{email}," +
            "gender=#{gender},d_id=#{dId} where id = #{id}")
    public void updateEmp(Employee employee);

    @Delete("delete from employee where id = #{id}")
    public void deleteEmpById(Integer id);

    @Insert("insert into employee(lastName,gender,email,d_id) values" +
            "(#{lastName},#{gender},#{email},#{dId} )")
    public void insertEmp(Employee employee);

    @Select("select * from employee where lastName = #{lastName}")
    public Employee getEmpByLastName(String lastName);
}
