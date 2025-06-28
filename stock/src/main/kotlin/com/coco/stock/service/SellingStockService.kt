package com.coco.stock.service

import com.coco.stock.kafka.OrderPlacedEvent
import com.coco.stock.repository.SellingStockRepository
import com.coco.stock.repository.StockProcessedEventRepository
import com.coco.stock.model.StockProcessedEvent
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SellingStockService(
    private val objectMapper: ObjectMapper,
    private val sellingStockRepository: SellingStockRepository,
    private val stockProcessedEventRepository: StockProcessedEventRepository,
) {
    fun existsByOrderKey(orderKey: String): Boolean {
        return stockProcessedEventRepository.existsByOrderKey(orderKey)
    }

    // todo update가 아닌 delta로 하고, 스케줄링으로 재고를 맞출 수 있다. (성능개선)
    @Transactional
    fun decreaseSellingStockForOrder(event: OrderPlacedEvent) {
        val affectedRows = sellingStockRepository.decreaseStockByProductIdAndQuantity(event.productId, event.quantity)

        if (affectedRows == 0) {
            throw IllegalStateException("상품 [${event.productId}]의 RDB 판매 가능 재고 차감 실패: 재고 부족 또는 상품 정보 없음.")
        }

        val eventPayloadJson = objectMapper.writeValueAsString(event)
        val processedEvent = StockProcessedEvent(
            orderKey = event.orderKey,
            eventType = "ORDER_PLACED", // 이벤트 타입 명시, 나중에 취소등등 추가
            eventPayload = eventPayloadJson,
            processedAt = event.placedAt
        )
        stockProcessedEventRepository.save(processedEvent)
    }
}