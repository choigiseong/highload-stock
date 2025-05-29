package com.coco.order

import org.springframework.stereotype.Component
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.script.DefaultRedisScript
import java.time.Duration

@Component
class SellingStockProvider(
    private val configProperties: ConfigProperties,
    private val redisTemplate: StringRedisTemplate,
    private val sellingStockRepository: SellingStockRepository
) {

    fun get(productId: Long): Int {
        val key = getKey(productId)
        val cached = redisTemplate.opsForValue().get(key)
        if (cached != null) return cached.toInt()

        val selling = sellingStockRepository.findByProductId(productId)?.stock
            ?: throw IllegalStateException("selling_stock 정보 없음")

        redisTemplate.opsForValue().set(key, selling.toString(), Duration.ofMinutes(5))
        return selling
    }

    fun decrease(productId: Long, quantity: Int): Boolean {
        val key = getKey(productId)
        get(productId) // 캐시 미존재 시 초기화

        val result = redisTemplate.execute(
            DefaultRedisScript(DECR_LUA, Long::class.java),
            listOf(key),
            quantity.toString()
        )

        return when (result) {
            -1L -> throw IllegalStateException("재고 부족: productId=$productId, 요청 수량=$quantity")
            -2L -> throw IllegalStateException("Redis 캐시 누락 또는 초기화 실패: productId=$productId")
            else -> true
        }
    }

    private fun getKey(productId: Long): String {
        val prefix = configProperties.redisProperties().sellingStockKey
        return "$prefix:$productId"
    }

    companion object {
        private val DECR_LUA = """
            local current = redis.call("GET", KEYS[1])
            if not current then return -2 end
            if tonumber(current) < tonumber(ARGV[1]) then return -1 end
            return redis.call("DECRBY", KEYS[1], ARGV[1])
        """.trimIndent()
    }
}
