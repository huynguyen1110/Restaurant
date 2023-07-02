package com.hivetech.ine2.htine2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEntityDto<T> {
    String message;
    T object;

    public ResponseEntityDto(String mess) {
        message = mess;
    }

    public ResponseEntityDto(T obj) {
        object = obj;
    }

}
