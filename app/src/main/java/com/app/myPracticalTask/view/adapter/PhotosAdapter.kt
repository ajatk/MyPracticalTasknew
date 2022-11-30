package com.app.myPracticalTask.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.app.myPracticalTask.R
import com.app.myPracticalTask.databinding.LayoutAlbumPhotosRowBinding
import com.app.myPracticalTask.databinding.LayoutAlbumRowBinding
import com.app.myPracticalTask.view.pojo.PojoAlbum
import com.app.myPracticalTask.view.pojo.PojoPhotos
import com.app.myPracticalTask.view.pojo.PojoPhotosItem

class PhotosAdapter(
    val requireActivity: FragmentActivity
) :RecyclerView.Adapter<PhotosAdapterViewHolder>() {

    private var pojoPhotos: ArrayList<PojoPhotosItem>?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosAdapterViewHolder {
        val view : LayoutAlbumPhotosRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.layout_album_photos_row, parent,false)
        return PhotosAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotosAdapterViewHolder, position: Int) {
       with(holder.binding){
           pojoPhotos!![position].let {
               ivImage.load(it.url?:""){
                  placeholder(R.drawable.ic_launcher_background)
             }
          // fromAlbumPhotos.invoke()

       }
       }

    }

    override fun getItemCount(): Int {
         return if(pojoPhotos!=null) pojoPhotos!!.size else 0
    }

    fun updateList(data: ArrayList<PojoPhotosItem>? ) {
      this.pojoPhotos =data

      notifyDataSetChanged()
    }

}

class PhotosAdapterViewHolder(val binding: LayoutAlbumPhotosRowBinding) :RecyclerView.ViewHolder(binding.root) {

}
