package com.test.cryptoPriceTrackingAndPortfolio.service;

import com.test.cryptoPriceTrackingAndPortfolio.model.OperationHistory;
import com.test.cryptoPriceTrackingAndPortfolio.dto.OperationHistoryDTO;
import com.test.cryptoPriceTrackingAndPortfolio.repository.OperationHistoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OperationHistoryService {

    private final OperationHistoryRepository operationHistoryRepository;
    private final ModelMapper modelMapper;

    public List<OperationHistoryDTO> getAll() {
        return operationHistoryRepository.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, OperationHistoryDTO.class))
                .toList();
    }

    public OperationHistoryDTO create(OperationHistoryDTO operationHistoryDTO) {
        return modelMapper.map(operationHistoryRepository.save(modelMapper.map(operationHistoryDTO, OperationHistory.class)), OperationHistoryDTO.class);
    }

    @Transactional
    public void deleteByUserIdAndCryptoId(Long userId, Long cryptoId) {
        operationHistoryRepository.deleteByUserIdAndCryptoId(userId, cryptoId);
    }
}
