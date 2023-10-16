package com.mertkaragul.beplanner.Service.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mertkaragul.beplanner.Model.DatabaseModel.DatabasePlanModel


@Database(entities = [DatabasePlanModel::class], version = 1)
abstract class DatabaseDAO : RoomDatabase(){
    abstract fun plannerDAO() : IDatabaseCRUD
}