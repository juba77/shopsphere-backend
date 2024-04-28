package com.shop.sphere.controllers;

import com.shop.sphere.service.BuyerService;
import com.shop.sphere.api.BuyersApi;
import com.shop.sphere.api.model.BuyerDTO;
import com.shop.sphere.api.model.IdBuyer;
import com.shop.sphere.dao.BuyerRepository;
import com.shop.sphere.mappers.BuyerMapper;
import com.shop.sphere.models.Buyer;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BuyerRestController implements BuyersApi {

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private BuyerRepository buyerRepository;

    private BuyerMapper buyerMapper = Mappers.getMapper(BuyerMapper.class);

    @Override
    public ResponseEntity<IdBuyer> createBuyer(@Valid @RequestBody BuyerDTO buyerDTO) {
        Buyer buyer = buyerMapper.buyerDtoToBuyer(buyerDTO);
        Buyer savedBuyer = buyerService.saveBuyer(buyer);
        IdBuyer idBuyer = new IdBuyer();
        idBuyer.setIdBuyer(savedBuyer.getId());
        return new ResponseEntity<>(idBuyer, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BuyerDTO> getBuyer(Long idBuyer) {
        Optional<Buyer> buyer = buyerRepository.findById(idBuyer);
        return buyer.map(value -> new ResponseEntity<>(buyerMapper.buyerToBuyerDto(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @Override
    public ResponseEntity<List<BuyerDTO>> getBuyers() {
        List<Buyer> buyers = (List<Buyer>) buyerRepository.findAll();
        if (buyers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<BuyerDTO> buyerDTOS = buyers.stream()
                .map(buyerMapper::buyerToBuyerDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(buyerDTOS, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<Void> updateBuyer(@Valid @RequestBody BuyerDTO buyerDTO) {
        Optional<Buyer> existingBuyer = buyerRepository.findById(buyerDTO.getId());
        if (existingBuyer.isPresent()) {
            Buyer updatedBuyer = buyerMapper.buyerDtoToBuyer(buyerDTO);
            buyerService.saveBuyer(updatedBuyer);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }}
