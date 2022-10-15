package com.github.robuspixelart404

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView

@SuppressLint("AppCompatCustomView")
class CustomTextView(context: Context, index: Int): TextView(context) {
    val index: Int = index
        get() = field
}