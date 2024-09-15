package com.example.homeworkout.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkout.databinding.ItemWorkoutsListBinding
import com.example.homeworkout.models.WorkoutsList

class WorkoutsListAdapter(val fragment:Fragment, val context:Context):RecyclerView.Adapter<WorkoutsListAdapter.WorkoutsListViewHolder>() {

    private var binding:ItemWorkoutsListBinding? = null

    inner class WorkoutsListViewHolder(itemBinding:ItemWorkoutsListBinding):
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallBack = object: DiffUtil.ItemCallback<WorkoutsList>(){
        override fun areItemsTheSame(oldItem: WorkoutsList, newItem: WorkoutsList): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: WorkoutsList, newItem: WorkoutsList): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutsListViewHolder {
        binding = ItemWorkoutsListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WorkoutsListViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: WorkoutsListViewHolder, position: Int) {
        val list = differ.currentList[position]
        holder.itemView.apply {
            binding?.currentDate?.text = list.firstDate
            binding?.seventhDate?.text = list.seventhDate


            val durationInSeconds = list.total_duration // Replace with your actual duration value
            val minutes = durationInSeconds / 60
            val seconds = durationInSeconds % 60
            val durationString = String.format("%02d:%02d", minutes, seconds)

            binding?.tvTimeSecs?.text = durationString
            binding?.numWorkouts?.text = list.num_workouts_list.size.toString()
            binding?.calorieCounts?.text = list.total_calories.toString()

            val exerciseAdapter = ExerciseHistoryAdapter()
            binding?.recyclerViewWorkoutsList?.adapter = exerciseAdapter
            binding?.recyclerViewWorkoutsList?.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)
            binding?.recyclerViewWorkoutsList?.setHasFixedSize(true)
            exerciseAdapter.differ.submitList(list.num_workouts_list)


        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}