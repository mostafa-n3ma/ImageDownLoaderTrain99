package com.mostafan3ma.android.imagedownloadertrain99

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class MyImageManger {
    companion object{
        fun saveImageToInternalStorage(context: Context, bitmap: Bitmap, imagename:String):String{
            val dir=context.filesDir
            val file= File(dir, IMAGES_FILE)
            if (!file.exists()){
                file.mkdir()
            }
            val image= File(file,imagename)
            val fos= FileOutputStream(image)
            bitmap.compress(Bitmap.CompressFormat.PNG,50,fos)

            return context.filesDir.absolutePath
        }

        fun getImageFromInternalStorage(context: Context, imagename: String): Bitmap?{
            val dir=context.filesDir
            val file= File(dir,IMAGES_FILE)
            if (!file.exists()){
                file.mkdir()
            }
            val image= File(file,imagename)
            return BitmapFactory.decodeStream(FileInputStream(image))
        }
        fun deleteImage(context: Context,imageName:String):Boolean{
            var deletedSuccessfully:Boolean=false
            val dir=context.filesDir
            val file=File(dir,IMAGES_FILE)
            if (file.exists())
            {
                val image=File(file,imageName)
                if (image.exists()) {
                    deletedSuccessfully = image.delete()
                }
            }else{
                file.mkdir()
            }

            return deletedSuccessfully

        }

    }



}