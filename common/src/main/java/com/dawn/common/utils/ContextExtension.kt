package com.dawn.common.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

fun Context.createPngImageFile(name: String, path: String = ""): File =
    File(dataDir, "$path/$name.png")

suspend inline fun Context.saveImageFile(
    image: Uri,
    outputFile: File,
    crossinline onSuccess: () -> Unit,
    crossinline onError: (Exception) -> Unit,
) {
    withContext(Dispatchers.IO){
        try {
            val bitmap = when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.P -> {
                    ImageDecoder.createSource(this@saveImageFile.contentResolver, image).let { source ->
                        ImageDecoder.decodeBitmap(source) { decoder, _, _ ->
                            decoder.allocator = ImageDecoder.ALLOCATOR_SOFTWARE
                            decoder.isMutableRequired = true
                        }
                    }
                }

                else -> this@saveImageFile.contentResolver.openInputStream(image)
                    ?.use { inputStream -> BitmapFactory.decodeStream(inputStream) }
            } ?: return@withContext

            FileOutputStream(outputFile).use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                outputStream.close()
            }

            bitmap.recycle()
            onSuccess()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            onError(e)
        }
    }
}