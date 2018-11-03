package com.example.zzz89.image_search_application.picture

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Environment
import com.example.zzz89.image_search_application.R
import java.io.File
import java.io.FileOutputStream

class PictureManager(context: Context, drawable: Drawable) {
    var context: Context
    val drawable: Drawable

    init{
        this.context = context
        this.drawable = drawable
    }

    fun saveImage(filename: String): File{
        val galleryPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString()
        val appFile = makeDirectory(galleryPath)
        val result = save(appFile, filename)

        return result
    }

    fun makeDirectory(path: String): File{
        val file: File = File(path + "/" + context.getString(R.string.app_name))
        if(!file.exists())
            file.mkdirs()
        return file
    }

    private fun save(dirFile: File, filename: String): File {
        var file: File = File(dirFile, filename)
        val pictureBitmap = (drawable as BitmapDrawable).bitmap
        val outStream = FileOutputStream(file)
        pictureBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream)
        outStream.flush()
        outStream.close()
        return file
    }
}