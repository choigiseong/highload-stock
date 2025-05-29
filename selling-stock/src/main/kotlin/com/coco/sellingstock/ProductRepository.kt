package com.coco.sellingstock

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ProductRepository : JpaRepository<Product, Long> {

    fun findByName(name: String): Product?

    @Query("SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.stocks WHERE p.id = :id")
    fun findWithStocksById(@Param("id") id: Long): Product?
}
