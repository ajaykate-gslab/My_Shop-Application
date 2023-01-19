package com.shop.myshop.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_info")
public class Order {
    @Id
    @GeneratedValue
    int orderId;

    int productId;

    @NotBlank
    String customerName;
    @NotBlank
    String mobile;

    @Email
    String email;
    @NotBlank
    String address;

    String productName;
    String couponType;

    String discountCode;
    double finalPrice;

}
