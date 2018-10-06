package com.MyBatis.Entity;

import org.apache.ibatis.type.Alias;

/**
 * @author 贺威
 * @create 2018-09-23 12:56
 */
@Alias("sd")//别名
public class User {

    private  Integer id;
    private String sname;
    private String  sex;
    private Integer age;
    private Address address;

    public User() {
        System.out.println("无参构造");
    }

    public User(Integer id, String name, String sex, Integer age) {
        this.id = id;
        this.sname = name;
        this.sex = sex;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return sname;
    }

    public void setName(String name) {
        this.sname = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + sname + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}
