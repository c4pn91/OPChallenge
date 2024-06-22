package com.c4pn91.opchallenge.domain.model

data class Movie(
    val backdropPath     : String?        = null,
    val id               : Int?           = null,
    val originalTitle    : String?        = null,
    val overview         : String?        = null,
    val posterPath       : String?        = null,
    val adult            : Boolean?       = null,
    val title            : String?        = null,
    val originalLanguage : String?        = null,
    val popularity       : Double?        = null,
    val releaseDate      : String?        = null,
    val voteAverage      : Double?        = null,
    val voteCount        : Int?           = null
)
