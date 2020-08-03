package cn.ypjalt.springboot.entity;

import javax.persistence.*;

// 使用 jpa 注解配置映射关系
@Entity // 告诉 jpa 这是一个实体类(和数据表映射的类)
@Table(name = "tbl_user") // @Table 来指定和哪个数据表对应,如果省略默认表名就是 user;
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增主键
    private Integer id;
    @Column(name = "last_name", length = 50)
    private String lastName;
    @Column // 省略默认列名就是属性名
    private String email;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
