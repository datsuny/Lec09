package com.isc.lec09

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.isc.lec09.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //utilizar la clase xxxxBinding

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //inicializar el objeto binding "puente" hacia los controles del XML
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Boton "Encriptar"
        binding.btEncodeText.setOnClickListener{
            val text = binding.etPlainText.text.toString()

            if(text.isNotEmpty()){
                binding.etCodeText.setText(Cifrado.cifrar(text))
            } else {
                Toast.makeText(this, "Escriba un texto en el espacio de arriba", Toast.LENGTH_LONG).show()
            }
        }

        //Boton "decifrar"
        binding.btDencodeText.setOnClickListener{
            val text = binding.etCodeText.text.toString()

            if(text.isNotEmpty()){
                binding.etPlainText.setText(Cifrado.descifrar(text))
            } else {
                Toast.makeText(this, "Escriba un texto en el espacio de abajo", Toast.LENGTH_LONG).show()
            }
        }

    }



}