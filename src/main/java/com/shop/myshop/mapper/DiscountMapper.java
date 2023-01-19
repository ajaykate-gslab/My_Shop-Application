package com.shop.myshop.mapper;

import com.shop.myshop.dto.DiscountDto;
import com.shop.myshop.entity.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DiscountMapper {
    DiscountMapper INSTANCE= Mappers.getMapper(DiscountMapper.class);
    Discount discountDtoToDiscountModel(DiscountDto discountDto);
    DiscountDto discountModelToDiscountDto(Discount discount);
}
