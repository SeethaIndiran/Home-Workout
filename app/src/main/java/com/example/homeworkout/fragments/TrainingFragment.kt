package com.example.homeworkout.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworkout.Constants
import com.example.homeworkout.R
import com.example.homeworkout.adapters.AdvancedExerciseAdapter
import com.example.homeworkout.adapters.BeginnerExerciseAdapter
import com.example.homeworkout.adapters.IntermediateExerciseAdapter
import com.example.homeworkout.databinding.FragmentTrainingBinding
import com.example.homeworkout.viewmodels.ExerciseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrainingFragment : Fragment() {

    private lateinit var binding: FragmentTrainingBinding
    private lateinit var beginAdapter:BeginnerExerciseAdapter
    private lateinit var interAdapter:IntermediateExerciseAdapter
    private lateinit var advanAdapter:AdvancedExerciseAdapter

    private val exerciseViewModel:ExerciseViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragmentbin
        binding = FragmentTrainingBinding.inflate(inflater,container,false)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarExercise)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Home Workout"

         populatingValues()
        setUpRecyclerViewBeginner()
        setUpRecyclerViewAdvanced()
       setUpRecyclerViewIntermediate()
        beginAdapter.differ.submitList(Constants.getBeginnerWorkouts())
        interAdapter.differ.submitList(Constants.getIntermediateWorkouts())
        advanAdapter.differ.submitList(Constants.getAdvancedWorkouts())
    }

private fun setUpRecyclerViewBeginner(){
    beginAdapter = BeginnerExerciseAdapter(this@TrainingFragment,requireContext())
    binding.rvBeginner.apply {
        adapter = beginAdapter
        layoutManager = LinearLayoutManager(activity)
        setHasFixedSize(true)
    }
}

    private fun setUpRecyclerViewIntermediate(){
        interAdapter = IntermediateExerciseAdapter(this@TrainingFragment,requireContext())
        binding.rvIntermediate.apply {
            adapter = interAdapter
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }
    }
    private fun setUpRecyclerViewAdvanced(){
        advanAdapter = AdvancedExerciseAdapter(this@TrainingFragment,requireContext())
        binding.rvAdvanced.apply {
            adapter = advanAdapter
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }
    }
    fun navigatingToWorkoutTypeFragmentForBeginner(position:Int){

        val bundle = Bundle().apply {
            putInt("workout",position)
            putString("player","beginner")

        }

        findNavController().navigate(R.id.action_trainingFragment_to_workoutTypeFragment,bundle)
    }

    fun navigatingToWorkoutTypeFragmentForIntermediate(position:Int){

        val bundle = Bundle().apply {
            putInt("workout",position)
            putString("player","intermediate")

        }

        findNavController().navigate(R.id.action_trainingFragment_to_workoutTypeFragment,bundle)
    }

    fun navigatingToWorkoutTypeFragmentForAdvanced(position:Int){

        val bundle = Bundle().apply {
            putInt("workout",position)
            putString("player","advanced")

        }

        findNavController().navigate(R.id.action_trainingFragment_to_workoutTypeFragment,bundle)
    }



    private fun populatingValues(){
        exerciseViewModel.getExercises().observe(viewLifecycleOwner, Observer {
            it?.let {

                binding.tvWorkout.text = it.size.toString()

                val total_calories = it.sumOf {
                    it.calories
                }
                binding.tvKcal.text = total_calories.toString()

                val total_duration = it.sumOf {
                    it.duration
                }

                val durationInSeconds = total_duration // Replace with your actual duration value
                val minutes = durationInSeconds / 60
                val seconds = durationInSeconds % 60
                val durationString = String.format("%02d:%02d", minutes, seconds)
                binding.tvMin.text = durationString
            }
        })
    }
}