package com.coco.orderplacement.kafka

import java.time.Instant

data class StockEvent(
    val orderKey: String,
    val productId: Long,
    val quantity: Int,
    val placedAt: Instant
)
