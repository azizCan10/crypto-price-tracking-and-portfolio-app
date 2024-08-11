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

    /**
     * This method gets the data from db according to logged user id
     *
     * @param id
     * @return
     */
    public List<OperationHistoryDTO> getAllByUserId(Long id) {
        return operationHistoryRepository.findAllByOperationHistoryUser_Id(id)
                .stream()
                .map(entity -> modelMapper.map(entity, OperationHistoryDTO.class))
                .toList();
    }

    /**
     * This method saves operation history entity to db
     *
     * @param operationHistoryDTO request dto
     * @return saved entity
     */
    public OperationHistoryDTO create(OperationHistoryDTO operationHistoryDTO) {
        return modelMapper.map(operationHistoryRepository.save(modelMapper.map(operationHistoryDTO, OperationHistory.class)), OperationHistoryDTO.class);
    }

    /**
     * This method deletes data from db according to logged user id and crypto id
     *
     * @param userId
     * @param cryptoId
     */
    @Transactional
    public void deleteByUserIdAndCryptoId(Long userId, Long cryptoId) {
        operationHistoryRepository.deleteByUserIdAndCryptoId(userId, cryptoId);
    }
}
