package ukponahiunsijeffery.example.quizapp

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.ListPreference
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

    }

    class QuizPreferenceFragment: PreferenceFragment(), Preference.OnPreferenceChangeListener {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.settings_preference)

            val difficulty: Preference = findPreference("difficulty_key")
            bindPreferenceToSummary(difficulty)

            val category: Preference = findPreference("category_key")
            bindPreferenceToSummary(category)
        }


        private fun bindPreferenceToSummary(currentPreference: Preference) {

            currentPreference.setOnPreferenceChangeListener(this)

            val sharedPref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(currentPreference.context)
            val preferenceString = sharedPref.getString(currentPreference.key, " ")
            onPreferenceChange(currentPreference, preferenceString)
        }


        override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {

            val newValueString = newValue.toString()

            val listPreference = preference as? ListPreference
            val prefIndex: Int = listPreference?.findIndexOfValue(newValueString)!!

            if (prefIndex >= 0){

                val labels: Array<out CharSequence>? = listPreference.entries
                preference.setSummary(labels!![prefIndex])
            }

            return true
        }


    }



}