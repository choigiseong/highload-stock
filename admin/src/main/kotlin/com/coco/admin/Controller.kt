package com.coco.admin

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class Controller {
    @GetMapping("/place-order")
    fun showPlaceOrderForm(): String {
        return "placeOrder"
    }
}