package com.example.homeworkout.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworkout.R
import com.example.homeworkout.adapters.WorkoutsListAdapter
import com.example.homeworkout.common.MyDayViewDecorator
import com.example.homeworkout.databinding.FragmentHistoryBinding
import com.example.homeworkout.models.WorkoutsList
import com.example.homeworkout.viewmodels.ExerciseViewModel
import com.example.homeworkout.viewmodels.WorkoutsListViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private lateinit var binding:FragmentHistoryBinding

    private lateinit var exerciseListAdapter:WorkoutsListAdapter

    private val viewModel:WorkoutsListViewModel by viewModels()

    private val exerciseViewModel:ExerciseViewModel by viewModels()

    val args:HistoryFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater,container,false)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarExercise)
        return binding.root



    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "History"
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbarExercise.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_historyFragment_to_reportFragment)
        }

        setUpRecyclerView()

        viewModel.getAllList().observe(viewLifecycleOwner, Observer {weeks->
          val list = ArrayList<WorkoutsList>()
           if(weeks!=null){
               for(itemList in weeks){
                   exerciseViewModel.getExercises().observe(viewLifecycleOwner, Observer {
                       if(it.isNotEmpty()){
                           for(exercise in it){
                               if(itemList.week_number == exercise.weekNumber){
                                   itemList.num_workouts_list.add(exercise)

                               }
                           }
                           list.add(itemList)
                           exerciseListAdapter.differ.submitList(list)

                       }
                   })








               }




           }
        })

        exerciseViewModel.getExercises().observe(viewLifecycleOwner, Observer {
            for(exercise in it){
                val stringDate = exercise.date
                val formatter = SimpleDateFormat("dd/MMM/yyyy")
                val date: Date = formatter.parse(stringDate) as Date
                val calender = Calendar.getInstance()
                calender.time = date
                val date_drawable = ContextCompat.getDrawable(requireContext(),R.drawable.date_background)
                // val color = ContextCompat.getDrawable(requireContext(),R.drawable.round_back)

                val decorator = date_drawable?.let { it -> MyDayViewDecorator(calender, it) }
                binding.prolificCalendarView.addDecorator(decorator)
            }

        })
      //  binding.prolificCalendarView.setHeaderTextAppearance(R.style.CalendarWidgetHeader)
        //binding.prolificCalendarView.setWeekDayTextAppearance(R.style.CalendarWidgetHeader)
        //binding.prolificCalendarView.setDateTextAppearance(R.style.CalendarWidgetHeader)



    }

    private fun populateAdapter(){
        viewModel.getAllList().observe(viewLifecycleOwner, Observer {
            it?.let {
                exerciseListAdapter.differ.submitList(it)
            }
        })
    }

private fun setUpRecyclerView(){
    exerciseListAdapter = WorkoutsListAdapter(this,requireContext())
    binding.recyclerView.adapter = exerciseListAdapter
    binding.recyclerView.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
    binding.recyclerView.setHasFixedSize(true)
}

}


