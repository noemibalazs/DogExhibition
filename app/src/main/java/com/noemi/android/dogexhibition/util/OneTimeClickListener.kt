package com.noemi.android.dogexhibition.util

import android.os.SystemClock
import android.view.View

abstract class OnTimeClickListener(private val limit: Int = 900) : View.OnClickListener{

    private var clickedLastTime = 0L
    abstract fun onViewClicked(v: View)

    override fun onClick(view: View) {
        val current = SystemClock.elapsedRealtime()
        if (current -  clickedLastTime> limit){
            clickedLastTime = current
            onViewClicked(view)
        }
    }
}