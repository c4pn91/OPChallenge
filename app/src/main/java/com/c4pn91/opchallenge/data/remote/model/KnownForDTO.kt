package com.c4pn91.opchallenge.data.remote.model

import com.c4pn91.opchallenge.data.local.entitie.KnownForEntity
import com.c4pn91.opchallenge.domain.model.KnownFor
import com.google.gson.annotations.SerializedName

data class KnownForDTO(
    @SerializedName("backdrop_path"     ) var backdropPath     : String?        = null,
    @SerializedName("id"                ) var id               : Int?           = null,
    @SerializedName("original_title"    ) var originalTitle    : String?        = null,
    @SerializedName("overview"          ) var overview         : String?        = null,
    @SerializedName("poster_path"       ) var posterPath       : String?        = null,
    @SerializedName("media_type"        ) var mediaType        : String?        = null,
    @SerializedName("adult"             ) var adult            : Boolean?       = null,
    @SerializedName("title"             ) var title            : String?        = null,
    @SerializedName("original_language" ) var originalLanguage : String?        = null,
    @SerializedName("popularity"        ) var popularity       : Double?        = null,
    @SerializedName("release_date"      ) var releaseDate      : String?        = null,
    @SerializedName("vote_average"      ) var voteAverage      : Double?        = null,
    @SerializedName("vote_count"        ) var voteCount        : Int?           = null
)

fun KnownForDTO.toKnownForEntity(personId: Int?) = KnownForEntity(
    id = id,
    personId = personId,
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

fun KnownForDTO.toKnownFor() = KnownFor(
    backdropPath = backdropPath,
    id = id,
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
