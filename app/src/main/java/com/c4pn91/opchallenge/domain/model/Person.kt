package com.c4pn91.opchallenge.domain.model

import com.c4pn91.opchallenge.data.remote.model.KnownForDTO

data class Person(
    var gender             : Int?                   = null,
    var id                 : Int?                   = null,
    var knownForDepartment : String?                = null,
    var name               : String?                = null,
    var originalName       : String?                = null,
    var popularity         : Double?                = null,
    var profilePath        : String?                = null,
    var knownFor           : List<KnownFor>         = emptyList()
)

fun Person.toEmpty() = Person()
