package com.coco.orderplacement

data class OrderPlacedEvent(
    val orderKey: String,
    val productId: Long,
    val quantity: Int,
)

//productPrice 필드가 추가되면
// 가격 업데이트가 이뤄졌을 때 판단에 도움이 될 수 있음
