package com.shop.myshop.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customer_info")
public class Customer {
    @Id
    @GeneratedValue
    int customerId;

    @NotEmpty(message = "Name should not empty")
    String customerName;

    @NotEmpty(message = "Address should not empty")
    String address;

    @NotBlank(message = "Mobile should not empty")
    String mobile;

    @Email(message = "Enter Valid email Id")
    String email;


}
