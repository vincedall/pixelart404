package com.github.robuspixelart404

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.LayoutDirection
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.edit
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.marginStart
import org.w3c.dom.Text
import org.xmlpull.v1.XmlPullParser
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    val paramsAnswers: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
    val paramsLine: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT, 2)
    val questions: MutableList<Question> = mutableListOf()
    var currentQuestion: Int = 0
    lateinit var answers: LinearLayout
    lateinit var questionView: TextView
    val textViewList: MutableList<CustomTextView> = mutableListOf()
    var init: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().
        add(R.id.main_fragment, MainFragment(this), "main_menu").commit()
        answers = findViewById(R.id.answers)
        questionView = findViewById(R.id.question)
    }

    fun correctAnswer(){
        println("Correct!")
        swapQuestion()
    }

    fun swapQuestion(){
        answers.removeAllViews()
        textViewList.clear()
        if(!init) {
            currentQuestion++
        }
        init = false
        questionView.text = questions[currentQuestion].question
        for (i in 0 until questions[currentQuestion].answers.size) {
            val textView: CustomTextView = CustomTextView(this, i)
            textViewList.add(textView)
            textView.text = questions[currentQuestion].answers[i]
            textView.textSize = 25F
            textView.layoutParams = paramsAnswers
            textView.gravity = Gravity.TOP
            textView.setPadding(40, 10, 0, 10)
            textView.setOnClickListener(View.OnClickListener {
                if (textView.index.equals(questions[currentQuestion].correctAnswer)) {
                    correctAnswer()
                }
            })
        }
        for (i in 0 until textViewList.size){
            answers.addView(textViewList[i])
            if (i < textViewList.size - 1) {
                val line: View = View(this)
                line.layoutParams = paramsLine
                line.setBackgroundColor(Color.LTGRAY)
                answers.addView(line)
            }
        }
    }

    fun swapFragment(target: String){
        if (target.equals("scan")){
            supportFragmentManager.beginTransaction().
            replace(R.id.main_fragment, ScanFragment(this), "scan").
            addToBackStack("scan").commit()
        }else if (target.equals("draw")){
            supportFragmentManager.beginTransaction().
            replace(R.id.main_fragment, DrawFragment(this), "draw").
            addToBackStack("draw").commit()
        }
    }
}