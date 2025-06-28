package com.coco.stock.model


import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "stock_processed_events")
class ProcessedEvent(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    val processedKey: String,
) {
    @Column(nullable = false)
    val createdAt: Instant? = null
}