package com.github.robuspixelart404

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class MainFragment(val activity: MainActivity) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val scan : TextView = view.findViewById(R.id.scan)
        val draw : TextView = view.findViewById(R.id.draw)
        scan.setOnClickListener(View.OnClickListener {
            activity.swapFragment("scan")
        })
        draw.setOnClickListener(View.OnClickListener {
            activity.swapFragment("draw")
        })
    }
}