package com.shop.myshop.controller;

import com.shop.myshop.dto.ProductDto;
import com.shop.myshop.entity.Product;
import com.shop.myshop.mapper.ProductMapper;
import com.shop.myshop.repository.ProductRepository;
import com.shop.myshop.service.ProductService;
import com.shop.myshop.validator.AuthorizeApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Api
public class ProductController {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductRepository productRepository;

    AuthorizeApi authorizeApi=new AuthorizeApi();
    Logger logger= LoggerFactory.getLogger(CustomerController.class);
//---------------------------------------------------------------------------------------------------------
    /*@ApiResponses(value = {@ApiResponse(code = 500, message = "Internal Server Error")})
public class Controller {

    @ApiResponses(value = {@ApiResponse(code = 404, message = "Resource Not Found"), @ApiResponse(code = 200, message = "Ok")})
    public ResponseEntity<> listApi(){
      // Code here
    }
    -----

    @GetMapping("/example")
@ApiOperation(value = "Example mapping")
@ApiResponses(value = {@ApiResponse(code = 200, message = "Successfull request"), @ApiResponse(code = 500, message = "Internal error")})
public void example() {
 // example
}
}*/
    //----------------------------------------------------------------------------------------------------

    //API to insert validated Product details
    @PostMapping("/product/insert")
    @ApiOperation(value = "Insert Product")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully Product Inserted"), @ApiResponse(code = 401, message = "Unauthorized Request for insert Product")})
    public ResponseEntity<Product> insertController(@RequestHeader (value = "gslab") String header,@Valid @RequestBody ProductDto productDto)
    {
        if (!authorizeApi.checkHeader(header))   //checkHeader method to authorize header
        {
            logger.error("ERROR 401");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(productRepository
                .save(productMapper
                        .productDtoToProductModel(productDto)),HttpStatus.CREATED);
    }

    //--------------------------------------------------------------------------------------------------

    //API to get all products
    @GetMapping("/product/getallproduct")
    public List<Product> showController()
    {

        return productRepository.findAll();
    }

    //--------------------------------------------------------------------------------------------------

    //API to update the product details which is already present
    @PutMapping("/product/update")
    public ResponseEntity<Product> updateProductController(@RequestHeader (value = "gslab") String header,
                                                           @Valid @RequestBody ProductDto productDto)
    {
        if (!authorizeApi.checkHeader(header))   //checkHeader method to authorize header
        {
            logger.error("ERROR 401");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Optional<Product> optionalProduct = productRepository.findById(productDto.getProductId());
        if(optionalProduct.isPresent()) {
            return new ResponseEntity<>(productRepository
                            .save(productMapper
                            .productDtoToProductModel(productDto)), HttpStatus.ACCEPTED);
        }
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //--------------------------------------------------------------------------------------------------

    //API to delete perticular Product based on productId
    @DeleteMapping("product/delete")
    @ApiOperation(value = "Delete Product")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfull request"), @ApiResponse(code = 401, message = "Unauthorized Request")})
    public ResponseEntity deleteProductController(@RequestHeader (value = "gslab") String header,@RequestBody ProductDto productDto) {
        if (!authorizeApi.checkHeader(header))   //checkHeader method to authorize header
        {
            logger.error("ERROR 401");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Optional<Product> productOptional = productRepository.findById(productDto.getProductId());
        if (productOptional.isPresent()) {
            productRepository.deleteById(productDto.getProductId());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }


    //-----------------------------------------------------------------------------------------------------------------------------
    //API to get all products by sorting
    @GetMapping("/product/getProductsBySorting")
    public List<Product> SortAndPagingController(@RequestParam(value = "pageNumber",defaultValue = "2",required = false) Integer pageNumber,
                                                 @RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize)
    {
        /*Sort sort=null;
        if (sortDir.equalsIgnoreCase("asc")){
            sort=Sort.by(sortBy).ascending();
        }else
        {
            sort=Sort.by(sortBy).descending();
        }*/

        Pageable p= PageRequest.of(pageNumber,pageSize);

        Page<Product> productPage= productRepository.findAll(p);

        List<Product> allProduct=productPage.getContent();


        //return productRepository.findAll();
        return allProduct;
    }
//----------------------------
    @GetMapping("/welcome")
    public String welcome(){
        String text="Welcome to test";

        return text;
    }
//-----------------------------
    @Autowired
    private ProductService productService;

    /*@GetMapping("/product/search")
    public ResponseEntity<List<Product>> searchProducts (@RequestParam("query") String query){
        return ResponseEntity.ok(productService.searchProduct(query));
    }*/

    //-----------------------------------------------------------------------------------------------------------------------------
    @GetMapping("/product/search")
    public ResponseEntity<List<Product>> searchProducts (@RequestBody ProductDto productDto,@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                                                         @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){

        Pageable paging = PageRequest.of(pageNumber,pageSize);
        Page<Product> pagedResult = productRepository.findAll(paging);
        List<Product> allSortedProducts =pagedResult.getContent();

        return ResponseEntity.ok(productRepository.searchProduct(
                productDto.getProductStatus(),productDto.getProductName(),productDto.getBrand()));

    }
    //-----------------------------------------------------------------------------------------------------------------------------
    //API to search product by status , productName, brand and also find product between given range
    //And display pageNumber of all accessed result pages with pageSize
    @GetMapping("/product/searches")
    public ResponseEntity<List<Product>> searchProducts1 (@RequestBody ProductDto productDto,
                                                          @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                                                          @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                          @RequestParam(value = "minPrice",defaultValue = "0") Double minPrice,
                                                          @RequestParam(value = "maxPrice",defaultValue = "2000") Double maxPrice)
    {
        Pageable paging = PageRequest.of(pageNumber,pageSize);
        Page<Product> pagedResult = productRepository.findAll(paging);
        List<Product> allSortedProducts =pagedResult.getContent();

        return ResponseEntity.ok(productRepository.searchProduct(productDto.getProductStatus(),productDto.getProductName(),
                productDto.getBrand(),minPrice,maxPrice,paging));

    }
    //-----------------------------------------------------------------------------------------------------------------------------

    //API to find exact product
    @GetMapping("/product/searchExactProduct")
    public ResponseEntity<List<Product>> searchExactProduct (@RequestBody ProductDto productDto,
                                                          @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                                                          @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                          @RequestParam(value = "minPrice",defaultValue = "0") double minPrice,
                                                          @RequestParam(value = "maxPrice",defaultValue = "2000") double maxPrice)
    {
        Pageable paging = PageRequest.of(pageNumber,pageSize);
        Page<Product> pagedResult = productRepository.findAll(paging);
        List<Product> allSortedProducts =pagedResult.getContent();

        return ResponseEntity.ok(productRepository.searchExactProduct(productDto.getProductStatus(),productDto.getProductName(),
                productDto.getBrand(),minPrice,maxPrice,paging));

    }
    //------------------------
    @GetMapping("/product/priceFilter")
    public ResponseEntity<List<Product>> priceFilter (       @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                                                             @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                             @RequestParam(value = "minPrice",defaultValue = "0") double minPrice,
                                                             @RequestParam(value = "maxPrice",defaultValue = "2000") double maxPrice)
    {
        Pageable paging = PageRequest.of(pageNumber,pageSize);
        Page<Product> pagedResult = productRepository.findAll(paging);
        List<Product> allSortedProducts =pagedResult.getContent();

        return ResponseEntity.ok(productRepository.priceFilter(minPrice,maxPrice,paging));

    }

}