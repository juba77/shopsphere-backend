package com.shop.sphere.service;

import com.shop.sphere.api.model.RoleEnum;
import com.shop.sphere.api.model.UserConnectedDTO;
import com.shop.sphere.dao.AdminRepository;
import com.shop.sphere.dao.BuyerRepository;
import com.shop.sphere.mappers.UserConnectedMapper;
import com.shop.sphere.models.Admin;
import com.shop.sphere.models.Buyer;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private UserConnectedMapper userConnectedMapper = Mappers.getMapper(UserConnectedMapper.class);

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BuyerService buyerService;

    @Value("${app.domain.name}")
    private String domainName;

    protected Buyer findBuyerByEmail(String email) {
        return buyerService.findBuyerByEmail(email);
    }

    protected Admin findAdminByEmailAndPassword(String email, String password){
        return adminRepository.findAdminByEmailAndPassword(email,password);
    }

    protected Buyer findBuyerByEmailAndPassword(String email, String password){
        return buyerService.findBuyerByEmailAndPassword(email,password);
    }

    public ResponseEntity<UserConnectedDTO> login(String email, String password){
        if(email.contains(domainName)){
            Admin admin = findAdminByEmailAndPassword(email,password);
            if(admin != null) {
                UserConnectedDTO userConnectedDTO = userConnectedMapper.adminToUserConnectedDto(admin);
                userConnectedDTO.setRole(RoleEnum.ADMIN);
                return ResponseEntity.ok().body(userConnectedDTO);
            }
        }
        Buyer buyer = findBuyerByEmail(email);
        if(buyer != null && passwordEncoder.matches(password, buyer.getPassword())) {
            UserConnectedDTO userConnectedDTO = userConnectedMapper.buyerToUserConnectedDto(buyer);
            userConnectedDTO.setRole(RoleEnum.BUYER);
            return ResponseEntity.ok().body(userConnectedDTO);
        }
        return ResponseEntity.badRequest().build();
    }

}
