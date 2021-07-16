package com.isc.lec09

import android.util.Base64
import java.security.Key
import java.security.KeyPairGenerator
import javax.crypto.Cipher

class Cifrado {
    //esta es la seccion para definir un "static" por ejemplo Cifrado.cifrar
    companion object{
        //atributo "static" para almacenar la llave publica
        private var publicKey: Key? = null

        //atributo "static" para almacenar la llave privada
        private var privateKey: Key? = null

        //inicializacion de atributos
        init {
            //se define el generador de llaves
            val kpg: KeyPairGenerator = KeyPairGenerator.getInstance("RSA")
            kpg.initialize(1024)

            //se define el par de llaves
            val kp = kpg.genKeyPair()

            //Se asignan las llaves
            publicKey = kp.public
            privateKey = kp.private

            //proceso para almacenarlas en fisico

        }
        /*funcion estatica para cifrar el texto
        * utiliza un objeto cipher para crear una instancia de encriptacion
        * RSA y se inicializa con la llave publica
        * se cifra con la llave publica, se descifra con la llave privada*/
        fun cifrar(textoPlano: String) : String {
            var texto: String //Donde quedara el texto cifrado

            //objeto para utilziar el algoritmo para cifrar
            var c: Cipher = Cipher.getInstance("RSA")
            c.init(Cipher.ENCRYPT_MODE, publicKey)

            //paso del texto a cifrar a un arreglo de bytes.
            val encodeByte: ByteArray = c.doFinal(textoPlano.toByteArray())
            texto = Base64.encodeToString(encodeByte, Base64.DEFAULT)

            return texto
        }

        /*funcion estatica para cifrar el texto
        * utiliza un objeto cipher para crear una instancia de encriptacion
        * RSA y se inicializa con la llave privada
        * se cifra con la llave publica, se descifra con la llave privada*/
        fun descifrar(textoEnClave: String) : String {
            var texto: String //Donde quedara el texto descifrado

            //objeto para utilziar el algoritmo para cifrar
            var c: Cipher = Cipher.getInstance("RSA")
            c.init(Cipher.DECRYPT_MODE, privateKey)

            //paso del texto a descifrar a un arreglo de bytes.
            val data = Base64.decode(textoEnClave, Base64.DEFAULT)
            val decodeByte: ByteArray = c.doFinal(data)

            //Se transforma el areglo en bytes a un texto legible.
            texto = String(decodeByte, charset("UTF-8"))

            return texto
        }
    }
}