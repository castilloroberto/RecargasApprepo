package com.example.recargasapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //una funcion para cargadr los datos


        //-----------------------------------------------------------
        //boton de recargas
        bt_recargas.setOnClickListener{
            val intent = Intent(this,RecargasActivity::class.java)
            startActivity(intent)
        }
        //boton de paquetes
        btn_paquetes.setOnClickListener{
            val i = Intent(this,PaquetesActivity::class.java)
            startActivity(i)
        }
    }

    override fun onCreateOptionsMenu(menu : Menu?): Boolean{
        menuInflater.inflate(R.menu.nav_option,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_opcionitem-> abirAjustes()
        }
        return super.onOptionsItemSelected(item)
    }

    //funcion para abrir ventana de ajustes

    private fun abirAjustes(){
        val ajustes = Intent(this,AjustesActivity::class.java)
        startActivity(ajustes)
    }

}