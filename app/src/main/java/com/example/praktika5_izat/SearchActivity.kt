package com.example.praktika5_izat

import android.R
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.praktika5_izat.ui.theme.Praktika5_IzatTheme

class SearchActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var query by remember { mutableStateOf("") }
            val filtered = getProducts().filter { it.name.contains(query, ignoreCase = true) }

            Praktika5_IzatTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Поиск", color = Color.White) },
                            navigationIcon = {
                                IconButton(onClick = { finish() }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_delete),
                                        contentDescription = "Назад",
                                        tint = Color.White
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF37474F))
                        )
                    }
                ) { padding ->
                    Column(Modifier.padding(padding).padding(16.dp)) {
                        OutlinedTextField(
                            value = query,
                            onValueChange = { query = it },
                            label = { Text("Введите название") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        LazyColumn {
                            items(filtered) { product ->
                                SearchItem(product)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchItem(product: Product) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable {
                val intent = Intent(context, DetailsActivity::class.java).apply {
                    putExtra("image", product.image)
                    putExtra("name", product.name)
                    putExtra("price", product.price)
                    putExtra("description", product.description)
                }
                context.startActivity(intent)
            }
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(product.name, style = MaterialTheme.typography.titleMedium)
            Text(product.price, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)

        }
    }
}

