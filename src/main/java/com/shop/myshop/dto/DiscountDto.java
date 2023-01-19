package com.shop.myshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Id;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@Component
public class DiscountDto{
    @Id
    @NotEmpty(message = "Discount Code Should not Empty")
    String discountCode;

    @Positive(message = "Value should be positive number")
    double value;

    @NotEmpty(message = "CouponType Should not empty")
    String couponType;
}
