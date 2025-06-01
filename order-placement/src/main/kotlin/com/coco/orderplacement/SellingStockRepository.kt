package com.coco.orderplacement

import org.springframework.data.jpa.repository.JpaRepository

interface SellingStockRepository : JpaRepository<SellingStock, Long> {

    fun findByProductId(productId: Long): SellingStock?

}
