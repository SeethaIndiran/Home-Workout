package com.example.homeworkout.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkout.R
import com.example.homeworkout.databinding.ItemExerciseRecyclerViewBinding
import com.example.homeworkout.fragments.TrainingFragment
import com.example.homeworkout.models.Workout
import kotlinx.android.synthetic.main.item_exercise_recycler_view.view.*

class BeginnerExerciseAdapter(val fragment: Fragment, val context: Context):RecyclerView.Adapter<BeginnerExerciseAdapter.ExerciseViewHolder>() {

    private var binding:ItemExerciseRecyclerViewBinding? = null

    inner class ExerciseViewHolder(itemBinding:ItemExerciseRecyclerViewBinding):
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallBack = object:DiffUtil.ItemCallback<Workout>(){
        override fun areItemsTheSame(oldItem: Workout, newItem: Workout): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Workout, newItem: Workout): Boolean {
           return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        binding = ItemExerciseRecyclerViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ExerciseViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = differ.currentList[position]
        holder.itemView.cl_layout.apply {
            binding?.tvItemBeginer?.text = exercise.name
            binding?.ivExercise?.setImageResource(exercise.image)
            binding?.clLayout?.background = ContextCompat.getDrawable(context,R.drawable.background_round_exercise)
            setOnClickListener {
                if(fragment is TrainingFragment){
                    fragment.navigatingToWorkoutTypeFragmentForBeginner(position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

}