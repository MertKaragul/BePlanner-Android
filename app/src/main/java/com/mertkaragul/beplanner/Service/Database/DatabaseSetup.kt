package com.mertkaragul.beplanner.Service.Database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mertkaragul.beplanner.Service.Database.DatabaseUtils.database

class DatabaseSetup {
    fun setupDatabase(context : Context){
        database = Room.databaseBuilder(context,DatabaseDAO::class.java, "BePlannerDatabase").build()
    }

    fun setupDatabaseCallback(context : Context): DatabaseDAO{
        return Room.databaseBuilder(context,DatabaseDAO::class.java, "BePlannerDatabase").build()
    }
}