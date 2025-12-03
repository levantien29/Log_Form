package com.example.Security_Login.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
    @GetMapping("/")
    public String welcome(){
        return "Trang chính - ai cũng truy cập được";
    }

    @GetMapping("/home")
    public String home(){
        return "Home page - public ";
    }

    @GetMapping("/user/profile")
    public String userProfile(){
        return "Trang người dùng user với admin đều truy cập được";
    }

    @GetMapping("/admin/infor")
    public String adminInfor(){
        return "Trang quản trị chỉ admin mới truy cập được";
    }
}
