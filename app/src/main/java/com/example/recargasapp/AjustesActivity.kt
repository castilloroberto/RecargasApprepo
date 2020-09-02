package com.example.recargasapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_ajustes.*

class AjustesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajustes)

        cargarData()
        btn_save.setOnClickListener { saveData()}

    }

    //funciones para guardar datos
    private fun cargarData() {
        val sharedprefs = getSharedPreferences("ajsutepin",Context.MODE_PRIVATE)
        val dato = sharedprefs.getString("pindato", "pin")
        val chitigoVal = sharedprefs.getBoolean("chiptigo", false)
        val chipclaroVal = sharedprefs.getBoolean("chipclaro", false)
        chiptigo.isChecked = chitigoVal
        chipclaro.isChecked = chipclaroVal

        tv_showpin.text = dato
        num_etpin.setText(dato)

    }

    private fun saveData() {
        val sharedprefs = getSharedPreferences("ajsutepin",Context.MODE_PRIVATE)
//        val sharedprefs = getPreferences(Context.MODE_PRIVATE)
        with(sharedprefs.edit()) {
            putString("pindato", num_etpin.text.toString())
            putBoolean("chiptigo", chiptigo.isChecked)
            putBoolean("chipclaro", chipclaro.isChecked)


            commit()
        }
        cargarData()
    }

}