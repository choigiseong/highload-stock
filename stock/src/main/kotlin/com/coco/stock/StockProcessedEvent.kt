package com.coco.stock


import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "stock_processed_events")
class StockProcessedEvent(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    val orderKey: String,

    @Column(nullable = false)
    val processedAt: Instant,

    @Column(nullable = false)
    val eventType: String,

    @Column(nullable = false)
    val eventPayload: String
)