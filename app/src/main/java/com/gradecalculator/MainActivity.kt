package com.gradecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.gradecalculator.ui.AddStudentScreen
import com.gradecalculator.ui.StudentListScreen
import com.gradecalculator.ui.theme.GradeCalculatorTheme
import com.gradecalculator.ui.theme.PurpleDark
import com.gradecalculator.ui.theme.PurplePrimary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GradeCalculatorTheme {
                GradeCalculatorApp()
            }
        }
    }
}

@Composable
fun GradeCalculatorApp() {
    var selectedTab by remember { mutableIntStateOf(0) }
    val students = remember {
        mutableStateListOf(
            Student("Alice", listOf(85, 92, 78, 90)),
            Student("Bob", listOf(55, 63, 48, 70)),
            Student("Charlie", null),
            Student("Diana", listOf(95, 98, 100, 92)),
            Student("Eve", listOf(72, 68, 74, 65))
        )
    }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = PurplePrimary
            ) {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = {},
                    label = { Text("View Grades") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = androidx.compose.ui.graphics.Color.White,
                        selectedTextColor = androidx.compose.ui.graphics.Color.White,
                        unselectedTextColor = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.6f),
                        indicatorColor = PurpleDark
                    )
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = {},
                    label = { Text("Add Student") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = androidx.compose.ui.graphics.Color.White,
                        selectedTextColor = androidx.compose.ui.graphics.Color.White,
                        unselectedTextColor = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.6f),
                        indicatorColor = PurpleDark
                    )
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedTab) {
                0 -> StudentListScreen(students)
                1 -> AddStudentScreen { student ->
                    students.add(student)
                }
            }
        }
    }
}
