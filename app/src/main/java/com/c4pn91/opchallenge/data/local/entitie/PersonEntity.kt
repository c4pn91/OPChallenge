package com.c4pn91.opchallenge.data.local.entitie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class PersonEntity(
    @ColumnInfo("id"                   )
    @PrimaryKey(autoGenerate = false         ) val id                 : Int?                   = null,
    @ColumnInfo("gender"               ) val gender             : Int?                   = null,
    @ColumnInfo("known_for_department" ) val knownForDepartment : String?                = null,
    @ColumnInfo("name"                 ) val name               : String?                = null,
    @ColumnInfo("original_name"        ) val originalName       : String?                = null,
    @ColumnInfo("popularity"           ) val popularity         : Double?                = null,
    @ColumnInfo("profile_path"         ) val profilePath        : String?                = null,
)