package com.example.quiz.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "state_table")
data class Quiz(
    @ColumnInfo(name = "StateName")
    var stateName: String,

    @ColumnInfo(name = "CapitalName")
    var capitalName: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    var id: Long = 0L

) : Parcelable