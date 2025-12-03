package com.example.quan_li_nhan_vien.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity // đánh dấu lớp nhân viên là 1 bảng trong Database
public class NhanVien {
    @Id//id la khoa chinh
    //tu dong tang id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String addrest;
    
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
    public String getAddrest() {
        return addrest;
    }
    public void setAddrest(String addrest) {
        this.addrest = addrest;
    }
}
