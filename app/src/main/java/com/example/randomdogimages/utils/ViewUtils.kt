package com.example.randomdogimages.utils

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.palette.graphics.Palette
import java.io.IOException
import java.net.URL

object ViewUtils {

    // Generate palette synchronously and return it
    fun createPaletteSync(bitmap: Bitmap): Palette = Palette.from(bitmap).generate()

    fun URL.toBitmap(): Bitmap? {
        return try {
            BitmapFactory.decodeStream(openStream())
        } catch (e: IOException) {
            null
        }
    }
}