package com.coco.stock
import com.coco.stock.persistence.model.Product
import com.coco.stock.persistence.model.Stock
import com.coco.stock.persistence.repository.ProductRepository
import com.coco.stock.persistence.repository.StockRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.time.LocalDateTime
import kotlin.test.assertEquals

@DataJpaTest
@Testcontainers
class ProductRepositoryTest @Autowired constructor(
	val productRepository: ProductRepository,
	val stockRepository: StockRepository
) {

	companion object {
		@Container
		val mysql: MySQLContainer<*> = MySQLContainer("mysql:8.0").apply {
			withDatabaseName("testdb")
			withUsername("test")
			withPassword("test")
		}

		@JvmStatic
		@DynamicPropertySource
		fun overrideProperties(registry: DynamicPropertyRegistry) {
			registry.add("spring.datasource.url") { mysql.jdbcUrl }
			registry.add("spring.datasource.username") { mysql.username }
			registry.add("spring.datasource.password") { mysql.password }
		}
	}

	@Test
	fun `상품과 재고를 저장하고 조회할 수 있다`() {
		// given
		val product = Product(name = "대파")
		product.addStock(Stock(product = product, stock = 100, expiredAt = LocalDateTime.of(2025, 6, 30, 0, 0)))
		product.addStock(Stock(product = product, stock = 50, expiredAt = LocalDateTime.of(2025, 7, 1, 0, 0)))

		productRepository.save(product)

		// when
		val loaded = productRepository.findWithStocksById(product.id!!)!!

		// then
		assertEquals("대파", loaded.name)
		assertEquals(2, loaded.stocks.size)
	}

}
