package com.coco.order

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val sellingStockRepository: SellingStockRepository
) {

    // 상품 등록
    @Transactional
    fun registerProduct(name: String): Product {
        val product = Product(name = name)
        return productRepository.save(product)
    }


}
