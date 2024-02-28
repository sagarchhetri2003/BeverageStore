package com.system.beverage_store.pojo;

import com.system.beverage_store.entity.Product;
import com.system.beverage_store.entity.ProductCart;
import com.system.beverage_store.service.ProductService;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCartPojo {
    private Integer id;

    private Integer user_id;
    private Integer product_id;

    public ProductCartPojo(ProductCart productCart) {
        this.id = productCart.getId();
        this.user_id = productCart.getUser().getId();
        this.product_id = productCart.getProduct().getId();
    }
}
