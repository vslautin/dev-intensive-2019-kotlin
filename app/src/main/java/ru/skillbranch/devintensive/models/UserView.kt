package ru.skillbranch.devintensive.models

data class UserView(
    val id:String,
    val fullName:String,
    val nickName:String,
    var avatar:String?,
    var status:String = "offline",
    val initials:String?
)