package com.shop.myshop.repository;

import com.shop.myshop.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query(value = "SELECT * FROM product_info p WHERE " +
            "p.product_status LIKE CONCAT('%',:status,'%')"+
            "or p.product_name LIKE CONCAT('%',:productName,'%')"+
            "or p.brand LIKE CONCAT('%',:brand,'%')"
            ,nativeQuery = true)
    List<Product> searchProduct(String status,
                                String productName,
                                String brand);

    @Query(value = "SELECT * FROM product_info p WHERE " +
            "p.product_status LIKE CONCAT('%',:status,'%')"+
            "or p.product_name LIKE CONCAT('%',:productName,'%')"+
            "or p.brand LIKE CONCAT('%',:brand,'%')"+
            "or p.product_price BETWEEN :minPrice AND :maxPrice"
            ,nativeQuery = true)
    List<Product> searchProduct(String status,
                                String productName,
                                String brand, Double minPrice, Double maxPrice, Pageable paging);
    //----------
    @Query(value = "SELECT * FROM product_info p WHERE " +
            "p.product_status =:status "+
            "and p.product_name = :productName "+
            "and p.brand = :brand "+
            "and p.product_price BETWEEN :minPrice AND :maxPrice"
            ,nativeQuery = true)
    List<Product> searchExactProduct(String status,
                                String productName,
                                String brand, double minPrice, double maxPrice, Pageable paging);
    //---------
    @Query(value = "select * from product_info where product_price between :minPrice and :maxPrice"
            ,nativeQuery = true)
    List<Product> priceFilter(double minPrice, double maxPrice, Pageable paging);
}