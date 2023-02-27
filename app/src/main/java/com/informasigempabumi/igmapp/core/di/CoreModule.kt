package com.informasigempabumi.igmapp.core.di

import com.informasigempabumi.igmapp.BuildConfig
import com.informasigempabumi.igmapp.core.data.GetDataGempaRepository
import com.informasigempabumi.igmapp.core.data.remote.RemoteDataSource
import com.informasigempabumi.igmapp.core.data.remote.network.ApiService
import com.informasigempabumi.igmapp.core.domain.repository.IGetDataGempaRespository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val netrworkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get()).build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single {
        RemoteDataSource(get())
    }
    single<IGetDataGempaRespository> { GetDataGempaRepository(get()) }
}