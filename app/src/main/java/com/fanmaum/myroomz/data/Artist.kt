package com.fanmaum.myroomz.data

data class Artist(
    val name: String,
    val img: String,
    val group: String?,
    var isSelect: Boolean,

    var drawableSampleImage : Int?=null
)
