package com.atguigu.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class SignupController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping(value = "/user/signup")
    //@RequestMapping(value="/user/login",method= RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session) {
        System.out.println(username);
        System.out.println(password);
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            String sql = "INSERT INTO user (username,password) VALUES ('" + username + "','" + password + "')";
            System.out.println(sql);
            jdbcTemplate.update(sql);
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
        }
        map.put("msg", "注册失败");
        return "signup";
    }
}
