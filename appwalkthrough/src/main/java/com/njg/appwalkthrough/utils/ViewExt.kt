package com.njg.appwalkthrough.utils

import android.content.Context
import android.graphics.Point
import android.support.annotation.LayoutRes
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.njg.appwalkthrough.AppWalkThrough
import com.njg.appwalkthrough.R

@Suppress("UNCHECKED_CAST")
internal fun <R : View> ViewGroup.inflate(
    ctxt: Context = context,
    @LayoutRes res: Int
) = LayoutInflater.from(ctxt).inflate(res, this, false) as R


@Suppress("UNCHECKED_CAST")
internal fun <T> AppWalkThrough.inflate(
    @LayoutRes res: Int,
    root: ViewGroup? = null
) = android.view.LayoutInflater.from(windowContext).inflate(res, root, false) as T



internal fun AppWalkThrough.setWindowConstraints(){
    window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE) ?: return
    val wm = this.window?.windowManager ?: return

    val display = wm.defaultDisplay
    val size = Point()
    display.getSize(size)
    val windowWidth = size.x
    val windowHeight = size.y

    context.resources.run {
        val windowVerticalPadding = getDimensionPixelSize(
            R.dimen.md_dialog_vertical_margin
        )
        val windowHorizontalPadding = getDimensionPixelSize(
            R.dimen.md_dialog_horizontal_margin
        )
        val maxWidth = getDimensionPixelSize(R.dimen.md_dialog_max_width)
        val calculatedWidth = windowWidth - windowHorizontalPadding * 2

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(this@setWindowConstraints.window!!.attributes)
        lp.width = Math.max(maxWidth, calculatedWidth)

        this@setWindowConstraints.window!!.attributes = lp
    }
}