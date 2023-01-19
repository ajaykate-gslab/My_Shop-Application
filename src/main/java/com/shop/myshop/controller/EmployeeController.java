package com.shop.myshop.controller;

import com.shop.myshop.employeeService.api.*;
import com.shop.myshop.employeeService.controller.*;
import com.shop.myshop.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController implements InsertApi, FetchByIdApi,UpdateByIdApi, DeleteByIdApi ,FetchAllEmployeesApi{

    @Autowired
    private EmployeeRepository employeeRepository;

    Logger logger= LoggerFactory.getLogger(EmployeeController.class);



    //Api to insert Employee Information
    @Override
    @PostMapping("employee/insert")
    public ResponseEntity<Employee> insertPost(@Valid Employee body) {
        com.shop.myshop.entity.Employee employee=new com.shop.myshop.entity.Employee();
        employee.setEmployeeId(body.getEmployeeId());
        employee.setEmployeeName(body.getEmployeeName());
        employee.setSalary(body.getSalary());
        employee.setEmailId(body.getEmailId());
        employee.setMobile(body.getMobile());
        employeeRepository.save(employee);
        logger.info("Employee Inserted");
        return new ResponseEntity<>(employee,HttpStatus.ACCEPTED);
    }
    //---------------------------------------------------------------------

    //Api to fetch employee by EmployeeId
    @Override
    @GetMapping("/employee/fetchById/{employeeId}")
    public ResponseEntity<Employee>
                fetchByIdEmployeeIdGet(Integer employeeId){
                Optional<com.shop.myshop.entity.Employee> optionalEmployee=employeeRepository
                        .findById(employeeId);
        if (optionalEmployee.isPresent()) {
            return new ResponseEntity<>(optionalEmployee.get(), HttpStatus.ACCEPTED);
        }
        else {
            logger.warn("EmployeeId Not Found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //---------------------------------------------------------------------

    //Api to delete employee by EmployeeId
    @Override
    @DeleteMapping("employee/delete")
    public ResponseEntity<Employee> deleteByIdDelete(Integer employeeId) {
        Optional<com.shop.myshop.entity.Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (!optionalEmployee.isPresent()) {
            logger.warn("EmployeeId Not Found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            employeeRepository.delete(optionalEmployee.get());
            return new ResponseEntity<>(optionalEmployee.get(), HttpStatus.OK);
        }
    }
    //---------------------------------------------------------------------

    //Api to update employee by EmployeeId
    @Override
    @PutMapping("/employee/update/{employeeId}")
    public ResponseEntity<Employee> updateByIdEmployeeIdPut(Integer employeeId, Employee body) {
        Optional<com.shop.myshop.entity.Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isPresent()) {
            com.shop.myshop.entity.Employee employee = optionalEmployee.get();
            employee.setEmployeeName(body.getEmployeeName());
            employee.setSalary(body.getSalary());
            employee.setEmailId(body.getEmailId());
            employee.setMobile(body.getMobile());
            employeeRepository.save(employee);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            logger.warn("EmployeeId Not Found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //---------------------------------------------------------------------

    //Api to Fetch All employees

    @Override
    @GetMapping("/employee/getAllEmployees")
    public ResponseEntity<List<Employee>> fetchAllEmployeesGet() {
        List<com.shop.myshop.entity.Employee> list=employeeRepository.findAll();
        return new ResponseEntity(list,HttpStatus.OK);
    }
}

