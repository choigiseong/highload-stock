package com.coco.orderplacement.service

import com.coco.orderplacement.kafka.StockEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class OrderPlacementService(
    private val sellingStockProvider: SellingStockProvider,
    private val eventPublisher: ApplicationEventPublisher,
) {
    fun
            placeOrder(productId: Long, quantity: Int, orderKey: String, orderPlacedAt: Instant) {
        sellingStockProvider.decrease(productId, quantity, orderKey)
        //todo 이 사이 죽었을 경우 대응 필요. 아마 복구 전략
        eventPublisher.publishEvent(StockEvent(orderKey, productId, quantity, orderPlacedAt))
    }
}
