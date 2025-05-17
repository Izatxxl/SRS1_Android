import com.example.praktika5_izat.R

data class Product(
    val image: Int,
    val name: String,
    val price: String
)

val products = listOf(
    Product(R.drawable.podbor_tovara1, "Телефон", "$500"),
    Product(R.drawable.podbor_tovara1, "Ноутбук", "$1000"),
    Product(R.drawable.podbor_tovara1, "Машина", "$20000")
)

