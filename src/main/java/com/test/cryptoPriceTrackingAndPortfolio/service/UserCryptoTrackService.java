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

    /**
     * This method saves request data to db
     *
     * @param user               logged user
     * @param userCryptoTrackDTO request dto
     * @return saved data
     */
    public UserCryptoTrackDTO create(UserDTO user, UserCryptoTrackDTO userCryptoTrackDTO) {
        userCryptoTrackDTO.setUser(new IdDTO(user.getId()));
        userCryptoTrackDTO.setCrypto(cryptoService.createOrGetCryptoIfExists(userCryptoTrackDTO.getCrypto()));
        return modelMapper.map(userCryptoTrackRepository.save(modelMapper.map(userCryptoTrackDTO, UserCryptoTrack.class)), UserCryptoTrackDTO.class);
    }

    /**
     * This method deletes data from db according to logged user id and crypto id
     *
     * @param user     logged user
     * @param cryptoId given crypto id
     */
    @Transactional
    public void deleteByCryptoId(UserDTO user, Long cryptoId) {
        userCryptoTrackRepository.deleteByUserIdAndCryptoId(user.getId(), cryptoId);
    }
}
