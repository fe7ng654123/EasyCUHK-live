package com.potechto.easycuhk

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.lwj.widget.viewpagerindicator.ViewPagerIndicator

class PictureViewer : AppCompatActivity() {
    private val img = listOf<Int>(R.drawable.bus_route, R.drawable.bus_route1, R.drawable.bus_route2)

    private lateinit var mPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pic_view)

        mPager = findViewById(R.id.pager)
        val mViewPagerIndicator:ViewPagerIndicator = findViewById(R.id.viePwagerIndicator)



        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        mPager.setPageTransformer(true, ZoomOutPageTransformer())
        mPager.adapter = pagerAdapter
//        Toast.makeText(, "Drag to the left", Toast.LENGTH_SHORT).show()
        Toast.makeText(applicationContext, "swipe left or right", Toast.LENGTH_SHORT).show()
        //viewpager是固定页数, 传入viewpager即可
        mViewPagerIndicator.setViewPager(mPager)

//        //viewpager是轮播图 ,如:总数为100000个 实际展示页为6个
//        //需要传入实际展示个数num
//        mViewPagerIndicator.setViewPager(mPager,3)
    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = 3

        override fun getItem(position: Int): Fragment {
            val fragment = PicFragment()
            val bundle = Bundle()
            bundle.putInt("image", img[position])
            fragment.arguments = bundle
            return fragment
        }
    }
}