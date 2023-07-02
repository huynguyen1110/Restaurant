package com.hivetech.ine2.htine2.service;

import com.hivetech.ine2.htine2.dto.MerchantDTO;
import com.hivetech.ine2.htine2.dto.PageDTO;
import com.hivetech.ine2.htine2.model.storage.entity.Merchant;
import com.hivetech.ine2.htine2.repository.MerchantRepository;
import com.hivetech.ine2.htine2.util.enumuration.MerchantStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class MerchantService {
    @Autowired
    private MerchantRepository merchantRepository;

    public PageDTO<MerchantDTO> getAllMerchant(int currentPage, int limit, String sortBy, String sortDirection, String keyword, MerchantStatus status) {
        List<String> merchantStatus = new ArrayList<>();
        if (status == null) {
            merchantStatus = Stream.of(MerchantStatus.values())
                    .map(MerchantStatus::getDescription)
                    .collect(Collectors.toList());
        } else {
            merchantStatus.add(status.getDescription());
        }
        Sort.Direction direction = Sort.Direction.DESC.toString().equals(sortDirection) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(currentPage - 1, limit, sort);
        Page<Merchant> merchantPage = merchantRepository.findAll(keyword, merchantStatus, pageable);
        List<Merchant> merchants = merchantPage.getContent();
        List<MerchantDTO> data = new ArrayList<>();
        for (Merchant merchant : merchants) {
            MerchantDTO dto = MerchantDTO.builder()
                    .id(merchant.getId())
                    .about(merchant.getAbout())
                    .restaurantName(merchant.getRestaurantName())
                    .restaurantSlug(merchant.getRestaurantSlug())
                    .contactName(merchant.getContactName())
                    .contactEmail(merchant.getContactEmail())
                    .contactPhone(merchant.getContactPhone())
                    .address(merchant.getAddress())
                    .status(merchant.getStatus())
                    .idHeader(merchant.getIdHeader())
                    .idLogo(merchant.getIdLogo())
                    .build();
            data.add(dto);
        }
        return new PageDTO(data, merchantPage.getTotalPages(), merchantPage.getTotalElements(), limit, currentPage);
    }

}
