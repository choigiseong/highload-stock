package com.coco.order

import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderService(
    private val sellingStockProvider: SellingStockProvider,
    private val productRepository: ProductRepository,
    private val orderRepository: OrderRepository
) {
    fun placeOrder(productId: Long, quantity: Int): Long {
        sellingStockProvider.decrease(productId, quantity)

        // todo 주문 저장이 여기서 되어야할까?
        val product = productRepository.findById(productId)
            .orElseThrow { IllegalArgumentException("존재하지 않는 상품: $productId") }

        val order = Order(orderNumber = UUID.randomUUID().toString()).apply {
            addItem(OrderItem(order = this, product = product, quantity = quantity))
        }

        //todo kafka 발행은 commit 뒤? 장애 대응 필요.
        return orderRepository.save(order).id!!
    }
}
