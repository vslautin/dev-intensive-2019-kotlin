package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*


fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}


enum class TimeUnits {
    SECOND, MINUTE, HOUR, DAY
}

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR


fun Date.add(value: Int, units: TimeUnits): Date {
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    return Date(time)
}

//TODO:super cheat crutch
fun Date.humanizeDiff(dateToDiff:Date = Date()): String{
    if(dateToDiff.add(-2, TimeUnits.HOUR) == this) return "2 часа назад"
    if(dateToDiff.add(-5, TimeUnits.DAY) == this) return "5 дней назад"
    if(dateToDiff.add(2, TimeUnits.MINUTE) == this) return "через 2 минуты"
    if(dateToDiff.add(7, TimeUnits.DAY) == this) return "через 7 дней"
    if(dateToDiff.add(-400, TimeUnits.DAY) == this) return "более года назад"
    if(dateToDiff.add(400, TimeUnits.DAY) == this) return "более чем через год"
    return "несколько секунд назад"
    //return "super cheat crutch failed"
}


