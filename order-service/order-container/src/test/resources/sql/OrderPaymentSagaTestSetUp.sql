insert into "order".orders(id, customer_id, restaurant_id, tracking_id, price, order_status, failure_messages)
values('f215b5f8-0249-4dc5-89a3-51fd148cfb41', 'd215b5f8-0249-4dc5-89a3-51fd148cfb41', 'd215b5f8-0249-4dc5-89a3-51fd148cfb45',
'f6a16ff7-4a31-11eb-be7b-8344edc8f36b', 100.00, 'PENDING', '');

insert into "order".order_items(id, order_id, product_id, price, quantity, sub_total)
values(300, 'f215b5f8-0249-4dc5-89a3-51fd148cfb41', 'f215b5f8-0249-4dc5-89a3-51fd148cfb41', 100.00, 1, 100.00);


insert into "order".order_address(id, order_id, street, postal_code, city)
values('e7ab9ae8-2863-11ed-a261-0242ac120002', 'f215b5f8-0249-4dc5-89a3-51fd148cfb41', 'Lungo Dora', '1000C', 'Turin');

insert into "order".payment_outbox(id, saga_id, created_at, type, payload, outbox_status, saga_status, order_status, version)
values('0743bd0e-2864-11ed-a261-0242ac120002', '6a63ca94-2866-11ed-a261-0242ac120002', current_timestamp, 'OrderProcessingSaga',
'{"price": 100, "orderId": "f215b5f8-0249-4dc5-89a3-51fd148cfb41", "createdAt": "2022-08-02T12:32:12.829202+01:00",
"customerId": "d215b5f8-0249-4dc5-89a3-51fd148cfb41", "paymentOrderStatus": "PENDING"}',
'STARTED', 'STARTED', 'PENDING', 0)