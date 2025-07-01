package com.coco.order.persistence.repository

import com.coco.order.persistence.model.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long> {
    fun existsByOrderNumber(orderNumber: String): Boolean
}