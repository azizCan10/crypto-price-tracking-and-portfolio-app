package com.test.cryptoPriceTrackingAndPortfolio.service;

import com.test.cryptoPriceTrackingAndPortfolio.dto.CryptoDTO;
import com.test.cryptoPriceTrackingAndPortfolio.model.Crypto;
import com.test.cryptoPriceTrackingAndPortfolio.repository.CryptoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CryptoService {

    private final CryptoRepository cryptoRepository;
    private final ModelMapper modelMapper;

    public CryptoDTO createCrypto(CryptoDTO cryptoDTO) {
        return modelMapper.map(cryptoRepository.save(modelMapper.map(cryptoDTO, Crypto.class)), CryptoDTO.class);
    }

    public List<CryptoDTO> getAllCryptos() {
        return cryptoRepository.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, CryptoDTO.class))
                .toList();
    }
}
