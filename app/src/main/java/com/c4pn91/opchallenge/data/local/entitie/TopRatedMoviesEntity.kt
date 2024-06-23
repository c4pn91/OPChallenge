package com.c4pn91.opchallenge.data.local.entitie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.c4pn91.opchallenge.domain.model.Movie

@Entity(tableName = "top_rated_movies")
data class TopRatedMoviesEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false      ) var id               : Int?           = null,
    @ColumnInfo("backdrop_path"     ) var backdropPath     : String?        = null,
    @ColumnInfo("original_title"    ) var originalTitle    : String?        = null,
    @ColumnInfo("overview"          ) var overview         : String?        = null,
    @ColumnInfo("poster_path"       ) var posterPath       : String?        = null,
    @ColumnInfo("adult"             ) var adult            : Boolean?       = null,
    @ColumnInfo("title"             ) var title            : String?        = null,
    @ColumnInfo("original_language" ) var originalLanguage : String?        = null,
    @ColumnInfo("popularity"        ) var popularity       : Double?        = null,
    @ColumnInfo("release_date"      ) var releaseDate      : String?        = null,
    @ColumnInfo("vote_average"      ) var voteAverage      : Double?        = null,
    @ColumnInfo("vote_count"        ) var voteCount        : Int?           = null
)

fun TopRatedMoviesEntity.toMovie() = Movie(
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