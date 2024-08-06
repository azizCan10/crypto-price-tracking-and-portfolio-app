package com.test.cryptoPriceTrackingAndPortfolio.service;

import com.test.cryptoPriceTrackingAndPortfolio.dto.UserCryptoTrackDTO;
import com.test.cryptoPriceTrackingAndPortfolio.model.UserCryptoTrack;
import com.test.cryptoPriceTrackingAndPortfolio.repository.UserCryptoTrackRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserCryptoTrackService {

    private final UserCryptoTrackRepository userCryptoTrackRepository;
    private final CryptoService cryptoService;
    private final ModelMapper modelMapper;

    public UserCryptoTrackDTO create(UserCryptoTrackDTO userCryptoTrackDTO) {
        userCryptoTrackDTO.setCrypto(cryptoService.createOrGetCryptoIfExists(userCryptoTrackDTO.getCrypto()));
        return modelMapper.map(userCryptoTrackRepository.save(modelMapper.map(userCryptoTrackDTO, UserCryptoTrack.class)), UserCryptoTrackDTO.class);
    }
}
