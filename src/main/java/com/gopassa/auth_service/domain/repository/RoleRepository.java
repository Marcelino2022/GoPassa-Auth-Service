package com.gopassa.auth_service.domain.repository;

import com.gopassa.auth_service.domain.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface RoleRepository extends JpaRepository<Role, String> {
}
