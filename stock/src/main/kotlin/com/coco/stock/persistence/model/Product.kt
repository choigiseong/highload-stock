package com.coco.stock.persistence.model

import com.coco.stock.persistence.model.Stock
import jakarta.persistence.*

@Entity
@Table(name = "product")
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(nullable = false)
    val name: String,
    @OneToMany(
        mappedBy = "product",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
    )
    val stocks: MutableList<Stock> = mutableListOf(),
) {

    fun addStock(stock: Stock) {
        this.stocks.add(stock)
        stock.product = this
    }
}
