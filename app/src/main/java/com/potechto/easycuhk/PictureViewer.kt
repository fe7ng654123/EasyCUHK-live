package com.potechto.easycuhk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager

class PictureViewer : AppCompatActivity() {
    private val img = listOf<Int>(R.drawable.bus_route, R.drawable.bus_route2)

    private lateinit var mPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pic_view)

        mPager = findViewById(R.id.pager)

        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        mPager.setPageTransformer(true, ZoomOutPageTransformer())
        mPager.adapter = pagerAdapter

    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = 2

        override fun getItem(position: Int): Fragment {
            val fragment = PicFragment()
            val bundle = Bundle()
            bundle.putInt("image", img[position])
            fragment.arguments = bundle
            return fragment
        }
    }
}