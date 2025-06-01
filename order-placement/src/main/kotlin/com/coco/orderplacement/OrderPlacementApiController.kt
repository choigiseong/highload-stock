package com.coco.orderplacement

import java.time.Instant

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/orders") // API의 기본 경로 설정
class OrderPlacementApiController(
    private val orderPlacementService: OrderPlacementService
) {
    data class PlaceOrderRequest(
        val productId: Long,
        val quantity: Int,
        val userId: Long
    )

    @PostMapping("/place") // POST /api/orders/place 엔드포인트
    fun placeOrder(@RequestBody request: PlaceOrderRequest): ResponseEntity<Map<String, Any>> {
        try {
            val now = Instant.now()
            // 요청에서 orderKey가 제공되지 않으면 새로 생성
            val actualOrderKey = generateUniqueOrderKey(request.userId, now)

            // 핵심 비즈니스 로직 호출
            orderPlacementService.placeOrder(
                request.productId,
                request.quantity,
                actualOrderKey,
                now
            )
            // 성공 응답 (성공 메시지와 실제 사용된 orderKey 반환)
            return ResponseEntity.ok(mapOf(
                "message" to "주문 요청이 성공적으로 처리되었습니다.",
                "orderKey" to actualOrderKey
            ))
        } catch (e: IllegalStateException) {
            // 재고 부족 등 비즈니스 로직상의 예외 처리
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf("error" to e.message.toString()))
        } catch (e: Exception) {
            // 기타 예상치 못한 오류 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapOf("error" to "예상치 못한 오류가 발생했습니다: ${e.message}"))
        }
    }

    // 테스트용 고유 주문 키 생성 헬퍼 함수
    private fun generateUniqueOrderKey(userId: Long, now: Instant): String {
        return userId.toString() + now.toString()
    }
}