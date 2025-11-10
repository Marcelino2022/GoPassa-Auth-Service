package com.gopassa.auth_service.application.services;

import com.gopassa.auth_service.application.dto.requestDTO.creationDTO.CreateUserRoleTDO;
import com.gopassa.auth_service.application.dto.requestDTO.updateDTO.UpdateUserRoleTDO;
import com.gopassa.auth_service.domain.model.entities.Role;
import com.gopassa.auth_service.domain.model.entities.User;
import com.gopassa.auth_service.domain.model.entities.UserRole;
import com.gopassa.auth_service.domain.repository.RoleRepository;
import com.gopassa.auth_service.domain.repository.UserRepository;
import com.gopassa.auth_service.domain.repository.UserRoleRepository;
import com.gopassa.auth_service.presentation.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@SuppressWarnings("null")
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<UserRole> findAll(){
        return userRoleRepository.findAll();
    }

    @Transactional
    public UserRole create(CreateUserRoleTDO createUserRoleTDO){

        User user = userRepository.findById(createUserRoleTDO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + createUserRoleTDO.getUserId()));

        Role role = roleRepository.findById(createUserRoleTDO.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found: " + createUserRoleTDO.getRoleId()));


        UserRole userRole = UserRole
                .builder()
                .user(user)
                .role(role)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userRoleRepository.save(userRole);
        return userRole;
    }

    public UserRole findById(String id){
        return userRoleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role with ID" + id + "not found"));
    }

    public UserRole update(String id, UpdateUserRoleTDO updateUserRoleTDO){

        Role role = roleRepository.findById(updateUserRoleTDO.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found: " + updateUserRoleTDO.getRoleId()));

        UserRole userRole = findById(id);
        userRole.setRole(role);
        userRole.setUpdatedAt(LocalDateTime.now());
        return userRole;
    }

    public void delete(String id){
        UserRole userRole = findById(id);
        userRoleRepository.delete(userRole);
    }
}
