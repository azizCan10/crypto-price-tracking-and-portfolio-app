package com.test.cryptoPriceTrackingAndPortfolio.service;

import com.test.cryptoPriceTrackingAndPortfolio.dto.CreatePortfolioRequest;
import com.test.cryptoPriceTrackingAndPortfolio.dto.OperationHistoryDTO;
import com.test.cryptoPriceTrackingAndPortfolio.dto.PortfolioDTO;
import com.test.cryptoPriceTrackingAndPortfolio.enums.BuySell;
import com.test.cryptoPriceTrackingAndPortfolio.model.Portfolio;
import com.test.cryptoPriceTrackingAndPortfolio.repository.PortfolioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final OperationHistoryService operationHistoryService;
    private final ModelMapper modelMapper;

    public PortfolioDTO addOperation(CreatePortfolioRequest createPortfolioRequest) {
        PortfolioDTO result = null;
        Portfolio entity = null;
        Optional<Portfolio> optionalEntity = portfolioRepository.findByUserAndCrypto(createPortfolioRequest.getPortfolioUser().getId(), createPortfolioRequest.getPortfolioCrypto().getId());

        if (createPortfolioRequest.getBuyOrSell().equals(BuySell.BUY)) {
            if (optionalEntity.isPresent()) {
                entity = optionalEntity.get();
                entity.setTotal(entity.getTotal().add(createPortfolioRequest.getAmount().multiply(createPortfolioRequest.getPrice())));
                entity.setAmount(entity.getAmount().add(createPortfolioRequest.getAmount()));
                entity.setPrice(entity.getTotal().divide(entity.getAmount(), 2, RoundingMode.HALF_UP));
            } else {
                entity = modelMapper.map(createPortfolioRequest, Portfolio.class);
                entity.setTotal(entity.getAmount().multiply(entity.getPrice()));
            }
        } else {
            if (optionalEntity.isPresent()) {
                entity = optionalEntity.get();
                entity.setAmount(entity.getAmount().subtract(createPortfolioRequest.getAmount()));

                if (entity.getAmount().toString().equals("0.00")) {
                    operationHistoryService.deleteByUserIdAndCryptoId(entity.getPortfolioUser().getId(), entity.getPortfolioCrypto().getId());
                    delete(entity.getPortfolioUser().getId(), entity.getPortfolioCrypto().getId());

                    throw new EntityNotFoundException("There is no " + entity.getPortfolioCrypto().getSymbol() + " in portfolio");
                }

                entity.setTotal(entity.getTotal().subtract(createPortfolioRequest.getAmount().multiply(createPortfolioRequest.getPrice())));
                entity.setPrice(entity.getTotal().divide(entity.getAmount(), 2, RoundingMode.HALF_UP));
            }
        }

        result = modelMapper.map(portfolioRepository.save(entity), PortfolioDTO.class);
        operationHistoryService.create(modelMapper.map(createPortfolioRequest, OperationHistoryDTO.class));

        return result;
    }

    @Transactional
    public void delete(Long userId, Long cryptoId) {
        Portfolio optionalEntity = portfolioRepository.findByUserAndCrypto(userId, cryptoId)
                .orElseThrow(() -> new EntityNotFoundException("Data not found"));

        portfolioRepository.delete(optionalEntity);
    }
}
