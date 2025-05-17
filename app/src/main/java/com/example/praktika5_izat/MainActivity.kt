package com.example.praktika5_izat

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.praktika5_izat.ui.theme.Praktika5_IzatTheme // если у тебя своя тема — поправь

data class Product(
    val image: Int,
    val name: String,
    val price: String,
    val description: String
)

val products = listOf(
    Product(R.drawable.__75, "Poco C75", "$500", "Бюджетный смартфон с хорошей камерой."),
    Product(R.drawable.msi, "MSI Gaming laptop 2017", "$1399", "Игровой ноутбук для современных игр."),
    Product(R.drawable.nissan_gtr_r_35, "Nissan GTR R35", "$78000", "Легендарный японский спорткар."),
    Product(R.drawable.iphone_15, "iPhone 15 Pro Max", "$1200", "Новый флагман Apple с топовой камерой."),
    Product(R.drawable.elitehouse, "Элитная квартира", "$25000", "Современное жильё в центре города."),
    Product(R.drawable.nissan_skyline_gtr_r36, "Nissan Skyline R36", "$120000", "Новое поколение Skyline.")
)

class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Praktika5_IzatTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Каталог товаров", color = Color.White) },
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF37474F))
                        )
                    },
                    containerColor = Color(0xFFECEFF1)
                ) { paddingValues ->
                    ProductList(Modifier.padding(paddingValues))
                }
            }
        }
    }
}

@Composable
fun ProductList(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.padding(16.dp)) {
        items(products) { product ->
            ProductCard(product = product)
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clickable {
                val intent = Intent(context, DetailsActivity::class.java).apply {
                    putExtra("image", product.image)
                    putExtra("name", product.name)
                    putExtra("price", product.price)
                    putExtra("description", product.description)
                }
                context.startActivity(intent)
            },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = product.image),
                contentDescription = product.name,
                modifier = Modifier.size(90.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(product.name, style = MaterialTheme.typography.titleMedium, color = Color(0xFF263238))
                Text(product.price, style = MaterialTheme.typography.bodyMedium, color = Color(0xFF607D8B))
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    Praktika5_IzatTheme {
        ProductList()
    }
}
