package com.test.cryptoPriceTrackingAndPortfolio.service;

import com.test.cryptoPriceTrackingAndPortfolio.dto.CreateUserRequest;
import com.test.cryptoPriceTrackingAndPortfolio.dto.UpdateUserRequest;
import com.test.cryptoPriceTrackingAndPortfolio.dto.UserDTO;
import com.test.cryptoPriceTrackingAndPortfolio.dto.UserResponseDTO;
import com.test.cryptoPriceTrackingAndPortfolio.model.User;
import com.test.cryptoPriceTrackingAndPortfolio.repository.UserRepository;
import com.test.cryptoPriceTrackingAndPortfolio.utils.ModelMapperUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserResponseDTO createUser(CreateUserRequest createUserRequest) {
        User entity = User.builder()
                .name(createUserRequest.getName())
                .surname(createUserRequest.getSurname())
                .username(createUserRequest.getUsername())
                .email(createUserRequest.getEmail())
                .password(passwordEncoder.encode(createUserRequest.getPassword()))
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .isEnabled(true)
                .accountNonLocked(true)
                .build();

        return modelMapper.map(userRepository.save(entity), UserResponseDTO.class);
    }

    @Override
    public UserDTO loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("user"));

        return modelMapper.map(user, UserDTO.class);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, UserResponseDTO.class))
                .toList();
    }

    public UserResponseDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(entity -> modelMapper.map(entity, UserResponseDTO.class))
                .orElseThrow(() -> new EntityNotFoundException("user"));
    }

    public UserResponseDTO updateUser(UpdateUserRequest updateUserRequest) {
        User entity = userRepository.findById(updateUserRequest.getId())
                .orElseThrow(() -> new EntityNotFoundException("user"));

        ModelMapperUtils.copyNonNullFields(updateUserRequest, entity);

        return modelMapper.map(userRepository.save(entity), UserResponseDTO.class);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
