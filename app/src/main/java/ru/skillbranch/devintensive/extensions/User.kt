package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.utils.Utils

fun User.getUserView(): UserView{
    return UserView(id,
        "$firstName $lastName",
        avatar = avatar,
        initials = Utils.toInitials(firstName,lastName),
        nickName = ""
    )
}