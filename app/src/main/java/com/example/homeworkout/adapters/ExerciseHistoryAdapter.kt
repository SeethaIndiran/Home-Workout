package com.example.homeworkout.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkout.databinding.ItemExerciseLayoutBinding
import com.example.homeworkout.models.Exercise

class ExerciseHistoryAdapter:RecyclerView.Adapter<ExerciseHistoryAdapter.ExerciseHistoryViewHolder>() {

    private var binding:ItemExerciseLayoutBinding? = null

    inner class ExerciseHistoryViewHolder(itemBinding:ItemExerciseLayoutBinding)
        :RecyclerView.ViewHolder(itemBinding.root)

    private val differCallBack = object: DiffUtil.ItemCallback<Exercise>(){
        override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem.time == newItem.time
        }

        override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
          return oldItem == newItem
        }


    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseHistoryViewHolder {

        binding = ItemExerciseLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ExerciseHistoryViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: ExerciseHistoryViewHolder, position: Int) {

        val exercise = differ.currentList[position]

        holder.itemView.apply {
            binding?.tvDate?.text = exercise.date
            binding?.tvTime?.text = exercise.time
            binding?.tvExerciseName?.text = exercise.name
            binding?.itemIvImage?.setImageResource(exercise.image)



            val durationInSeconds = exercise.duration // Replace with your actual duration value
            val minutes = durationInSeconds / 60
            val seconds = durationInSeconds % 60
            val durationString = String.format("%02d:%02d", minutes, seconds)


            binding?.tvTimeSecs?.text = durationString



           binding?.calorieCounts?.text = exercise.calories.toString()
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}