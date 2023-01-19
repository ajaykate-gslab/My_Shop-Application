package com.shop.myshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product_info")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int productId;
    @NotBlank
    String productName;
    @NotBlank
    String brand;

    @Positive
    double productPrice;
    @NotBlank
    String productColour;

    String productStatus;


}
