package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*


fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}


enum class TimeUnits(val time: Long, private val pluralForms: List<String>) {
    //private val pluralForms: List<String> = List(3)

    SECOND(1000L, listOf("секунду", "секунды", "секунд")),
    MINUTE(60 * TimeUnits.SECOND.time, listOf("минуту", "минуты", "минут")),
    HOUR(60 * TimeUnits.MINUTE.time, listOf("час", "часа", "часов")),
    DAY(24 * TimeUnits.HOUR.time, listOf("день", "дня", "дней")),
    MONTH(31 * TimeUnits.DAY.time, listOf("месяц", "месяца", "месяцев")),
    YEAR(366 * TimeUnits.DAY.time, listOf("год", "года", "лет"));

    open fun plural(value: Int): String {
        val meaningfulValue: Int = if (value % 100 < 20) value % 100 else value % 10
        val pluralisedUnit = when (meaningfulValue) {
            1 -> pluralForms[0]; 2, 3, 4 -> pluralForms[1]; else -> pluralForms[2]
        }
        //else is 0L,5L,6L,7L,8L,9L,10L,11L,12L,13L,14L,15L,16L,17L,18L,19L
        return "$value $pluralisedUnit"
    }
}


fun Date.add(value: Int, units: TimeUnits): Date {
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * TimeUnits.SECOND.time
        TimeUnits.MINUTE -> value * TimeUnits.MINUTE.time
        TimeUnits.HOUR -> value * TimeUnits.HOUR.time
        TimeUnits.DAY -> value * TimeUnits.DAY.time
        TimeUnits.MONTH -> value * TimeUnits.MONTH.time
        TimeUnits.YEAR -> value * TimeUnits.YEAR.time
    }
    return Date(time)
}


fun Date.humanizeDiff(dateToDiff: Date = Date()): String {
    val thisWasBefore: Boolean = this.before(dateToDiff)
    val beforeAfter: String = if (thisWasBefore) "назад" else "через"
    val diff: Long = if (thisWasBefore) dateToDiff.time - this.time
    else this.time - dateToDiff.time
    val unit: TimeUnits =
        when {
            diff < TimeUnits.MINUTE.time -> TimeUnits.SECOND
            diff < TimeUnits.HOUR.time -> TimeUnits.MINUTE
            diff < TimeUnits.DAY.time -> TimeUnits.HOUR
            diff < TimeUnits.MONTH.time -> TimeUnits.DAY
            diff < TimeUnits.YEAR.time -> TimeUnits.DAY
            else -> TimeUnits.YEAR
        }
    val value: Int = (diff / unit.time).toInt()
    val unitsString =
        if (unit == TimeUnits.SECOND)
            "несколько секунд"
        else unit.plural(value)
    return if (unit == TimeUnits.YEAR)
        if (thisWasBefore) "более года назад" else "более чем через год"
    else
        if (thisWasBefore) "$unitsString $beforeAfter" else "$beforeAfter $unitsString"
}


