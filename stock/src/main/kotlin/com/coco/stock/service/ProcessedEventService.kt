package com.coco.stock.service

import com.coco.stock.model.ProcessedEvent
import com.coco.stock.repository.ProcessedEventRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ProcessedEventService(
    private val processedEventRepository: ProcessedEventRepository
) {

    fun existsByProcessedKey(existsByProcessedKey: String): Boolean {
        return processedEventRepository.existsByProcessedKey(existsByProcessedKey)
    }

    @Transactional
    fun <T> execute(processedKey: String, function: () -> T): T {
        val processedEvent = ProcessedEvent(
            processedKey = processedKey,
        )
        processedEventRepository.save(processedEvent)
        return function()
    }
}