package com.app.myPracticalTask.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.app.myPracticalTask.MainActivity
import com.app.myPracticalTask.databinding.FragmentAlbumBinding
import com.app.myPracticalTask.view.adapter.AlbumAdapter
import com.app.myPracticalTask.view.adapter.AlbumAdapter_aa
import com.app.myPracticalTask.view.pojo.*
import com.hubwallet.commons.utils.Utils
import com.hubwallet.utillss.ResultWrapper
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

@AndroidEntryPoint
class AlbumFragment : Fragment() {
    private val viewModel: AlbumViewModel by viewModels()
   private var viewBinding :FragmentAlbumBinding?=null
    private var pojoAlbum: PojoAlbum?=null
    private var pojoPhotos: PojoPhotos?=null
    private var photosList: ArrayList<PhotoWithIdModel>?=null
    private val albumAdapter by lazy {
        AlbumAdapter_aa(requireActivity(),fromAlbumPhotos)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = FragmentAlbumBinding.inflate(layoutInflater)
        val view = viewBinding!!.root
        with(viewBinding!!){
            rvAlbum.adapter = albumAdapter
            albumApi()
        }
        return view
    }
    private fun albumApi() {
        viewModel.AndroidGetAlbumData1( ).observe(viewLifecycleOwner,{
            updateUI(it)
        })
    }
    private fun updateUI(_state: ResultWrapper<Any>?) {
        when (_state) {
            is ResultWrapper.Loading -> {
                viewBinding!!.progressBarB.visibility = View.VISIBLE
            }
            is ResultWrapper.NetworkError -> {
                viewBinding!!.progressBarB.visibility = View.GONE
            }
            is ResultWrapper.GenericError -> {
                viewBinding!!.progressBarB.visibility = View.GONE
                _state.error?.error?.let {
                    Toast.makeText(requireContext(), "err " + it, Toast.LENGTH_SHORT).show()
                }
            }
            is ResultWrapper.Success -> {
                when (_state.data) {
                    is PojoPhotos -> {
                        _state.data.let {
                            pojoPhotos =it
                            setData()
                        }
                        // Toast.makeText(requireActivity(), "${_state.data.message} ", Toast.LENGTH_LONG).show()
                    }
                    is AlbumWithPhotoResponse -> {
                        Utils.ifNotNull(
                            _state.data.pojoAlbum, _state.data.pojoPhotos
                        ) {
                                pojAlbum: Response<PojoAlbum>, pojPhotos: Response<PojoPhotos>,
                            ->
                            pojoAlbum = pojAlbum.body()
                            pojoPhotos = pojPhotos.body()
                            Log.e("TAG", "onBindViewHolder: ${pojoPhotos!!.size}", )
                            setData()
                        }
                    }
                }
                viewBinding!!.progressBarB.visibility = View.GONE
            }
        }
    }

    private fun setData() {
        photosList = arrayListOf<PhotoWithIdModel>()
         val map =   pojoPhotos!!.groupBy { it.albumId }

        pojoPhotos?.let {
            for((k,v) in it){
//                       if(k!!.equals(it.id))
//                       pojoPhotos2?.addAll(v)

                Log.e("TAG", "onBindViewHolder:00.. $k...$v", )
            }
        }

       // Log.e("TAG", "onBindViewHolder: ${map.size}", )
        Log.e("TAG", "onBindViewHolder: ${pojoPhotos!!.size}", )
         albumAdapter.updateList(pojoPhotos, map)
         //albumAdapter.updateList(pojoAlbum, map)
    }

    val  fromAlbumPhotos:(str:String, id:Int)->Unit = {str:String, id:Int ->

    }


}