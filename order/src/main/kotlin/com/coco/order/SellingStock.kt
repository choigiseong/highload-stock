package com.coco.order

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "selling_stock")
class SellingStock(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    var product: Product,

    @Column(nullable = false)
    val stock: Int,

    @Column(name = "expired_at")
    val expiredAt: LocalDateTime?
) {
}
