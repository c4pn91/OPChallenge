package com.c4pn91.opchallenge.di

import com.c4pn91.opchallenge.data.remote.api.MoviesApiClient
import com.c4pn91.opchallenge.data.remote.api.PersonApiClient
import com.c4pn91.opchallenge.data.remote.interceptor.AuthInterceptor
import com.c4pn91.opchallenge.util.Constants
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providePersonApiClient(retrofit: Retrofit): PersonApiClient {
        return retrofit.create(PersonApiClient::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieApiClient(retrofit: Retrofit): MoviesApiClient {
        return retrofit.create(MoviesApiClient::class.java)
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }
}