package com.app.myPracticalTask.view.di

import com.app.myPracticalTask.view.pojo.PojoAlbum
import com.app.myPracticalTask.view.pojo.PojoPhotos
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AlbumRepository @Inject constructor(var albumApi: AlbumApi) {

    /*suspend fun albumData1(): PojoAlbum {
        return albumApi.getAlbumData()
    }*/
    suspend fun photosData1(): PojoPhotos {
        return albumApi.getPhotosData1()
    }

    suspend fun albumData()= flow {
        emit( albumApi.getAlbumData())
    }
    suspend fun photosData()= flow {
        emit( albumApi.getPhotosData())
    }
}