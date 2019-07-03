package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView

fun User.getUserView(): UserView{
    return UserView(id,
        "$firstName $lastName",
        avatar = avatar,
        initials = "${firstName?.trim()?.substring(0, 1)}${lastName?.trim()?.substring(0, 1)}",
        nickName = ""
    )
}