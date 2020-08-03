package cn.ypjalt.springboot.service;

import cn.ypjalt.springboot.bean.Department;
import cn.ypjalt.springboot.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "dept"/*,cacheManager = "cacheManager"*/)
@Service
public class DeptService {
    @Autowired
    DepartmentMapper departmentMapper;

    /**
     * 缓存的数据存入 redis
     * 第二次查询就不能反序列化回来
     * 存的是 dept 的 json 数据
     *
     * @param id
     * @return
     */

    @Cacheable
    public Department getDeptById(Integer id) {
        System.out.println("查询部门" + id);
        return departmentMapper.getDepById(id);
    }

}
