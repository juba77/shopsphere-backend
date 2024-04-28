package com.shop.sphere.service;

import com.shop.sphere.dao.BuyerRepository;
import com.shop.sphere.models.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Buyer saveBuyer(Buyer buyer) {
        String encodedPassword = passwordEncoder.encode(buyer.getPassword());
        buyer.setPassword(encodedPassword);
        return buyerRepository.save(buyer);
    }

    public Buyer findBuyerByEmailAndPassword(String email, String password) {
        Optional<Buyer> buyerOpt = buyerRepository.findByEmail(email);
        if (buyerOpt.isPresent() && passwordEncoder.matches(password, buyerOpt.get().getPassword())) {
            return buyerOpt.get();
        }
        return null;
    }

    public Buyer findBuyerByEmail(String email) {
        return buyerRepository.findBuyerByEmail(email);
    }
}
