package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*


data class User(
    val id: String,
    var firstName:String?,
    var lastName:String?,
    var avatar:String?,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = null,
    val isOnline: Boolean = false
)   {
    constructor(id: String, firstName:String?, lastName:String?): this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    constructor(id: String): this(id, "John", "Doe $id")

    init{
        println("Hail Gydra")
    }

    /*override fun toString(): String {
        return("""
                id: $id
                firstName: $firstName
                lastName: $lastName
                avatar: $avatar
                rating: $rating
                respect: $respect
                lastVisit: $lastVisit
                isOnline: $isOnline
                """.trimIndent())
    }*/

    companion object Factory{
        var lastId: Int = -1

        fun makeUser(fullName: String?): User{
            lastId++
            val parts = Utils.parseFullString(fullName)
            return User(id ="$lastId", firstName = parts.first?: "No name", lastName = parts.second?: "No surname")
        }
    }

}
