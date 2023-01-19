package com.shop.myshop.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "discount_info")
public class Discount {
    @Id
    @NotEmpty
    String discountCode;

    double value;
    @NotEmpty
    String couponType;    //FLAT OR PERCENTAGE

}
