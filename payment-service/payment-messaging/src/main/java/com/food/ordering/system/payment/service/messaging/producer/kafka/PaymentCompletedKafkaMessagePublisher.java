package com.food.ordering.system.payment.service.messaging.producer.kafka;

import com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel;
import com.food.ordering.system.kafka.producer.service.KafkaMessageHelper;
import com.food.ordering.system.kafka.producer.service.KafkaProducer;
import com.food.ordering.system.payment.service.domain.config.PaymentServiceConfigData;
import com.food.ordering.system.payment.service.domain.event.PaymentCompletedEvent;
import com.food.ordering.system.payment.service.domain.ports.output.message.publisher.PaymentCompletedMessagePublisher;
import com.food.ordering.system.payment.service.messaging.mapper.PaymentMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentCompletedKafkaMessagePublisher implements PaymentCompletedMessagePublisher {

    private final PaymentMessagingDataMapper paymentMessagingDataMapper;
    private final KafkaProducer kafkaProducer;
    private final PaymentServiceConfigData paymentServiceConfigData;
    private final KafkaMessageHelper kafkaMessageHelper;

    public PaymentCompletedKafkaMessagePublisher(PaymentMessagingDataMapper paymentMessagingDataMapper,
                                                 KafkaProducer kafkaProducer,
                                                 PaymentServiceConfigData paymentServiceConfigData,
                                                 KafkaMessageHelper kafkaMessageHelper) {
        this.paymentMessagingDataMapper = paymentMessagingDataMapper;
        this.kafkaProducer = kafkaProducer;
        this.paymentServiceConfigData = paymentServiceConfigData;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    public void publish(PaymentCompletedEvent domainEvent) {
        String orderId = domainEvent.getPayment().getOrderId().getValue().toString();

        log.info("received PaymentCompletedEvent for order id: {}", orderId);

        PaymentResponseAvroModel paymentResponseAvroModel = paymentMessagingDataMapper
                .paymentCompletedEventToPaymentResponseAvroModel(domainEvent);

        kafkaProducer.send(paymentServiceConfigData.getPaymentResponseTopicName(),
                orderId,
                paymentResponseAvroModel,
                kafkaMessageHelper.getKafkaCallback(
                        paymentServiceConfigData.getPaymentResponseTopicName(),
                        paymentResponseAvroModel,
                        orderId,
                        "RestaurantApprovalRequestAvroModel"
                ));
    }
}
