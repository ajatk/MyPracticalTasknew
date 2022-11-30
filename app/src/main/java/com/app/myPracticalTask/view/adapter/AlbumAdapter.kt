package com.app.myPracticalTask.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.myPracticalTask.R
import com.app.myPracticalTask.databinding.LayoutAlbumRowBinding
import com.app.myPracticalTask.view.pojo.PojoAlbum
import com.app.myPracticalTask.view.pojo.PojoPhotos
import com.app.myPracticalTask.view.pojo.PojoPhotosItem

class AlbumAdapter(
    val requireActivity: FragmentActivity,
    val fromAlbumPhotos: (str: String, id: Int) -> Unit
) :RecyclerView.Adapter<AlbumAdapterViewHolder>() {

    private var pojoAlbum: PojoAlbum?=null
    private var pojoPhotos: Map<Int?, List<PojoPhotosItem>>?=null
    private var pojoPhotos2:   ArrayList<PojoPhotosItem>?=null
    private var pojoPhotos3: PojoPhotos?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumAdapterViewHolder {
        val view : LayoutAlbumRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.layout_album_row, parent,false)
        return AlbumAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumAdapterViewHolder, position: Int) {
       with(holder.binding){
           pojoAlbum!![position].let {
               tvTitle.text = it.title?:""
              // fromAlbumPhotos.invoke()
              rvAlbum.adapter =photosAdapter
           //  val map =   pojoPhotos!!.groupBy { it.albumId?:"" }
//               for((k,v) in map){
//                   if(k.equals(it.id))
//                   pojoPhotos2?.addAll(v)
//               }
               pojoPhotos?.entries?.let { its->
//                   for (i in 0 until its.size) {
//                     if((it.id?:0).equals(its.))
//                   }
                   its.forEach { (key, value) ->
                       if((it.id?:0).equals(key))
                           pojoPhotos2?.addAll(value)
                   }
               }
               Log.e("TAG", "onBindViewHolder: ${pojoPhotos2?.size}", )
               Log.e("TAG", "onBindViewHolder: ${pojoPhotos2?.size}", )
               photosAdapter.updateList(pojoPhotos2)
       }
       }

    }

    override fun getItemCount(): Int {
         return if(pojoAlbum!=null)  pojoAlbum!!.size else 0
    }

    fun updateList(data: PojoAlbum?, pojoPhotos: Map<Int?, List<PojoPhotosItem>>) {
      this.pojoAlbum =data
      this.pojoPhotos =pojoPhotos
      notifyDataSetChanged()
    }
 val photosAdapter by lazy {
     PhotosAdapter(requireActivity)
 }
}

class AlbumAdapterViewHolder(val binding: LayoutAlbumRowBinding) :RecyclerView.ViewHolder(binding.root) {

}
