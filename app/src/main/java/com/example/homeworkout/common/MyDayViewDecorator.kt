package com.example.homeworkout.common


import android.graphics.drawable.Drawable
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import java.util.*

class MyDayViewDecorator(val date:Calendar, val drawable: Drawable):DayViewDecorator {
    override fun shouldDecorate(day: CalendarDay?): Boolean {
         return day?.equals(CalendarDay.from(date)) ?:false
    }

    override fun decorate(view: DayViewFacade?) {
        view?.setBackgroundDrawable(drawable)
    }
}