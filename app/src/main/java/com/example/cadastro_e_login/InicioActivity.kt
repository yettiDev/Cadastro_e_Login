package com.example.cadastro_e_login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cadastro_e_login.databinding.ActivityInicioBinding

class InicioActivity : AppCompatActivity() {
    lateinit var binding: ActivityInicioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityInicioBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_inicio)



    }
}