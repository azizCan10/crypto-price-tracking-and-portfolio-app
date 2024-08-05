package com.test.cryptoPriceTrackingAndPortfolio.service;

import com.test.cryptoPriceTrackingAndPortfolio.model.UserCryptoOperationHistory;
import com.test.cryptoPriceTrackingAndPortfolio.dto.UserCryptoOperationHistoryDTO;
import com.test.cryptoPriceTrackingAndPortfolio.repository.UserCryptoOperationHistoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserCryptoOperationHistoryService {

    private final UserCryptoOperationHistoryRepository userCryptoOperationHistoryRepository;
    private final ModelMapper modelMapper;

    public List<UserCryptoOperationHistoryDTO> getAll() {
        return userCryptoOperationHistoryRepository.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, UserCryptoOperationHistoryDTO.class))
                .toList();
    }

    public UserCryptoOperationHistoryDTO create(UserCryptoOperationHistoryDTO userCryptoOperationHistoryDTO) {
        return modelMapper.map(userCryptoOperationHistoryRepository.save(modelMapper.map(userCryptoOperationHistoryDTO, UserCryptoOperationHistory.class)), UserCryptoOperationHistoryDTO.class);
    }

    @Transactional
    public void deleteByUserIdAndCryptoId(Long userId, Long cryptoId) {
        userCryptoOperationHistoryRepository.deleteByUserIdAndCryptoId(userId, cryptoId);
    }
}
