package com.shop.myshop.mapper;

import com.shop.myshop.dto.CustomerDto;
import com.shop.myshop.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE= Mappers.getMapper(CustomerMapper.class);

    CustomerDto customerModelToCustomerDto(Customer customer);
    Customer customerDtoToCustomerModel(CustomerDto customerDto);
}
