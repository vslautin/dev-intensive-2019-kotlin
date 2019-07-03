package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.format
import java.util.*

class ImageMessage(id: String,
                   from: User?,
                   chat: Chat,
                   isIncoming: Boolean,
                   date: Date = Date(),
                   var image:String?
)
    : BaseMessage(id, from, chat, isIncoming, date) {
    override fun formatMessage() = "id:$id ${from?.firstName} ${if(isIncoming) "получил" else "отправил"} $image ${date.format()}"

}