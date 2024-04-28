package com.shop.sphere.mappers;

import com.shop.sphere.api.model.AdminDTO;
import com.shop.sphere.models.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    AdminDTO adminToAdminDto(Admin admin);

}
