package com.gradecalculator

fun getGrade(average: Double): String {
    return when {
        average >= 90 -> "A"
        average >= 80 -> "B"
        average >= 70 -> "C"
        average >= 60 -> "D"
        else -> "F"
    }
}

fun getAverage(scores: List<Int>): Double {
    return scores.average()
}
