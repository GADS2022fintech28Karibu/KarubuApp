package com.pauljuma.karibuapp.data

import com.pauljuma.karibuapp.R

data class FavoriteMealsItems1(
    var imageId: Int,
    var imageName: String
)

object Favorites{
    val cityImage = listOf(
        R.drawable.one, R.drawable.two,
        R.drawable.three, R.drawable.four,
        R.drawable.five, R.drawable.six,
        R.drawable.seven, R.drawable.eight,
        R.drawable.nine, R.drawable.ten,
        R.drawable.eleven, R.drawable.twelve,
        R.drawable.thirteen
    )

    var favoriteMealName = listOf(
        "Galata Bistro", "Burger", "Paneer Manchurian",
        "Pancake", "Fried Chicken", "Shack's Steak",
        "Chicken Fingers", "Number Eight", "Number Nine",
        "Number Ten", "Number Eleven", " Number Twelve", "Number Thirteen"
    )
}
