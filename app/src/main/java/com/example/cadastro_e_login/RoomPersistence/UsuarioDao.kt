package com.example.cadastro_e_login.RoomPersistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UsuarioDao {

    @Query("SELECT EXISTS(SELECT * FROM userBank WHERE usuario=:usuario AND senha=:senha)")
    fun login(usuario:String, senha:String):Boolean

    @Query("SELECT EXISTS(SELECT * FROM userBank WHERE usuario=:usuario)")
    fun verificarUser(usuario: String):Boolean

    @Query("SELECT * FROM userBank")
    fun getAll():List<Usuario>

    @Update
    fun update(usuario: Usuario)

    @Insert
    fun insert(usuario: Usuario)

    @Delete
    fun delete(usuario: Usuario)
}