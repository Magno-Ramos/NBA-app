package com.app.pagingexample.di.modules

import com.app.pagingexample.service.RetrofitService
import com.app.pagingexample.service.WebService
import com.app.pagingexample.service.WebServiceImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun providesRetrofitService(): RetrofitService {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpBuilder = OkHttpClient.Builder()
        httpBuilder.addInterceptor(loggingInterceptor)

        return Retrofit.Builder()
            .baseUrl(RetrofitService.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpBuilder.build())
            .build()
            .create(RetrofitService::class.java)
    }

    @Provides
    fun providesWebService(retrofitService: RetrofitService): WebService {
        return WebServiceImpl(retrofitService)
    }
}