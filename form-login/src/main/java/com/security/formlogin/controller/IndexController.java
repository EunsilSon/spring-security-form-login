package com.security.formlogin.controller;

import com.security.formlogin.model.User;
import com.security.formlogin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public IndexController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * 로그인 성공 후 페이지 이동
     * @return index.html
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 로그인 페이지 이동
     * @return loginForm.html
     */
    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    /**
     * 회원가입 페이지 이동
     * @return joinForm.html
     */
    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    /**
     * 회원가입 진행
     * @param user
     * @return 로그인 페이지 리다이렉트
     */
    @PostMapping("/join")
    public String join(User user) {
        user.setRole("ROLE_USER");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/loginForm";
    }
}

