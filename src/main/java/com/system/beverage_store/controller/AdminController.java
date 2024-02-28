package com.system.beverage_store.controller;

import com.system.beverage_store.entity.*;
import com.system.beverage_store.pojo.NotificationsPojo;
import com.system.beverage_store.pojo.ProductPojo;
import com.system.beverage_store.service.*;
import com.system.beverage_store.service.impl.ProductCartServices;
import com.system.beverage_store.service.impl.ProductServiceImpl;
import com.system.beverage_store.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.util.Base64;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final ProductService productService;
    private final NotificationsService notificationsService;
    private final QueryService queryService;
    private final UserService userService;
    private final ProductCartService productCartService;
    private final ProductServiceImpl productServiceImpl;
    private final ProductCartServices productCartServices;
    private final UserServiceImpl userServiceImpl;
    @GetMapping("/dashboard")
    public String getAdminPage(Model model, Principal principal) {
        if (principal!=null) {
            model.addAttribute("info", userService.findByEmail(principal.getName()));
        }
        Long products=productServiceImpl.countRows();
        model.addAttribute("pro",products);
        Long productCarts=productCartServices.countRows();
        model.addAttribute("proCart",productCarts);
        Long totalUsers=userServiceImpl.countRows();
        model.addAttribute("totalUser",totalUsers);
        return "admin_dashboard";
    }

    @GetMapping("/add-product")
    public String getAddProductPage(Model model, Principal principal) {
        if (principal!=null) {
            model.addAttribute("info", userService.findByEmail(principal.getName()));
        }
        model.addAttribute("product", new ProductPojo());
        return "add_products";
    }
    @PostMapping("/save/product")
    public String saveProduct(@Valid ProductPojo productPojo, Model model, Principal principal) throws IOException {
        if (principal!=null) {
            model.addAttribute("info", userService.findByEmail(principal.getName()));
        }
        productService.save(productPojo);
        return "redirect:/landing";
    }
    @GetMapping("/user-list")
    public String getUserListPage(Model model, Principal principal) {
        if (principal!=null) {
            model.addAttribute("info", userService.findByEmail(principal.getName()));
        }
        List<User> users = userService.fetchAll();
        model.addAttribute("userlist", users.stream().map(user ->
                User.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .address(user.getAddress())
                        .number(user.getNumber())
                        .build()

        ));
        return "userlist";
    }
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model, Principal principal) {
        if (principal!=null) {
            model.addAttribute("info", userService.findByEmail(principal.getName()));
        }
        userService.deleteById(id);
        return "redirect:/admin/user-list";
    }
    @GetMapping("/add-notices")
    public String addNotices(Model model, Principal principal) {
        if (principal!=null) {
            model.addAttribute("info", userService.findByEmail(principal.getName()));
        }
        model.addAttribute("notice", new NotificationsPojo());
        return "notify_customers";
    }
    @PostMapping("/save/notices")
    public String saveNotices(@Valid NotificationsPojo notificationsPojo, Model model, Principal principal){
        if (principal!=null) {
            model.addAttribute("info", userService.findByEmail(principal.getName()));
        }
        notificationsService.save(notificationsPojo);
        return "redirect:/user/notifications";
    }
    @GetMapping("/order-list")
    public String getOrderListPage(Model model, Principal principal) {
        if (principal!=null) {
            model.addAttribute("info", userService.findByEmail(principal.getName()));
        }
        assert principal != null;
        Integer id = userService.findByEmail(principal.getName()).getId();
        List<ProductCart> list = productCartService.fetchAll(id);
        model.addAttribute("cartItems", list);
        return "order_list";
    }
    @GetMapping("/removeProduct/{id}")
    public String deleteOrderList(@PathVariable("id") Integer id){
        productCartService.deleteFromCart(id);
        return "redirect:/admin/order-list";
    }
    @GetMapping("/product-list")
    public String getProductList(Model model, Principal principal) {
        if (principal!=null) {
            model.addAttribute("info", userService.findByEmail(principal.getName()));
        }
        List<Product> products = productService.fetchAll();
//        model.addAttribute("product", products.stream().map(product ->
//                Product.builder()
//                        .id(product.getId())
//                        .imageBase64(getImageBase64(product.getPhoto()))
//                        .name(product.getName())
//                        .quantity(product.getQuantity())
//                        .price(product.getPrice())
//                        .build()
//                ));
        model.addAttribute("product",products);
        return "productlist";
    }
    @GetMapping("/editProduct/{id}")
    public String editProducts(@PathVariable("id") Integer id, Model model, Principal principal) {
        if (principal!=null) {
            model.addAttribute("info", userService.findByEmail(principal.getName()));
        }
        Product products = productService.fetchById(id);
        model.addAttribute("product", new ProductPojo(products));
        return "add_products";
    }
    @GetMapping("/deleteProduct/{id}")
    public String deleteProducts(@PathVariable("id") Integer id, Model model, Principal principal) {
        if (principal!=null) {
            model.addAttribute("info", userService.findByEmail(principal.getName()));
        }
        productService.deleteById(id);
        return "redirect:/admin/product-list";
    }
    @GetMapping("/notice-list")
    public String getNoticeList(Model model, Principal principal) {
        if (principal!=null) {
            model.addAttribute("info", userService.findByEmail(principal.getName()));
        }
        List<Notifications> notifications = notificationsService.fetchAll();
        model.addAttribute("notice", notifications);
        return "notice-list";
    }
    @GetMapping("/edit/{id}")
    public String editNotices(@PathVariable("id") Integer id, Model model, Principal principal) {
        if (principal!=null) {
            model.addAttribute("info", userService.findByEmail(principal.getName()));
        }
        Notifications notifications = notificationsService.fetchById(id);
        model.addAttribute("notice", new NotificationsPojo(notifications));
        return "notify_customers";
    }
    @GetMapping("/delete/{id}")
    public String deleteNotices(@PathVariable("id") Integer id, Model model, Principal principal) {
        if (principal!=null) {
            model.addAttribute("info", userService.findByEmail(principal.getName()));
        }
        notificationsService.deleteById(id);
        return "redirect:/admin/notice-list";
    }
    @GetMapping("/queries")
    public String getQueryPage(Model model, Principal principal) {
        if (principal!=null) {
            model.addAttribute("info", userService.findByEmail(principal.getName()));
        }
        List<Queries> queries = queryService.fetchAll();
        model.addAttribute("queries", queries);
        return "query_section";
    }

    @GetMapping("/settings")
    public String getAdminSettingsPage(Model model, Principal principal) {
        if (principal!=null) {
            model.addAttribute("info", userService.findByEmail(principal.getName()));
        }
        return "admin_settings";
    }
    public String getImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "/kanpai_store/";
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
