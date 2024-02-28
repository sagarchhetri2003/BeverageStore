package com.system.beverage_store.controller;

import com.system.beverage_store.entity.Product;
import com.system.beverage_store.pojo.ProductCartPojo;
import com.system.beverage_store.service.ProductCartService;
import com.system.beverage_store.service.ProductService;
import com.system.beverage_store.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/landing")

public class LandingController {
    private final ProductService productService;
    private final UserService userService;
    private final ProductCartService productCartService;
    private final JavaMailSender getJavaMailSender;
    @GetMapping()
    public String getProductsPage(Model model, Principal principal, Authentication authentication) {
        if (authentication!=null){
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities) {
                if (grantedAuthority.getAuthority().equals("Admin")) {
                    return "redirect:/admin/dashboard";
                }
            }
        }
        List<Product> products = productService.fetchAll();
        if (principal!=null) {
            model.addAttribute("info", userService.findByEmail(principal.getName()));
        }
//        model.addAttribute("product", products.stream().map(product ->
//                        Product.builder()
//                                .id(product.getId())
//                                .imageBase64(getImageBase64(product.getPhoto()))
//                                .name(product.getName())
//                                .quantity(product.getQuantity())
//                                .price(product.getPrice())
//                                .build()
//                )
//        );
        model.addAttribute("product", products);
        model.addAttribute("savecarts", new ProductCartPojo());
        return ("landing");
    }
    @PostMapping("/save")
    public String savecart(@Valid ProductCartPojo productCartPojo, Model model, Principal principal) {
        productCartService.save(productCartPojo);
        return "redirect:/cart";
    }

    public String getImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "/beverage_store/";
        File file = new File(filePath + fileName);
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        String base64 = Base64.getEncoder().encodeToString(bytes);
        return base64;
    }
}
