package com.shop.sphere.mappers;

import com.shop.sphere.api.model.BuyerDTO;
import com.shop.sphere.models.Buyer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BuyerMapper {

    BuyerDTO buyerToBuyerDto(Buyer buyer);

    Buyer buyerDtoToBuyer(BuyerDTO buyerDTO);
}
