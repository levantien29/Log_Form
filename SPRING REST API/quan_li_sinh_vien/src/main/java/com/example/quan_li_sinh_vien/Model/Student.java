
//Model là lớp đại diện cho bảng dữ liệu trong cơ sở dữ liệu (CSDL).
//Mỗi đối tượng Model là 1 dòng (record) trong bảng.

package com.example.quan_li_sinh_vien.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

//chỉ định đây là lớp ánh xạ với bảng trong cơ sở dữ liệu
@Entity
public class Student {
    // id la khoa chinh
    @Id
    // tu dong tang id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
