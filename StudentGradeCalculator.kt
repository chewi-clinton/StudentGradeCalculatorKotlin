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

// Step 5: Function to input a student from the terminal
fun inputStudent(): Student {
    print("Enter student name: ")
    val name = readLine()?.trim() ?: ""

    print("Enter scores separated by commas (e.g. 85,92,78): ")
    val input = readLine()?.trim()

    val scores = if (input.isNullOrEmpty()) {
        null
    } else {
        input.split(",").mapNotNull { it.trim().toIntOrNull() }
    }

    return Student(name, scores)
}

// Step 4: Main function with sample data
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
