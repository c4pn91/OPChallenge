package com.c4pn91.opchallenge.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.c4pn91.opchallenge.data.local.dao.PersonDao
import com.c4pn91.opchallenge.data.local.dao.PopularMoviesDao
import com.c4pn91.opchallenge.data.local.entitie.KnownForEntity
import com.c4pn91.opchallenge.data.local.entitie.PersonEntity
import com.c4pn91.opchallenge.data.local.entitie.PopularMoviesEntity
import com.c4pn91.opchallenge.data.local.entitie.TopRatedMoviesEntity
import com.c4pn91.opchallenge.data.local.entitie.UpcomingMoviesEntity

const val VERSION_1 = 1

const val CURRENT_DATABASE_VERSION = VERSION_1

@Database(
    entities = [
        PersonEntity::class,
        KnownForEntity::class,
        PopularMoviesEntity::class,
        TopRatedMoviesEntity::class,
        UpcomingMoviesEntity::class
    ],
    version = CURRENT_DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
    abstract fun popularMoviesDao(): PopularMoviesDao
}