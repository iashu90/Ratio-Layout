package com.example.ratio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.main_activity.*

class Screen1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

//        block8.setOnClickListener {
//            val intent = Intent(this, Screen2Activity::class.java)
//            startActivity(intent)
//        }

       viewPager.adapter = ScreenSlidePagerAdapter(supportFragmentManager)



    }


}
