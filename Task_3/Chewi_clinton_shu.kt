// ==========================================
// Task 3: Object-Oriented Grade Calculator
// Demonstrates: Classes, Constructors, Inheritance,
// Abstract Classes, Data Classes, Interfaces,
// Sealed Classes, Companion Objects, Visibility Modifiers
// ==========================================

// --- Data Class: holds assessment info ---
data class Assessment(
    val subject: String,
    val score: Int,
    val maxScore: Int = 100
) {
    val percentage: Double
        get() = (score.toDouble() / maxScore) * 100
}

// --- Abstract Class: base for all people in the system ---
abstract class Person(val name: String, protected var age: Int) {

    init {
        require(age >= 0) { "Age must be non-negative" }
        require(name.isNotBlank()) { "Name cannot be blank" }
    }

    abstract fun role(): String

    open fun displayInfo() {
        println("Name: $name | Age: $age | Role: ${role()}")
    }

    override fun toString(): String = "$name (${role()}, age $age)"
}

// --- Open class: Student extends Person ---
open class Student(
    name: String,
    age: Int,
    val studentId: String,
    private val assessments: MutableList<Assessment> = mutableListOf()
) : Person(name, age) {

    override fun role(): String = "Student"

    fun addAssessment(assessment: Assessment) {
        assessments.add(assessment)
    }

    fun getAssessments(): List<Assessment> = assessments.toList()

    fun calculateAverage(): Double {
        if (assessments.isEmpty()) return 0.0
        return assessments.map { it.percentage }.average()
    }

    fun getGrade(): String {
        val avg = calculateAverage()
        return when {
            avg >= 90 -> "A"
            avg >= 80 -> "B"
            avg >= 70 -> "C"
            avg >= 60 -> "D"
            else -> "F"
        }
    }

    override fun displayInfo() {
        super.displayInfo()
        println("  Student ID: $studentId")
        if (assessments.isNotEmpty()) {
            println("  Average: ${"%.1f".format(calculateAverage())}% | Grade: ${getGrade()}")
            println("  Assessments: ${assessments.size}")
        } else {
            println("  No assessments recorded")
        }
    }

    override fun toString(): String =
        "$name (Student #$studentId, avg: ${"%.1f".format(calculateAverage())}%)"
}

// --- GraduateStudent inherits from Student (multi-level inheritance) ---
class GraduateStudent(
    name: String,
    age: Int,
    studentId: String,
    val thesisTopic: String,
    private val advisor: String
) : Student(name, age, studentId) {

    override fun role(): String = "Graduate Student"

    override fun displayInfo() {
        super.displayInfo()
        println("  Thesis: $thesisTopic")
        println("  Advisor: $advisor")
    }
}

// --- Teacher extends Person ---
class Teacher(
    name: String,
    age: Int,
    val employeeId: String,
    private val department: String,
    private val courses: MutableList<String> = mutableListOf()
) : Person(name, age) {

    override fun role(): String = "Teacher"

    fun addCourse(course: String) {
        if (course !in courses) courses.add(course)
    }

    fun getCourses(): List<String> = courses.toList()

    override fun displayInfo() {
        super.displayInfo()
        println("  Employee ID: $employeeId")
        println("  Department: $department")
        if (courses.isNotEmpty()) {
            println("  Courses: ${courses.joinToString(", ")}")
        }
    }

    override fun toString(): String = "$name (Teacher, $department)"
}

// --- Interface: Gradable - defines grading behavior ---
interface Gradable {
    fun calculateAverage(): Double
    fun getGrade(): String
    fun isPassing(): Boolean = calculateAverage() >= 60.0
}

// --- Interface: Exportable - defines export behavior ---
interface Exportable {
    fun toCSV(): String
    fun toSummaryString(): String
}

// --- Class implementing multiple interfaces ---
class CourseResult(
    val studentName: String,
    val courseName: String,
    private val scores: List<Int>
) : Gradable, Exportable {

    override fun calculateAverage(): Double {
        if (scores.isEmpty()) return 0.0
        return scores.average()
    }

    override fun getGrade(): String {
        val avg = calculateAverage()
        return when {
            avg >= 90 -> "A"
            avg >= 80 -> "B"
            avg >= 70 -> "C"
            avg >= 60 -> "D"
            else -> "F"
        }
    }

    override fun toCSV(): String =
        "$studentName,$courseName,${"%.1f".format(calculateAverage())},${getGrade()}"

    override fun toSummaryString(): String =
        "$studentName - $courseName: ${"%.1f".format(calculateAverage())}% (${getGrade()})"

    override fun toString(): String = toSummaryString()
}

// --- Sealed Class: represents grade evaluation result ---
sealed class GradeResult {
    data class Passed(val studentName: String, val grade: String, val average: Double) : GradeResult()
    data class Failed(val studentName: String, val average: Double, val deficit: Double) : GradeResult()
    data class Incomplete(val studentName: String, val reason: String) : GradeResult()
    object NoData : GradeResult()
}

// Exhaustive when handling for sealed class
fun handleGradeResult(result: GradeResult): String = when (result) {
    is GradeResult.Passed -> "${result.studentName} passed with ${result.grade} (${"%.1f".format(result.average)}%)"
    is GradeResult.Failed -> "${result.studentName} failed (${"%.1f".format(result.average)}%) - needs ${"%.1f".format(result.deficit)} more points"
    is GradeResult.Incomplete -> "${result.studentName}: Incomplete - ${result.reason}"
    GradeResult.NoData -> "No grade data available"
}

// Evaluate a student and return a sealed class result
fun evaluateStudent(student: Student): GradeResult {
    if (student.getAssessments().isEmpty()) {
        return GradeResult.Incomplete(student.name, "No assessments submitted")
    }
    val avg = student.calculateAverage()
    return if (avg >= 60.0) {
        GradeResult.Passed(student.name, student.getGrade(), avg)
    } else {
        GradeResult.Failed(student.name, avg, 60.0 - avg)
    }
}

// --- Companion Object: GradeCalculator utility ---
class GradeCalculator private constructor() {
    companion object {
        const val PASSING_THRESHOLD = 60.0
        const val HONOR_ROLL_THRESHOLD = 85.0

        fun fromScore(score: Int, maxScore: Int = 100): String {
            val percentage = (score.toDouble() / maxScore) * 100
            return when {
                percentage >= 90 -> "A"
                percentage >= 80 -> "B"
                percentage >= 70 -> "C"
                percentage >= 60 -> "D"
                else -> "F"
            }
        }

        fun isHonorRoll(average: Double): Boolean = average >= HONOR_ROLL_THRESHOLD

        fun createReport(people: List<Person>): String {
            val sb = StringBuilder()
            sb.appendLine("=== Grade Calculator Report ===")
            sb.appendLine("Total people: ${people.size}")

            val students = people.filterIsInstance<Student>()
            val teachers = people.filterIsInstance<Teacher>()

            sb.appendLine("Students: ${students.size}")
            sb.appendLine("Teachers: ${teachers.size}")

            if (students.isNotEmpty()) {
                val avgOfAll = students.filter { it.getAssessments().isNotEmpty() }
                    .map { it.calculateAverage() }
                    .average()
                sb.appendLine("Class Average: ${"%.1f".format(avgOfAll)}%")
            }

            return sb.toString()
        }
    }
}
