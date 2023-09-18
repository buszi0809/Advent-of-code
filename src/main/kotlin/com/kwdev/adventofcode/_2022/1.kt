package com.kwdev.adventofcode._2022

import java.io.File

fun main() {
    resolve2022p1().also { println(it) }
}

fun resolve2022p1(): Int {
    val input = File("input/2022/1.txt").readLines()

    return input
        .windowedBy { it.isEmpty() }
        .map { window -> window.map { it.toInt() } }
        .maxOf { it.sum() }
}

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
