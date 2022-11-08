package com.github.robuspixelart404

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class MainFragment() : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val screenWidth = Resources.getSystem().displayMetrics.widthPixels
        val screenHeight = Resources.getSystem().displayMetrics.heightPixels
        val grid: CustomGridView = view.findViewById(R.id.grid)
        grid.layoutParams.height = screenHeight / 9 * 5
        grid.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    grid.xMouse = event.x
                    grid.yMouse = event.y
                    grid.invalidate()
                    grid.performClick()
                }
                MotionEvent.ACTION_MOVE -> {
                    grid.xMouse = event.x
                    grid.yMouse = event.y
                    grid.invalidate()
                    grid.performClick()
                }
            }
            return@setOnTouchListener true
        }
        val draw: TextView = view.findViewById(R.id.draw)
        draw.layoutParams.width = screenWidth / 2
        draw.setOnClickListener(View.OnClickListener {

        })
        val scan: TextView = view.findViewById(R.id.scan)
        scan.layoutParams.width = screenWidth / 2
        scan.setOnClickListener(View.OnClickListener {

        })
    }
}