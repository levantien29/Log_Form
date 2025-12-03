package com.example.quan_li_nhan_vien.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quan_li_nhan_vien.Model.NhanVien;
import com.example.quan_li_nhan_vien.Repository.NhanVienRepository;

@RestController
// @RequestMapping là một annotation trong Spring Framework được sử dụng để ánh
// xạ các yêu cầu HTTP đến các phương thức trong controller.
@RequestMapping("/nhanvien")
public class NhanVienController {
    private final NhanVienRepository nhanVienRepository;

    public NhanVienController(NhanVienRepository nhanvienRepository){
        this.nhanVienRepository = nhanvienRepository;
    }

    @GetMapping 
    public List<NhanVien>getAllNhanVien(){
        return nhanVienRepository.findAll();
    }

    //them nhan vien
    @PostMapping
    public NhanVien addNhanien(@RequestBody NhanVien nhanVien){
        return nhanVienRepository.save(nhanVien);
    }
    
    //ham xoaa nhan vien
    @DeleteMapping("/{id}")
    public void deleteNhanVien(@PathVariable Long id){
        nhanVienRepository.deleteById(id);
    }
}
