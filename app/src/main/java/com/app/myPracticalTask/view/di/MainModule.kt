package com.app.myPracticalTask.view.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@InstallIn(ViewModelComponent::class)
@Module
object MainModule {

    @Provides
    fun providesAlbumApi(retrofit: Retrofit):AlbumApi{
        return retrofit.create(AlbumApi::class.java)
    }

    @Provides
    fun providesAlbumRepository(albumApi: AlbumApi):AlbumRepository{
        return AlbumRepository(albumApi)
    }
}