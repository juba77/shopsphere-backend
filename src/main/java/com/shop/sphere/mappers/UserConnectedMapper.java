package com.shop.sphere.mappers;

import com.shop.sphere.api.model.UserConnectedDTO;
import com.shop.sphere.models.Admin;
import com.shop.sphere.models.Buyer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConnectedMapper {

    UserConnectedDTO adminToUserConnectedDto(Admin admin);

    UserConnectedDTO buyerToUserConnectedDto(Buyer buyer);

}
