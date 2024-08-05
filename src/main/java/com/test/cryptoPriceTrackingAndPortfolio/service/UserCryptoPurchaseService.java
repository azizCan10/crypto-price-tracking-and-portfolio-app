package com.test.cryptoPriceTrackingAndPortfolio.service;

import com.test.cryptoPriceTrackingAndPortfolio.dto.CreateUserCryptoPurchaseDTO;
import com.test.cryptoPriceTrackingAndPortfolio.dto.UserCryptoOperationHistoryDTO;
import com.test.cryptoPriceTrackingAndPortfolio.dto.UserCryptoPurchaseDTO;
import com.test.cryptoPriceTrackingAndPortfolio.enums.BuySell;
import com.test.cryptoPriceTrackingAndPortfolio.model.UserCryptoPurchase;
import com.test.cryptoPriceTrackingAndPortfolio.repository.UserCryptoPurchaseRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserCryptoPurchaseService {

    private final UserCryptoPurchaseRepository userCryptoPurchaseRepository;
    private final UserCryptoOperationHistoryService userCryptoOperationHistoryService;
    private final ModelMapper modelMapper;

    public UserCryptoPurchaseDTO create(CreateUserCryptoPurchaseDTO userCryptoPurchaseDTO) {
        UserCryptoPurchaseDTO result = null;
        Optional<UserCryptoPurchase> optionalEntity = userCryptoPurchaseRepository.findByUserAndCrypto(userCryptoPurchaseDTO.getUserCryptoPurchaseUser().getId(), userCryptoPurchaseDTO.getUserCryptoPurchaseCrypto().getId());

        if (userCryptoPurchaseDTO.getBuyOrSell().equals(BuySell.BUY)) {
            if (optionalEntity.isPresent()) {
                UserCryptoPurchase entity = optionalEntity.get();
                entity.setTotal(entity.getTotal().add(userCryptoPurchaseDTO.getAmount().multiply(userCryptoPurchaseDTO.getPrice())));
                entity.setAmount(entity.getAmount().add(userCryptoPurchaseDTO.getAmount()));
                entity.setPrice(entity.getTotal().divide(entity.getAmount(), 2, RoundingMode.HALF_UP));

                result = modelMapper.map(userCryptoPurchaseRepository.save(entity), UserCryptoPurchaseDTO.class);
                userCryptoOperationHistoryService.create(modelMapper.map(userCryptoPurchaseDTO, UserCryptoOperationHistoryDTO.class));
            } else {
                UserCryptoPurchase toSave = modelMapper.map(userCryptoPurchaseDTO, UserCryptoPurchase.class);
                toSave.setTotal(toSave.getAmount().multiply(toSave.getPrice()));

                result = modelMapper.map(userCryptoPurchaseRepository.save(toSave), UserCryptoPurchaseDTO.class);
                userCryptoOperationHistoryService.create(modelMapper.map(userCryptoPurchaseDTO, UserCryptoOperationHistoryDTO.class));
            }
        } else {
            if (optionalEntity.isPresent()) {
                UserCryptoPurchase entity = optionalEntity.get();
                entity.setTotal(entity.getTotal().subtract(userCryptoPurchaseDTO.getAmount().multiply(userCryptoPurchaseDTO.getPrice())));
                entity.setAmount(entity.getAmount().subtract(userCryptoPurchaseDTO.getAmount()));

                if (entity.getAmount().toString().equals("0.00")) {
                    userCryptoOperationHistoryService.deleteByUserIdAndCryptoId(entity.getUserCryptoPurchaseUser().getId(), entity.getUserCryptoPurchaseCrypto().getId());
                    delete(entity.getUserCryptoPurchaseUser().getId(), entity.getUserCryptoPurchaseCrypto().getId());

                    throw new EntityNotFoundException("User crypto purchase not found");
                }

                entity.setPrice(entity.getTotal().divide(entity.getAmount(), 2, RoundingMode.HALF_UP));
            } else {
                throw new RuntimeException("No purchase before");
            }
        }

        return result;
    }

    @Transactional
    public void delete(Long userId, Long cryptoId) {
        UserCryptoPurchase optionalEntity = userCryptoPurchaseRepository.findByUserAndCrypto(userId, cryptoId)
                .orElseThrow(() -> new EntityNotFoundException("Data not found"));

        userCryptoPurchaseRepository.delete(optionalEntity);
    }
}
