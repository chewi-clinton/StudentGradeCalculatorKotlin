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

// Step 3: Function to print grades for a list of students
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
