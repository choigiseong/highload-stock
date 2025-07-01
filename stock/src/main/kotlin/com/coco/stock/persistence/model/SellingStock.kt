package com.coco.stock.persistence.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "selling_stock")
class SellingStock(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val productId: Long,

    @Column(nullable = false)
    val stock: Int,

    @Column(name = "expired_at")
    val expiredAt: LocalDateTime?
) {
}
