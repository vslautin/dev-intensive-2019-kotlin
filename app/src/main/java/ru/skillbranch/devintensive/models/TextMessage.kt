package ru.skillbranch.devintensive.models

import java.util.*

class TextMessage(
    id: String,
    from: User?,
    chat: Chat,
    isIncoming: Boolean,
    date: Date = Date(),
    var text:String?
) : BaseMessage(id, from, chat, isIncoming, date) {
    override fun formatMessage() = """
    $id
    $from
    $chat
    $isIncoming
    $date
    $text
    """.trimIndent()
}