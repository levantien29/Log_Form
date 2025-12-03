package com.example.Product_Rest.Exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class ErrorResponse {
    @Builder.Default // lấy giờ hiện tại
    //Nếu bạn truyền thì builder dùng giá trị bạn truyền. 
    //Nếu không truyền, thì @Builder.Default sẽ giúp giữ lại giá trị mặc định bạn đã gán.
    private LocalDateTime localDateTime = LocalDateTime.now();
    private int status;
    private String message;
    private List<String> messages;
    private String path;

}
