package ru.skillbranch.devintensive.utils

object Utils{
    fun parseFullName(fullString: String?):Pair<String?, String?> {
        val fullName = fullString?.trim()
        val first = fullName?.substringBefore(" ")?.ifBlank { null }
        val second = if(first == null) null else fullName?.substringAfter(" ", "")?.ifBlank { null }
        return Pair(first, second)
    }

}