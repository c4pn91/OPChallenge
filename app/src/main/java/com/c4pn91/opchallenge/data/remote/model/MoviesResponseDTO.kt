package com.c4pn91.opchallenge.data.remote.model

import com.google.gson.annotations.SerializedName

data class MoviesResponseDTO(
    @SerializedName("page"          ) var page         : Int?            = null,
    @SerializedName("results"       ) var movies       : List<MoviesDTO> = emptyList(),
    @SerializedName("total_pages"   ) var totalPages   : Int?            = null,
    @SerializedName("total_results" ) var totalResults : Int?            = null
)