package com.fanmaum.myroomz.utils.watcher

val specialCharactersPattern = "[\\p{L}0-9]+".toRegex()
val recommendCharactersPattern = "[!@$%^&*()_+\\s\\t\\[\\]{};:\'\",.<>/?|`~]".toRegex()
val emojiPattern = "[\u2700-\u27BF]|[\uE000-\uF8FF]|\uD83C[\uDC00-\uDFFF]|\uD83D[\uDC00-\uDFFF]|[\u2011-\u26FF]|\uD83E[\uDD10-\uDDFF]".toRegex()
val specialCharactersAndEmojiPattern = "$specialCharactersPattern".toRegex()
val recommendCharactersAndEmojiPattern = "$emojiPattern|$recommendCharactersPattern".toRegex()