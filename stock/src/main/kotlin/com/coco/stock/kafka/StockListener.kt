package com.coco.stock.kafka

import com.coco.stock.service.ProcessedEventService
import com.coco.stock.service.SellingStockService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class StockListener(
    private val sellingStockService: SellingStockService,
    private val processedEventService: ProcessedEventService
) {

    @KafkaListener(
        topics = ["\${kafka.order-topic}"],
        groupId = "\${kafka.order-topic}-stockservice",
    )
    fun handleOrderPlacedEvent(event: OrderPlacedEvent) {
        if (processedEventService.existsByProcessedKey(event.orderKey)) {
            return
        }
        processedEventService.execute(event.orderKey) {
            sellingStockService.decreaseSellingStockForOrder(event)
        }
    }
}