package com.shop.sphere.controllers;

import com.shop.sphere.api.AdminsApi;
import com.shop.sphere.api.model.AdminDTO;
import com.shop.sphere.dao.AdminRepository;
import com.shop.sphere.mappers.AdminMapper;
import com.shop.sphere.models.Admin;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
public class AdminRestController implements AdminsApi {

    @Autowired
    private AdminRepository adminRepository;
    private AdminMapper adminMapper = Mappers.getMapper(AdminMapper.class);

    @Override
    public ResponseEntity<AdminDTO> getAdmin(Long idAdmin) {
        Optional<Admin> admin = adminRepository.findById(idAdmin);
        if(admin.isPresent())
            return ResponseEntity.ok(adminMapper.adminToAdminDto(admin.get()));
        return ResponseEntity.notFound().build();
    }

}
