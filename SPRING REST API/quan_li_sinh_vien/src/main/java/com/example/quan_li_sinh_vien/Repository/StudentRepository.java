
//Là lớp dùng để giao tiếp với CSDL.
//Nó giúp ta thêm, sửa, xóa, tìm kiếm dữ liệu mà không cần viết SQL.

package com.example.quan_li_sinh_vien.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.quan_li_sinh_vien.Model.Student;

//// JpaRepository đã cung cấp các phương thức như save(), findAll(), findById(), deleteById()...
/// Long là kiểu dữ liệu trong khoá chính
public interface StudentRepository extends JpaRepository<Student, Long> {
    
}
