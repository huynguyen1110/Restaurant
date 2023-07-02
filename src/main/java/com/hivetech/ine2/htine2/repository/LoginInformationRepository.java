package com.hivetech.ine2.htine2.repository;

import com.hivetech.ine2.htine2.model.storage.entity.LoginInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginInformationRepository extends JpaRepository<LoginInformation, Integer> {
}
