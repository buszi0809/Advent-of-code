package com.kwdev.adventofcode._2022

import java.io.File

fun main() {
    println("p1 ${resolve2022d4p1()}")
    println("p2 ${resolve2022d4p2()}")
}

fun resolve2022d4p1(): Int {
    val input = File("input/2022/4.txt").readLines()

    return input.fold(0) { acc, line ->
        val (first, second) = parsePair(line)
        if (first.contains(second) || second.contains(first)) {
            acc + 1
        } else {
            acc
        }
    }
}

fun resolve2022d4p2(): Int {
    val input = File("input/2022/4.txt").readLines()

    return input.fold(0) { acc, line ->
        val (first, second) = parsePair(line)
        if (first.overlaps(second)) {
            acc + 1
        } else {
            acc
        }
    }
}

private fun IntRange.contains(another: IntRange): Boolean =
    first <= another.first && last >= another.last

private fun IntRange.overlaps(another: IntRange): Boolean = intersect(another).isNotEmpty()

private fun parsePair(line: String) = line.split(',').map { parseRange(it) }

private fun parseRange(input: String): IntRange {
    val (first, second) = input.split('-').map { it.toInt() }
    return IntRange(first, second)
}
