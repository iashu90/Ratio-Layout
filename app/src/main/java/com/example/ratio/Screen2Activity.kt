package com.example.ratio

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.screen4.*

class Screen2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen4)

        block6.setOnClickListener{
            val intent = Intent(this, Screen3Activity::class.java)
            startActivity(intent)
        }


    }

}
