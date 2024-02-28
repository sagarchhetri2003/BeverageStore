package com.system.beverage_store.service.impl;

import com.system.beverage_store.entity.Product;
import com.system.beverage_store.pojo.ProductPojo;
import com.system.beverage_store.repo.ProductRepo;
import com.system.beverage_store.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    public final ProductRepo productRepo;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/beverage_store";
    @Override
    public List<Product> fetchAll() {
        return findAllInList(productRepo.findAll());
    }

    @Override
    public String save(ProductPojo productPojo) throws IOException {
        Product product;
        if (productPojo.getId() != null) {
            product = productRepo.findById(productPojo.getId()).orElseThrow(() -> new RuntimeException("Not Found"));
        } else {
            product = new Product();
        }

        if(productPojo.getId()!=null){
            product.setId(productPojo.getId());
        }
        product.setName(productPojo.getName());
        product.setQuantity(productPojo.getQuantity());
        product.setPrice(productPojo.getPrice());
        if(productPojo.getPhoto()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, productPojo.getPhoto().getOriginalFilename());
            fileNames.append(productPojo.getPhoto().getOriginalFilename());
            Files.write(fileNameAndPath, productPojo.getPhoto().getBytes());

            product.setPhoto(productPojo.getPhoto().getOriginalFilename());
        }

        productRepo.save(product);
        return "created";
    }

    @Override
    public Product fetchById(Integer id) {
        return productRepo.findById(id).orElseThrow(()->new RuntimeException("not found"));
    }

    @Override
    public void deleteById(Integer id) {
        productRepo.deleteById(id);
    }


    public List<Product> findAllInList(List<Product> list){
        Stream<Product> allCart=list.stream().map(product ->
                Product.builder()
                        .id(product.getId())
                        .imageBase64(getImageBase64(product.getPhoto()))
                        .name(product.getName())
                        .quantity(product.getQuantity())
                        .price(product.getPrice())
                        .build()
        );

        list = allCart.toList();
        return list;
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
    public Long countRows() {
        return productRepo.countAllRows();
    }
}
