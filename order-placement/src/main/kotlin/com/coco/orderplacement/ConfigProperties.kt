package com.coco.orderplacement

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ConfigProperties {

    @Bean
    @ConfigurationProperties(prefix = "redis")
    fun redisProperties() = RedisProperties()

    @Bean
    @ConfigurationProperties(prefix = "kafka")
    fun kafkaProperties() = KafkaProperties()

    class RedisProperties {
        lateinit var host: String
        lateinit var port: String
        lateinit var sellingStockKey: String
        lateinit var ordersKey: String
    }

    class KafkaProperties {
        lateinit var orderTopic: String
    }
}