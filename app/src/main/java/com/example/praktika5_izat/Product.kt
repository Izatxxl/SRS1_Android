package com.example.praktika5_izat

import androidx.annotation.DrawableRes



data class Product(
    @DrawableRes val image: Int,
    val name: String,
    val price: String,
    val description: String
)

