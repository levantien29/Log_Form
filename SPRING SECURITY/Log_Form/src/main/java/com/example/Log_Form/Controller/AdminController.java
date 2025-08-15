package com.example.Log_Form.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {
    @GetMapping("/admin/dashboard")
    @ResponseBody
    public String adminDashboard() {
        return "Trang Admin(Quản trị) chỉ Admin mới truy cập được";
    }
}
