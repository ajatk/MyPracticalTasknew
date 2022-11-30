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

class AlbumAdapter_aa(
    val requireActivity: FragmentActivity,
    val fromAlbumPhotos: (str: String, id: Int) -> Unit
) :RecyclerView.Adapter<AlbumAdapterViewHoldera>() {

    private var pojoAlbum: PojoPhotos?=null
    private var pojoPhotos: Map<Int?, List<PojoPhotosItem>>?=null
    private var pojoPhotos2:   ArrayList<PojoPhotosItem>?=null
    private var pojoPhotos3: PojoPhotos?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumAdapterViewHoldera {
        val view : LayoutAlbumRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.layout_album_row, parent,false)
        return AlbumAdapterViewHoldera(view)
    }

    override fun onBindViewHolder(holder: AlbumAdapterViewHoldera, position: Int) {
       with(holder.binding){
           pojoAlbum!![position].let {
               tvTitle.text = it.title?:""
              // fromAlbumPhotos.invoke()
              rvAlbum.adapter =photosAdapter
           //  val map =   pojoPhotos!!.groupBy { it.albumId?:"" }
                pojoPhotos?.let {
                   for((k,v) in it){
//                       if(k!!.equals(it.id))
//                           pojoPhotos2?.addAll(v)
                       Log.e("TAG", "onBindViewHolder:00.. $k...$v", )
                   }
               }

              /* pojoPhotos?.entries?.let { its->
                   its.forEach { (key, value) ->
                       if((it.id?:0).equals(key))
                           pojoPhotos2?.addAll(value)
                   }
               }*/
               Log.e("TAG", "onBindViewHolder: ${pojoPhotos2?.size}", )
               Log.e("TAG", "onBindViewHolder: ${pojoPhotos2?.size}", )
               photosAdapter.updateList(pojoPhotos2)
       }
       }

    }

    override fun getItemCount(): Int {
         return if(pojoAlbum!=null)  pojoAlbum!!.size else 0
    }

    fun updateList(data: PojoPhotos?, pojoPhotos: Map<Int?, List<PojoPhotosItem>>) {
      this.pojoAlbum =data
      this.pojoPhotos =pojoPhotos
      notifyDataSetChanged()
    }
 val photosAdapter by lazy {
     PhotosAdapter(requireActivity)
 }
}

class AlbumAdapterViewHoldera(val binding: LayoutAlbumRowBinding) :RecyclerView.ViewHolder(binding.root) {

}
