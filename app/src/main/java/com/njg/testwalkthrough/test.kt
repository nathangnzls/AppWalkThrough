package com.njg.testwalkthrough

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.njg.appwalkthrough.AppWalkThrough

class test: AppCompatActivity(){

    private var layouts: IntArray? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layouts = intArrayOf(
            R.layout.welcome_slide2,
            R.layout.welcome_slide3,
            R.layout.welcome_slide4,
            R.layout.welcome_slide2
        )
           AppWalkThrough(this, layouts!!).show()
    }
}