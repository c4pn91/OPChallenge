package com.c4pn91.opchallenge.data.remote.model

import com.c4pn91.opchallenge.data.local.entitie.PersonEntity
import com.c4pn91.opchallenge.domain.model.Person
import com.google.gson.annotations.SerializedName

data class ResultsDTO(
    @SerializedName("gender"               ) var gender             : Int?                = null,
    @SerializedName("id"                   ) var id                 : Int?                = null,
    @SerializedName("known_for_department" ) var knownForDepartment : String?             = null,
    @SerializedName("name"                 ) var name               : String?             = null,
    @SerializedName("original_name"        ) var originalName       : String?             = null,
    @SerializedName("popularity"           ) var popularity         : Double?             = null,
    @SerializedName("profile_path"         ) var profilePath        : String?             = null,
    @SerializedName("known_for"            ) var knownForDTO        : ArrayList<KnownForDTO> = arrayListOf()
)

fun ResultsDTO.toPersonEntity() = PersonEntity(
    id = id,
    gender = gender,
    knownForDepartment = knownForDepartment,
    name = name,
    originalName = originalName,
    popularity = popularity,
    profilePath = profilePath
)

fun ResultsDTO.toPerson() = Person(
    gender = gender,
    id = id,
    knownForDepartment = knownForDepartment,
    name = name,
    originalName = originalName,
    popularity = popularity,
    profilePath = profilePath,
    knownFor = knownForDTO.map { it.toKnownFor() }
)
