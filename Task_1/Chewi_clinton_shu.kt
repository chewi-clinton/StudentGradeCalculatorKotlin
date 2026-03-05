// Student data class
data class Student(
    val name: String,
    val scores: List<Int>?
)

// Calculate grade based on average score
fun getGrade(average: Double): String {
    return when {
        average >= 90 -> "A"
        average >= 80 -> "B"
        average >= 70 -> "C"
        average >= 60 -> "D"
        else -> "F"
    }
}

// Print grades for a list of students
fun printGrades(students: List<Student>) {
    for (student in students) {
        val scores = student.scores
        if (scores == null || scores.isEmpty()) {
            println("${student.name}: No scores available")
        } else {
            val average = scores.average()
            val grade = getGrade(average)
            println("${student.name}: Average = ${"%.1f".format(average)}, Grade = $grade")
        }
    }
}

fun main() {
    val students = listOf(
        Student("Alice", listOf(85, 92, 78, 90)),
        Student("Bob", listOf(55, 63, 48, 70)),
        Student("Charlie", null),
        Student("Diana", listOf(95, 98, 100, 92)),
        Student("Eve", listOf(72, 68, 74, 65))
    )

    println("=== Student Grade Report ===")
    printGrades(students)
}
