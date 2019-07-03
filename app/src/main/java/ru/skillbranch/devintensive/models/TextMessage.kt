package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.format
import java.util.*

class TextMessage(
    id: String,
    from: User?,
    chat: Chat,
    isIncoming: Boolean,
    date: Date = Date(),
    val text:String?
) : BaseMessage(id, from, chat, isIncoming, date) {
    override fun formatMessage() = "id:$id ${from?.firstName.orEmpty()} ${if(isIncoming) "получил" else "отправил"} сообщение \"$text\" ${date.format()}"
}