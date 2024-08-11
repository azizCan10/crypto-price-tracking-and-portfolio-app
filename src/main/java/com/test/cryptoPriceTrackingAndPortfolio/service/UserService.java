package com.test.cryptoPriceTrackingAndPortfolio.service;

import com.test.cryptoPriceTrackingAndPortfolio.dto.CreateUserRequest;
import com.test.cryptoPriceTrackingAndPortfolio.dto.UpdateUserRequest;
import com.test.cryptoPriceTrackingAndPortfolio.dto.UserDTO;
import com.test.cryptoPriceTrackingAndPortfolio.dto.UserResponseDTO;
import com.test.cryptoPriceTrackingAndPortfolio.enums.Role;
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
    private final MailService mailService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    /**
     * This method saves new user to db and sends welcome mail to registered user
     *
     * @param createUserRequest request dto
     * @return saved data
     */
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

        UserResponseDTO result = modelMapper.map(userRepository.save(entity), UserResponseDTO.class);
        mailService.sendWelcomeMail(result.getEmail());

        return result;
    }

    /**
     * This method gets user data according to username
     *
     * @param username given username
     * @return user data
     * @throws UsernameNotFoundException if there is no data with given username, throws Exception
     */
    @Override
    public UserDTO loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("user"));

        return modelMapper.map(user, UserDTO.class);
    }

    /**
     * This method gets all users from db
     *
     * @return user list
     */
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, UserResponseDTO.class))
                .toList();
    }

    /**
     * This method gets user data according to id
     *
     * @param id user id
     * @return user data
     */
    public UserResponseDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(entity -> modelMapper.map(entity, UserResponseDTO.class))
                .orElseThrow(() -> new EntityNotFoundException("user"));
    }

    /**
     * This method updates user
     *
     * @param updateUserRequest request dto
     * @return updated user
     */
    public UserResponseDTO updateUser(UpdateUserRequest updateUserRequest) {
        User entity = userRepository.findById(updateUserRequest.getId())
                .orElseThrow(() -> new EntityNotFoundException("user"));

        if (updateUserRequest.getPassword() != null) {
            updateUserRequest.setPassword(passwordEncoder.encode(updateUserRequest.getPassword()));
        }

        ModelMapperUtils.copyNonNullFields(updateUserRequest, entity);

        return modelMapper.map(userRepository.save(entity), UserResponseDTO.class);
    }

    /**
     * This method deletes user from db according to id
     *
     * @param id user id
     */
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * This method deletes all users whose role is user
     */
    public void deleteAllUsers() {
        userRepository.deleteAll(userRepository.findByAuthorities(Role.ROLE_USER));
    }
}
