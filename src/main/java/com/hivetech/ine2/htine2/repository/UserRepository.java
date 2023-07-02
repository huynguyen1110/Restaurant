package com.hivetech.ine2.htine2.repository;

import com.hivetech.ine2.htine2.model.storage.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUserName(String userName);
    boolean existsByUserName(String username);
    User findUsersByUserName(String userName);

    @Query(value = "SELECT * FROM Users  where user_name LIKE CONCAT('%',:username,'%') OR  email LIKE CONCAT('%',:username,'%')",nativeQuery = true)
    Page<User> findUsers(@Param("username") String username, Pageable pageable);

}
