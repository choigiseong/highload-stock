package com.coco.orderplacement.service

import com.coco.orderplacement.kafka.OrderPlacedEvent
import com.coco.orderplacement.persistence.repository.ProductRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class OrderPlacementService(
    private val sellingStockProvider: SellingStockProvider,
    private val eventPublisher: ApplicationEventPublisher,
    private val productRepository: ProductRepository
) {
    fun placeOrder(productId: Long, quantity: Int, orderKey: String, orderPlacedAt: Instant) {
        // db 조회를 해야할까? 다른 방법은?
        val product = productRepository.findById(productId)
            .orElseThrow {
                IllegalArgumentException("존재하지 않는 상품: $productId")
            }

        sellingStockProvider.decrease(productId, quantity, orderKey)
        //todo 이 사이 죽었을 경우 대응 필요. 아마 복구 전략
        eventPublisher.publishEvent(
            OrderPlacedEvent(
                orderKey = orderKey,
                productId = productId,
                quantity = quantity,
                productName = product.name,
                productPrice = product.price,
                placedAt = orderPlacedAt
            )
        )
    }
}
