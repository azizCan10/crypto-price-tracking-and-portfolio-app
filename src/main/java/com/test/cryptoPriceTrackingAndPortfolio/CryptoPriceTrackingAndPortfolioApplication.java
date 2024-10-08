package com.test.cryptoPriceTrackingAndPortfolio;

import com.test.cryptoPriceTrackingAndPortfolio.dto.*;
import com.test.cryptoPriceTrackingAndPortfolio.model.UserCryptoTrack;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableAsync
@SpringBootApplication
public class CryptoPriceTrackingAndPortfolioApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptoPriceTrackingAndPortfolioApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<UserCryptoTrack, CryptoDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getCrypto().getId());
                map().setSymbol(source.getCrypto().getSymbol());
            }
        });

        modelMapper.addMappings(new PropertyMap<CreatePortfolioRequest, OperationHistoryDTO>() {
            @Override
            protected void configure() {
                map().setOperationHistoryUser(source.getPortfolioUser());
                map().setOperationHistoryCrypto(source.getPortfolioCrypto());
            }
        });

        return modelMapper;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
