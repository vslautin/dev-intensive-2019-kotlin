package ru.skillbranch.devintensive.utils

object Utils{
    fun parseFullName(fullString: String?):Pair<String?, String?> {
        val fullName = fullString?.trim()
        val first = fullName?.substringBefore(" ")?.ifBlank { null }
        val second = if(first == null) null else fullName?.substringAfter(" ", "")?.ifBlank { null }
        return Pair(first, second)
    }

    fun toInitials(firstName:String?, lastName:String?): String{
        val firstLetter:String = firstName?.take(1)?.capitalize().orEmpty()
        val secondLetter:String = lastName?.take(1)?.capitalize().orEmpty()
        return "$firstLetter$secondLetter".ifEmpty { "null" }
    }

    fun transliteration(payload:String, divider:String = " "): String{
        val result:StringBuilder = StringBuilder()
        payload.forEach { result.append(dictionary.get<String, String>(it.toString())?: it )}
        return if(divider != " ") result.toString().replace(" ", divider) else result.toString()
    }

    val dictionary:HashMap<String, String> = hashMapOf(
        "а" to "a",
        "б" to "b",
        "в" to "v",
        "г" to "g",
        "д" to "d",
        "е" to "e",
        "ё" to "e",
        "ж" to "zh",
        "з" to "z",
        "и" to "i",
        "й" to "i",
        "к" to "k",
        "л" to "l",
        "м" to "m",
        "н" to "n",
        "о" to "o",
        "п" to "p",
        "р" to "r",
        "с" to "s",
        "т" to "t",
        "у" to "u",
        "ф" to "f",
        "х" to "h",
        "ц" to "c",
        "ч" to "ch",
        "ш" to "sh",
        "щ" to "sh'",
        "ъ" to "",
        "ы" to "i",
        "ь" to "",
        "э" to "e",
        "ю" to "yu",
        "я" to "ya",
        "Р" to "R",
        "С" to "S",
        "Т" to "T",
        "У" to "U",
        "Ф" to "F",
        "Х" to "H",
        "Ц" to "C",
        "Ч" to "Ch",
        "Ш" to "Sh",
        "Щ" to "Sh'",
        "Ъ" to "",
        "Ы" to "I",
        "Ь" to "",
        "Э" to "E",
        "Ю" to "Yu",
        "Я" to "Ya",
        "Ё" to "E",
        "А" to "A",
        "Б" to "B",
        "В" to "V",
        "Г" to "G",
        "Д" to "D",
        "Е" to "E",
        "Ж" to "Zh",
        "З" to "Z",
        "И" to "I",
        "Й" to "I",
        "К" to "K",
        "Л" to "L",
        "М" to "M",
        "Н" to "N",
        "О" to "O",
        "П" to "P"
    )
}