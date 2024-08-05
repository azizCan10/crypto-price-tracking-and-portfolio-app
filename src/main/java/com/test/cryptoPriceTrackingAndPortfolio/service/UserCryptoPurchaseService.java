package com.test.cryptoPriceTrackingAndPortfolio.service;

import com.test.cryptoPriceTrackingAndPortfolio.dto.UserCryptoPurchaseDTO;
import com.test.cryptoPriceTrackingAndPortfolio.model.UserCryptoPurchase;
import com.test.cryptoPriceTrackingAndPortfolio.repository.UserCryptoPurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserCryptoPurchaseService {

    private final UserCryptoPurchaseRepository userCryptoPurchaseRepository;
    private final ModelMapper modelMapper;

    public UserCryptoPurchaseDTO create(UserCryptoPurchaseDTO userCryptoPurchaseDTO) {
        return modelMapper.map(userCryptoPurchaseRepository.save(modelMapper.map(userCryptoPurchaseDTO, UserCryptoPurchase.class)), UserCryptoPurchaseDTO.class);
    }
}
