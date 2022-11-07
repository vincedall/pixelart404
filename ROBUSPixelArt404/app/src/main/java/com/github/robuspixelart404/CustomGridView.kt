package com.github.robuspixelart404

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CustomGridView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    var gridWidth: Int = 8
    var gridHeight: Int = 8
    var squareWidth: Float = 80F
    var squareHeight: Float = 80F
    var xMouse: Float = 0F
    var yMouse: Float = 0F
    var erasing: Boolean = false
    val squares: MutableList<MutableList<Boolean>> =
        MutableList(gridWidth) { mutableListOf() }
    val paintGrid : Paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL
    }
    val paintSquare : Paint = Paint().apply {
        color = Color.YELLOW
        style = Paint.Style.FILL
    }
    val paletteRed: Paint = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL
    }
    val paletteYellow: Paint = Paint().apply {
        color = Color.YELLOW
        style = Paint.Style.FILL
    }
    val paletteBlue: Paint = Paint().apply {
        color = Color.BLUE
        style = Paint.Style.FILL
    }
    val paletteGreen: Paint = Paint().apply {
        color = Color.GREEN
        style = Paint.Style.FILL
    }
    val paletteBlack: Paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL
    }
    val paletteWhite: Paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
    }
    val screenWidth = Resources.getSystem().displayMetrics.widthPixels.toFloat()
    val screenHeight = Resources.getSystem().displayMetrics.heightPixels.toFloat()
    val horizStart = 100F
    val vertStart = 100F
    val horizDist: Float = (screenWidth - horizStart) / gridWidth.toFloat()
    val vertDist: Float = horizDist
    val horizCoords: MutableList<Float> = MutableList(gridWidth) { 0F }
    val vertCoords: MutableList<Float> = MutableList(gridHeight) { 0F }

    fun initCoords(){
        for (i in 0 until gridWidth){
            horizCoords[i] = horizStart + (i * horizDist)
        }
        for (i in 0 until gridHeight){
            vertCoords[i] = vertStart + (i * vertDist)
        }
    }

    fun drawSquare(canvas: Canvas?, coordX: Float, coordY: Float, paint: Paint){
        canvas?.drawRect(coordX - squareWidth / 2F, coordY - squareHeight / 2F,
            coordX + squareWidth / 2F, coordY + squareHeight / 2F, paint)
    }

    fun drawPalette(canvas: Canvas?){
        val paletteCoordX: Float = horizCoords[1]
        val paletteCoordY: Float = vertCoords[vertCoords.size - 1] + 200
        drawSquare(canvas, paletteCoordX, paletteCoordY, paletteRed)
        drawSquare(canvas, paletteCoordX + squareWidth, paletteCoordY, paletteYellow)
        drawSquare(canvas, paletteCoordX + 2 * squareWidth, paletteCoordY, paletteBlue)
        drawSquare(canvas, paletteCoordX + 3 * squareWidth, paletteCoordY, paletteGreen)
        drawSquare(canvas, paletteCoordX + 4 * squareWidth , paletteCoordY, paletteBlack)
        drawSquare(canvas, paletteCoordX + 5 * squareWidth , paletteCoordY, paletteWhite)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        initCoords()
        for (i in 0 until gridWidth){
            for (j in 0 until gridHeight){
                canvas?.drawCircle(horizCoords[i], vertCoords[j], 10F, paintGrid)
            }
        }
        drawPalette(canvas)
        for(i in 0 until gridWidth){
            for (j in 0 until gridHeight){
                if (xMouse < horizCoords[i] + squareWidth / 2F && xMouse > horizCoords[i] - squareWidth / 2F &&
                    yMouse < vertCoords[j] + squareHeight / 2F && yMouse > vertCoords[j] - squareHeight / 2F){
                    if (!erasing){
                        squares[i][j] = true
                    }else {
                        squares[i][j] = false
                    }
                }
            }
        }
        for (i in 0 until gridWidth){
            for (j in 0 until gridHeight){
                if(squares[i][j]){
                    drawSquare(canvas, horizCoords[i], vertCoords[j], paintSquare)
                }
            }
        }
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }
}