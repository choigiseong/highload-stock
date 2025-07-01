package com.coco.order.persistence.repository

import com.coco.order.persistence.model.OrderItem
import org.springframework.data.jpa.repository.JpaRepository

interface OrderItemRepository : JpaRepository<OrderItem, Long>