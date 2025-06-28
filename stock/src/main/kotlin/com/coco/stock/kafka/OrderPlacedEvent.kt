package com.coco.stock.kafka

import java.time.Instant

data class OrderPlacedEvent(
    val orderKey: String,
    val productId: Long,
    val quantity: Int,
    val productName: String,
    val productPrice: Long,
    val placedAt: Instant
    // val productSku: String? = null, // SKU 등 추가 정보가 필요하다면 여기에 포함
    // val productImageUrl: String? = null
)
