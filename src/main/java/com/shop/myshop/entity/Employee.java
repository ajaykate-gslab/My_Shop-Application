package com.shop.myshop.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "employee")
public class Employee extends com.shop.myshop.employeeService.controller.Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer employeeId;
    private String employeeName;
    private BigDecimal salary;
    private String emailId;
    private String mobile;

    public Employee(Integer employeeId, String employeeName, BigDecimal salary, String emailId, String mobile) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.salary = salary;
        this.emailId = emailId;
        this.mobile = mobile;
    }
}
