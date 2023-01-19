package com.shop.myshop.mapper;

import com.shop.myshop.dto.ProductDto;
import com.shop.myshop.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE=Mappers.getMapper(ProductMapper.class);

    Product productDtoToProductModel(ProductDto productDto);
    ProductDto productModelToProductDto(Product product);

}
