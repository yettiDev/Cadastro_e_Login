package com.example.cadastro_e_login

import android.content.Intent
import android.graphics.Color
import android.os.AsyncTask
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.cadastro_e_login.RoomPersistence.MyDataBase
import com.example.cadastro_e_login.RoomPersistence.Usuario
import com.example.cadastro_e_login.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var db: MyDataBase
    var isAllowed: Boolean = false
    var isAllowedLenghtUser: Boolean = false
    var isAllowedLenghtSenha:Boolean=false


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.usuario.addTextChangedListener(loginTextWatcher)
        binding.senha.addTextChangedListener(loginTextWatcher)



        db = Room.databaseBuilder(applicationContext, MyDataBase::class.java, "userbank")
            .fallbackToDestructiveMigration().build()


        binding.fazerLogin.setOnClickListener {
            val intent:Intent=Intent(applicationContext,LoginAcitivity::class.java)
            startActivity(intent)
        }

    }


    val loginTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

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
        override fun afterTextChanged(editable: Editable?) {


                val usuario = binding.usuario.text.toString()
                val userTamanho = binding.usuario.length()
                val senhaTamanho = binding.senha.length()


            Thread(Runnable {                  isAllowed = !db.UsuarioDao().verificarUser(usuario)
            }).start()


                isAllowedLenghtSenha = senhaTamanho > 5


                isAllowedLenghtUser = userTamanho >= 4




            binding.BotaoEntrar.setOnClickListener {


                verificacaoCadastro()


            }
        }




        private  fun verificacaoCadastro(){

                var usertamanho = binding.usuario.length()
                if (isAllowedLenghtUser == true) {

                    if (isAllowedLenghtSenha == true) {

                        if (isAllowed == true) {

                                val usuario = Usuario(
                                    0,
                                    binding.usuario.text.toString(),
                                    binding.senha.text.toString()
                                )

                            Thread(Runnable{
                                db.UsuarioDao().insert(usuario)
                            }).start()

                                consulta()

                                binding.usuario.setText("")
                                binding.senha.setText("")

                                val intent: Intent =
                                    Intent(applicationContext, LoginAcitivity::class.java)
                                startActivity(intent)
                            Toast.makeText(applicationContext,"Conta criada com sucesso!", Toast.LENGTH_LONG).show()


                        } else {

                                Toast.makeText(
                                    applicationContext,
                                    "NOME JA EXISTENTE!",
                                    Toast.LENGTH_LONG
                                ).show()

                            consulta()

                        }
                    } else {

                            Toast.makeText(
                                applicationContext,
                                "SENHA COM POUCOS CARACTERES!",
                                Toast.LENGTH_LONG
                            ).show()

                    }
                } else {

                        Toast.makeText(
                            applicationContext,
                            "USUARIO COM POUCOS CARACTERES!",
                            Toast.LENGTH_SHORT
                        ).show()

                    consulta()
                }
            }
            }



        fun consulta(){
            Thread(Runnable {
                val usuarios=db.UsuarioDao().getAll()
                for(user:Usuario in usuarios){
                    Log.i("Registro, " ,"nome "+user.usuario)
                }
            }).start()
        }
    }
