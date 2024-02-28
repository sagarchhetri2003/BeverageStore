package com.system.beverage_store.service;

import com.system.beverage_store.entity.ProductCart;
import com.system.beverage_store.pojo.ProductCartPojo;

import java.security.Principal;
import java.util.List;

public interface ProductCartService {
    List<ProductCart> fetchAll();

    ProductCartPojo save(ProductCartPojo productCartPojo);

//    String saveToCart(Integer id, Principal principal);
    ProductCart fetchOne(Integer id);

    void deleteFromCart(Integer id);

    String updateQuantity(ProductCart productCart);

    List<ProductCart> fetchAll(Integer id);
}
