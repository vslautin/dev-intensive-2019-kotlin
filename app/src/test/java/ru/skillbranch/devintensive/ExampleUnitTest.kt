package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.models.User
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
    fun test_user_factory(){
        val user1 = User.makeUser("Adolf   Putin ")
        val user2 = User.makeUser(  " ")
        val user3 = User.makeUser(" Gitlar")

        println("$user1\n\n$user2\n\n$user3")
        fun getUserInfo(): User? = null// = user1
        //val (a, b, c) = user1
        val userInfo = getUserInfo()
        getUserInfo()
    }
}
