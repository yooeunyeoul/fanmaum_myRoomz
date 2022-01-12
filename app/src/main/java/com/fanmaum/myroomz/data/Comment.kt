package com.fanmaum.myroomz.data

data class Comment(
    val name: String,
    val profileImage: Any,
    val comment:String,
    val date : String,
    val one: One,
    val two: Two,
    val three: Three,
    val four: Four,
    val five: Five,
    val six: Six,
    val seven: Seven
)


data class One(val checked : Boolean)
data class Two(val checked : Boolean)
data class Three(val checked : Boolean)
data class Four(val checked : Boolean)
data class Five(val checked : Boolean)
data class Six(val checked : Boolean)
data class Seven(val checked : Boolean)

