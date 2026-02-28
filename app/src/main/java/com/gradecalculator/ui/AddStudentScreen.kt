package com.gradecalculator.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.gradecalculator.Student
import com.gradecalculator.ui.theme.PurplePrimary

@Composable
fun AddStudentScreen(onAddStudent: (Student) -> Unit) {
    var name by remember { mutableStateOf("") }
    var scoresText by remember { mutableStateOf("") }
    var showSuccess by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Add Student",
            style = MaterialTheme.typography.headlineLarge,
            color = PurplePrimary,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                showSuccess = false
                errorMessage = ""
            },
            label = { Text("Student Name") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PurplePrimary,
                focusedLabelColor = PurplePrimary,
                cursorColor = PurplePrimary
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = scoresText,
            onValueChange = {
                scoresText = it
                showSuccess = false
                errorMessage = ""
            },
            label = { Text("Scores (comma-separated)") },
            placeholder = { Text("e.g. 85, 92, 78, 90") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PurplePrimary,
                focusedLabelColor = PurplePrimary,
                cursorColor = PurplePrimary
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (name.isBlank()) {
                    errorMessage = "Please enter a student name"
                    return@Button
                }

                val scores = if (scoresText.isBlank()) {
                    null
                } else {
                    scoresText.split(",").mapNotNull { it.trim().toIntOrNull() }
                }

                val student = Student(name.trim(), scores)
                onAddStudent(student)

                name = ""
                scoresText = ""
                showSuccess = true
                errorMessage = ""
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PurplePrimary)
        ) {
            Text("Add Student", style = MaterialTheme.typography.titleMedium, color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (showSuccess) {
            Text(
                text = "Student added successfully!",
                color = Color(0xFF4CAF50),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
