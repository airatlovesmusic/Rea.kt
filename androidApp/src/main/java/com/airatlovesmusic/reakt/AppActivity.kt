package com.airatlovesmusic.reakt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import getName
import kotlinx.android.synthetic.main.activity_main.*

class AppActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.text = "Hello World from ${getName()}"
    }

}