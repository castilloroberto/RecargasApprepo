package com.example.recargasapp

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_recargas.*


class RecargasActivity : AppCompatActivity() {

    val REQUEST_PHONE_CALL = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recargas)

        btn_enviar.setOnClickListener {
            validarpermiso(android.Manifest.permission.CALL_PHONE,"phone",REQUEST_PHONE_CALL)
            //enviar texto a el textView
//            resgistro_id.text = id_Number.text.toString()
//            saveData()
            cerrarteclado(et_monto)
            iniciarCall(et_num.text.toString(),et_monto.text.toString())
        }
    }

    /*val sharedprefs = getSharedPreferences("ajsutepin",Context.MODE_PRIVATE)
  val dato = sharedprefs.getString("pindato", "pin")
  tv_showpin.text = dato
  num_etpin.setText(dato)*/
    private fun iniciarCall(numero:String,monto:String) {
        val callintent = Intent(Intent.ACTION_CALL)
        val sharedprefs = getSharedPreferences("ajsutepin", Context.MODE_PRIVATE)
        val dato = sharedprefs.getString("pindato", "pin")
        val cadena = "*601*1*$numero*$monto*$dato#"
        callintent.data = Uri.parse("tel:" + Uri.encode(cadena))
        startActivity(callintent)
    }

    private fun validarpermiso(permission: String, name: String, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when {
                ContextCompat.checkSelfPermission(
                    applicationContext,
                    permission
                ) == PackageManager.PERMISSION_GRANTED -> {
//                    Toast.makeText(
//                        applicationContext,
//                        "$name permisso rr",
//                        Toast.LENGTH_SHORT
//                    ).show()


                }
                shouldShowRequestPermissionRationale(permission) -> showDialog(
                    permission,
                    name,
                    requestCode
                )
                else -> ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
            }
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        fun innerCheck(name: String) {
            if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(applicationContext, "$name permiso refuse", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(applicationContext, "$name permiso granted", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        when(requestCode){
            REQUEST_PHONE_CALL -> innerCheck("phone")


        }
    }

    private fun showDialog(permission: String, name: String, requestCode: Int) {
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setMessage(" el permiso para acceder $name es nesesario")
            setTitle("permiso requerido")
            setPositiveButton("ok") { dialog, which ->
                ActivityCompat.requestPermissions(
                    this@RecargasActivity,
                    arrayOf(permission),
                    requestCode
                )
            }
        }
        val dialog = builder.create()
        dialog.show()
    }


    private fun cerrarteclado(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }



    //para volver al activity main

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                finish()
                return  true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }
}