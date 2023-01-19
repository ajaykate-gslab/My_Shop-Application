package com.shop.myshop.service;

import com.shop.myshop.entity.Product;
import com.shop.myshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
/*
    @Override
    public List<Product> searchProduct(String ststus, String productName,String) {
        List<Product> products = productRepository.searchProduct(productName,);
        return products;
    }*/

    /*@Override
    public List<Product> searchProduct(String status, String productName, String brand) {
        List<Product> products = productRepository.searchProduct(status,productName,brand);
        return null;
    }*/
}
