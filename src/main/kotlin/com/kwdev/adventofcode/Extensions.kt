package com.kwdev.adventofcode

inline fun <T> List<T>.windowedBy(separatorSelector: (T) -> Boolean): List<List<T>> {
    val list = arrayListOf<List<T>>()
    var lastSeparatorIndex = 0
    forEachIndexed { index, element ->
        val isSeparator = separatorSelector(element)
        if (isSeparator) {
            list.add(subList(lastSeparatorIndex, index).toList())
            lastSeparatorIndex = index + 1
        } else if (index == lastIndex) {
            list.add(subList(lastSeparatorIndex, index + 1).toList())
        }
    }
    return list
}

fun <T> T.println(prefix: String? = null): T {
    if (prefix != null) {
        kotlin.io.println("$prefix $this")
    } else {
        println(this)
    }
    return this
}
