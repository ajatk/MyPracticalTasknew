package com.hubwallet.commons.utils


import com.hubwallet.commons.constants.ConstantValues
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object  Apifactory {

	/*//OkhttpClient for building http request url

	private val okHttpClient = OkHttpClient().newBuilder()
		.addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
		//.addInterceptor(AuthTokenInterceptor())
		//.addInterceptor(ErrorInterceptor(App.instance))
		.connectTimeout(5, TimeUnit.MINUTES) // connect timeout
		.writeTimeout(5, TimeUnit.MINUTES) // write timeout
		.readTimeout(5, TimeUnit.MINUTES) //
		.build()


	fun retrofit(): Retrofit = Retrofit.Builder()
		.client(okHttpClient)
		.baseUrl(ConstantValues.baseUrl)

		.build()*/





}