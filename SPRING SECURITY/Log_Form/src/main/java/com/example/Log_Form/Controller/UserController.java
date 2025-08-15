package com.example.Log_Form.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @GetMapping("/user/home")
    @ResponseBody
    public String userhome(){
        return "Trang người dung của User và Admin đều truy cập được";
    }
}
