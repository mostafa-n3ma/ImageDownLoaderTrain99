package com.mostafan3ma.android.imagedownloadertrain99

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.mostafan3ma.android.imagedownloadertrain99.MyImageManger.Companion.saveImageToInternalStorage
import com.mostafan3ma.android.imagedownloadertrain99.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import okhttp3.internal.notify

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.downloadBtn.setOnClickListener {
            if (!valetedFields()) {
                return@setOnClickListener
            }
            val url = binding.urlEt.text.toString()
            val name = binding.imageNameEt.text.toString()

            lifecycleScope.launch {
                val bitmap = getBitmap(url)
                saveImageToInternalStorage(this@MainActivity, bitmap, name)
            }

        }

        binding.gallaryBtn.setOnClickListener {
            Toast.makeText(this, "go to gallery", Toast.LENGTH_LONG).show()
            startActivity(Intent(this,galleryActivity::class.java))
        }


    }

    private fun valetedFields(): Boolean {
        return when {
            binding.urlEt.text?.trim()!!.isEmpty() -> {
                Toast.makeText(this, "please enter url to download", Toast.LENGTH_LONG).show()
                false
            }
            binding.imageNameEt.text.trim().isEmpty() -> {
                Toast.makeText(this, "please enter a name to save image with", Toast.LENGTH_LONG)
                    .show()
                false
            }
            else -> {
                true
            }
        }
    }

    private suspend fun getBitmap(url: String): Bitmap {
        val loading: ImageLoader = ImageLoader(this)
        val request = ImageRequest.Builder(this)
            .data(url)
            .build()

            val result: Drawable = (loading.execute(request) as SuccessResult).drawable
            return (result as BitmapDrawable).bitmap
    }
}