package com.hivetech.ine2.htine2.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageDTO<T> {
    private List<T> data;
    private int totalPages;
    private long totalItems;
    private int limit;
    private int currentPage;
}
