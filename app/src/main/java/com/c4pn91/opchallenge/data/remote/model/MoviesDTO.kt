package com.c4pn91.opchallenge.data.remote.model

import com.c4pn91.opchallenge.data.local.entitie.KnownForEntity
import com.c4pn91.opchallenge.data.local.entitie.PopularMoviesEntity
import com.c4pn91.opchallenge.data.local.entitie.TopRatedMoviesEntity
import com.c4pn91.opchallenge.data.local.entitie.UpcomingMoviesEntity
import com.c4pn91.opchallenge.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class MoviesDTO(
    @SerializedName("adult"             ) var adult            : Boolean?       = null,
    @SerializedName("backdrop_path"     ) var backdropPath     : String?        = null,
    @SerializedName("id"                ) var id               : Int?           = null,
    @SerializedName("original_language" ) var originalLanguage : String?        = null,
    @SerializedName("original_title"    ) var originalTitle    : String?        = null,
    @SerializedName("overview"          ) var overview         : String?        = null,
    @SerializedName("popularity"        ) var popularity       : Double?        = null,
    @SerializedName("poster_path"       ) var posterPath       : String?        = null,
    @SerializedName("release_date"      ) var releaseDate      : String?        = null,
    @SerializedName("title"             ) var title            : String?        = null,
    @SerializedName("vote_average"      ) var voteAverage      : Double?        = null,
    @SerializedName("vote_count"        ) var voteCount        : Int?           = null
)

fun MoviesDTO.toPopularMovieEntity() = PopularMoviesEntity(
    id = id,
    backdropPath = backdropPath,
    originalTitle = originalTitle,
    overview = overview,
    posterPath = posterPath,
    adult = adult,
    title = title,
    originalLanguage = originalLanguage,
    popularity = popularity,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    voteCount = voteCount
)

fun MoviesDTO.toTopRatedMovieEntity() = TopRatedMoviesEntity(
    id = id,
    backdropPath = backdropPath,
    originalTitle = originalTitle,
    overview = overview,
    posterPath = posterPath,
    adult = adult,
    title = title,
    originalLanguage = originalLanguage,
    popularity = popularity,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    voteCount = voteCount
)

fun MoviesDTO.toUpcomingMovieEntity() = UpcomingMoviesEntity(
    id = id,
    backdropPath = backdropPath,
    originalTitle = originalTitle,
    overview = overview,
    posterPath = posterPath,
    adult = adult,
    title = title,
    originalLanguage = originalLanguage,
    popularity = popularity,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    voteCount = voteCount
)

fun MoviesDTO.toMovie() = Movie(
    id = id,
    backdropPath = backdropPath,
    originalTitle = originalTitle,
    overview = overview,
    posterPath = posterPath,
    adult = adult,
    title = title,
    originalLanguage = originalLanguage,
    popularity = popularity,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    voteCount = voteCount
)