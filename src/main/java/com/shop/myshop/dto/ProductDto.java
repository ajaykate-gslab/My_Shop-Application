package com.shop.myshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@Component
public class ProductDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int productId;

    @NotEmpty(message = "Product name should not empty or null")
    String productName;

    @NotEmpty(message = "Brand name should not empty")
    String brand;

    @Positive(message = "Product Price should be positive number")
    double productPrice;

    @NotEmpty(message = "Product Colour should not empty")
    String productColour;

    String productStatus;


}
