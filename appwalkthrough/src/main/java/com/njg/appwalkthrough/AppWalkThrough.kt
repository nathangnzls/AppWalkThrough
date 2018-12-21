package com.njg.appwalkthrough

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.njg.appwalkthrough.utils.inflate
import com.njg.appwalkthrough.utils.setWindowConstraints

class AppWalkThrough(val windowContext: Context, layouts: IntArray, inActiveDotsColor: IntArray, activeDotsColor: IntArray) : Dialog(windowContext){
    internal val view: View = inflate(R.layout.appwalkthrough_main)
    internal var viewPager: ViewPager
    internal var next: Button
    internal var skip: Button
    internal var layouts2: IntArray
    internal var inActiveColor: IntArray
    internal var activeColor: IntArray
    internal var myViewPagerAdapter: MyViewPagerAdapter = MyViewPagerAdapter()
    internal var dots: Array<TextView?>? = null
    internal var dotsLayout: LinearLayout? = null
    internal var viewPagerPageChangeListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageSelected(position: Int) {
            addBottomDots(position)
        }
        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {

        }
        override fun onPageScrollStateChanged(arg0: Int) {
        }
    }
    init {
        layouts2  = layouts
        inActiveColor = inActiveDotsColor
        activeColor = activeDotsColor
        setContentView(view)
        setWindowConstraints()
        viewPager = findViewById<View>(R.id.view_pager) as ViewPager
        dotsLayout = findViewById<View>(R.id.layoutDots) as LinearLayout
        next = findViewById<View>(R.id.btn_next) as Button
        skip = findViewById<View>(R.id.btn_skip) as Button
        addBottomDots(0)
        viewPager.adapter = myViewPagerAdapter
        viewPager!!.addOnPageChangeListener(viewPagerPageChangeListener)
        next.setOnClickListener {
            skip.visibility = View.VISIBLE
            if(viewPager.currentItem == 0){
                skip.visibility = View.INVISIBLE
            }else{
                skip.visibility = View.VISIBLE
            }
            val current = getItem(+1)
            if (current < layouts!!.size) {
                // move to next screen
                viewPager!!.currentItem = current
                if(viewPager.currentItem == layouts.count()){
                    next.setText(R.string.close)
                }else{
                }
            } else {
                this.dismiss()
            }
        }
        skip.setOnClickListener { this.dismiss() }
    }
    inner class MyViewPagerAdapter : PagerAdapter() {
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = getLayoutInflater().inflate(layouts2!![position], container, false)
            container.addView(view)
            return view
        }
        override fun getCount(): Int {
            return layouts2!!.size
        }
        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }
        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            val view = `object` as View
            container.removeView(view)
        }
    }
    private fun addBottomDots(currentPage: Int) {
        if(viewPager.currentItem == 0){
            skip.visibility = View.INVISIBLE
        }else{
            skip.visibility = View.VISIBLE
        }
        dots = arrayOfNulls(layouts2!!.size)
        dotsLayout!!.removeAllViews()
        for (i in dots!!.indices) {
            dots!![i] = TextView(context)
            dots!![i]!!.text = Html.fromHtml("&#8226;")
            dots!![i]!!.textSize = 35f
            dots!![i]!!.setTextColor(inActiveColor[currentPage])
            dotsLayout!!.addView(dots!![i])
        }
        if (dots!!.size > 0)
            dots!![currentPage]!!.setTextColor(activeColor[currentPage])
    }
    private fun getItem(i: Int): Int {
        return viewPager!!.currentItem + i
    }
}



