package com.acsoft.movietime.feature_profile.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.acsoft.movietime.feature_profile.data.local.LocalDataSource
import com.acsoft.movietime.feature_profile.data.local.ProfileDao
import com.acsoft.movietime.feature_profile.data.local.ProfileDatabase
import com.acsoft.movietime.feature_profile.data.remote.ApiService
import com.acsoft.movietime.feature_profile.data.remote.RemoteDataSource
import com.acsoft.movietime.feature_profile.data.repository.PopularPersonProfileRepositoryImpl
import com.acsoft.movietime.feature_profile.domain.repository.PopularPersonProfileRepository
import com.acsoft.movietime.utils.AppConstants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProfileModule {

     @Provides
     @Singleton
     fun provideContext(application: Application): Context {
          return application.applicationContext
     }

     //Retrofit
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

     @Provides
     fun providePopularPersonProfileRepository(
          remoteDataSource: RemoteDataSource,
          localDataSource: LocalDataSource
     ): PopularPersonProfileRepository {
          return PopularPersonProfileRepositoryImpl(remoteDataSource, localDataSource)
     }

     //Room
     @Provides
     fun providesProfileDao(profileDatabase: ProfileDatabase): ProfileDao {
          return profileDatabase.profileDao()
     }

     @Provides
     fun providesUserDatabase(@ApplicationContext context: Context): ProfileDatabase {
          return Room.databaseBuilder(context, ProfileDatabase::class.java, "ProfileDatabase").build()
     }

     @Singleton
     @Provides
     fun provideLocalDataSource(profileDao: ProfileDao) = LocalDataSource(profileDao)

}