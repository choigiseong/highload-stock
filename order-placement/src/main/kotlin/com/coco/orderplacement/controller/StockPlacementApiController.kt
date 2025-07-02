package com.coco.orderplacement.controller

import com.coco.orderplacement.service.OrderPlacementService
import java.time.Instant

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/orders") 
class StockPlacementApiController(
    private val orderPlacementService: OrderPlacementService
) {
    data class PlaceOrderRequest(
        val orderKey: String,
        val productId: Long,
        val quantity: Int,
        val userId: Long
    )

    @PostMapping("/place") 
    fun placeStock(@RequestBody request: PlaceOrderRequest): ResponseEntity<Map<String, Any>> {
        try {
            val now = Instant.now()

            orderPlacementService.placeOrder(
                request.productId,
                request.quantity,
                request.orderKey,
                now
            )
            return ResponseEntity.ok(mapOf(
                "message" to "주문 요청이 성공적으로 처리되었습니다.",
                "orderKey" to request.orderKey
            ))
        } catch (e: IllegalStateException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf("error" to e.message.toString()))
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapOf("error" to "예상치 못한 오류가 발생했습니다: ${e.message}"))
        }
    }

}