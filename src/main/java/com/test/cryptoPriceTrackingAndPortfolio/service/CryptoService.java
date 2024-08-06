package com.test.cryptoPriceTrackingAndPortfolio.service;

import com.test.cryptoPriceTrackingAndPortfolio.dto.CryptoDTO;
import com.test.cryptoPriceTrackingAndPortfolio.model.Crypto;
import com.test.cryptoPriceTrackingAndPortfolio.repository.CryptoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CryptoService {

    private final CryptoRepository cryptoRepository;
    private final ModelMapper modelMapper;

    public CryptoDTO createCrypto(CryptoDTO cryptoDTO) {
        return modelMapper.map(cryptoRepository.save(modelMapper.map(cryptoDTO, Crypto.class)), CryptoDTO.class);
    }

    public CryptoDTO createOrGetCryptoIfExists(CryptoDTO cryptoDTO) {
        Optional<Crypto> entityOptional = cryptoRepository.getCryptoBySymbol(cryptoDTO.getSymbol());

        if (entityOptional.isPresent())
            return modelMapper.map(entityOptional.get(), CryptoDTO.class);

        return createCrypto(cryptoDTO);
    }
}
