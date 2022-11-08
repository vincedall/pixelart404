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
    val paintGrid : Paint = Paint().apply {
        color = Color.BLACK
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
    val paletteEraser: Paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
    }
    val paletteWhite: Paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL
    }
    var currentColor : Paint = paletteYellow
    val screenWidth = Resources.getSystem().displayMetrics.widthPixels.toFloat()
    val screenHeight = Resources.getSystem().displayMetrics.heightPixels.toFloat()
    val horizStart = 100F
    val vertStart = 100F
    val horizDist: Float = (screenWidth - horizStart) / gridWidth.toFloat()
    val vertDist: Float = horizDist
    val horizCoords: MutableList<Float> = MutableList(gridWidth) { 0F }
    val vertCoords: MutableList<Float> = MutableList(gridHeight) { 0F }
    var paletteCoordX: Float = 0F
    var paletteCoordY: Float = 0F
    val palettes: MutableList<PaletteColor> = mutableListOf()
    val squares: MutableList<Square> = mutableListOf()

    fun initCoords(){
        for (i in 0 until gridWidth){
            horizCoords[i] = horizStart + (i * horizDist)
        }
        for (i in 0 until gridHeight){
            vertCoords[i] = vertStart + (i * vertDist)
        }
        paletteCoordX = horizCoords[1]
        paletteCoordY = vertCoords[vertCoords.size - 1] + 200
        palettes.add(PaletteColor(paletteCoordX, paletteCoordY, paletteRed))
        palettes.add(PaletteColor(paletteCoordX + squareWidth, paletteCoordY, paletteYellow))
        palettes.add(PaletteColor(paletteCoordX + 2 * squareWidth, paletteCoordY, paletteBlue))
        palettes.add(PaletteColor(paletteCoordX + 3 * squareWidth, paletteCoordY, paletteGreen))
        palettes.add(PaletteColor(paletteCoordX + 4 * squareWidth, paletteCoordY, paletteBlack))
        palettes.add(PaletteColor(paletteCoordX + 5 * squareWidth, paletteCoordY, paletteWhite))
        for (i in 0 until gridWidth){
            for (j in 0 until gridHeight){
                squares.add(Square(horizCoords[i] - squareWidth / 2F, vertCoords[j] - squareHeight / 2F,
                    paletteWhite, false))
            }
        }
    }

    fun drawSquare(canvas: Canvas?, x: Float, y: Float, color: Paint){
        canvas?.drawRect(x, y, x + squareWidth, y + squareHeight, color)
    }

    fun drawPalette(canvas: Canvas?){
        drawSquare(canvas, palettes[0].x, palettes[0].y, palettes[0].color)
        drawSquare(canvas, palettes[1].x, palettes[1].y, palettes[1].color)
        drawSquare(canvas, palettes[2].x, palettes[2].y, palettes[2].color)
        drawSquare(canvas, palettes[3].x, palettes[3].y, palettes[3].color)
        drawSquare(canvas, palettes[4].x, palettes[4].y, palettes[4].color)
        drawSquare(canvas, palettes[5].x, palettes[5].y, paletteEraser)
    }

    fun drawGrid(canvas: Canvas?){
        for (i in 0 until gridWidth){
            for (j in 0 until gridHeight){
                canvas?.drawCircle(horizCoords[i], vertCoords[j], 10F, paintGrid)
            }
        }
    }

    fun isIntersect(xTouch: Float, yTouch: Float, x: Float, y: Float, width: Float, height: Float): Boolean{
        if (xTouch > x && xTouch < x + width &&
            yTouch > y && yTouch < y + height){
            return true
        }
        return false
    }

    fun drawSquares(canvas: Canvas?){
        for (square in squares) {
            if (square.drawn) {
                drawSquare(canvas, square.x, square.y, square.color)
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        initCoords()
        drawGrid(canvas)
        drawPalette(canvas)
        for (palette in palettes){
            if(isIntersect(xMouse, yMouse, palette.x, palette.y, squareWidth, squareHeight)){
                currentColor = palette.color
                erasing = currentColor == paletteWhite
            }
        }
        for (square in squares){
            if (isIntersect(xMouse, yMouse, square.x, square.y, squareWidth, squareHeight)){
                square.drawn = !erasing
                square.color = currentColor
            }
        }
        drawSquares(canvas)
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }
}