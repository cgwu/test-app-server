package com.wyjf.app.domain;

import javax.persistence.*;

/**
 * Created by dannis on 2017/8/22.
 * For test:
 *
     CREATE TABLE `t_user` (
         `id` bigint(20) NOT NULL AUTO_INCREMENT,
         `name` varchar(60) DEFAULT NULL,
         `pwd` varchar(60) DEFAULT NULL,
     PRIMARY KEY (`id`)
     ) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

     insert into t_user (name,pwd) values ('dannis','123');
     insert into t_user (name,pwd) values ('张三','123');
 */
@Entity
@Table(name = "t_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String pwd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
