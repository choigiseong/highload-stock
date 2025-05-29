package com.coco.sellingstock

import org.springframework.data.jpa.repository.JpaRepository

interface SellingStockRepository : JpaRepository<SellingStock, Long> {

    fun findByProductId(productId: Long): SellingStock?

}
