package com.srikandi.common.extensions

inline fun <reified T> clazz() = T::class.java

inline fun <reified T> tag() = T::class.java.simpleName

fun Int?.orZero(): Int = this ?: 0

fun Long?.orZero(): Long = this ?: 0L

fun Float?.orZero(): Float = this ?: 0F

fun Double?.orZero(): Double = this ?: 0.0

fun Boolean?.orFalse(): Boolean = this ?: false
