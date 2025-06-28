package com.coco.stock.repository

import com.coco.stock.model.SellingStock
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface SellingStockRepository : JpaRepository<SellingStock, Long> {

    @Modifying // UPDATE, DELETE 쿼리에 필요
    @Query("UPDATE SellingStock ss SET ss.stock = ss.stock - :quantity WHERE ss.productId = :productId AND ss.stock >= :quantity")
    fun decreaseStockByProductIdAndQuantity(productId: Long, quantity: Int): Int // affected rows 반환
}
