package com.coco.order.service

import com.coco.order.controller.view.OrderRequest
import com.coco.order.persistence.model.Order
import com.coco.order.persistence.model.OrderItem
import com.coco.order.persistence.repository.OrderRepository
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
    fun save(event: OrderRequest) {
        //오히려 여기서 redis 서비스를 호출.
        // redis는 성공했지만, timeout의 경우는?
//        val order = Order(orderNumber = event.orderKey).apply {
//            addItem(
//                OrderItem(
//                    order = this,
//                    productId = event.productId,
//                    productName = event.productName,
//                    productPrice = event.productPrice,
//                    quantity = event.quantity
//                )
//            )
//        }
//        orderRepository.save(order)
        // 주문 완료 이벤트 발행?
    }

}