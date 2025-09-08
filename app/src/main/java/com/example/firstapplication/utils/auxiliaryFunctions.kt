package com.example.firstapplication.utils

import android.icu.text.ListFormatter.Width
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.firstapplication.R

@Composable
fun Width(width:Int){ Spacer(Modifier.width(width.dp)) }

@Composable
fun Height(height:Int) { Spacer(Modifier.height(height.dp)) }

@Composable
fun WidthLCream(width:Int) {
    Spacer(
        Modifier
        .width(width.dp)
        .background(colorResource(R.color.light_cream)))
}

@Composable
fun HeightLCream(height:Int) {
    Spacer(
        Modifier
        .height(height.dp)
        .background(colorResource(R.color.light_cream)))
}

