package ru.skillbranch.devintensive.extensions

fun String.truncate(size:Int = 16):String{
    if(this.trimEnd().length <= size)
        return this.trimEnd()
    return this.substring(0, size).trimEnd().plus("...")
}

fun String.stripHtml():String{
    return this.stripTags().stripMnemonics().stripBlanks()
}

fun String.stripTags():String{
    return this.replace(regex = "<.+?>".toRegex(RegexOption.DOT_MATCHES_ALL), replacement = "")
}

fun String.stripMnemonics():String{
    return this.replace(regex = "&.+?;".toRegex(RegexOption.DOT_MATCHES_ALL), replacement = " ") //&lt;, &gt; и &amp;
}

fun String.stripEscapes():String{
    return this.replace(regex = "\\n".toRegex(RegexOption.DOT_MATCHES_ALL), replacement = " ")
}

fun String.stripBlanks():String{
    return this.trim().replace(regex = "\\s{2,}".toRegex(RegexOption.DOT_MATCHES_ALL), replacement = " ")
}