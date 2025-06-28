package com.coco.stock.kafka

import com.coco.stock.kafka.OrderPlacedEvent
import com.coco.stock.service.SellingStockService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class StockListener(
    private val sellingStockService: SellingStockService,
) {

    @KafkaListener(
        topics = ["\${kafka.order-topic}"],
        groupId = "\${kafka.order-topic}-stockservice",
    )
    fun handleOrderPlacedEvent(event: OrderPlacedEvent) {
        if (sellingStockService.existsByOrderKey(event.orderKey)) {
            return
        }
        sellingStockService.decreaseSellingStockForOrder(event)
    }
}