package com.coco.stock

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val stockRepository: StockRepository
) {

    // 상품 등록
    @Transactional
    fun registerProduct(name: String): Product {
        val product = Product(name = name)
        return productRepository.save(product)
    }

    // 상품별 재고 등록
    @Transactional
    fun addStock(productId: Long, quantity: Int, expiredAt: LocalDateTime?): Stock {
        val product = productRepository.findById(productId)
            .orElseThrow { IllegalArgumentException("상품을 찾을 수 없습니다: $productId") }

        val stock = Stock(product = product, stock = quantity, expiredAt = expiredAt)
        return stockRepository.save(stock)
    }

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
