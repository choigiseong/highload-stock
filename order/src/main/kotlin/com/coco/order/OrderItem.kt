package com.coco.order

import jakarta.persistence.*


@Entity
@Table(name = "order_item") // 'order_item'은 SQL 예약어가 아니므로 괜찮습니다.
class OrderItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    val order: Order,

    @Column(nullable = false)
    val productId: Long,

    @Column(nullable = false)
    val productName: String,

    @Column(nullable = false)
    val productPrice: Long,

    @Column(nullable = false)
    val quantity: Int
)