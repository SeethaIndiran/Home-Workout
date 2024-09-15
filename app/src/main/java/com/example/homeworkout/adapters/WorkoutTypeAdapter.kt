package com.example.homeworkout.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkout.databinding.ItemWorkoutTypeBinding
import com.example.homeworkout.models.WorkoutType

class WorkoutTypeAdapter:RecyclerView.Adapter<WorkoutTypeAdapter.WorkoutTypeHolder>() {

    private var binding : ItemWorkoutTypeBinding? = null

    inner class WorkoutTypeHolder(itemBinding:ItemWorkoutTypeBinding):
            RecyclerView.ViewHolder(itemBinding.root)

    private val differCallBack = object: DiffUtil.ItemCallback<WorkoutType>(){
        override fun areItemsTheSame(oldItem: WorkoutType, newItem: WorkoutType): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: WorkoutType, newItem: WorkoutType): Boolean {
           return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutTypeHolder {
        binding = ItemWorkoutTypeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WorkoutTypeHolder(binding!!)
    }

    override fun onBindViewHolder(holder: WorkoutTypeHolder, position: Int) {
        val workout_type = differ.currentList[position]
        holder.itemView.apply {

            binding?.tvWorkoutType?.text = workout_type.name
            binding?.ivWorkoutType?.setAnimation(workout_type.imageAnimation)
            binding?.ivWorkoutType?.playAnimation()
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}