package com.njg.testwalkthrough

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /*findViewById<View>(R.id.btn_play_again).setOnClickListener {
            // We normally won't show the welcome slider again in real app
            // but this is for testing
            val prefManager = PrefManager(applicationContext)

            // make first time launch TRUE
            prefManager.setFirstTimeLaunch(true)

            startActivity(Intent(this@MainActivity, WelcomeActivity::class.java))
            finish()
        }*/
    }
}