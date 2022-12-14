package ukponahiunsijeffery.example.quizapp.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import ukponahiunsijeffery.example.quizapp.R
import ukponahiunsijeffery.example.quizapp.databinding.FragmentQuizBinding
import ukponahiunsijeffery.example.quizapp.model.Quiz

class QuizFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FragmentQuizBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_quiz, container, false)

        val quizArrayList = arguments?.getParcelableArrayList<Quiz>("ARRAYLIST")
        binding.quizQuestion.text = quizArrayList.toString()

        val currentQuiz = quizArrayList?.get(0)

        val incorrectAnswerSize = currentQuiz?.incorrectAnswers?.size

        if (incorrectAnswerSize == 1){
            binding.quizQuestion.text = currentQuiz?.question
            binding.correctAnswer.text = currentQuiz?.correctAnswer
            binding.firstWrongAnswer.text = currentQuiz?.incorrectAnswers!![0]
        }

        else{
            binding.quizQuestion.text = currentQuiz?.question
            binding.correctAnswer.text = currentQuiz?.correctAnswer
            binding.firstWrongAnswer.text = currentQuiz?.incorrectAnswers!![0]
            binding.secondWrongAnswer.text = currentQuiz.incorrectAnswers!![1]
            binding.thirdWrongAnswer.text = currentQuiz.incorrectAnswers!![2]
        }


        var score: Int = 0
        binding.correctAnswer.setOnClickListener {
            binding.correctAnswer.setTextColor(resources.getColor(R.color.purple_700))
            binding.firstWrongAnswer.setTextColor(resources.getColor(R.color.dark_gray))
            binding.secondWrongAnswer.setTextColor(resources.getColor(R.color.dark_gray))
            binding.thirdWrongAnswer.setTextColor(resources.getColor(R.color.dark_gray))

            score = score + 1
        }

        binding.firstWrongAnswer.setOnClickListener {
            binding.firstWrongAnswer.setTextColor(resources.getColor(R.color.purple_700))
            binding.correctAnswer.setTextColor(resources.getColor(R.color.dark_gray))
            binding.secondWrongAnswer.setTextColor(resources.getColor(R.color.dark_gray))
            binding.thirdWrongAnswer.setTextColor(resources.getColor(R.color.dark_gray))
        }

        binding.secondWrongAnswer.setOnClickListener {
            binding.secondWrongAnswer.setTextColor(resources.getColor(R.color.purple_700))
            binding.correctAnswer.setTextColor(resources.getColor(R.color.dark_gray))
            binding.firstWrongAnswer.setTextColor(resources.getColor(R.color.dark_gray))
            binding.thirdWrongAnswer.setTextColor(resources.getColor(R.color.dark_gray))
        }

        binding.thirdWrongAnswer.setOnClickListener {
            binding.thirdWrongAnswer.setTextColor(resources.getColor(R.color.purple_700))
            binding.correctAnswer.setTextColor(resources.getColor(R.color.dark_gray))
            binding.firstWrongAnswer.setTextColor(resources.getColor(R.color.dark_gray))
            binding.secondWrongAnswer.setTextColor(resources.getColor(R.color.dark_gray))
        }

        var i: Int = 0
        binding.nextQuestionButton.setOnClickListener {

            // Resets the Texts at each click
            binding.quizQuestion.text = ""
            binding.correctAnswer.text = ""
            binding.firstWrongAnswer.text = ""
            binding.secondWrongAnswer.text = ""
            binding.thirdWrongAnswer.text = ""


            i = i + 1

            if (i == 10){

                val bundle = Bundle()
                bundle.putString("FINAL_SCORE", score.toString())

                findNavController().navigate(R.id.action_quizFragment_to_quizEndFragment, bundle)
            }

            else{
                val mostCurrentQuizArrayList = quizArrayList[i]

                val incorrectAnswerSize = mostCurrentQuizArrayList.incorrectAnswers.size

                if (incorrectAnswerSize == 1){
                    binding.quizQuestion.text = mostCurrentQuizArrayList?.question
                    binding.correctAnswer.text = mostCurrentQuizArrayList?.correctAnswer
                    binding.firstWrongAnswer.text = mostCurrentQuizArrayList?.incorrectAnswers!![0]
                }

                else{
                    binding.quizQuestion.text = mostCurrentQuizArrayList?.question
                    binding.correctAnswer.text = mostCurrentQuizArrayList?.correctAnswer
                    binding.firstWrongAnswer.text = mostCurrentQuizArrayList?.incorrectAnswers!![0]
                    binding.secondWrongAnswer.text = mostCurrentQuizArrayList.incorrectAnswers!![1]
                    binding.thirdWrongAnswer.text = mostCurrentQuizArrayList.incorrectAnswers!![2]
                }
            }

            // Resets the Texts color after each click
            binding.correctAnswer.setTextColor(resources.getColor(R.color.dark_gray))
            binding.firstWrongAnswer.setTextColor(resources.getColor(R.color.dark_gray))
            binding.secondWrongAnswer.setTextColor(resources.getColor(R.color.dark_gray))
            binding.thirdWrongAnswer.setTextColor(resources.getColor(R.color.dark_gray))

        }


        return binding.root
    }


}