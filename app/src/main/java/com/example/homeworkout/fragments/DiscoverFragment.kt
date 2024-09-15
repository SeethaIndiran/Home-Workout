package com.example.homeworkout.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworkout.Constants
import com.example.homeworkout.R
import com.example.homeworkout.adapters.DiscoverBeginnerAdapter
import com.example.homeworkout.adapters.DiscoverChallengeAdapter
import com.example.homeworkout.adapters.HIITAdapter
import com.example.homeworkout.databinding.FragmentDiscoverBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscoverFragment : Fragment() {
   private lateinit var binding:FragmentDiscoverBinding
   private lateinit var hiitAdapter:HIITAdapter
    private lateinit var fastAdapter:HIITAdapter
   private lateinit var beginAdapter:DiscoverBeginnerAdapter
   private lateinit var challengeAdapter:DiscoverChallengeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentDiscoverBinding.inflate(inflater,container,false)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Discover"
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setUpRecyclerViewPicksforyou()
        setUpRecyclerViewForFatBurning()
        setUpRecyclerViewBeginners()
        setUpRecyclerViewFastWorkout()
        val list = Constants.getPicksForYou()
        hiitAdapter.differ.submitList(list)
        challengeAdapter.differ.submitList(Constants.getAdvancedWorkouts())
        beginAdapter.differ.submitList(Constants.getBeginnerWorkouts())
        fastAdapter.differ.submitList(Constants.getFastWorkout())

    }



    private fun setUpRecyclerViewPicksforyou(){
        hiitAdapter = HIITAdapter(this@DiscoverFragment,requireContext())
        binding.rvPicks.apply {
            adapter = hiitAdapter
            layoutManager =GridLayoutManager(activity,2,GridLayoutManager.HORIZONTAL,false)
           // layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            setHasFixedSize(true)
        }
    }

    private fun setUpRecyclerViewFastWorkout(){
        fastAdapter = HIITAdapter(this@DiscoverFragment,requireContext())
        binding.rvFast.apply {
            adapter = fastAdapter
            layoutManager =GridLayoutManager(activity,2,GridLayoutManager.HORIZONTAL,false)
            // layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            setHasFixedSize(true)
        }
    }

    private fun setUpRecyclerViewBeginners(){
        beginAdapter = DiscoverBeginnerAdapter(this@DiscoverFragment,requireContext())
        binding.rvBeginner.apply {
            adapter = beginAdapter
            //  layoutManager =GridLayoutManager(activity,2,GridLayoutManager.HORIZONTAL,false)
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            setHasFixedSize(true)
        }
    }

    private fun setUpRecyclerViewForFatBurning(){
        challengeAdapter = DiscoverChallengeAdapter(this@DiscoverFragment,requireContext())
        binding.rvChallenge.apply {
           adapter = challengeAdapter
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            setHasFixedSize(true)
        }
    }

    fun navigatingToWorkoutTypeFragmentForBeginner(position:Int){

        val bundle = Bundle().apply {
            putInt("workout",position)
            putString("player","beginner")

        }

        findNavController().navigate(R.id.action_discoverFragment_to_workoutTypeFragment,bundle)
    }


}