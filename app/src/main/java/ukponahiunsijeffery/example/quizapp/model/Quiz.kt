package ukponahiunsijeffery.example.quizapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Quiz(
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: ArrayList<String>
): Parcelable
