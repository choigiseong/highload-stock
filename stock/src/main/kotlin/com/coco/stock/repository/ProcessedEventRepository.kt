package com.coco.stock.repository

import com.coco.stock.model.ProcessedEvent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProcessedEventRepository : JpaRepository<ProcessedEvent, Long> {
    fun existsByProcessedKey(processedKey: String): Boolean
}