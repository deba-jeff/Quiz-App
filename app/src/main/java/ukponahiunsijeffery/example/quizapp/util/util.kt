package ukponahiunsijeffery.example.quizapp.util

import org.json.JSONArray
import org.json.JSONObject
import ukponahiunsijeffery.example.quizapp.model.Quiz


fun parseQuizJsonStringToQuizArrayList(rawJsonString: String): ArrayList<Quiz>{

    val quizArrayList = ArrayList<Quiz>()

    val baseJsonObject = JSONObject(rawJsonString)
    val resultsJsonArray = baseJsonObject.getJSONArray("results")

    for (i in 0 until resultsJsonArray.length()) {

        val resultsObject = resultsJsonArray.getJSONObject(i)

        val question: String = resultsObject.getString("question")
        val correctAnswer: String = resultsObject.getString("correct_answer")

        val incorrectAnswerArray: JSONArray = resultsObject.getJSONArray("incorrect_answers")

        val incorrectAnswerLength = incorrectAnswerArray.length()

        val incorrectAnswerArrayList = ArrayList<String>()

        if (incorrectAnswerLength == 1){
            val onlyIncorrectAnswer = incorrectAnswerArray[0]
            incorrectAnswerArrayList.add(onlyIncorrectAnswer.toString())
        }

        else{
            val firstAnswer = incorrectAnswerArray[0]
            val secondAnswer = incorrectAnswerArray[1]
            val thirdAnswer = incorrectAnswerArray[2]

            incorrectAnswerArrayList.add(firstAnswer.toString())
            incorrectAnswerArrayList.add(secondAnswer.toString())
            incorrectAnswerArrayList.add(thirdAnswer.toString())
        }

        val quiz = Quiz(question, correctAnswer, incorrectAnswerArrayList)

        quizArrayList.add(quiz)
    }

    return quizArrayList

}