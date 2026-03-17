package com.example.grid.model



import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic (
    @StringRes val stringTopicId: Int,
    val number: Int,
    @DrawableRes val imageTopicId: Int,
)