package com.edwardoboh.productservicebcommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {
    @Id
    private ObjectId id;
    private String name;
    private String description;
    private BigDecimal price;
}
