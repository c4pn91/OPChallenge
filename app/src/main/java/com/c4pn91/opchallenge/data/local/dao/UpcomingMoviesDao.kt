package com.c4pn91.opchallenge.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.c4pn91.opchallenge.data.local.entitie.TopRatedMoviesEntity
import com.c4pn91.opchallenge.data.local.entitie.UpcomingMoviesEntity

@Dao
interface UpcomingMoviesDao {
    @Query("SELECT * FROM upcoming_movies WHERE id = :movieId")
    suspend fun getUpcomingMovieById(movieId: Int): UpcomingMoviesEntity

    @Query("SELECT * FROM upcoming_movies")
    suspend fun getAllUpcomingMovies(): List<UpcomingMoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUpcomingMovies(movies: List<UpcomingMoviesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpcomingMovie(movieEntity: UpcomingMoviesEntity)

    @Query("DELETE FROM upcoming_movies")
    suspend fun deleteAllUpcomingMovies()
}