package com.coco.stock.service

import com.coco.stock.persistence.repository.ProductRepository
import com.coco.stock.persistence.repository.StockRepository
import com.coco.stock.persistence.model.Product
import com.coco.stock.persistence.model.Stock
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val stockRepository: StockRepository
) {

    // 상품과 그 재고 조회
    @Transactional(readOnly = true)
    fun getProductWithStocks(productId: Long): Product? {
        return productRepository.findWithStocksById(productId)
    }

    // 재고 총합 계산
    @Transactional(readOnly = true)
    fun getTotalStock(productId: Long): Int {
        return stockRepository.findByProductId(productId)
            .sumOf { it.stock }
    }

    // 유통기한 빠른 순 정렬
    @Transactional(readOnly = true)
    fun getSortedStocks(productId: Long): List<Stock> {
        return stockRepository.findByProductIdOrderByExpiredAtAsc(productId)
    }
}
