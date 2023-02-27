package com.example.cadastro_e_login

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.room.Room
import com.example.cadastro_e_login.RoomPersistence.MyDataBase
import com.example.cadastro_e_login.databinding.ActivityLoginAcitivityBinding

class LoginAcitivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginAcitivityBinding
    lateinit var db:MyDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityLoginAcitivityBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        db=Room.databaseBuilder(applicationContext,MyDataBase::class.java,"userbank").fallbackToDestructiveMigration().build()

        binding.criarConta.setOnClickListener {
            val intent:Intent=Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }


        binding.BotaoEntrar.setOnClickListener {
            val usuario=binding.usuario.text.toString()
            val senha=binding.senha.text.toString()
            val textoDigitado= binding.usuario.text.toString()

           Thread(Runnable {
               if (db.UsuarioDao().login(usuario,senha)){

                   runOnUiThread {  Toast.makeText(applicationContext,"Bem vindo ${usuario}!", Toast.LENGTH_LONG).show()
                   val intent:Intent=Intent(applicationContext, InicioActivity::class.java)
                       val textoDigitado=binding.usuario.text.toString()


                   binding.usuario.setText("")
                   binding.senha.setText("")
                   startActivity(intent)}
               }else{
                runOnUiThread {           Toast.makeText(applicationContext,"Usuario ou senha errada!", Toast.LENGTH_LONG).show()
                }
               }
           }).start()
        }

        binding.usuario.addTextChangedListener(loginTextWatcher)
        binding.senha.addTextChangedListener(loginTextWatcher)



    }
    val loginTextWatcher=object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        @SuppressLint("SuspiciousIndentation")
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val usuario = binding.usuario.text.toString().trim()
            val senha = binding.senha.text.toString().trim()

                binding.BotaoEntrar.isEnabled = usuario.isNotEmpty() && senha.isNotEmpty()

            if (usuario.isNotEmpty() && senha.isNotEmpty()) {
                binding.BotaoEntrar.setBackgroundColor(Color.RED)
            } else {
                binding.BotaoEntrar.setBackgroundColor(Color.LTGRAY)
            }

        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }
    }