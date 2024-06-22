package com.c4pn91.opchallenge.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.c4pn91.opchallenge.data.local.entitie.PopularMoviesEntity

@Dao
interface PopularMoviesDao {
    @Query("SELECT * FROM popular_movies WHERE id = :movieId")
    suspend fun getPopularMovieById(movieId: Int): PopularMoviesEntity

    @Query("SELECT * FROM popular_movies")
    suspend fun getAllPopularMovies(): List<PopularMoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPopularMovies(movies: List<PopularMoviesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovie(movieEntity: PopularMoviesEntity)

    @Query("DELETE FROM popular_movies")
    suspend fun deleteAllPopularMovies()
}