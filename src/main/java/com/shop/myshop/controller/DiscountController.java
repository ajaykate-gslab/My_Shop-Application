package com.shop.myshop.controller;

import com.shop.myshop.dto.DiscountDto;
import com.shop.myshop.entity.Discount;
import com.shop.myshop.mapper.DiscountMapper;
import com.shop.myshop.repository.DiscountRepository;
import com.shop.myshop.validator.AuthorizeApi;
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
public class DiscountController {

    @Autowired
    private DiscountDto discountDto;
    @Autowired
    private DiscountMapper discountMapper;
    @Autowired
    private DiscountRepository discountRepository;

    AuthorizeApi authorizeApi=new AuthorizeApi();

    Logger logger= LoggerFactory.getLogger(CustomerController.class);

    //-------------------------------------------------------------------------------

    @PostMapping("/discount/create")
    public ResponseEntity<Discount> createDiscount(@RequestHeader (value = "gslab") String header,
                                                   @Valid  @RequestBody DiscountDto discountDto){
        if (!authorizeApi.checkHeader(header))   //checkHeader method to authorize header
        {
            logger.error("ERROR 401");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(discountRepository.save(discountMapper.discountDtoToDiscountModel(discountDto)), HttpStatus.CREATED);
    }

    //-------------------------------------------------------------------------------

    //To Delete Particular Coupon Code
    @DeleteMapping("/discount/delete")
    public ResponseEntity<Discount> deleteDiscount( @RequestHeader (value = "gslab") String header,
                                                    @RequestBody DiscountDto discountDto){
        if (!authorizeApi.checkHeader(header))   //checkHeader method to authorize header
        {
            logger.error("ERROR 401");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Optional<Discount> optionalDiscount=discountRepository.findById(discountDto.getDiscountCode());
        if (optionalDiscount.isPresent()){
            discountRepository.deleteById(discountDto.getDiscountCode());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //--------------------------------------------------------------------------------

    //To get all Discount Coupon Codes
    @GetMapping("/discount/getall")
    public List<Discount> getAllDiscount(){
        return discountRepository.findAll();
    }

    //----------------------------------------------------------------------------------

    //To Update Particular Coupon Code
    @PutMapping("/discount/update")
    public ResponseEntity<Discount> updateDiscount(@RequestHeader (value = "gslab") String header,
                                                   @Valid @RequestBody DiscountDto discountDto){
        if (!authorizeApi.checkHeader(header))   //checkHeader method to authorize header
        {
            logger.error("ERROR 401");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Optional<Discount> optionalDiscount= discountRepository.findById(discountDto.getDiscountCode());
        if (optionalDiscount.isPresent()){
            return new ResponseEntity<>(discountRepository.save(discountMapper.discountDtoToDiscountModel(discountDto)),HttpStatus.ACCEPTED);

        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}