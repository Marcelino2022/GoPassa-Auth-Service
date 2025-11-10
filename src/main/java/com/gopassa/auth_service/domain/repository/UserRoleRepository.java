package com.gopassa.auth_service.domain.repository;

import com.gopassa.auth_service.domain.model.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {

}
