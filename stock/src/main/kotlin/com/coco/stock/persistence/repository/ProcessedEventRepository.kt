package com.coco.stock.persistence.repository

import com.coco.stock.persistence.model.ProcessedEvent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProcessedEventRepository : JpaRepository<ProcessedEvent, Long> {
    fun existsByProcessedKey(processedKey: String): Boolean
}