package com.coco.order.controller.view

data class OrderRequest(
    val productId: Long,
    val quantity: Int,
    // val productSku: String? = null, // SKU 등 추가 정보가 필요하다면 여기에 포함
    // val productImageUrl: String? = null
)

