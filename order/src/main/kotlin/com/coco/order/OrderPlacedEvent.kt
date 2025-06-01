package com.coco.order

data class OrderPlacedEvent(
    val orderKey: String,
    val productId: Long,
    val quantity: Int,
    val productName: String,
    val productPrice: Long
    // val productSku: String? = null, // SKU 등 추가 정보가 필요하다면 여기에 포함
    // val productImageUrl: String? = null
)

