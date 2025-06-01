package com.coco.order

import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long> {
    fun existsByOrderNumber(orderNumber: String): Boolean
}