package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.format
import java.util.*

class TextMessage(
    id: String,
    from: User?,
    chat: Chat,
    isIncoming: Boolean,
    date: Date = Date(),
    var text:String?
) : BaseMessage(id, from, chat, isIncoming, date) {
    override fun formatMessage() = "id:$id ${from?.firstName} ${if(isIncoming) "получил" else "отправил"} \"$text\" ${date.format()}"
}