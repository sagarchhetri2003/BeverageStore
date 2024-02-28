package com.system.beverage_store;

import com.system.beverage_store.entity.Product;
import com.system.beverage_store.repo.ProductRepo;
import jakarta.websocket.OnError;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;

    @Test
    @Order(1)
    @Transactional
    @Rollback(value = false)
    public void saveProduct() {
        Product product = Product.builder()
                .name("Testing Product Name")
                .price("10000")
                .quantity("2")
                .build();

        product = productRepo.save(product);
        Assertions.assertThat(product.getId()).isNotNull();
    }

    @Test
    @Order(2)
    @Transactional
    @Rollback
    public void findById() {
        Product savedProduct = productRepo.save(Product.builder()
                .name("Testing Product Name")
                .price("10000")
                .quantity("2")
                .build());

        Optional<Product> optionalProduct = productRepo.findById(savedProduct.getId());
        Assertions.assertThat(optionalProduct).isPresent();

        Product foundProduct = optionalProduct.get();
        Assertions.assertThat(foundProduct.getId()).isEqualTo(savedProduct.getId());
        Assertions.assertThat(foundProduct.getName()).isEqualTo("Testing Product Name");
        Assertions.assertThat(foundProduct.getPrice()).isEqualTo("10000");
        Assertions.assertThat(foundProduct.getQuantity()).isEqualTo("2");
    }

    @Test
    @Order(3)
    public void findAllData() {
        List<Product> productList = productRepo.findAll();
        Assertions.assertThat(productList.size()).isGreaterThan(0);
    }
    @Test
    @Order(4)
    public  void updateProduct(){
        Product product=productRepo.findById(1).get();
        product.setName("updated product name");
        product=productRepo.save(product);
        Assertions.assertThat(product.getName()).isEqualTo("updated product name");
    }

    @Test
    @Order(5)
    public void deleteById() {
        Product savedProduct = productRepo.save(Product.builder()
                .name("Testing Product Name")
                .price("10000")
                .quantity("2")
                .build());

        productRepo.deleteById(savedProduct.getId());
        Optional<Product> product = productRepo.findById(savedProduct.getId());
        Assertions.assertThat(product).isEmpty();
    }
}
