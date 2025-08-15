package com.example.Log_Api.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/logout")
    public String logout() {
        return "login";
    }
    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "Trang chu - da dang nhap";
    }

    @GetMapping("/user/info")
    @ResponseBody
    public String user() {
        return "Trang nguoi dung - user, admin deu dang nhap duoc";
    }

    @GetMapping("/admin/manager")
    @ResponseBody
    public String admin() {
        return "Trang quan tri - chi ADMIN moi vao duoc";
    }
}
