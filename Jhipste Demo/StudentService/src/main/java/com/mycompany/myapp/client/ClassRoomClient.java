package com.mycompany.myapp.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mycompany.myapp.config.FeignClientConfig;
import com.mycompany.myapp.service.dto.ClassRoomResponse;

@FeignClient(name = "classRoomService", configuration = FeignClientConfig.class)
public interface ClassRoomClient {
    @GetMapping("/api/class-rooms/{id}")
    ClassRoomResponse getClassRoomById(@PathVariable("id") Long id);
}