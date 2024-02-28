package com.system.beverage_store.service;

import com.system.beverage_store.entity.Product;
import com.system.beverage_store.pojo.ProductPojo;
import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Product> fetchAll();

    String save(ProductPojo productPojo) throws IOException;

    Product fetchById(Integer id);

    void deleteById(Integer id);
}
