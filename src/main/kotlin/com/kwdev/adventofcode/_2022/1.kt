package com.kwdev.adventofcode._2022

import com.kwdev.adventofcode.windowedBy
import java.io.File

fun main() {
    println("p1 ${resolve2022d1p1()}")
    println("p2 ${resolve2022d1p2()}")
}

fun resolve2022d1p1(): Int {
    val input = File("input/2022/1.txt").readLines()

    return input
        .windowedBy { it.isEmpty() }
        .map { window -> window.map { it.toInt() } }
        .maxOf { it.sum() }
}

fun resolve2022d1p2(): Int {
    val input = File("input/2022/1.txt").readLines()

    return input
        .windowedBy { it.isEmpty() }
        .map { window -> window.map { it.toInt() } }
        .sortedByDescending { it.sum() }
        .take(3)
        .sumOf { it.sum() }
}
