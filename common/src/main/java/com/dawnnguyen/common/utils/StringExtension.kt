package com.dawnnguyen.common.utils

import java.util.regex.Pattern

fun String.isValidEmail(): Boolean {
    return this.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

// This ext fun validate password with rule >=6 char, at least 1 special char, 1 number, 1 normal char, 1 capitalize char
fun String.isValidPassword(): Boolean{
    val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!\"#\$%&\'(\\]\\[)*+,-.\\\\/\\:;<=>?@^_`{|}~])[A-Za-z\\d!\"#\$%&\'(\\]\\[)*+,-.\\\\/\\:;<=>?@^_`{|}~]{6,255}\$"
    val pattern = Pattern.compile(passwordPattern)
    return this.isNotEmpty() && pattern.matcher(this).matches()
}

fun String.convertToUnsignedWord(): String {
    var str = this
    str = str.replace("[àáạảãâầấậẩẫăằắặẳẵ]".toRegex(), "a")
    str = str.replace("[èéẹẻẽêềếệểễ]".toRegex(), "e")
    str = str.replace("[ìíịỉĩ]".toRegex(), "i")
    str = str.replace("[òóọỏõôồốộổỗơờớợởỡ]".toRegex(), "o")
    str = str.replace("[ùúụủũưừứựửữ]".toRegex(), "u")
    str = str.replace("[ỳýỵỷỹ]".toRegex(), "y")
    str = str.replace("đ".toRegex(), "d")
    str = str.replace("[ÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴ]".toRegex(), "A")
    str = str.replace("[ÈÉẸẺẼÊỀẾỆỂỄ]".toRegex(), "E")
    str = str.replace("[ÌÍỊỈĨ]".toRegex(), "I")
    str = str.replace("[ÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠ]".toRegex(), "O")
    str = str.replace("[ÙÚỤỦŨƯỪỨỰỬỮ]".toRegex(), "U")
    str = str.replace("[ỲÝỴỶỸ]".toRegex(), "Y")
    str = str.replace("Đ".toRegex(), "D")
    return str
}
