package com.coco.orderplacement.persistence.repository

import com.coco.orderplacement.persistence.model.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {

}
