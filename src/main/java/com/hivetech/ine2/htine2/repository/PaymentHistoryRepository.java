package com.hivetech.ine2.htine2.repository;

import com.hivetech.ine2.htine2.model.storage.entity.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Integer> {
}
