package com.mostafan3ma.android.imagedownloadertrain99

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.mostafan3ma.android.imagedownloadertrain99.databinding.ImageItemBinding

class ImagesAdapter(private val longClickListener: DeleteListener) :
    ListAdapter<Image, ImageViewHolder>(ImageDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageItem=getItem(position)
        holder.itemView.setOnLongClickListener {
            longClickListener.onLongClick(imageItem,it)
            return@setOnLongClickListener true
        }
        holder.bind(imageItem)

    }


}

class ImageDiffCallBack : DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }


}

class ImageViewHolder(private val binding: ImageItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Image) {
        binding.imageName.text = item.name
        binding.image.setImageBitmap(item.image)
    }
    companion object{
        fun from(parent: ViewGroup):ImageViewHolder{
            val layoutInflater=LayoutInflater.from(parent.context)
            val binding=ImageItemBinding.inflate(layoutInflater,parent,false)
            return ImageViewHolder(binding)
        }
    }

}

class DeleteListener(val longListener: (image: Image, view: View) -> Unit) {
    fun onLongClick(image: Image, view: View) = longListener(image, view)
}
