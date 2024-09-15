package com.example.homeworkout.fragments

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworkout.Constants
import com.example.homeworkout.R
import com.example.homeworkout.adapters.WorkoutTypeAdapter
import com.example.homeworkout.databinding.FragmentWorkoutTypeBinding
import com.example.homeworkout.models.Workout
import com.example.homeworkout.models.WorkoutType
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WorkoutTypeFragment : Fragment() {

    private lateinit var binding: FragmentWorkoutTypeBinding

    private lateinit var typeAdapter: WorkoutTypeAdapter

    val args:WorkoutTypeFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_workoutTypeFragment_to_trainingFragment)
            }
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWorkoutTypeBinding.inflate(inflater,container,false)
      //  (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      //  (activity as AppCompatActivity).supportActionBar?.title = "WorkoutType"
      //  (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

     //   binding.toolbar.setNavigationOnClickListener {
     //       findNavController().navigate(R.id.action_historyFragment_to_reportFragment)
     //   }

        val toolbar = binding.toolbar
        val collapsingToolbar = binding.collapsingToolbarLayout
        val appBar = binding.appbarLayout

        appBar.addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (collapsingToolbar.height + verticalOffset < 2 * ViewCompat.getMinimumHeight(
                    collapsingToolbar
                )
            ) {
                toolbar.navigationIcon
                    ?.setColorFilter(resources.getColor(R.color.black), PorterDuff.Mode.SRC_ATOP)
            } else {
                toolbar.navigationIcon?.setColorFilter(resources.getColor(R.color.white), PorterDuff.Mode.SRC_ATOP)
                 toolbar.title = ""
            }
        })

          binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_workoutTypeFragment_to_trainingFragment)
        }


        var wr_position = 0
        var wr_list = ArrayList<Workout>()
        var wt_list = ArrayList<WorkoutType>() //= wr_list[wr_position].type


        if(args.player == "beginner"){
            wr_list = Constants.getBeginnerWorkouts()
            wr_position = args.workout
            wt_list = wr_list[wr_position].type
        }else if(args.player == "intermediate"){
            wr_list = Constants.getIntermediateWorkouts()
            wr_position = args.workout
            wt_list = wr_list[wr_position].type
        }else if(args.player == "advanced"){
            wr_list = Constants.getAdvancedWorkouts()
            wr_position = args.workout
            wt_list = wr_list[wr_position].type
        }
        binding.toolbar.title = wr_list[wr_position].name

        setUpRecyclerViewForWorkoutType()


        typeAdapter.differ.submitList(wt_list)
        binding.ivTitle.setImageResource(wr_list[wr_position].image)

        binding.startBtn.setOnClickListener {
                   val player_type = args.player
            val bundle = Bundle().apply {
                putInt("workout_position",wr_position)
                putString("player",player_type)
            }

            findNavController().navigate(R.id.action_workoutTypeFragment_to_workoutReadyFragment,bundle)
        }

    }

private fun setUpRecyclerViewForWorkoutType(){
    typeAdapter = WorkoutTypeAdapter()
    binding.rvWorkoutType.apply {
        adapter = typeAdapter
        layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        setHasFixedSize(true)
    }

}
}