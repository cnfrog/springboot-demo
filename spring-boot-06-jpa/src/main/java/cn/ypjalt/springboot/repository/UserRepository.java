package cn.ypjalt.springboot.repository;

import cn.ypjalt.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

// 继承JpaRepository来完成数据库的操作
public interface UserRepository extends JpaRepository<User, Integer> {
}
