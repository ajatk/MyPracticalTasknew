package com.app.myPracticalTask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.app.myPracticalTask.databinding.ActivityMainBinding
import com.app.myPracticalTask.view.AlbumFragment
import com.app.myPracticalTask.view.AlbumViewModel
import com.app.myPracticalTask.view.pojo.AlbumWithPhotoResponse
import com.app.myPracticalTask.view.pojo.PojoAlbum
import com.app.myPracticalTask.view.pojo.PojoPhotos
import com.hubwallet.commons.utils.Utils
import com.hubwallet.utillss.ResultWrapper
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var viewBinding:ActivityMainBinding?=null
    private var pojoAlbum: PojoAlbum?=null
    private var pojoPhotos: PojoPhotos?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        // homepage()
        println("kdfskdjhfksdjkfjsdkl000000000")
    }




    /* fun setData(): PojoAlbum {
    return pojoAlbum!!
    }
    fun setPhotoData(): PojoPhotos {
    return pojoPhotos!!
    }*/

    /*fun homepage() {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.frame_main, AlbumFragment())
        //fragment.arguments = bundle
         ft.addToBackStack(null)
        ft.commit()


    }*/
}