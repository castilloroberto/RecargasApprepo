package com.example.recargasapp

import android.graphics.drawable.Animatable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_paquetes.*

class PaquetesActivity : AppCompatActivity() {

    private val rotateOpen: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.rotate_open_anim
        )
    }
    private val rotateClose: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.rotate_close_anim
        )
    }
    private val fromButtom: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.from_buttom_anim
        )
    }
    private val toButtom: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.to_buttom_anim
        )
    }
    private var clicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paquetes)

        fB_add.setOnClickListener { onAddBtnClick() }
        fab_calls.setOnClickListener {}
        fab_net.setOnClickListener {}
        fab_super.setOnClickListener {}


    }

    private fun onAddBtnClick() {
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked

    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked) {
            fab_calls.visibility = View.VISIBLE
            fab_net.visibility = View.VISIBLE
            fab_super.visibility = View.VISIBLE
        } else {
            fab_calls.visibility = View.INVISIBLE
            fab_net.visibility = View.INVISIBLE
            fab_super.visibility = View.INVISIBLE
        }
    }

    private fun setAnimation(clicked: Boolean) {

        if(!clicked){
            fab_calls.startAnimation(fromButtom)
            fab_net.startAnimation(fromButtom)
            fab_super.startAnimation(fromButtom)
            fB_add.startAnimation(rotateOpen)
        }else{
            fab_calls.startAnimation(toButtom)
            fab_net.startAnimation(toButtom)
            fab_super.startAnimation(toButtom)
            fB_add.startAnimation(rotateClose)

        }
    }


    //animacion cierre de ventana

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}