package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.format
import java.util.*

class ImageMessage(id: String,
                   from: User?,
                   chat: Chat,
                   isIncoming: Boolean,
                   date: Date = Date(),
                   val image:String?
)
    : BaseMessage(id = id, from = from, chat = chat, isIncoming = isIncoming, date = date) {
    override fun formatMessage() = "id:$id ${from?.firstName.orEmpty()} ${if(isIncoming) "получил" else "отправил"} изображение \"$image\" ${date.format()}"

}