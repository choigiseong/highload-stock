package com.coco.order

import org.springframework.stereotype.Component
import org.springframework.data.redis.core.RedisTemplate
import java.time.Duration

@Component
class AvailableStockProvider(
    private val configProperties: ConfigProperties,
    private val redisTemplate: RedisTemplate<String, String>,
    private val sellingStockRepository: SellingStockRepository
) {

    fun get(productId: Long): Int {
        val sellingStockKey = configProperties.redisProperties().sellingStockKey
        val key = "$sellingStockKey:$productId"
        val cached = redisTemplate.opsForValue().get(key)
        if (cached != null) return cached.toInt()

        //todo 핫키 업데이트 방지 필요
        val selling = sellingStockRepository.findByProductId(productId)?.stock
            ?: throw IllegalStateException("selling_stock 정보 없음")

        redisTemplate.opsForValue().set(key, selling.toString(), Duration.ofMinutes(5))
        return selling
    }
}
