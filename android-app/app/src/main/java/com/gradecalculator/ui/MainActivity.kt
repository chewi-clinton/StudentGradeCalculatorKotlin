package com.gradecalculator.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gradecalculator.R
import com.gradecalculator.databinding.ActivityMainBinding
import com.gradecalculator.model.Student
import com.gradecalculator.model.SubjectScore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val sampleStudents = listOf(
        Student("Alice", mutableListOf(
            SubjectScore("Math", 85), SubjectScore("Science", 92),
            SubjectScore("English", 78), SubjectScore("History", 90)
        )),
        Student("Bob", mutableListOf(
            SubjectScore("Math", 55), SubjectScore("Science", 63),
            SubjectScore("English", 48), SubjectScore("History", 70)
        )),
        Student("Charlie"),
        Student("Diana", mutableListOf(
            SubjectScore("Math", 95), SubjectScore("Science", 98),
            SubjectScore("English", 100), SubjectScore("History", 92)
        )),
        Student("Eve", mutableListOf(
            SubjectScore("Math", 72), SubjectScore("Science", 68),
            SubjectScore("English", 74), SubjectScore("History", 65)
        ))
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupStats()
        setupNavigation()
    }

    private fun setupStats() {
        val validStudents = sampleStudents.filter { it.hasScores }
        binding.tvTotalStudents.text = sampleStudents.size.toString()

        if (validStudents.isNotEmpty()) {
            val avg = validStudents.map { it.average }.average()
            binding.tvClassAverage.text = "${"%.1f".format(avg)}%"
        } else {
            binding.tvClassAverage.text = "N/A"
        }
    }

    private fun setupNavigation() {
        binding.cardManualEntry.setOnClickListener {
            startActivity(Intent(this, ManualEntryActivity::class.java))
        }

        binding.cardUploadExcel.setOnClickListener {
            startActivity(Intent(this, ExcelUploadActivity::class.java))
        }
    }
}
