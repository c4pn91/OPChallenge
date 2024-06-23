package com.c4pn91.opchallenge.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.c4pn91.opchallenge.data.local.entitie.PopularMoviesEntity
import com.c4pn91.opchallenge.data.local.entitie.TopRatedMoviesEntity

@Dao
interface TopRatedMoviesDao {
    @Query("SELECT * FROM top_rated_movies WHERE id = :movieId")
    suspend fun getTopRatedMovieById(movieId: Int): TopRatedMoviesEntity

    @Query("SELECT * FROM top_rated_movies")
    suspend fun getAllTopRatedMovies(): List<TopRatedMoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTopRatedMovies(movies: List<TopRatedMoviesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopRatedMovieMovie(movieEntity: TopRatedMoviesEntity)

    @Query("DELETE FROM top_rated_movies")
    suspend fun deleteAllTopRatedMovies()
}