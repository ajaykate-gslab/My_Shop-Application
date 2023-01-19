package com.shop.myshop.mapper;

import com.shop.myshop.dto.EmployeeDto;
import com.shop.myshop.employeeService.controller.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeMapper INSTANCE= Mappers.getMapper(EmployeeMapper.class);

    EmployeeDto employeeModelToEmployeeDto(Employee employee);
    Employee employeeDtoToEmployeeModel(EmployeeDto employeeDto);



    com.shop.myshop.entity.Employee serviceEmployeeToEntityEmployee(Employee employee);
    Employee entityEmployeeToServiceEmployee(com.shop.myshop.entity.Employee employee);
}
