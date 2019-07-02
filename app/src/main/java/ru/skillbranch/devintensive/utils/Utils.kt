package ru.skillbranch.devintensive.utils

object Utils{
    fun parseFullString(fullString: String?):Pair<String?, String?> {
        val fullName = fullString?.trim()
        val first = fullName?.substringBefore(" ")?.ifBlank { null }
        val second = fullName?.substringAfter(" ", "")?.ifBlank { null }
        return Pair(first, second)
    }

}