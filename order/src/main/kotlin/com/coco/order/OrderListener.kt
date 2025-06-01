package com.coco.order

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class OrderListener(
    private val orderService: OrderService
) {

    @KafkaListener(
        topics = ["\${kafka.order-topic}"],
        groupId = "\${kafka.order-topic}-orderservice"
    )
    fun handleOrderPlacedEvent(event: OrderPlacedEvent) {
        if (orderService.existsByOrderNumber(event.orderKey)) {
            return
        }
        orderService.save(event)
    }

}