package com.gopassa.auth_service.controller;

import com.gopassa.auth_service.application.dto.entitiesDTO.UserRoleDTO;
import com.gopassa.auth_service.application.dto.requestDTO.creationDTO.CreateUserRoleTDO;
import com.gopassa.auth_service.application.dto.requestDTO.updateDTO.UpdateUserRoleTDO;
import com.gopassa.auth_service.application.services.UserRoleService;
import com.gopassa.auth_service.domain.model.entities.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user-roles")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class UserRoleController {
    private final UserRoleService userRoleService;

    @GetMapping
    public ResponseEntity<List<UserRoleDTO>> findAll() {
        List<UserRole> userRoles = userRoleService.findAll();
        List<UserRoleDTO> userRolesDtos = userRoles.stream()
                .map(UserRoleDTO::create)
                .toList();
        return ResponseEntity.ok(userRolesDtos);
    }

    @PostMapping
    public ResponseEntity<UserRoleDTO> create(@RequestBody CreateUserRoleTDO createUserRoleDTO) {
        UserRole userRole = userRoleService.create(createUserRoleDTO);
        return ResponseEntity.ok(UserRoleDTO.create(userRole));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserRoleDTO> findById(@PathVariable String id) {
        UserRole userRole = userRoleService.findById(id);
        return ResponseEntity.ok(UserRoleDTO.create(userRole));
    }

    @PutMapping("{id}")
    public ResponseEntity<UserRoleDTO> update(@PathVariable String id,
                                              @RequestBody UpdateUserRoleTDO updateUserRoleTDO) {
        UserRole userRole = userRoleService.update(id, updateUserRoleTDO);
        return ResponseEntity.ok(UserRoleDTO.create(userRole));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userRoleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
