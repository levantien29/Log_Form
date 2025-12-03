package com.example.Quan_Li_Nhan_Su.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getter, setter, toString..
@NoArgsConstructor // ham tao khong doi so
@AllArgsConstructor// ham tao co doi so
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String email;
    private String address;
}
