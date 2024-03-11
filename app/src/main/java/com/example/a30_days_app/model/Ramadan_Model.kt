package com.example.a30_days_app.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Ramadan_Model(
    @StringRes val dayNumberId: Int,
    @StringRes val quoteId: Int,
    @DrawableRes val imageId: Int,
    @StringRes val descriptionId: Int,
)
