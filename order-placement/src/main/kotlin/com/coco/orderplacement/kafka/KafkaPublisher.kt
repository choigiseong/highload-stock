package com.coco.orderplacement.kafka

import com.coco.orderplacement.config.ConfigProperties
import org.springframework.context.event.EventListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaPublisher(
    private val configProperties: ConfigProperties,
    private val kafkaTemplate: KafkaTemplate<String, OrderPlacedEvent> // 실제 KafkaTemplate
) {

    @EventListener
    fun handleOrderPlacedEvent(event: OrderPlacedEvent) {
        kafkaTemplate.send(configProperties.kafkaProperties().orderTopic, event.orderKey, event)
            .whenComplete() { result, ex ->
                if (ex != null) {
                    // 프로듀서 dlt, db 저장
                }
            }
    }
}