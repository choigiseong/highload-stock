package com.coco.stock.service

import com.coco.stock.kafka.OrderPlacedEvent
import com.coco.stock.persistence.repository.SellingStockRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service

@Service
class SellingStockService(
    private val sellingStockRepository: SellingStockRepository,
) {


    // todo update가 아닌 delta로 하고, 스케줄링으로 재고를 맞출 수 있다. (성능개선)
    fun decreaseSellingStockForOrder(event: OrderPlacedEvent) {
        val affectedRows = sellingStockRepository.decreaseStockByProductIdAndQuantity(event.productId, event.quantity)

        if (affectedRows == 0) {
            throw IllegalStateException("상품 [${event.productId}]의 RDB 판매 가능 재고 차감 실패: 재고 부족 또는 상품 정보 없음.")
        }
    }
}