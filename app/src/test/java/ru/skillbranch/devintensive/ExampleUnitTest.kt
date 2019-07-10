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
    fun test_user_instance() {
        val user1 = User("1", "John", "Wick")
        val user2 = User("2", "John", "Wick")
        val user3 = User("3", "John", "Mnemonic", null, lastVisit = Date(), isOnline = true)

        println("$user1\n\n$user2\n\n$user3")
    }
    @Test
    fun test_user_builder() {
        val date = Date()
        val user01 = User.Builder().id("1").firstName("John").lastName("Wick").build()
        val user03 = User.Builder().id("3").firstName("John").lastName("Mnemonic").lastVisit(date).isOnline(true).build()

        val user1 = User("1", "John", "Wick")
        val user3 = User("3", "John", "Mnemonic", null, lastVisit = date, isOnline = true)

        println("$user01\n\n$user03")
        println("$user1\n\n$user3")

        assertEquals(user01, user1)
        assertEquals(user03, user3)
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
            type = "image",
            payload = "thats_it_questionmark.jpg",
            isIncoming = true,
            date = date.add(1, TimeUnits.HOUR)
        )
        println(textMessage.formatMessage())
        println(imageMessage.formatMessage())
        println(responseImageMessage.formatMessage())

        assertEquals(textMessage.formatMessage(), "id:0 Tupak отправил сообщение \"Hi! How r u?\" ${date.format()}")
        assertEquals(imageMessage.formatMessage(), "id:1 Lara получил изображение \"big_dick.jpg\" ${date.add(1, TimeUnits.MINUTE).format()}")
        assertEquals(responseImageMessage.formatMessage(), "id:2 Tupak получил изображение \"thats_it_questionmark.jpg\" ${date.add(1, TimeUnits.HOUR).format()}")



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
        assertEquals( "2 часа назад", currentDate.add(-2, TimeUnits.HOUR).humanizeDiff(currentDate))
        assertEquals( "5 дней назад", currentDate.add(-5, TimeUnits.DAY).humanizeDiff(currentDate))
        assertEquals( "через 2 минуты", currentDate.add(2, TimeUnits.MINUTE).humanizeDiff(currentDate))
        assertEquals( "через 7 дней", currentDate.add(7, TimeUnits.DAY).humanizeDiff(currentDate))
//        assertEquals( "1 год назад", currentDate.add(-400, TimeUnits.DAY).humanizeDiff(currentDate))
//        assertEquals( "через 1 год", currentDate.add(400, TimeUnits.DAY).humanizeDiff(currentDate))
//        assertEquals( "30 секунд назад", currentDate.add(-30, TimeUnits.SECOND).humanizeDiff(currentDate))
        assertEquals( "более года назад", currentDate.add(-400, TimeUnits.DAY).humanizeDiff(currentDate))
        assertEquals( "более чем через год", currentDate.add(400, TimeUnits.DAY).humanizeDiff(currentDate))
        assertEquals( "несколько секунд назад", currentDate.add(-30, TimeUnits.SECOND).humanizeDiff(currentDate))
    }

    @Test
    fun test_util_transliteration() {
        assertEquals(Utils.transliteration("Женя Стереотипов"), "Zhenya Stereotipov")
        assertEquals(Utils.transliteration("Amazing Петр","_"), "Amazing_Petr")
    }

    @Test
    fun test_util_initials() {
        assertEquals(Utils.toInitials("Adolf", "Putin"), "AP")
        assertEquals(Utils.toInitials("robert", "rodrigez"), "RR")
        assertEquals(Utils.toInitials("", "Zverev"), "Z")
        assertEquals(Utils.toInitials("Gitlar", ""), "G")
        assertEquals(Utils.toInitials(null, "Zverev"), "Z")
        assertEquals(Utils.toInitials("Gitlar", null), "G")
        assertEquals(Utils.toInitials(null, null), null)
        assertEquals(Utils.toInitials(" ", ""), null)
    }

    @Test
    fun test_string_truncate() {
        assertEquals("Bender Bending R...", "Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate())
        assertEquals("Bender Bending...", "Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate(15))
        assertEquals("A", "A     ".truncate(3))
    }

    @Test
    fun test_string_stripHtml() {
        assertEquals("Образовательное IT-сообщество Skill Branch", "<p class=\"title\">Образовательное IT-сообщество Skill Branch</p>".stripHtml())
        assertEquals("Образовательное IT-сообщество Skill Branch", "<p>Образовательное       IT-сообщество Skill Branch</p>".stripHtml())
        assertEquals("HTML Document Этот текст будет полужирным, а этот — ещё и курсивным.", """<!DOCTYPE html>
<html>
   <head>
      <meta charset="utf-8" />
      <title>HTML Document</title>
   </head>
   <body>
      <p>
         <b>
            Этот текст будет полужирным, <i>а этот — ещё и курсивным</i>.
         </b>
      </p>
   </body>
</html>""".stripHtml())

    }

    @Test
    fun test_string_stripBlanks() {
        assertEquals("Образовательное IT-сообщество Skill Branch", " Образовательное     IT-сообщество  Skill   Branch ".stripBlanks())
    }




    @Test
    fun test_user_view() {
        val user1 = User.makeUser("Adolf   Putin ")
        val user2 = User.makeUser(" ")
        val user3 = User.makeUser(" Gitlar")

        println("$user1\n$user2\n$user3")
        println("${user1.getUserView()}\n${user2.getUserView()}\n${user3.getUserView()}")

    }

    @Test
    fun test_timeunits_plural() {
        assertEquals( "30 секунд", TimeUnits.SECOND.plural(30))
        assertEquals( "93 секунды", TimeUnits.SECOND.plural(93))
        assertEquals( "101 секунду", TimeUnits.SECOND.plural(101))
        assertEquals( "2 минуты",TimeUnits.MINUTE.plural(2))
        assertEquals( "55 минут",TimeUnits.MINUTE.plural(55))
        assertEquals( "10001 минуту",TimeUnits.MINUTE.plural(10001))
        assertEquals( "2 часа", TimeUnits.HOUR.plural(2))
        assertEquals( "19 часов", TimeUnits.HOUR.plural(19))
        assertEquals( "103451 час", TimeUnits.HOUR.plural(103451))
        assertEquals( "5 дней", TimeUnits.DAY.plural(5))
        assertEquals( "7 дней",TimeUnits.DAY.plural(7))
        assertEquals( "1 год", TimeUnits.YEAR.plural(1))
        assertEquals( "400 лет", TimeUnits.YEAR.plural(400))
        assertEquals( "42 года", TimeUnits.YEAR.plural(42))
    }
}
