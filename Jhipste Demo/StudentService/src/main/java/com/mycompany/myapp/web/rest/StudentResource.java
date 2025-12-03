package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Student;
import com.mycompany.myapp.service.StudentService;
import com.mycompany.myapp.service.dto.StudentRequest;
import com.mycompany.myapp.service.dto.StudentResponse;
import com.mycompany.myapp.service.dto.StudentResponseId;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
@RequestMapping("/api/students")
public class StudentResource {

    private final StudentService service;

    // Chỉ USER và ADMIN được xem danh sách
    @GetMapping("/all")
@PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
public ResponseEntity<Page<StudentResponse>> getAll(Pageable pageable){
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    System.out.println("Authorities: " + auth.getAuthorities());
    return ResponseEntity.ok(service.getAll(pageable));
}

    // USER hoặc ADMIN xem chi tiết
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<StudentResponseId> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }

    // Chỉ ADMIN được tạo mới
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<StudentResponseId> createStudent(@Valid @RequestBody StudentRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    // Chỉ ADMIN được update
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<StudentResponseId> updateStudent(@Valid @RequestBody StudentRequest request, @PathVariable Long id){
        return ResponseEntity.ok(service.update(request, id));
    }

    // Chỉ ADMIN được xóa
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/classroom/{classroomId}")
    public ResponseEntity<List<StudentResponse>> getStudentsByClassroom(
            @PathVariable Long classroomId) {
        List<StudentResponse> students = service.getStudentsByClassroom(classroomId);
        return ResponseEntity.ok(students);
    }
}
