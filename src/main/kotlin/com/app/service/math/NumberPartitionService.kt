package com.app.service.math

import jakarta.inject.Singleton
import kotlin.random.Random

@Singleton
class NumberPartitionService {

    fun partitionNumber(number: Int): Triple<Int, Int, Int> {
        if (number < 0) {
            return Triple(0, 0, 0)
        }

        val firstNumber = Random.nextInt(number)
        val secondNumber = Random.nextInt(number - firstNumber)
        val thirdNumber = number - firstNumber - secondNumber

        return Triple(firstNumber, secondNumber, thirdNumber)
    }
}