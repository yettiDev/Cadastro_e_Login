package com.example.cadastro_e_login.RoomPersistence

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Usuario::class], version = 2)
abstract class MyDataBase: RoomDatabase() {
    abstract fun UsuarioDao():UsuarioDao

}