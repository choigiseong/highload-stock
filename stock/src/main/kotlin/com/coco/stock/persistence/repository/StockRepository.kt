package com.coco.stock.persistence.repository

import com.coco.stock.persistence.model.Stock
import org.springframework.data.jpa.repository.JpaRepository

interface StockRepository : JpaRepository<Stock, Long> {

    fun findByProductId(productId: Long): List<Stock>

    fun findByProductIdOrderByExpiredAtAsc(productId: Long): List<Stock>
}
