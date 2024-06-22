package com.c4pn91.opchallenge.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.c4pn91.opchallenge.data.local.entitie.KnownForEntity
import com.c4pn91.opchallenge.data.local.entitie.PersonEntity
import com.c4pn91.opchallenge.data.local.entitie.toKnownFor
import com.c4pn91.opchallenge.domain.model.Person

data class PersonWithKnownFor(
    @Embedded val person: PersonEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "person_id"
    )
    val items: List<KnownForEntity>
)

fun PersonWithKnownFor.toPerson() = Person(
    gender = person.gender,
    id = person.id,
    knownForDepartment = person.knownForDepartment,
    name = person.name,
    originalName = person.originalName,
    popularity = person.popularity,
    profilePath = person.profilePath,
    knownFor = items.map { it.toKnownFor() }
)