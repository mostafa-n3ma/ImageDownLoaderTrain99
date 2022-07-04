package com.mostafan3ma.android.imagedownloadertrain99

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.mostafan3ma.android.imagedownloadertrain99.MyImageManger.Companion.deleteImage
import com.mostafan3ma.android.imagedownloadertrain99.MyImageManger.Companion.getImageFromInternalStorage
import com.mostafan3ma.android.imagedownloadertrain99.databinding.ActivityGalleryBinding
import java.io.File

class galleryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGalleryBinding
    private lateinit var imagesAdapter: ImagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val imagesList: MutableList<Image>? = getImagesFromStorage()
        imagesAdapter= ImagesAdapter(DeleteListener{ selectedimage, view->
            deleteImage(this,selectedimage.name)
            imagesList?.remove(selectedimage)
            imagesAdapter.notifyDataSetChanged()
        })
        imagesAdapter.submitList(imagesList)
        binding.imageRec.adapter=imagesAdapter

    }

    private fun getImagesFromStorage(): MutableList<Image> {
        var imagesList= mutableListOf<Image>()
        val dir=this.filesDir
        val imagesFile=File(dir, IMAGES_FILE)
        if (!imagesFile.exists()){
            imagesFile.mkdir()
        }
        for (file in imagesFile.listFiles()!!){
            val name=file.name
            val imageBit=getImageFromInternalStorage(this,name)
            imagesList.add(Image(name,imageBit!!))
        }

        return imagesList
    }
}