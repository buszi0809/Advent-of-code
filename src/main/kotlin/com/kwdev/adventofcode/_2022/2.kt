package com.kwdev.adventofcode._2022

import java.io.File

fun main() {
    println("p1 ${resolve2022d2p1()}")
    println("p2 ${resolve2022d2p2()}")
}

fun resolve2022d2p1(): Int {
    val input = File("input/2022/2.txt").readLines()

    return input.sumOf { line ->
        resolveScore(line[0], line[2])
    }
}

fun resolve2022d2p2(): Int {
    val input = File("input/2022/2.txt").readLines()

    return input
        .map { line ->
            line[0] to resolveRequiredSelection(line[0], line[2])
        }
        .sumOf { (opponent, player) ->
            resolveScore(opponent, player)
        }
}

private fun resolveRequiredSelection(opponent: Char, resolution: Char): Char =
    when (resolution) {
        'X' -> resolveLosingSelection(opponent)
        'Y' -> resolveDrawingSelection(opponent)
        'Z' -> resolveWinningSelection(opponent)
        else -> throw IllegalArgumentException("Unknown resolution $resolution")
    }

private fun resolveLosingSelection(opponent: Char) =
    when (opponent) {
        'A' -> 'Z'
        'B' -> 'X'
        'C' -> 'Y'
        else -> throw IllegalArgumentException("Unknown selection $opponent")
    }

private fun resolveDrawingSelection(opponent: Char) =
    when (opponent) {
        'A' -> 'X'
        'B' -> 'Y'
        'C' -> 'Z'
        else -> throw IllegalArgumentException("Unknown selection $opponent")
    }

private fun resolveWinningSelection(opponent: Char) =
    when (opponent) {
        'A' -> 'Y'
        'B' -> 'Z'
        'C' -> 'X'
        else -> throw IllegalArgumentException("Unknown selection $opponent")
    }

private fun resolveScore(opponent: Char, player: Char) =
    resolveOutcomeScore(opponent, player) + resolveSelectionScore(player)

private fun resolveOutcomeScore(opponent: Char, player: Char): Int =
    when {
        player wins opponent -> 6
        player draws opponent -> 3
        player loses opponent -> 0
        else -> throw IllegalArgumentException("Unknown score for selection $opponent $player")
    }

private infix fun Char.draws(opponent: Char) = opponent.code == (this.code - 23)

private infix fun Char.wins(opponent: Char) =
    (this == 'X' && opponent == 'C') || (this == 'Y' && opponent == 'A') || (this == 'Z' && opponent == 'B')

private infix fun Char.loses(opponent: Char) =
    (this == 'X' && opponent == 'B') || (this == 'Y' && opponent == 'C') || (this == 'Z' && opponent == 'A')

private fun resolveSelectionScore(selection: Char): Int = selection.code - 87
