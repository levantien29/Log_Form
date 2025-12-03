package com.example.quan_li_nhan_vien.Repository;

//repository là lớp dùng để giao tiếp vói cơ sở dữ liệu
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quan_li_nhan_vien.Model.NhanVien;

//Jparepository cung cap cac phuongg thuc save(), fianAll()....
//Long : kieu du lieu cua khoa  chinh
public interface NhanVienRepository extends JpaRepository<NhanVien, Long>{

}
