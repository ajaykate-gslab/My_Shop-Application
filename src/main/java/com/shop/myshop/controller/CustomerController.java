package com.shop.myshop.controller;

import com.shop.myshop.dto.CustomerDto;
import com.shop.myshop.dto.OrderDto;
import com.shop.myshop.entity.Customer;
import com.shop.myshop.entity.Discount;
import com.shop.myshop.entity.Order;
import com.shop.myshop.entity.Product;
import com.shop.myshop.mapper.CustomerMapper;
import com.shop.myshop.mapper.OrderMapper;
import com.shop.myshop.repository.CustomerRepository;
import com.shop.myshop.repository.DiscountRepository;
import com.shop.myshop.repository.OrderRepository;
import com.shop.myshop.repository.ProductRepository;
import com.shop.myshop.validator.AuthorizeApi;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import java.util.*;

@RestController
@EnableWebMvc
@Api(value = "abc")
public class CustomerController{

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DiscountRepository discountRepository;

    AuthorizeApi authorizeApi=new AuthorizeApi();
//---------------------------------------------------

    //To print Memoty Status
    @GetMapping("/memorystatus")
    public void memoryStatus(){
        ArrayList<Integer> arrayList = new ArrayList<>();
        logger.info("i \t Free Memory \t Total Memory \t Max Memory");
        for (int i = 0; i < 10; i++) {
            arrayList.add(i);

            logger.info(i + " \t " + Runtime.getRuntime().freeMemory() +
                    " \t \t " + Runtime.getRuntime().totalMemory() +
                    " \t \t " + Runtime.getRuntime().maxMemory());
        }
    }

//    -----------------------------------------------------------
    Logger logger= LoggerFactory.getLogger(CustomerController.class);

    //--------------------------------------------------------------------------------------------------

    //API to insert customer
    @PostMapping("customer/create")
    public ResponseEntity<Customer> createCustomer(@RequestHeader (value = "gslab") String header,
            @Valid  @RequestBody CustomerDto customerDto){

        if (!authorizeApi.checkHeader(header))   //checkHeader method to authorize header
        {
            logger.error("ERROR 401");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        else {
        return new ResponseEntity<>(customerRepository
                .save(customerMapper.customerDtoToCustomerModel(customerDto)),
                HttpStatus.CREATED);}
    }

//-----------------------------------------------------------------------------------------------

    //api to update customer information
    @PutMapping("customer/update") //customer create api
    public ResponseEntity<Customer> updateCustomer(@RequestHeader (value = "gslab") String header,
            @Valid @RequestBody CustomerDto customerDto){

        if (!authorizeApi.checkHeader(header))   //checkHeader method to authorize header
        {
            logger.error("ERROR 401");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Optional<Customer> optionalCustomer=customerRepository.findById(customerDto.getCustomerId());
        if (optionalCustomer.isPresent()){
            return new ResponseEntity<>(customerRepository.save(customerMapper.customerDtoToCustomerModel(customerDto)),HttpStatus.ACCEPTED);
        }
        logger.error("404 ERROR");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
//-----------------------------------------------------------------------------------------------

    //api to delete customer
    @DeleteMapping("customer/delete")   //customer delete api
    public ResponseEntity<Customer> deleteCustomer(@RequestHeader (value = "gslab") String header,
                                                   @RequestBody CustomerDto customerDto){
        if (!authorizeApi.checkHeader(header))   //checkHeader method to authorize header
        {
            logger.error("ERROR 401");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Optional<Customer> optionalCustomer =customerRepository.findById(customerDto.getCustomerId());
        if (optionalCustomer.isPresent()){
            customerRepository.deleteById(customerDto.getCustomerId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //--------------------------------------------------------------------------------------------------

    //To get all customer
    @GetMapping("customer/getall")
    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }


    //--------------------------------------------------------------------------------------------------
    //API TO PLACE ORDER
    @PostMapping("order/buy")
    public ResponseEntity<Order> buyproduct(@RequestHeader (value = "gslab") String header,
                                            @Valid @RequestBody OrderDto orderDto) {

        if (!authorizeApi.checkHeader(header))   //checkHeader method to authorize header
        {
            logger.error("ERROR 401");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Optional<Product> optionalProduct = productRepository.findById(orderDto.getProductId());
        Optional<Discount> optionalDiscount = discountRepository.findById(orderDto.getDiscountCode());

        if (optionalProduct.isPresent()) {
            if (optionalDiscount.isPresent()) {

                Product product1 = optionalProduct.get();
                Discount discount1 = optionalDiscount.get();

                orderDto.setProductId(product1.getProductId());
                orderDto.setProductName(product1.getProductName());
                orderDto.setMobile(orderDto.getMobile());
                orderDto.setEmail(orderDto.getEmail());
                orderDto.setAddress(orderDto.getAddress());
                orderDto.setCouponType(discount1.getCouponType());
                orderDto.setDiscountCode(discount1.getDiscountCode());

                if ("FLAT".equals(orderDto.getCouponType())) {
                    double y = product1.getProductPrice() - discount1.getValue();
                    orderDto.setFinalPrice(y);

                } else if ("PERCENTAGE".equals(orderDto.getCouponType())) {
                    double y = product1.getProductPrice() * discount1.getValue() / 100;
                    orderDto.setFinalPrice(y);
                }

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            logger.info("Order Placed");
            return new ResponseEntity<>(orderRepository
                    .save(orderMapper
                            .orderDtoToOrderModel(orderDto)),HttpStatus.OK);


        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //--------------------------------------------------------------------------------------------------

    //API to Cancel Order
    @DeleteMapping("order/cancel")
    public ResponseEntity<Order> cancelProduct(@RequestHeader (value = "gslab") String header,
                                               @RequestBody OrderDto orderDto) {

        if (!authorizeApi.checkHeader(header))   //checkHeader method to authorize header
        {
            logger.error("ERROR 401");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Optional<Order> optionalOrder = orderRepository.findById(orderDto.getOrderId());
        if (optionalOrder.isPresent()) {
            orderRepository.deleteById(orderDto.getOrderId());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            logger.info("Order Cancelled");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
