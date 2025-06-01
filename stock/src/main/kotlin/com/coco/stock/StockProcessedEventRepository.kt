package com.coco.stock

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StockProcessedEventRepository : JpaRepository<StockProcessedEvent, Long> {
    fun existsByOrderKey(orderKey: String): Boolean
}