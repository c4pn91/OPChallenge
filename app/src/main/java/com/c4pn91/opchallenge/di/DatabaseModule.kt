package com.c4pn91.opchallenge.di

import android.content.Context
import androidx.room.Room
import com.c4pn91.opchallenge.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val APP_DATABASE_NAME = "app_database"

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, AppDatabase::class.java, APP_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun providePersonDao(db: AppDatabase) = db.personDao()

    @Singleton
    @Provides
    fun providePopularMoviesDao(db: AppDatabase) = db.popularMoviesDao()

}