package com.taufikhidayat.ceritakan.ui.common.utils

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat


fun Int.asDrawable(context: Context): Drawable? = ContextCompat.getDrawable(context, this)
fun Int.asColorStateList(context: Context): ColorStateList? = ContextCompat.getColorStateList(context, this)
fun Int.asColor(context: Context): Int = ContextCompat.getColor(context, this)