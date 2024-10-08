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
    private final CryptoService cryptoService;
    private final ModelMapper modelMapper;

    /**
     * This method saves data to db according to logged user id and cryptocurrency.
     * If there is existing data in the db for the given user id and cryptocurrency,
     * it will perform checks according to the amount, price, and buy/sell operation.
     *
     * @param createPortfolioRequest request dto
     * @return saved data
     */
    public PortfolioDTO addOperation(CreatePortfolioRequest createPortfolioRequest) {
        createPortfolioRequest.setPortfolioCrypto(cryptoService.createOrGetCryptoIfExists(createPortfolioRequest.getPortfolioCrypto()));
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

                    throw new EntityNotFoundException("crypto with symbol: " + entity.getPortfolioCrypto().getSymbol());
                }

                entity.setTotal(entity.getTotal().subtract(createPortfolioRequest.getAmount().multiply(createPortfolioRequest.getPrice())));
                entity.setPrice(entity.getTotal().divide(entity.getAmount(), 2, RoundingMode.HALF_UP));
            } else {
                throw new EntityNotFoundException("crypto with symbol: " + createPortfolioRequest.getPortfolioCrypto().getSymbol());
            }
        }

        result = modelMapper.map(portfolioRepository.save(entity), PortfolioDTO.class);
        operationHistoryService.create(modelMapper.map(createPortfolioRequest, OperationHistoryDTO.class));

        return result;
    }

    /**
     * This method deletes data from db according to logged user id and crypto id
     *
     * @param userId
     * @param cryptoId
     */
    @Transactional
    public void delete(Long userId, Long cryptoId) {
        Portfolio optionalEntity = portfolioRepository.findByUserAndCrypto(userId, cryptoId)
                .orElseThrow(() -> new EntityNotFoundException("portfolio with userId: " + userId));

        portfolioRepository.delete(optionalEntity);
    }
}
