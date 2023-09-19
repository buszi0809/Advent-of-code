package com.kwdev.adventofcode._2022

import java.io.File

fun main() {
    println("p1 ${resolve2022d3p1()}")
    println("p2 ${resolve2022d3p2()}")
}

fun resolve2022d3p1(): Int {
    val input = File("input/2022/3.txt").readLines()

    return input
        .mapNotNull { line ->
            val first = line.slice(0..(line.lastIndex / 2))
            val second = line.slice((line.lastIndex / 2 + 1)..line.lastIndex)
            first.find { second.contains(it) }
        }
        .sumOf { resolveScore(it) }
}

fun resolve2022d3p2(): Int {
    val input = File("input/2022/3.txt").readLines()

    return input
        .windowed(size = 3, step = 3) { (first, second, third) ->
            first.first { second.contains(it) && third.contains(it) }
        }
        .sumOf { resolveScore(it) }
}

private fun resolveScore(char: Char) =
    if (char.isUpperCase()) {
        char.code - 38
    } else {
        char.code - 96
    }
