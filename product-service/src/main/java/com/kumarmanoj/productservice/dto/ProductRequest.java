package com.kumarmanoj.productservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class ProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
}
