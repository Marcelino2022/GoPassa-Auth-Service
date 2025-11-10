package com.gopassa.auth_service.application.services;

import com.gopassa.auth_service.application.dto.requestDTO.creationDTO.CreateUserDTO;
import com.gopassa.auth_service.application.dto.requestDTO.creationDTO.CreateUserRoleTDO;
import com.gopassa.auth_service.application.dto.requestDTO.updateDTO.UpdateUSerDTO;
import com.gopassa.auth_service.domain.model.entities.User;
import com.gopassa.auth_service.domain.model.enums.UserStatus;
import com.gopassa.auth_service.domain.repository.UserRepository;
import com.gopassa.auth_service.presentation.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;

    public  List<User> findAll(){
        return userRepository.findAll();
    }


    @Transactional
    public User create(CreateUserDTO createUserDTO) {

        String tenantId = createUserDTO.getTenantId();
        if(tenantId == null){
            tenantId = UUID.randomUUID().toString();
        }

        User user = User
                .builder()
                .tenantId(tenantId)
                .firstName(createUserDTO.getFirstName())
                .lastName(createUserDTO.getLastName())
                .email(createUserDTO.getEmail())
                .mobileNumber(createUserDTO.getMobileNumber())
                .userType(createUserDTO.getUserType())
                .userStatus(UserStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userRepository.save(user);

        for (String roles : createUserDTO.getRoles()) {
            CreateUserRoleTDO createUserRoleTDO = new CreateUserRoleTDO(user.getId(), roles);
            userRoleService.create(createUserRoleTDO);   
        }

        return user;
    }


    public  User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User by ID " + id + "not found"));
    }

    @Transactional
    public  User update(String id, UpdateUSerDTO updateUSerDTO) {
        User user = findById(id);
        user.setFirstName(updateUSerDTO.getFirstName());
        user.setLastName(updateUSerDTO.getLastName());
        user.setEmail(updateUSerDTO.getEmail());
        user.setMobileNumber(updateUSerDTO.getMobileNumber());
        user.setUserType(updateUSerDTO.getUserType());
        user.setUserStatus(convertToUserStatus(String.valueOf(updateUSerDTO.getUserStatus())));
        user.setUpdatedAt(LocalDateTime.now());
        return user;
    }


    @Transactional
    public  void delete(String id) {
        User user = findById(id);
        userRepository.delete(user);
    }

    private UserStatus convertToUserStatus(String statusStr){
        try {
            return UserStatus.valueOf(statusStr);
        } catch (IllegalArgumentException | NullPointerException e){
            throw new InvalidParameterException("Invalid user status");
        }
    }


    public Optional<User> getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
}
