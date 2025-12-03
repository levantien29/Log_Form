package com.mycompany.myapp.web.rest;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mycompany.myapp.service.SubjectService;
import com.mycompany.myapp.service.dto.SubjectDTO;
import com.mycompany.myapp.service.dto.SubjectRequest;
import com.mycompany.myapp.service.dto.SubjectResponse;
import com.mycompany.myapp.service.dto.SubjectResponseId;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectResource {
    private final SubjectService service;

    @GetMapping("/all")
    public ResponseEntity<Page<SubjectDTO>> getAll(Pageable pageable){
        return ResponseEntity.ok(service.getAll(pageable));
    }
   
    @GetMapping("/{id}")
    public ResponseEntity<SubjectResponseId> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<SubjectResponseId> create(@Valid @RequestBody SubjectRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectResponseId> update(@Valid @RequestBody SubjectRequest request, @PathVariable Long id){
        return ResponseEntity.ok(service.update(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SubjectDTO> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<SubjectResponse>> getTeacherById(@PathVariable Long teacherId){
        return ResponseEntity.ok(service.getSubjectByTeacher(teacherId));
    }
}
