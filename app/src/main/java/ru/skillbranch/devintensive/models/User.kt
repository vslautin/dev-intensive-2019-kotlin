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

    companion object Factory{
        var lastId: Int = -1

        fun makeUser(fullName: String?): User{
            lastId++
            val parts = Utils.parseFullName(fullName)
            return User(id ="$lastId", firstName = parts.first.orEmpty(), lastName = parts.second.orEmpty())
        }
    }

}
