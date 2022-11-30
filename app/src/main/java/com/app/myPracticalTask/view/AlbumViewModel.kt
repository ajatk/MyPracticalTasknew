package com.app.myPracticalTask.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.myPracticalTask.view.di.AlbumRepository
import com.app.myPracticalTask.view.pojo.*
import com.hubwallet.commons.utils.Utils
import com.hubwallet.utillss.ErrorResponse
import com.hubwallet.utillss.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.FileNotFoundException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(val repository: AlbumRepository) : ViewModel() {

     private var liveDataAlbumData1 = MutableLiveData<ResultWrapper<PojoPhotos>>()
    fun AndroidGetAlbumData1( ) :MutableLiveData<ResultWrapper<PojoPhotos>>{
        viewModelScope.launch (Dispatchers.IO){
            liveDataAlbumData1.postValue(ResultWrapper.Loading)
            try {
                val responsee = repository.photosData1()
                liveDataAlbumData1.postValue(ResultWrapper.Success(responsee))
            }catch (e:Exception){
                e.printStackTrace()
                liveDataAlbumData1.postValue(ResultWrapper.GenericError(
                    ErrorResponse(-1, e.message)
                ))

            }
        }
        return liveDataAlbumData1
    }
    private var liveDataAlbumData = MutableLiveData<ResultWrapper<AlbumWithPhotoResponse>>()
    fun AndroidGetAlbumData( ) :MutableLiveData<ResultWrapper<AlbumWithPhotoResponse>>{
        viewModelScope.launch(){
            liveDataAlbumData.postValue(ResultWrapper.Loading)
            try {
                //val responsee = repository.albumData()
                     repository.photosData()
                    .zip(repository.albumData()){
                            photos,album, ->
                        return@zip (AlbumWithPhotoResponse(
                            pojoAlbum = album,
                            pojoPhotos = photos
                        ))
                    }
                      .flowOn(Dispatchers.IO)
                    .catch { e ->
                        if (e is IOException) liveDataAlbumData.postValue(ResultWrapper.NetworkError)
                        else liveDataAlbumData.postValue(
                            ResultWrapper.GenericError(   ErrorResponse(-1, e.message) ) )
                    }.collect {
                             Utils.ifNotNull(it.pojoAlbum, it.pojoPhotos){
                                      pojoAlbum: Response<PojoAlbum>,
                                     pojoPhotos: Response<PojoPhotos> ->
                                 if( pojoAlbum.isSuccessful() &&  pojoPhotos.isSuccessful()){
                                     liveDataAlbumData.postValue(ResultWrapper.Success(it))
                                 }else{
                                     printError( pojoAlbum,  pojoPhotos )
                                 }
                             }
                         }

            }catch (e:Exception){
                e.printStackTrace()
                liveDataAlbumData.postValue(ResultWrapper.GenericError(
                    ErrorResponse(-1, e.message)
                ))

            }
        }
        return liveDataAlbumData
    }

    private fun printError(services: Response<PojoAlbum>, pojoAvailibility: Response<PojoPhotos>
    ) {
        if (!services.isSuccessful)
            liveDataAlbumData.postValue(Utils.parseError(services))
        else if (!pojoAvailibility.isSuccessful)
            liveDataAlbumData.postValue(Utils.parseError(pojoAvailibility))
    }
}