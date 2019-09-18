package com.example.ratio

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.ratio.DemoScreen1Fragment
import com.example.ratio.DemoScreen2Fragment
import com.example.ratio.DemoScreen3Fragment

class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(
    fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getCount(): Int = 3

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return DemoScreen1Fragment()
            1 -> return DemoScreen2Fragment()
            2 -> return DemoScreen3Fragment()
        }
        return DemoScreen1Fragment()
    }
}