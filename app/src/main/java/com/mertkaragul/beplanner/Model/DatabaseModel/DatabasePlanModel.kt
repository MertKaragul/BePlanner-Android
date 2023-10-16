package com.mertkaragul.beplanner.Model.DatabaseModel

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID


@Entity
data class DatabasePlanModel(
    @PrimaryKey(autoGenerate = true)
    val uuid : Int = 0,
    var name : String,
    var time : Long,
    var status : Boolean
)
