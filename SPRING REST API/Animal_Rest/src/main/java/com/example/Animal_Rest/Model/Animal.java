package com.example.Animal_Rest.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // ánh xạ với bảng csdl
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "amimal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String species; //loài
    private String color;
    private int age;
    private String gender;
    private String password;
}
