package com.shop.myshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Component
@NoArgsConstructor
public class CustomerDto {

    @Id
    int customerId;
    @NotEmpty(message = "Customer Name should not empty")
    String customerName;

    @NotEmpty(message = "Address should not empty")
    String address;

    @NotEmpty
    @Size(min = 10 ,max = 10,message = "Mobile number size must be 10 digit !!!")
    String mobile;

    @Email(message = "Invalid email Id")
    String email;
    //---

}
