package com.hivetech.ine2.htine2.repository;

import com.hivetech.ine2.htine2.model.storage.entity.ERole;
import com.hivetech.ine2.htine2.model.storage.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    
    Optional<Role> findByName(ERole name);
}
