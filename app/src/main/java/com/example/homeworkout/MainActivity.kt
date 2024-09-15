package com.example.homeworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.homeworkout.databinding.ActivityMainBinding
import com.example.homeworkout.fragments.EditDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),EditDialogFragment.UpdateListener,TextToSpeech.OnInitListener {

    private lateinit var binding : ActivityMainBinding
    private lateinit var mNavController: NavController
    private var tts:TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tts = TextToSpeech(this,this)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment

        mNavController = navHostFragment.navController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        // val appBarConfiguration = AppBarConfiguration(
        //     setOf(R.id.provideDetailsFragment, R.id.multiPlayerFragment)
        //)
        val bottom_nav_view = binding.bottomNavigationView
        setupWithNavController(bottom_nav_view, mNavController)

        navHostFragment.findNavController()
            .addOnDestinationChangedListener { _, destination, _ ->

                when (destination.id) {
                    R.id.trainingFragment, R.id.discoverFragment, R.id.reportFragment, R.id.settingsFragment ->
                        bottom_nav_view.visibility = View.VISIBLE
                    else -> bottom_nav_view.visibility = View.GONE
                }

            }
    }

    override fun onUpdateUI(value: String) {

    }

    override fun onInit(status: Int) {

    }
}