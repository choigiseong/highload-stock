package com.coco.order.controller

import com.coco.order.service.OrderService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderApiController(
    private val orderService: OrderService
) {

    @PostMapping
    fun order() {
        // todo order service
    }
}