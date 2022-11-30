package com.app.myPracticalTask


import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.myPracticalTask.databinding.ActivityMainPlaceBinding
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity2_places : AppCompatActivity() {

    private var viewBinding:ActivityMainPlaceBinding?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewBinding = ActivityMainPlaceBinding.inflate(layoutInflater)
        viewBinding = DataBindingUtil.setContentView(this,R.layout.activity_main_place)

        val apiKey =  "AIzaSyAaUEPA0CJUBNFnEoXQh_AQnyyG1ItKJhc"//"AIzaSyCKDJ2rRB4vWWasJscbo21sc_arURaAEbU"
        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, apiKey)
        }
        println("kdfskdjhfksdjkfjsdkl")
        // Create a new Places client instance.

        // Create a new Places client instance.
        val placesClient = Places.createClient(this)
        // Initialize the AutocompleteSupportFragment.
        // Initialize the AutocompleteSupportFragment.
        val autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.place_autocomplete_fragment)
                    as AutocompleteSupportFragment?
//        autocompleteFragment.view.findViewById(R.id.place_autocomplete_search_input)).setTextSize(10.0f);
        autocompleteFragment?.view?.findViewById<EditText>(com.google.android.libraries.places.R.id.places_autocomplete_search_input)?.layoutParams?.width  =100
           // LinearLayout.LayoutParams(0, 0)
        autocompleteFragment?.view?.layoutParams = LinearLayout.LayoutParams(100, 0)
      // autocompleteFragment?.view?.layout(100,0,100,0)
        autocompleteFragment!!.setTypeFilter(TypeFilter.CITIES)
        autocompleteFragment!!.setPlaceFields(
            listOf(
                Place.Field.ID,
                Place.Field.NAME
            )
        )
        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                // TODO: Get info about the selected place.
                Toast.makeText(applicationContext, place.name, Toast.LENGTH_SHORT).show()
            }

            override fun onError(status: Status) {
                // TODO: Handle the error.
                Toast.makeText(applicationContext, status.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }




    /* fun setData(): PojoAlbum {
    return pojoAlbum!!
    }
    fun setPhotoData(): PojoPhotos {
    return pojoPhotos!!
    }*/

   /* fun homepage() {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.frame_main, AlbumFragment())
        //fragment.arguments = bundle
         ft.addToBackStack(null)
        ft.commit()


    }*/
}