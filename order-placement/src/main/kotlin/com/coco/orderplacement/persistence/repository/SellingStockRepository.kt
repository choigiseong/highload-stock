package com.coco.orderplacement.persistence.repository

import com.coco.orderplacement.persistence.model.SellingStock
import org.springframework.data.jpa.repository.JpaRepository

interface SellingStockRepository : JpaRepository<SellingStock, Long> {

    fun findByProductId(productId: Long): SellingStock?

}
