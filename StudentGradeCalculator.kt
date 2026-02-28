// Step 1: Define the Student data class
data class Student(
    val name: String,
    val scores: List<Int>?
)

// Step 2: Function to calculate grade based on average score
fun getGrade(average: Double): String {
    return when {
        average >= 90 -> "A"
        average >= 80 -> "B"
        average >= 70 -> "C"
        average >= 60 -> "D"
        else -> "F"
    }
}
