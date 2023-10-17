package com.mertkaragul.beplanner.Service.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mertkaragul.beplanner.Model.DatabaseModel.DatabasePlanModel

@Dao
interface IDatabaseCRUD {
    @Query("SELECT * FROM DatabasePlanModel")
    suspend fun getAll() : MutableList<DatabasePlanModel>
    @Query("SELECT * FROM DatabasePlanModel WHERE uuid IN (:id)")
    suspend fun getById(id : Int) : DatabasePlanModel
    @Query("SELECT * FROM DatabasePlanModel WHERE name IN (:name)")
    suspend fun getByName(name : String) : DatabasePlanModel
    @Query("DELETE FROM DatabasePlanModel")
    suspend fun deleteAlL()

    @Update
    suspend fun update (databasePlanModel: DatabasePlanModel)
    @Insert
    suspend fun insert(databasePlanModel: DatabasePlanModel)
    @Delete
    suspend fun delete(databasePlanModel: DatabasePlanModel)
}