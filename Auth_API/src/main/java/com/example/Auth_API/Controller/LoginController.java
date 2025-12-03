package com.example.Auth_API.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;  

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // trả về file login.html trong templates
    }

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "Trang chu - da dang nhap";
    }

    @GetMapping("/user/info")
    @ResponseBody
    public String userInfo() {
        return "Trang nguoi dung ";
    }

    @GetMapping("/admin/dashboard")
    @ResponseBody
    public String admin() {
        return "Trang quan tri";
    }
}
