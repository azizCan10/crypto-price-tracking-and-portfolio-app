package com.test.cryptoPriceTrackingAndPortfolio.service;

import com.test.cryptoPriceTrackingAndPortfolio.dto.IdDTO;
import com.test.cryptoPriceTrackingAndPortfolio.dto.UserCryptoTrackDTO;
import com.test.cryptoPriceTrackingAndPortfolio.dto.UserDTO;
import com.test.cryptoPriceTrackingAndPortfolio.model.UserCryptoTrack;
import com.test.cryptoPriceTrackingAndPortfolio.repository.UserCryptoTrackRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserCryptoTrackService {

    private final UserCryptoTrackRepository userCryptoTrackRepository;
    private final CryptoService cryptoService;
    private final ModelMapper modelMapper;

    public UserCryptoTrackDTO create(UserDTO user, UserCryptoTrackDTO userCryptoTrackDTO) {
        userCryptoTrackDTO.setUser(new IdDTO(user.getId()));
        userCryptoTrackDTO.setCrypto(cryptoService.createOrGetCryptoIfExists(userCryptoTrackDTO.getCrypto()));
        return modelMapper.map(userCryptoTrackRepository.save(modelMapper.map(userCryptoTrackDTO, UserCryptoTrack.class)), UserCryptoTrackDTO.class);
    }

    @Transactional
    public void deleteByCryptoId(UserDTO user, Long cryptoId) {
        userCryptoTrackRepository.deleteByUserIdAndCryptoId(user.getId(), cryptoId);
    }
}
