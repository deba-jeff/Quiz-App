package ukponahiunsijeffery.example.quizapp.screens

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ukponahiunsijeffery.example.quizapp.R
import ukponahiunsijeffery.example.quizapp.SettingsActivity
import ukponahiunsijeffery.example.quizapp.api.QuizApi
import ukponahiunsijeffery.example.quizapp.databinding.FragmentHomeBinding
import ukponahiunsijeffery.example.quizapp.model.Quiz
import ukponahiunsijeffery.example.quizapp.util.parseQuizJsonStringToQuizArrayList

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FragmentHomeBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_home, container, false)

        val sharedPref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val difficulty: String = sharedPref.getString("difficulty_key", "easy")!!
        val category: String = sharedPref.getString("category_key", "9")!!


        binding.startButton.setOnClickListener { view ->

            QuizApi.retrofitService.getProperties(
                10,
                category,
                difficulty)

                .enqueue( object: Callback<String>{
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        val responseText = response.body()

                        val quizArrayList: ArrayList<Quiz> = parseQuizJsonStringToQuizArrayList(
                            responseText.toString()
                        )
                        val bundle = bundleOf("ARRAYLIST" to quizArrayList)

                        view.findNavController().navigate(R.id.action_homeFragment_to_quizFragment, bundle)
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Toast.makeText(context, "Check your internet connection", Toast.LENGTH_LONG).show()
                    }

                })

        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val intent: Intent = Intent(requireContext(), SettingsActivity::class.java)

        when(item.itemId){
            R.id.settings_menu_item ->
                startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }


}