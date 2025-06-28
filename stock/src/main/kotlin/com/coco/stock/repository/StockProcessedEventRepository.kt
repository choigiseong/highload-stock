package com.coco.stock.repository

import com.coco.stock.model.StockProcessedEvent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StockProcessedEventRepository : JpaRepository<StockProcessedEvent, Long> {
    fun existsByOrderKey(orderKey: String): Boolean
}