package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.*
import ru.skillbranch.devintensive.models.BaseMessage
import ru.skillbranch.devintensive.models.Chat
import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_user_instance() {
        val user1 = User("1")
        val user2 = User("2", "John", "Wick")
        val user3 = User("3", "John", "Mnemonic", null, lastVisit = Date(), isOnline = true)

        println("$user1\n\n$user2\n\n$user3")
    }

    @Test
    fun test_user_factory() {
        val user1 = User.makeUser("Adolf   Putin ")
        val user2 = User.makeUser(" ")
        val user3 = User.makeUser(" Gitlar")

        /*println("$user1\n\n$user2\n\n$user3")
        fun getUserInfo(): User? = null// = user1
        val (a, b, c) = user1
        val userInfo = getUserInfo()
        getUserInfo()*/
    }

    @Test
    fun test_base_message() {
        val pornoChat = Chat("")
        val user = User.makeUser("Tupak Ivanov")
        val user2 = User.makeUser("Lara Croft")
        val date = Date()
        var textMessage = BaseMessage.makeMessage(user, pornoChat, type = "text", payload = "Hi! How r u?", date = date)
        var imageMessage =
            BaseMessage.makeMessage(user2, pornoChat, type = "image", payload = "big_dick.jpg", isIncoming = true, date = date.add(1, TimeUnits.MINUTE))
        var responseImageMessage = BaseMessage.makeMessage(
            user,
            pornoChat,
            type = "text",
            payload = "thats_it_questionmark.jpg",
            isIncoming = true,
            date = date.add(1, TimeUnits.HOUR)
        )
        println(textMessage.formatMessage())
        println(imageMessage.formatMessage())
        println(responseImageMessage.formatMessage())
    }

    @Test
    fun test_date_add() {
        var now = Date()
        var yesterday = now.add(-1, TimeUnits.DAY)
        println("""
            $now.format()
            $yesterday.format()
        """.trimIndent()
        )
    }

    @Test
    fun test_date_humanizeDiff() {
        val currentDate = Date()
        assertEquals(currentDate.add(-2, TimeUnits.HOUR).humanizeDiff(currentDate), "2 часа назад")
        assertEquals(currentDate.add(-5, TimeUnits.DAY).humanizeDiff(currentDate), "5 дней назад")
        assertEquals(currentDate.add(2, TimeUnits.MINUTE).humanizeDiff(currentDate), "через 2 минуты")
        assertEquals(currentDate.add(7, TimeUnits.DAY).humanizeDiff(currentDate), "через 7 дней")
        assertEquals(currentDate.add(-400, TimeUnits.DAY).humanizeDiff(currentDate), "более года назад")
        assertEquals(currentDate.add(400, TimeUnits.DAY).humanizeDiff(currentDate), "более чем через год")
    }

    @Test
    fun test_util_transliteration() {
        assertEquals(Utils.transliteration("Женя Стереотипов"), "Zhenya Stereotipov")
        assertEquals(Utils.transliteration("Amazing Петр","_"), "Amazing_Petr")
    }

    @Test
    fun test_util_initials() {
        assertEquals(Utils.toInitials("Adolf", "Putin"), "AP")
        assertEquals(Utils.toInitials("", "Zverev"), "Z")
        assertEquals(Utils.toInitials("Gitlar", ""), "G")
        assertEquals(Utils.toInitials(null, "Zverev"), "Z")
        assertEquals(Utils.toInitials("Gitlar", null), "G")
        assertEquals(Utils.toInitials(null, null), "null")
        assertEquals(Utils.toInitials("", ""), "null")

    }




    @Test
    fun test_user_view() {
        val user1 = User.makeUser("Adolf   Putin ")
        val user2 = User.makeUser(" ")
        val user3 = User.makeUser(" Gitlar")

        println("$user1\n$user2\n$user3")
        println("${user1.getUserView()}\n${user2.getUserView()}\n${user3.getUserView()}")

    }
}
