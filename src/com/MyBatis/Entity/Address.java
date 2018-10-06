package com.MyBatis.Entity;

import java.util.Set;

/**
 * @author 贺威
 * @create 2018-09-28 8:36
 */
public class Address {

    private Integer id;
    private String Name;
    private Set<User> users;

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Address() {
        super();
    }

    public Address(Integer id, String name) {
        this.id = id;
        Name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                '}';
    }
}
