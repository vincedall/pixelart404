package com.github.robuspixelart404

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.w3c.dom.Text

class DrawFragment(val activity: MainActivity) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.draw_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val grid: CustomGridView = view.findViewById(R.id.grid)
        for (list in grid.squares){
            for (i in 0 until grid.gridHeight){
                list.add(false)
            }
        }
        grid.setOnTouchListener { _, event ->
            when (event.action){
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
    }
}