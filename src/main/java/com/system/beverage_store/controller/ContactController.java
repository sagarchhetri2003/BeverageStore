package com.system.beverage_store.controller;

import com.system.beverage_store.pojo.QueriesPojo;
import com.system.beverage_store.service.QueryService;
import com.system.beverage_store.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/contact")
public class ContactController {
    private final UserService userService;
    private final JavaMailSender getJavaMailSender;
    private final QueryService queryService;
    @GetMapping("")
    public String getContactUsPage(Model model, Principal principal) {
        if (principal!=null) {
            model.addAttribute("info", userService.findByEmail(principal.getName()));
        }

        model.addAttribute("queries", new QueriesPojo());
        return "contactus";
    }
    @PostMapping("/saveQueries")
    public String saveQuery(@Valid QueriesPojo queriesPojo) {
        queryService.save(queriesPojo);
        return "redirect:/landing";
    }
}
