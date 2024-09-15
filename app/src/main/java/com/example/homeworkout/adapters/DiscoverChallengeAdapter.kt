package com.example.homeworkout.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkout.databinding.ItemChallengeBinding
import com.example.homeworkout.fragments.DiscoverFragment
import com.example.homeworkout.models.Workout

class DiscoverChallengeAdapter(val fragment: Fragment, val context: Context): RecyclerView.Adapter<DiscoverChallengeAdapter.BeginnerViewHolder>() {

    private var binding: ItemChallengeBinding? = null

    inner class BeginnerViewHolder(itemBinding: ItemChallengeBinding):
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallBack = object: DiffUtil.ItemCallback<Workout>(){
        override fun areItemsTheSame(oldItem: Workout, newItem: Workout): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Workout, newItem: Workout): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeginnerViewHolder {
        binding = ItemChallengeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BeginnerViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: BeginnerViewHolder, position: Int) {
        val exercise = differ.currentList[position]
        holder.itemView.apply {
            binding?.tvHiit?.text = exercise.name
            setOnClickListener {
                if(fragment is DiscoverFragment){
                    fragment.navigatingToWorkoutTypeFragmentForBeginner(position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}