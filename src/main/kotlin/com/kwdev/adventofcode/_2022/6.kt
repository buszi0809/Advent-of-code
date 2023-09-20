package com.kwdev.adventofcode._2022

import java.io.File

fun main() {
    println("p1 ${resolve2022d6p1()}")
    println("p2 ${resolve2022d6p2()}")
}

fun resolve2022d6p1(): Int {
    val input = File("input/2022/6.txt").readText()

    return input
        .windowed(4)
        .first { window ->
            window.toSet().size == window.length
        }
        .let {
            input.indexOf(it) + 4
        }
}

fun resolve2022d6p2(): Int {
    val input = File("input/2022/6.txt").readText()

    return input
        .windowed(14)
        .first { window ->
            window.toSet().size == window.length
        }
        .let {
            input.indexOf(it) + 14
        }
}
