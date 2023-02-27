package com.example.cadastro_e_login.RoomPersistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userBank")
data class Usuario(@PrimaryKey(autoGenerate = true)val id:Int=0,
                   @ColumnInfo(name = "usuario") val usuario:String,
                   @ColumnInfo(name = "senha") val senha:String,){

}
