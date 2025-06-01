package com.coco.order

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(
    private val orderRepository: OrderRepository,
) {
    fun existsByOrderNumber(orderNumber: String): Boolean {
        return orderRepository.existsByOrderNumber(orderNumber)
    }

    @Transactional
    fun save(event: OrderPlacedEvent) {
        val order = Order(orderNumber = event.orderKey).apply {
            addItem(
                OrderItem(
                    order = this,
                    productId = event.productId,
                    productName = event.productName,
                    productPrice = event.productPrice,
                    quantity = event.quantity
                )
            )
        }
        orderRepository.save(order)
    }

}