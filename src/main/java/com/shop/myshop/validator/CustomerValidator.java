package com.shop.myshop.validator;
import com.shop.myshop.entity.Customer;
import java.util.Objects;

public class CustomerValidator {
    private CustomerValidator()
    {
    }

    public static void validateCutomer(Customer customer) {
        if (Objects.isNull(customer)) {
            throw new NullPointerException("Customer is null");
        }
    }
}