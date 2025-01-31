package com.route.todoc41.ui.utils

import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.Calendar

fun Calendar.year(): Int {
    return this.get(Calendar.YEAR)
}

fun Calendar.month(): Int {
    return this.get(Calendar.MONTH)
}

fun Calendar.day(): Int {
    return this.get(Calendar.DAY_OF_MONTH)
}

fun Calendar.updateDate(year: Int, month: Int, day: Int) {
    set(Calendar.YEAR, year)
    set(Calendar.MONTH, month)
    set(Calendar.DAY_OF_MONTH, day)
}
//fun Calendar.clearTime(){
//    set(Calendar.YEAR, year)
//    set(Calendar.MONTH, month)
//    set(Calendar.DAY_OF_MONTH, day)
//}

fun CalendarDay.toMillSeconds(): Long {
    val calendar = Calendar.getInstance()
    calendar.updateDate(this.year, this.month - 1, this.day)
    calendar.clear(Calendar.HOUR)
    calendar.clear(Calendar.MINUTE)
    calendar.clear(Calendar.SECOND)
    calendar.clear(Calendar.MILLISECOND)
    return calendar.timeInMillis
}
