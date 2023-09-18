package com.kwdev.adventofcode

import kotlin.test.assertEquals

infix fun <T> T.shouldBe(another: T) = assertEquals(this, another)
