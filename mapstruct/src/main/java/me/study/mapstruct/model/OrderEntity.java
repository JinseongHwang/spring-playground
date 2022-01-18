package me.study.mapstruct.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
//@Builder
@EqualsAndHashCode
public class OrderEntity {

    private Long id; // DTO에는 없음

    private String name;

    private String product;

    private Integer price;

    private String address;

    private LocalDateTime orderedTime;
}
