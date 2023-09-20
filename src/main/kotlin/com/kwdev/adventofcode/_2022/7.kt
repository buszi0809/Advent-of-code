package com.kwdev.adventofcode._2022

import java.io.File as JavaFile

private data class File(
    val name: String,
    val size: Int
)

private data class Directory(
    val name: String,
    val parent: Directory? = null,
    val children: ArrayList<Directory> = arrayListOf(),
    val files: ArrayList<File> = arrayListOf()
) {
    val totalSize: Int
        get() = children.sumOf { it.totalSize } + files.sumOf { it.size }

    fun flattenDirectories(): List<Directory> {
        val result = arrayListOf<Directory>()
        flattenDirectories(result)
        return result
    }

    private fun flattenDirectories(result: ArrayList<Directory>) {
        result.add(this)
        children.forEach { it.flattenDirectories(result) }
    }

    fun findDirectories(predicate: (Directory) -> Boolean): List<Directory> {
        val result = arrayListOf<Directory>()
        findDirectories(result, predicate)
        return result
    }

    private fun findDirectories(result: ArrayList<Directory>, predicate: (Directory) -> Boolean) {
        if (predicate(this)) {
            result.add(this)
        }

        children.forEach { directory ->
            directory.findDirectories(result, predicate)
        }
    }
}

fun main() {
    println("p1 ${resolve2022d7p1()}")
    println("p2 ${resolve2022d7p2()}")
}

fun resolve2022d7p1(): Int {
    val input = JavaFile("input/2022/7.txt").readLines()

    val rootDirectory = parseDirectories(input)

    return rootDirectory
        .findDirectories { it.totalSize <= 100_000 }
        .sumOf { it.totalSize }
}

fun resolve2022d7p2(): Int {
    val input = JavaFile("input/2022/7.txt").readLines()

    val rootDirectory = parseDirectories(input)
    val neededSpace = rootDirectory.totalSize - 40_000_000

    return rootDirectory
        .flattenDirectories()
        .filterNot { it.totalSize < neededSpace }
        .minOf { it.totalSize }
}

private fun parseDirectories(input: List<String>): Directory {
    val rootDirectory = Directory("/")
    var currentDirectory = rootDirectory

    for (line in input.drop(1)) {
        when {
            line == "\$ ls" -> continue
            line.startsWith("\$ cd ") -> currentDirectory = executeChangeDirectory(currentDirectory, line)
            line.startsWith("dir ") -> tryCreateChildDirectory(currentDirectory, line)
            else -> tryAddFile(currentDirectory, line)
        }
    }

    return rootDirectory
}

private fun executeChangeDirectory(currentDirectory: Directory, line: String): Directory =
    line.removePrefix("\$ cd ").let { name ->
        if (name == "..") {
            requireNotNull(currentDirectory.parent) { "$currentDirectory does not have a parent" }
        } else {
            requireNotNull(currentDirectory.children.find { it.name == name }) { "$currentDirectory does not have a child named $name" }
        }
    }

private fun tryCreateChildDirectory(currentDirectory: Directory, line: String) {
    val name = line.removePrefix("dir ")
    if (currentDirectory.children.none { it.name == name }) {
        currentDirectory.children.add(Directory(name = name, parent = currentDirectory))
    }
}

private fun tryAddFile(currentDirectory: Directory, line: String) {
    val separatorIndex = line.indexOfFirst { it == ' ' }.takeIf { it != -1 } ?: return
    val size = line.slice(0..<separatorIndex).toIntOrNull() ?: return
    val name = line.slice((separatorIndex + 1)..line.lastIndex)
    if (currentDirectory.files.none { it.name == name }) {
        currentDirectory.files.add(File(name = name, size = size))
    }
}
