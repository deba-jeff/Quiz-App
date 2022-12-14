package ukponahiunsijeffery.example.quizapp.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import ukponahiunsijeffery.example.quizapp.R
import ukponahiunsijeffery.example.quizapp.databinding.FragmentQuizEndBinding


class QuizEndFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FragmentQuizEndBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_quiz_end, container, false
        )

        val newFinalScore = requireArguments().getString("FINAL_SCORE")
        binding.finalScore.text = "You scored  $newFinalScore"

        binding.tryAgainButton.setOnClickListener { button ->
            button.findNavController().navigate(R.id.action_quizEndFragment_to_homeFragment)
        }

        return binding.root

    }

}