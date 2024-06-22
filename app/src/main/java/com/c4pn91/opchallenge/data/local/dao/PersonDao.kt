package com.c4pn91.opchallenge.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.c4pn91.opchallenge.data.local.entitie.KnownForEntity
import com.c4pn91.opchallenge.data.local.entitie.PersonEntity
import com.c4pn91.opchallenge.data.local.relation.PersonWithKnownFor

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(person: PersonEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllKnownFor(items: List<KnownForEntity>)

    @Transaction
    @Query("SELECT * FROM person LIMIT 1")
    suspend fun getPersonWithKnownFor(): PersonWithKnownFor

    @Query("DELETE FROM person")
    suspend fun deletePerson()

    @Query("DELETE FROM known_for")
    suspend fun deleteAllKnownFor()

    @Transaction
    suspend fun deleteAllData() {
        deletePerson()
        deleteAllKnownFor()
    }
}