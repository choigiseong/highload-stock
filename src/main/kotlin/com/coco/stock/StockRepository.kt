package com.coco.stock

import org.springframework.data.jpa.repository.JpaRepository

interface StockRepository : JpaRepository<Stock, Long> {

    fun findByProductId(productId: Long): List<Stock>

    fun findByProductIdOrderByExpiredAtAsc(productId: Long): List<Stock>
}
