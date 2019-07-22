package ru.skillbranch.devintensive.models

class Bender(private var status: Status = Status.NORMAL, private var question: Question = Question.NAME) {

    fun askQuestion(): String = question.question

    fun checkStatus() = status.color

    fun listenAnswer(answer: String): String {
        return if (question.answers.contains(answer)) {
            question = question.next()
            "You right"
        } else {
            status = status.next()
            "Terribly wrong"
        }
    }


    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 255, 0));

        fun next() = when {
            this == values().last() -> this
            else -> values()[this.ordinal + 1]
        }
    }

    enum class Question(val question: String, val answers: List<String>) {
        NAME("whats my name?", listOf("bender")),
        PROFESSION("whats my profession", listOf("banger")),
        MATERIAL("what my body is made of", listOf("metal")),
        BDAY("whats my b-day", listOf("2000")),
        SERIAL("whats my serial number", listOf("31337")),
        IDLE("thats enough", listOf(""));

        fun next() = when {
            this == values().last() -> this
            else -> values()[this.ordinal + 1]
        }

    }
}