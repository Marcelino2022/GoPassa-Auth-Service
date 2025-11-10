package com.gopassa.auth_service.application.services;

import com.gopassa.auth_service.application.dto.requestDTO.creationDTO.CreateRoleDTO;
import com.gopassa.auth_service.application.dto.requestDTO.updateDTO.UpdateRoleDTO;
import com.gopassa.auth_service.domain.model.entities.Role;
import com.gopassa.auth_service.domain.repository.RoleRepository;
import com.gopassa.auth_service.presentation.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@SuppressWarnings("null")
public class RoleService {
    private final RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findById(String id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role with ID " + id + "not found"));
    }

    @Transactional
    public Role create(CreateRoleDTO createRoleDTO) {

        Role role = Role.builder()
                .designation(createRoleDTO.getDesignation())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return roleRepository.save(role);
    }

    @Transactional
    public Role update(String id, UpdateRoleDTO updateRoleDTO) {
        Role role = findById(id);
        role.setDesignation(updateRoleDTO.getDesignation());
        role.setUpdatedAt(LocalDateTime.now());
        return role;
    }

    @Transactional
    public void delete(String id) {
        Role role = findById(id);
        roleRepository.delete(role);
    }

}
