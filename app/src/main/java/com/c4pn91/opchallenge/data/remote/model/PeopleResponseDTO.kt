package com.c4pn91.opchallenge.data.remote.model

import com.google.gson.annotations.SerializedName

data class PeopleResponseDTO(
    @SerializedName("page"          ) var page         : Int?                  = null,
    @SerializedName("results"       ) var results      : ArrayList<ResultsDTO> = arrayListOf(),
    @SerializedName("total_pages"   ) var totalPages   : Int?                  = null,
    @SerializedName("total_results" ) var totalResults : Int?                  = null
)
