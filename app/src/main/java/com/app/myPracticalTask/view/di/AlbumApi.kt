package com.app.myPracticalTask.view.di

import com.app.myPracticalTask.view.pojo.PojoAlbum
import com.app.myPracticalTask.view.pojo.PojoAlbumItem
import com.app.myPracticalTask.view.pojo.PojoPhotos
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface AlbumApi {
    @GET("albums")
    suspend fun getAlbumData(): Response<PojoAlbum>

    @GET("photos")
    suspend fun getPhotosData(): Response<PojoPhotos>

    @GET("photos")
    suspend fun getPhotosData1():  PojoPhotos

}