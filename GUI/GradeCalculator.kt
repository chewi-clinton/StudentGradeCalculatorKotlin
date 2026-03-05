import com.formdev.flatlaf.FlatDarkLaf
import java.awt.*
import javax.swing.*
import javax.swing.border.EmptyBorder

// ==================== COLORS & THEME ====================
object AppColors {
    val purplePrimary = Color(128, 0, 128)
    val purpleLight = Color(186, 85, 211)
    val purpleDark = Color(75, 0, 130)
    val purpleAccent = Color(155, 89, 182)
    val background = Color(25, 25, 35)
    val surface = Color(35, 35, 50)
    val surfaceLight = Color(45, 45, 65)
    val cardBg = Color(40, 40, 58)
    val textPrimary = Color(240, 240, 245)
    val textSecondary = Color(180, 180, 195)
    val success = Color(46, 204, 113)
    val warning = Color(241, 196, 15)
    val danger = Color(231, 76, 60)
    val gradeA = Color(46, 204, 113)
    val gradeB = Color(52, 152, 219)
    val gradeC = Color(241, 196, 15)
    val gradeD = Color(230, 126, 34)
    val gradeF = Color(231, 76, 60)
}

// ==================== MAIN APPLICATION ====================
class GradeCalculatorApp : JFrame("Grade Calculator") {
    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        size = Dimension(1100, 700)
        minimumSize = Dimension(900, 600)
        setLocationRelativeTo(null)
        contentPane.background = AppColors.background
        layout = BorderLayout()

        add(JLabel("Grade Calculator - Coming Soon", SwingConstants.CENTER).apply {
            font = Font("SansSerif", Font.BOLD, 28)
            foreground = AppColors.textPrimary
        }, BorderLayout.CENTER)
    }
}

fun main() {
    FlatDarkLaf.setup()
    SwingUtilities.invokeLater {
        GradeCalculatorApp().isVisible = true
    }
}
