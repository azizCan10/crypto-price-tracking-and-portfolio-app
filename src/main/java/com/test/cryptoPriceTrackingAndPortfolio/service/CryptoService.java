package com.test.cryptoPriceTrackingAndPortfolio.service;

import com.test.cryptoPriceTrackingAndPortfolio.model.Crypto;
import com.test.cryptoPriceTrackingAndPortfolio.repository.CryptoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CryptoService {

    private final CryptoRepository cryptoRepository;

    public Crypto createCrypto(Crypto crypto) {
        return cryptoRepository.save(crypto);
    }

    public List<Crypto> getAllCryptos() {
        return cryptoRepository.findAll();
    }
}
