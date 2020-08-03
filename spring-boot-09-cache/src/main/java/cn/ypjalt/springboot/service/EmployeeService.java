package cn.ypjalt.springboot.service;

import cn.ypjalt.springboot.bean.Employee;
import cn.ypjalt.springboot.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "emp"/*, cacheManager = "cacheManager"*/) // 抽取缓存的公共配置
@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 将方法的结果进行保存,以后再要相同的数据,直接从缓存中获取,不用调用方法
     * CacheManager 管理多个 Cache 组件的,对缓存的真正 CRUD 操作在 cache 组件中,每一个缓存组件有自己唯一的名字
     * 几个属性:
     * cacheNames/value:指定缓存组件的名字,是数组的方式,可以指定多个缓存
     * key:缓存数据使用的 key,默认使用方法参数的值 1-方法的返回值
     * 编写 SpEL #id :参数 id 的值 #a0 #p0
     * <p>
     * keyGenerator:key 的生成器,可以指定 key 的生成器组件 id
     * key/keyGenerator 二选一
     * cacheManager:指定缓存管理器,或者cacheResolver指定获取解析器
     * condition:指定符合条件的情况下才缓存
     * condition="#id>0"
     * condition="#a0>1" 第一个参数的值>1 的时候才进行缓存
     * unless:否定缓存,当 unless 指定条件为 True,方法的返回的值就不会缓存,可以获取到结果进行判断
     * unless = "#result==null"
     * unless="#a0==2" 如果第一个参数是 2,结果不缓存
     * sync:是否使用异步
     * <p>
     * 原理:
     * 1.自动配置类:CacheAutoConfiguration
     * 2.缓存的配置类
     * "org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration"
     * "org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration"
     * "org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration"
     * "org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration"
     * "org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration"
     * "org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration"
     * "org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration"
     * "org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration"
     * "org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration"
     * "org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration"
     * 3.哪个配置类默认生效 : SimpleCacheConfiguration
     * 4.给容器中注册了一个 CacheManager:ConcurrentMapCacheManager
     * 5.可以获取和创建类型 ConcurrentMapCache 的缓存组件,他的作用将数据保存在 ConcurrentMap 中
     * 运行流程:
     * Cacheable
     * 1.方法运行之前, 先去查询 cache(缓存组件),按照 cacheNames 指定的名字获取
     * (cachemanager 先获取相应的缓存ConcurrentMapCacheManager-getCache),第一次获取缓存如果没有
     * cache 组件会自动创建
     * 2.去 cache (ConcurrentMapCache-lookup)中查找缓存的内容,使用一个 key,默认是方法的参数
     * key 是某一种策略生成的,默认是使用keyGenerator生成的,默认使用 SimpleKeyGenerator 生成 key
     * SimpleKeyGenerator生成 key 的默认策略
     * 如果没有参数,key = new SimpleKey()
     * 如果有一个参数 key=参数的值
     * 如果有多个参数,key = new SimpleKey(params)
     * <p>
     * 3.没有查到缓存就调用目标方法
     * 4.将目标方法返回的结果,放入缓存中
     *
     * @Cacheable标注的方法执行之前会检查缓存中有没有这个数据,默认按照参数的值作为 key 去查询缓存如果没有就运行方法,并将结果放入缓存中
     * 以后再来调用直接使用缓存中的数据
     * 核心:
     * 1).使用 CacheManager [ConcurrentMapCacheManager]按照名字得到 Cache[ConcurrentMapCache] 组件
     * 2).key 是使用keyGenerator,默认是 simplekeyGenerator
     */
    @Cacheable(
//            cacheNames = {"emp"},
//            key = "#root.methodName+'['+#id+']'",
//            keyGenerator = "myKeyGenerator",
//            condition =
//                    "#a0>2",
            unless = "#a0==2"
    )
    public Employee getEmp(Integer id) {
        System.out.println("查询" + id + "号员工");
        Employee empById = employeeMapper.getEmpById(id);
        return empById;
    }

    /**
     * @CachePut 既调用方法, 又更新缓存数据,同步更新缓存
     * 修改了数据库某个数据,同时更新缓存
     * 运行时机:
     * 1.先调用目标方法
     * 2.将目标方法的结果缓存起来
     * 测试步骤:
     * 1.查询 1 号员工,查到的结果放入缓存中
     * id = 1, value: lastName:hangshan
     * 2.以后查询还是之前的结果
     * 3.更新 1 号员工
     * 将方法的返回值也放进缓存
     * key:传入的 employee 对象 值:返回的 employee 对象
     * 4.查询 1 号员工
     * 应该是更新后的员工
     * key="#employee.id" 使用传入的参数的员工 id
     * key="#result.id" 使用返回后的 id
     * @Cacheable 的 key 是不能用#result
     * 再次查询没有更新
     */
    @CachePut(value = "emp", key = "#result.id")
    public Employee updateEmp(Employee employee) {
        System.out.println("update emp" + employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     * CacheEvict 缓存清除
     * key:指定要清除的数据
     * allEntries = true 指定清除这个缓存中的所有数据
     * beforeInvocation = false 缓存的清除是否在方法之前执行
     * 默认false是在方法执行之后执行的,如果方法执行出现异常不清除缓存
     * beforeInvocation = true 代表清除缓存在方法执行之前,无论方法是否出现异常,缓存都清除
     */
    @CacheEvict(value = "emp",
//            key = "#id",
            allEntries = true,
            beforeInvocation = false)
    public void deleteEmp(Integer id) {
        System.out.println("deleEmp" + id);
    }

    // Caching 定义复杂的缓存规则
    @Caching(
            cacheable = {
                    @Cacheable(/*value = "emp",*/ key = "#lastName")
            },
            put = {
                    @CachePut(/*value = "emp",*/ key = "#result.id"),
                    @CachePut(/*value = "emp",*/ key = "#result.email")
            }
    )
    public Employee getEmpByLastName(String lastName) {
        Employee empByLastName = employeeMapper.getEmpByLastName(lastName);
        return empByLastName;
    }


}
