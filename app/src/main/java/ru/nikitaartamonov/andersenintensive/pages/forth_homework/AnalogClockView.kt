package ru.nikitaartamonov.andersenintensive.pages.forth_homework

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import ru.nikitaartamonov.andersenintensive.R
import java.util.*
import kotlin.math.cos
import kotlin.math.sin

private const val DEFAULT_FRAME_STROKE_WIDTH = 10
private const val DEFAULT_FRAME_COLOR = Color.CYAN
private const val DEFAULT_SECOND_ARROW_COLOR = Color.RED
private const val DEFAULT_MINUTE_ARROW_COLOR = Color.GREEN
private const val DEFAULT_HOUR_ARROW_COLOR = Color.BLUE
private const val DEFAULT_SECOND_ARROW_WIDTH = 2
private const val DEFAULT_MINUTE_ARROW_WIDTH = 3
private const val DEFAULT_HOUR_ARROW_WIDTH = 6
private const val DEFAULT_STICK_WIDTH = 5
private const val DEFAULT_SECOND_ARROW_PADDING_RATIO = 0.9f
private const val DEFAULT_MINUTE_ARROW_PADDING_RATIO = 0.8f
private const val DEFAULT_HOUR_ARROW_PADDING_RATIO = 0.6f

class AnalogClockView @JvmOverloads constructor(
    context: Context,
    private var attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var frameColor = DEFAULT_FRAME_COLOR
    private var frameStrokeWidth = context.dpToPx(DEFAULT_FRAME_STROKE_WIDTH)
    private var secondArrowColor = DEFAULT_SECOND_ARROW_COLOR
    private var minuteArrowColor = DEFAULT_MINUTE_ARROW_COLOR
    private var hourArrowColor = DEFAULT_HOUR_ARROW_COLOR
    private var secondArrowWidth = context.dpToPx(DEFAULT_SECOND_ARROW_WIDTH)
    private var minuteArrowWidth = context.dpToPx(DEFAULT_MINUTE_ARROW_WIDTH)
    private var hourArrowWidth = context.dpToPx(DEFAULT_HOUR_ARROW_WIDTH)
    private var stickWidth = context.dpToPx(DEFAULT_STICK_WIDTH)
    private var secondArrowPaddingRatio = DEFAULT_SECOND_ARROW_PADDING_RATIO
    private var minuteArrowPaddingRatio = DEFAULT_MINUTE_ARROW_PADDING_RATIO
    private var hourArrowPaddingRatio = DEFAULT_HOUR_ARROW_PADDING_RATIO

    private val framePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val centralCirclePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val stickPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val secondArrowPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val minuteArrowPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val hourArrowPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val viewRect = Rect()

    private var radius: Float = 0f

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.AnalogClockView)
            frameColor = typedArray.getColor(
                R.styleable.AnalogClockView_frameColor, DEFAULT_FRAME_COLOR
            )
            frameStrokeWidth = typedArray.getDimension(
                R.styleable.AnalogClockView_frameStrokeWidth,
                context.dpToPx(DEFAULT_FRAME_STROKE_WIDTH)
            )
            secondArrowColor = typedArray.getColor(
                R.styleable.AnalogClockView_secondArrowColor,
                DEFAULT_SECOND_ARROW_COLOR
            )
            minuteArrowColor = typedArray.getColor(
                R.styleable.AnalogClockView_minuteArrowColor,
                DEFAULT_MINUTE_ARROW_COLOR
            )
            hourArrowColor = typedArray.getColor(
                R.styleable.AnalogClockView_hourArrowColor,
                DEFAULT_HOUR_ARROW_COLOR
            )
            secondArrowWidth = typedArray.getDimension(
                R.styleable.AnalogClockView_secondArrowWidth,
                context.dpToPx(DEFAULT_SECOND_ARROW_WIDTH)
            )
            minuteArrowWidth = typedArray.getDimension(
                R.styleable.AnalogClockView_minuteArrowWidth,
                context.dpToPx(DEFAULT_MINUTE_ARROW_WIDTH)
            )
            hourArrowWidth = typedArray.getDimension(
                R.styleable.AnalogClockView_hourArrowWidth,
                context.dpToPx(DEFAULT_HOUR_ARROW_WIDTH)
            )
            stickWidth = typedArray.getDimension(
                R.styleable.AnalogClockView_stickWidth,
                context.dpToPx(DEFAULT_STICK_WIDTH)
            )
            secondArrowPaddingRatio = typedArray.getDimension(
                R.styleable.AnalogClockView_secondArrowPaddingRatio,
                DEFAULT_SECOND_ARROW_PADDING_RATIO
            )
            minuteArrowPaddingRatio = typedArray.getDimension(
                R.styleable.AnalogClockView_minuteArrowPaddingRatio,
                DEFAULT_MINUTE_ARROW_PADDING_RATIO
            )
            hourArrowPaddingRatio = typedArray.getDimension(
                R.styleable.AnalogClockView_hourArrowPaddingRatio,
                DEFAULT_HOUR_ARROW_PADDING_RATIO
            )
            setup()
            typedArray.recycle()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (w == 0) return
        radius = w / 2f - frameStrokeWidth
        with(viewRect) {
            left = 0
            top = 0
            right = w
            bottom = h
        }
    }

    override fun onDraw(canvas: Canvas) {
        drawFrame(canvas)
        drawSticks(canvas)
        drawArrows(canvas)
        drawCentralCircle(canvas)
        postInvalidateDelayed(1000)
    }

    private fun drawSticks(canvas: Canvas) {
        for (i in 0..12) {
            val angle = Math.PI / 6 * i
            val x1 = (width / 2 + cos(angle) * (radius)).toFloat()
            val x2 = (width / 2 + cos(angle) * (radius * 0.9)).toFloat()
            val y1 = (height / 2 + sin(angle) * (radius)).toFloat()
            val y2 = (height / 2 + sin(angle) * (radius * 0.9)).toFloat()
            canvas.drawLine(x1, y1, x2, y2, stickPaint)
        }
    }

    private fun drawCentralCircle(canvas: Canvas) {
        canvas.drawCircle(canvas.width / 2f, canvas.height / 2f, hourArrowWidth, centralCirclePaint)
    }

    private fun drawArrows(canvas: Canvas) {
        val calendar = Calendar.getInstance()
        val second = calendar.get(Calendar.SECOND).toFloat()
        val minute = calendar.get(Calendar.MINUTE).toFloat()
        val hour = calendar.get(Calendar.HOUR_OF_DAY) % 12.toFloat()
        drawArrow(canvas, (hour + minute / 60) * 5, ArrowType.HOUR)
        drawArrow(canvas, minute + second / 50, ArrowType.MINUTE)
        drawArrow(canvas, second, ArrowType.SECOND)
    }

    private fun drawArrow(canvas: Canvas, position: Float, arrowType: ArrowType) {
        val angle = Math.PI * position / 30 - Math.PI / 2
        val paint: Paint
        val currentRadius: Float

        when (arrowType) {
            ArrowType.SECOND -> {
                paint = secondArrowPaint
                currentRadius = radius * secondArrowPaddingRatio
            }
            ArrowType.MINUTE -> {
                paint = minuteArrowPaint
                currentRadius = radius * minuteArrowPaddingRatio
            }
            ArrowType.HOUR -> {
                paint = hourArrowPaint
                currentRadius = radius * hourArrowPaddingRatio
            }
        }
        canvas.drawLine(
            width / 2f,
            height / 2f,
            (width / 2 + cos(angle) * currentRadius).toFloat(),
            (height / 2 + sin(angle) * currentRadius).toFloat(),
            paint
        )
    }

    private fun drawFrame(canvas: Canvas) {
        canvas.drawCircle(canvas.width / 2f, canvas.height / 2f, radius, framePaint)
    }

    private fun setup() {
        with(framePaint) {
            style = Paint.Style.STROKE
            strokeWidth = frameStrokeWidth
            color = frameColor
        }
        with(secondArrowPaint) {
            style = Paint.Style.FILL
            strokeWidth = secondArrowWidth
            color = secondArrowColor
        }
        with(minuteArrowPaint) {
            style = Paint.Style.FILL
            strokeWidth = minuteArrowWidth
            color = minuteArrowColor
        }
        with(hourArrowPaint) {
            style = Paint.Style.FILL
            strokeWidth = hourArrowWidth
            color = hourArrowColor
        }
        with(centralCirclePaint) {
            style = Paint.Style.FILL
            strokeWidth = hourArrowWidth
            color = secondArrowColor
        }
        with(stickPaint) {
            style = Paint.Style.FILL
            strokeWidth = stickWidth
            color = frameColor
        }
    }
}

fun Context.dpToPx(dp: Int): Float {
    return dp.toFloat() * this.resources.displayMetrics.density
}

enum class ArrowType {
    SECOND, MINUTE, HOUR
}