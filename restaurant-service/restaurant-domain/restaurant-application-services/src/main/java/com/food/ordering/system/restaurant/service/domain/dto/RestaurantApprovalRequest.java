package com.food.ordering.system.restaurant.service.domain.dto;

import com.food.ordering.system.restaurant.service.domain.entity.Product;
import com.food.ordering.system.valueobject.RestaurantApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class RestaurantApprovalRequest {

    private String id;
    private String sagaId;
    private String restaurantId;
    private String orderId;
    private RestaurantApprovalStatus restaurantApprovalStatus;
    private List<Product> products;
    private BigDecimal price;
    private Instant createdAt;
}
