package com.hivetech.ine2.htine2.repository;

import com.hivetech.ine2.htine2.model.storage.entity.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Integer> {
    @Query("SELECT m FROM Merchant m " +
            "WHERE CONCAT(m.contactName, ' ', m.contactEmail, ' ', m.contactPhone) LIKE %:keyword% " +
            "AND m.status IN :merchantStatus")
    Page<Merchant> findAll(@Param("keyword") String keyword,
                           @Param("merchantStatus") List<String> merchantStatus,
                           Pageable pageable);
}
