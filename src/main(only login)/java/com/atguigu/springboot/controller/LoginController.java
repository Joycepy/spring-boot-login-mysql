package com.atguigu.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping(value = "/user/login")
    //@RequestMapping(value="/user/login",method= RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session) {
        if (!StringUtils.isEmpty(username)) {
            String sql = "select password from user where username = '"+username+"'";
            System.out.println(sql);
            String pwd=jdbcTemplate.queryForObject(sql,String.class);
            System.out.println(pwd);
            if (!pwd.isEmpty()&&pwd.equals(password)) {
                session.setAttribute("loginUser", username);
                return "redirect:/main.html";
            }
        }
        map.put("msg", "用户名密码错误");
        return "login";
    }
}
