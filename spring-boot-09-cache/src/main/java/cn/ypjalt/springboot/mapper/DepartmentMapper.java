package cn.ypjalt.springboot.mapper;

import cn.ypjalt.springboot.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DepartmentMapper {


    @Select("select * from department where id=#{id}")
    public Department getDepById(Integer id);

}
