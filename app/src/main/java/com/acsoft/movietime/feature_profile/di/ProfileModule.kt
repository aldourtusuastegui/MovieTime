package com.acsoft.movietime.feature_profile.di

import com.acsoft.movietime.feature_profile.data.remote.ApiService
import com.acsoft.movietime.feature_profile.data.remote.RemoteDataSource
import com.acsoft.movietime.utils.AppConstants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProfileModule {

     @Singleton
     @Provides
     fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
          .baseUrl(AppConstants.SERVER_URL)
          .addConverterFactory(GsonConverterFactory.create(gson))
          .build()

     @Provides
     fun provideGson(): Gson = GsonBuilder().create()

     @Provides
     fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

     @Singleton
     @Provides
     fun provideRemoteDataSource(apiService: ApiService) = RemoteDataSource(apiService)
}