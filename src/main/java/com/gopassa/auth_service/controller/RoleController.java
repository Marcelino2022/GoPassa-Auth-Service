package com.gopassa.auth_service.controller;

import com.gopassa.auth_service.application.dto.entitiesDTO.RoleDTO;
import com.gopassa.auth_service.application.dto.requestDTO.creationDTO.CreateRoleDTO;
import com.gopassa.auth_service.application.dto.requestDTO.updateDTO.UpdateRoleDTO;
import com.gopassa.auth_service.application.services.RoleService;
import com.gopassa.auth_service.domain.model.entities.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("roles")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> findAll() {
        List<Role> roles =  roleService.findAll();
        return ResponseEntity.ok(RoleDTO.createList(roles));
    }

    @PostMapping
    public ResponseEntity<RoleDTO> create(@RequestBody CreateRoleDTO createRoleDTO ) {
        Role role = roleService.create(createRoleDTO);
        return ResponseEntity.created(URI.create("roles/" + role.getId()))
                .body(RoleDTO.create(role));
    }

    @GetMapping("{id}")
    public ResponseEntity<RoleDTO> findById(@PathVariable("id") String id) {
        Role role = roleService.findById(id);
        return ResponseEntity.ok(RoleDTO.create(role));
    }

    @PutMapping("{id}")
    public ResponseEntity<RoleDTO> update(@PathVariable("id") String id, @RequestBody UpdateRoleDTO updateRoleDTO ) {
        Role role = roleService.update(id, updateRoleDTO);
        return ResponseEntity.ok(RoleDTO.create(role));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
