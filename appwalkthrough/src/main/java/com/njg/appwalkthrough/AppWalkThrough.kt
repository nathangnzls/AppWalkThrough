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

class AppWalkThrough(val windowContext: Context, layouts: IntArray) : Dialog(windowContext){
    internal val view: View = inflate(R.layout.appwalkthrough_main)
    internal var viewPager: ViewPager
    internal lateinit var next: Button
    internal lateinit var skip: Button
    internal var layouts2: IntArray
    internal var myViewPagerAdapter: MyViewPagerAdapter = MyViewPagerAdapter()
    internal var dots: Array<TextView?>? = null
    internal var dotsLayout: LinearLayout? = null
    internal var viewPagerPageChangeListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageSelected(position: Int) {
            addBottomDots(position)
            // changing the next button text 'NEXT' / 'GOT IT'
            /* if (position == layouts!!.size - 1) {
                 // last page. make button text to GOT IT
                 btnNext!!.text = getString(R.string.start)
                 btnSkip!!.visibility = View.GONE
             } else {
                 // still pages are left
                 btnNext!!.text = getString(R.string.next)
                 btnSkip!!.visibility = View.VISIBLE
             }*/
        }
        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {
        }
        override fun onPageScrollStateChanged(arg0: Int) {
        }
    }

    init {
        layouts2  = layouts
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
                    Log.d("BUTON",viewPager!!.currentItem.toString()+layouts!!.size.toString())
                    next.setText(R.string.close)
                }else{
                    Log.d("BUTON",viewPager!!.currentItem.toString()+layouts!!.size.toString())
                }
            } else {
                this.dismiss()
                //launchHomeScreen()
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
        dots = arrayOfNulls(layouts2!!.size)
        val colorsActive = context.resources.getIntArray(R.array.array_dot_active)
        val colorsInactive = context.resources.getIntArray(R.array.array_dot_inactive)
        dotsLayout!!.removeAllViews()
        for (i in dots!!.indices) {
            dots!![i] = TextView(context)
            dots!![i]!!.text = Html.fromHtml("&#8226;")
            dots!![i]!!.textSize = 35f
            dots!![i]!!.setTextColor(colorsInactive[currentPage])
            dotsLayout!!.addView(dots!![i])
        }
        if (dots!!.size > 0)
            dots!![currentPage]!!.setTextColor(colorsActive[currentPage])
    }

    private fun getItem(i: Int): Int {
        return viewPager!!.currentItem + i
    }


}



