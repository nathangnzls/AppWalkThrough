package com.njg.testwalkthrough

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.njg.appwalkthrough.AppWalkThrough

class test: AppCompatActivity(){
    lateinit var colorsActive: IntArray
    lateinit var colorsInactive: IntArray
    lateinit var layouts: IntArray
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         colorsActive = resources.getIntArray(com.njg.appwalkthrough.R.array.array_dot_active)
         colorsInactive = resources.getIntArray(com.njg.appwalkthrough.R.array.array_dot_inactive)
        layouts = intArrayOf(
            R.layout.welcome_slide2,
            R.layout.welcome_slide3,
            R.layout.welcome_slide4,
            R.layout.welcome_slide2
        )
           AppWalkThrough(this, layouts!!, colorsInactive, colorsActive).show()
        setContentView(R.layout.activity_main)
    }
}