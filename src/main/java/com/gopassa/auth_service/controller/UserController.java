package com.gopassa.auth_service.controller;

import com.gopassa.auth_service.application.dto.entitiesDTO.UserDTO;
import com.gopassa.auth_service.application.dto.requestDTO.creationDTO.CreateUserDTO;
import com.gopassa.auth_service.application.dto.requestDTO.updateDTO.UpdateUSerDTO;
import com.gopassa.auth_service.application.services.UserService;
import com.gopassa.auth_service.domain.model.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> users = userService.findAll();
        List<UserDTO> dtos = users.stream()
                .map(UserDTO::create)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody CreateUserDTO createUserDTO) {
        User user = userService.create(createUserDTO);
        return ResponseEntity.created(URI.create("users/" + user.getId()))
                .body(UserDTO.create(user));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable("id") String id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(UserDTO.create(user));
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDTO> update(@PathVariable("id") String id, @RequestBody UpdateUSerDTO updateUSerDTO) {
        User user = userService.update(id, updateUSerDTO);
        return ResponseEntity.ok(UserDTO.create(user));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
