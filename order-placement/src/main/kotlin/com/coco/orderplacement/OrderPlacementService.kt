package com.coco.orderplacement

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class OrderPlacementService(
    private val sellingStockProvider: SellingStockProvider,
    private val eventPublisher: ApplicationEventPublisher
) {
    fun placeOrder(productId: Long, quantity: Int, orderKey: String) {
        sellingStockProvider.decrease(productId, quantity, orderKey)
        //todo 이 사이 죽었을 경우 대응 필요. 아마 복구 전략
        eventPublisher.publishEvent(
            OrderPlacedEvent(
                orderKey, productId, quantity
            )
        )
    }
}
