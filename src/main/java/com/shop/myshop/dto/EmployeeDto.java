package com.shop.myshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@NoArgsConstructor
@Component
public class EmployeeDto {



    private Integer employeeId;

    @NotEmpty(message = "Customer Name should not empty")
    private String employeeName;

    @NotEmpty(message = "Customer Salary should not empty")
    private BigDecimal salary;

}
