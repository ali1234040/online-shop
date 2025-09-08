package com.example.firstapplication.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.firstapplication.R
import com.example.firstapplication.data.db.ImageFile


fun saveProfileImage(context: Context, bitmap: Bitmap) {
    context.openFileOutput(ImageFile.PROFILE, Context.MODE_PRIVATE).use { out ->
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
    }
}

fun loadProfileImage(context: Context): Bitmap {
    return try {
        context.openFileInput(ImageFile.PROFILE).use { input ->
            BitmapFactory.decodeStream(input)
        }
    } catch (e: Exception) {
        Log.i("TAG", "Exception: ${e.message}")
        getDefaultBitmap(context, R.drawable.img_profile)
    }
}

private fun getDefaultBitmap(context: Context, image: Int): Bitmap {
    return BitmapFactory.decodeResource(context.resources, image)
}
