package com.flf69740.blinklibraryone

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

class CustomButton : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val paint = Paint()
    private val path = Path()
    private var mText: String? = null

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val unity: Float = (height* 5/100).toFloat()
        val unitx: Float = (width* 10/1000).toFloat()

        definePaintOutsideBorder()
        defineOutsideBorderGeometry(canvas, unitx, unity)

        paint.color = ContextCompat.getColor(context, R.color.greyTintPrimary)
        defineInsideBorderGeometry(canvas, unitx, unity)

        definePaintInside()
        defineInsideGeometry(canvas, unitx, unity)

        mText?.let {
            definePaintText()
            canvas?.drawText(it, width/2f, height / 2f - (paint.descent() + paint.ascent()) /2 , paint)
        }

    }

    /**
     *  PAINT
     */

    private fun definePaintInside(): Paint = paint.apply {
        isAntiAlias = true
        shader = LinearGradient(0f, 0f, width.toFloat(), height.toFloat(), ContextCompat.getColor(context, R.color.colorPrimary), ContextCompat.getColor(context, R.color.colorPrimaryDark), Shader.TileMode.CLAMP)
        style = Paint.Style.FILL
        strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = 5f
    }

    private fun definePaintOutsideBorder(): Paint = paint.apply {
        isAntiAlias = true
        shader = null
        color = ContextCompat.getColor(context, R.color.greyTintDark)
        style = Paint.Style.FILL_AND_STROKE
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 1f
    }

    private fun definePaintText(): Paint = paint.apply {
        isAntiAlias = true
        shader = null
        color = Color.WHITE
        typeface = Typeface.createFromAsset(context.assets, "fonts/Roboto-Bold.ttf")
        textAlign = Paint.Align.CENTER
        style = Paint.Style.FILL
        textSize = height/4f
    }

    /**
     *  GEOMETRY
     */

    private fun defineOutsideBorderGeometry(canvas: Canvas?, unitx: Float, unity: Float) = canvas?.let {
        path.reset()
        path.moveTo(unitx * 2, 0f)
        path.lineTo((unitx * 2) + unitx * 20, 0f)
        path.lineTo((unitx * 22) + unitx, unity)
        path.lineTo( width - (unitx * 23), unity)
        path.lineTo( width - (unitx * 22), 0f)
        path.lineTo(width - unitx * 2, 0f)
        path.lineTo(width.toFloat(), unity * 2)

        path.lineTo(width.toFloat(), height - unity * 2)
        path.lineTo(width - unitx * 2, height.toFloat())
        path.lineTo( width - (unitx * 22), height.toFloat())
        path.lineTo( width - (unitx * 23), height - unity)
        path.lineTo((unitx * 22) + unitx, height - unity)
        path.lineTo((unitx * 2) + unitx * 20, height.toFloat())
        path.lineTo(unitx * 2, height.toFloat())

        path.lineTo(0f, height - unity * 2)
        path.lineTo(0f, unity * 2)
        path.close()
        it.drawPath(path, paint)
    }

    private fun defineInsideBorderGeometry(canvas: Canvas?, unitx: Float, unity: Float) = canvas?.let {
        path.reset()
        path.moveTo(unitx * 3, unity * 5/3)
        path.lineTo(width - unitx * 3, unity * 5/3)
        path.lineTo(width - unity * 5/3, unity * 10/3)

        path.lineTo(width - unity * 5/3, height - unity * 10/3)
        path.lineTo(width - unitx * 3, height - unity * 5/3)

        path.lineTo(unitx * 3, height - unity * 5/3)
        path.lineTo(unity * 5/3, height - unity * 10/3)

        path.lineTo(unity * 5/3, unity * 10/3)
        path.close()

        it.drawPath(path, paint)
    }

    private fun defineInsideGeometry(canvas: Canvas?, unitx: Float, unity: Float) = canvas?.let {
        path.reset()
        path.moveTo(unitx * 13/3, unity * 9/3)
        path.lineTo(width - unitx * 13/3, unity * 9/3)
        path.lineTo(width - unity * 8/3, unity * 13/3)

        path.lineTo(width - unity * 8/3, height - unity * 13/3)
        path.lineTo(width - unitx * 13/3, height - unity * 9/3)

        path.lineTo(unitx * 13/3, height - unity * 9/3)
        path.lineTo(unity * 8/3, height - unity * 13/3)

        path.lineTo(unity * 8/3, unity * 13/3)
        path.close()

        it.drawPath(path, paint)
    }

    /**
     *  ACTION
     */

    fun defineTextBtn(text: String){
        mText = text
        invalidate()
    }




}