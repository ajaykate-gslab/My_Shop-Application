package com.shop.myshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@Component
public class OrderDto {
    @Id
    @GeneratedValue
    int orderId;
    int productId;

    @NotEmpty(message = "Customer Name should not empty")
    String customerName;

    @NotEmpty
    @Size(min = 10,max = 10,message = "Mobile number size must be 10 digit")
    String mobile;

    @Email(message = "Enter Valid Email Id")
    String email;

    @NotEmpty(message = "Address should not empty")
    String address;

    String productName;
    String couponType;

    @NotEmpty(message = "Discount Code should not empty")
    String discountCode;
    double finalPrice;
}
