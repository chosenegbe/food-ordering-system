package com.food.ordering.system.restaurant.service.domain.event;

import com.food.ordering.system.event.publisher.DomainEventPublisher;
import com.food.ordering.system.restaurant.service.domain.entity.OrderApproval;
import com.food.ordering.system.valueobject.RestaurantId;

import java.time.ZonedDateTime;
import java.util.List;

public class OrderRejectedEvent extends OrderApprovalEvent{

    private final DomainEventPublisher<OrderRejectedEvent> orderRejectedDomainEventPublisher;

    public OrderRejectedEvent(OrderApproval orderApproval,
                              RestaurantId restaurantId,
                              List<String> failureMessages,
                              ZonedDateTime createdAt,
                              DomainEventPublisher<OrderRejectedEvent> orderRejectedDomainEventPublisher) {
        super(orderApproval, restaurantId, failureMessages, createdAt);
        this.orderRejectedDomainEventPublisher = orderRejectedDomainEventPublisher;
    }

    @Override
    public void fire() {
        orderRejectedDomainEventPublisher.publish(this);
    }
}
