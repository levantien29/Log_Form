package com.mycompany.myapp.web.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import com.mycompany.myapp.service.ClassRoomService;
import com.mycompany.myapp.service.dto.ClassRoomDTO;
import com.mycompany.myapp.service.dto.ClassRoomRequest;
import com.mycompany.myapp.service.dto.ClassRoomResponseId;
import com.mycompany.myapp.service.dto.ClassRoomResponseTc;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
@RequestMapping("/api/class-rooms")
public class ClassRoomResource {
    private final ClassRoomService service;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Page<ClassRoomDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<ClassRoomResponseId> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ClassRoomResponseTc> create(@Valid @RequestBody ClassRoomRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ClassRoomResponseTc> partialUpdate(
            @PathVariable Long id,
            @RequestBody ClassRoomRequest request) {
        return ResponseEntity.ok(service.partialUpdate(request, id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}