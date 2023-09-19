package com.kwdev.adventofcode._2022

import java.io.File

fun main() {
    println("p1 ${resolve2022d5p1()}")
    println("p1 ${resolve2022d5p2()}")
}

fun resolve2022d5p1(): String {
    val input = File("input/2022/5.txt").readLines()

    val stacks = parseStacksInitialState(input)
    parseInstructions(input).forEach { (amount, from, to) ->
        val boxes = stacks[from].takeLast(amount)
        stacks[from] = stacks[from].dropLast(amount)
        stacks[to] = stacks[to] + boxes.reversed()
    }

    return stacks.joinToString(separator = "") { "${it.lastOrNull() ?: ""}" }
}

fun resolve2022d5p2(): String {
    val input = File("input/2022/5.txt").readLines()

    val stacks = parseStacksInitialState(input)
    parseInstructions(input).forEach { (amount, from, to) ->
        val boxes = stacks[from].takeLast(amount)
        stacks[from] = stacks[from].dropLast(amount)
        stacks[to] = stacks[to] + boxes
    }

    return stacks.joinToString(separator = "") { "${it.lastOrNull() ?: ""}" }
}

private fun parseStacksInitialState(input: List<String>): Array<List<Char>> {
    val separatorIndex = input.indexOfFirst { it.isEmpty() }

    return input
        .slice(0..(separatorIndex - 2))
        .map { row ->
            row.windowed(size = 3, step = 4) { it[1] }
        }
        .let { data ->
            Array(data.last().size) { index ->
                data.map { it.getOrNull(index) ?: ' ' }
                    .filter { it != ' ' }
                    .reversed()
            }
        }
}

private fun parseInstructions(input: List<String>): List<Triple<Int, Int, Int>> {
    val separatorIndex = input.indexOfFirst { it.isEmpty() }

    return input
        .slice(separatorIndex.inc()..input.lastIndex)
        .map { row ->
            val (first, second, third) = row
                .split("move ", " from ", " to ")
                .drop(1)
                .map { it.toInt() }
            Triple(first, second - 1, third - 1)
        }
}
