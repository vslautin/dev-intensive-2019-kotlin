package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*


data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = null,
    val isOnline: Boolean = false
) {
    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    companion object Factory {
        var lastId: Int = -1

        fun makeUser(fullName: String?): User {
            lastId++
            val parts = Utils.parseFullName(fullName)
            return User(id = "$lastId", firstName = parts.first.orEmpty(), lastName = parts.second.orEmpty())
        }

        fun makeUser(
            firstName: String?,
            lastName: String?,
            avatar: String?,
            rating: Int?,
            respect: Int?,
            lastVisit: Date?,
            isOnline: Boolean?
        ): User {
            lastId++

            return User("$lastId", firstName, lastName, avatar, rating!!, respect!!, lastVisit!!, isOnline!!)
        }

    }

    class Builder {

        var id: String = ""
        var firstName: String? = null
        var lastName: String? = null
        var avatar: String? = null
        var rating: Int = 0
        var respect: Int = 0
        var lastVisit: Date? = null
        var isOnline: Boolean = false

        fun id(id: String):Builder {
            this.id = id
            return this
        }

        fun firstName(firstName: String):Builder {
            this.firstName = firstName
            return this
        }

        fun lastName(lastName: String):Builder {
            this.lastName = lastName
            return this
        }

        fun avatar(avatar: String):Builder {
            this.avatar = avatar
            return this
        }

        fun rating(rating: Int):Builder {
            this.rating = rating
            return this
        }

        fun respect(respect: Int):Builder {
            this.respect = respect
            return this
        }

        fun lastVisit(lastVisit: Date):Builder {
            this.lastVisit = lastVisit
            return this
        }

        fun isOnline(isOnline: Boolean):Builder {
            this.isOnline = isOnline
            return this
        }

        fun build(): User {
            //return makeUser(firstName = firstName, lastName = lastName,avatar = avatar,rating = rating,respect = respect,lastVisit = lastVisit,isOnline = isOnline)
            return User(id, firstName, lastName, avatar, rating, respect, lastVisit, isOnline)
        }
    }

}
