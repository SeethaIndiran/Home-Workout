package com.example.homeworkout.fragments

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.homeworkout.Constants
import com.example.homeworkout.R
import com.example.homeworkout.databinding.FragmentFinishBinding
import com.example.homeworkout.models.GraphModel
import com.example.homeworkout.models.WorkoutsList
import com.example.homeworkout.viewmodels.ExerciseViewModel
import com.example.homeworkout.viewmodels.GraphViewModel
import com.example.homeworkout.viewmodels.WorkoutsListViewModel
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import nl.dionsegijn.konfetti.KonfettiView
import nl.dionsegijn.konfetti.models.Size
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class FinishFragment : Fragment() {

    private lateinit var binding: FragmentFinishBinding

    private val graphViewModel:GraphViewModel by viewModels()

    private val viewModel:WorkoutsListViewModel by viewModels()

    private val exerciseViewModel:ExerciseViewModel by viewModels()

    private  lateinit var konfetti_view:KonfettiView

    val args:FinishFragmentArgs by navArgs()

    @Inject
    lateinit var sharedPref: SharedPreferences


    companion object {
        private const val METRIC_UNITS_VIEW = "METRIC_UNITS_VIEW"
        private const val US_UNITS_VIEW = "US_UNITS_VIEW"
    }

    private var currentVisibleView: String = METRIC_UNITS_VIEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFinishBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = binding.toolbarFinish
        val collapsingToolbar = binding.collapseLl
        val appBar = binding.appLl

        appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (collapsingToolbar.height + verticalOffset < 2 * ViewCompat.getMinimumHeight(
                    collapsingToolbar
                )
            ) {
                toolbar.navigationIcon?.setColorFilter(resources.getColor(R.color.black), PorterDuff.Mode.SRC_ATOP)

            } else {
                toolbar.navigationIcon
                    ?.setColorFilter(resources.getColor(R.color.white), PorterDuff.Mode.SRC_ATOP)
            }
        })

        binding.toolbarFinish.setNavigationIcon(R.drawable.ic_baseline_arrow_back)

        collapsingToolbar.title = "GO TO HISTORY"

        binding.toolbarFinish.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_finishFragment_to_historyFragment)
        }



     populatingValues()
      showConfetti()
        makeVisibleMetricUnitsView()
        sharedPref.edit().putBoolean(Constants.METRIC_UNIT_CLICK,true).apply()
        sharedPref.edit().putBoolean(Constants.US_UNIT_CLICK,false).apply()

        binding.rgUnits.setOnCheckedChangeListener { _, checkId: Int ->

            if (checkId == R.id.rbMetricUnits) {
                makeVisibleMetricUnitsView()
                sharedPref.edit().putBoolean(Constants.METRIC_UNIT_CLICK,true).apply()
                sharedPref.edit().putBoolean(Constants.US_UNIT_CLICK,false).apply()
            } else {
                makeVisibleUSUnitsView()
                sharedPref.edit().putBoolean(Constants.METRIC_UNIT_CLICK,false).apply()
                sharedPref.edit().putBoolean(Constants.US_UNIT_CLICK,true).apply()
            }
        }

        binding.btnCalculate.setOnClickListener {
            calculateUnits()
        }

        binding.finishNextBtn.setOnClickListener {
            val exercise = args.exercise

            val bundle = Bundle().apply {
                putSerializable("exercise",exercise)
            }
            findNavController().navigate(R.id.action_finishFragment_to_historyFragment,bundle)
        }

        insertDetails()

    }

    private fun insertDetails(){
        val workoutList = args.workout
        val exercise = args.exercise
        var workout :WorkoutsList

      viewModel.getAllList().observe(viewLifecycleOwner, Observer { it ->
          if(it.isEmpty()){
              viewModel.insertWorkoutsList(workoutList!!)

          }else{
             val num = it.size
              val item = it[num-1]
              if(  item.week_number == workoutList!!.week_number){
                   viewModel.updateWorkoutsList(item)
              }else{
                  viewModel.insertWorkoutsList(workoutList)

              }
          }
      })

    }

    private fun populatingValues(){
        val exercise = args.exercise

        binding.tvFinishExercise.text = exercise?.name

        exerciseViewModel.getExercises().observe(viewLifecycleOwner, Observer {
            it?.let {

                binding.exerciseCount.text = it.size.toString()

                val total_calories = it.sumOf {
                    it.calories
                }
                binding.calorieCount.text = total_calories.toString()

                val total_duration = it.sumOf {
                    it.duration
                }

                val durationInSeconds = total_duration // Replace with your actual duration value
                val minutes = durationInSeconds / 60
                val seconds = durationInSeconds % 60
                val durationString = String.format("%02d:%02d", minutes, seconds)
                binding.timeCount.text = durationString
            }
        })
    }


    private fun showConfetti(){

           konfetti_view = binding.konfettiView

        konfetti_view.build().addColors(Color.GREEN,Color.YELLOW,Color.MAGENTA)
            .setDirection(0.0,359.0)
            .setSpeed(1f,5f)
            .setFadeOutEnabled(true)
            .addShapes(nl.dionsegijn.konfetti.models.Shape.Square,nl.dionsegijn.konfetti.models.Shape.Circle)
            .setTimeToLive(2000L)
            .addSizes(Size(12))
            .setPosition(-50f, konfetti_view.width + 50f,-50f,-50f)
            .streamFor(700,5000L)
    }


    private fun makeVisibleMetricUnitsView() {
        currentVisibleView = METRIC_UNITS_VIEW
        binding.tilMetricUnitWeight.visibility = View.VISIBLE
        binding.tilMetricUnitHeight.visibility = View.VISIBLE
        binding.tilMetricUnitWeightUs.visibility = View.GONE
        binding.tilMetricUnitHeightUsFeet.visibility = View.GONE
        binding.tilMetricUnitHeightUsInch.visibility = View.GONE

        binding.etMetricUnitHeight.text!!.clear()
        binding.etMetricUnitWeight.text!!.clear()

        binding.llDisplayBmiResult.visibility = View.INVISIBLE
    }

    private fun makeVisibleUSUnitsView() {
        currentVisibleView = US_UNITS_VIEW
        binding.tilMetricUnitWeight.visibility = View.INVISIBLE
        binding.tilMetricUnitHeight.visibility = View.INVISIBLE
        binding.tilMetricUnitWeightUs.visibility = View.VISIBLE
        binding.tilMetricUnitHeightUsFeet.visibility = View.VISIBLE
        binding.tilMetricUnitHeightUsInch.visibility = View.VISIBLE

        binding.etMetricUnitWeightUs.text!!.clear()
        binding.etMetricUnitHeightUsFeet.text!!.clear()
        binding.etMetricUnitHeightUsInch.text!!.clear()

        binding.llDisplayBmiResult.visibility = View.INVISIBLE
    }


    private fun displayBMIResults(bmi: Float) {
        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0) {

            bmiLabel = "Severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0) {

            bmiLabel = " Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0) {

            bmiLabel = "Normal "
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0) {

            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0) {

            bmiLabel = "Obese class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0) {

            bmiLabel = "Obese class || (Severely obese)"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else {

            bmiLabel = "Obese class ||| ( Very Severely obese)"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        }

        val bmiValue = BigDecimal(bmi.toDouble())
            .setScale(2, RoundingMode.HALF_EVEN).toString()

        binding.llDisplayBmiResult.visibility = View.VISIBLE
        binding.tvBmiValue.text = bmiValue
        binding.tvBmiType.text = bmiLabel
        binding.tvBmiDescription.text = bmiDescription
    }



    @SuppressLint("SimpleDateFormat")
    private fun calculateUnits() {

        if (currentVisibleView == METRIC_UNITS_VIEW) {
            if (validateMetricUnits()) {
                val heightValue: Float =
                    binding.etMetricUnitHeight.text.toString().toFloat() / 100

                sharedPref.edit().putString(Constants.METRIC_HEIGHT,binding.etMetricUnitHeight.text.toString()).apply()

                val weightValue: Float = binding.etMetricUnitWeight.text.toString().toFloat()

                sharedPref.edit().putString(Constants.METRIC_WEIGHT,binding.etMetricUnitWeight.text.toString()).apply()


             insertGraphEntriesToDatabase(weightValue)

                val bmi = weightValue / (heightValue * heightValue)
                sharedPref.edit().putFloat(Constants.METRIC_BMI_VALUE,bmi).apply()
                displayBMIResults(bmi)

            } else {

                Toast.makeText(activity, "Please enter valid details", Toast.LENGTH_SHORT)
                    .show()
            }
        }else{
            if(validateUSUnits()){
                val usUNitHeightValueFeet: String =
                    binding.etMetricUnitHeightUsFeet.text.toString()

                sharedPref.edit().putString(Constants.US_FEET,usUNitHeightValueFeet).apply()

                val usUNitHeightValueInch: String =
                    binding.etMetricUnitHeightUsInch.text.toString()
                sharedPref.edit().putString(Constants.US_INCH,usUNitHeightValueInch).apply()

                val usUNitWeightValue: Float =
                    binding.etMetricUnitWeightUs.text.toString().toFloat()
              //  insertDetails(usUNitWeightValue.toDouble())
                val kg = (usUNitWeightValue.toDouble() * 0.45359237).toInt()

                sharedPref.edit().putString(Constants.US_WEIGHT,binding.etMetricUnitWeightUs.text.toString()
                ).apply()

                val heightValue = usUNitHeightValueInch.toFloat() + usUNitHeightValueFeet.toFloat() *12


                insertGraphEntriesToDatabase(kg.toFloat())

                val bmi = 703* (usUNitWeightValue / (heightValue * heightValue))

                sharedPref.edit().putFloat(Constants.US_BMI_VALUE,bmi).apply()

                displayBMIResults(bmi)

            }else{

                Toast.makeText(activity, "Please enter valid details", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun insertGraphEntriesToDatabase(weightValue:Float){
        val calendar = Calendar.getInstance()
        val dateFormatter = SimpleDateFormat("dd/MMM")
        val currentDate =  dateFormatter.format(calendar.time)

        val lbs = (weightValue.toDouble()*2.2045).toFloat()

      //  graphViewModel.insertGraphEntries(GraphModel(weightValue,currentDate))

        graphViewModel.getAllEntries().observe(viewLifecycleOwner, Observer {
            if(it.isEmpty()) {

                graphViewModel.insertGraphEntries(GraphModel(weightValue,lbs,currentDate))

            }else{
                val num = it.size
                val model = it[num-1]
                if(model.date == currentDate){
                    model.weight = weightValue
                    model.lbWeight = lbs

                    graphViewModel.updateGraphEntries(model)

                }else{
                    graphViewModel.insertGraphEntries(GraphModel(weightValue,lbs,currentDate))
                }
            }

        })





    }


    private fun validateMetricUnits(): Boolean {
        var isValid = true


        if (binding?.etMetricUnitWeight?.text.toString().isEmpty()) {
            isValid = false
        } else if (binding?.etMetricUnitHeight?.text.toString().isEmpty()) {

            isValid = false
        }
        return isValid
    }

    private fun validateUSUnits(): Boolean {
        var isValid = true

        when {
            binding?.etMetricUnitWeightUs?.text.toString().isEmpty() -> {
                isValid = false
            }

            binding?.etMetricUnitHeightUsFeet?.text.toString().isEmpty() -> {
                isValid = false
            }

            binding?.etMetricUnitHeightUsInch?.text.toString().isEmpty() -> {
                isValid = false
            }
        }


        return isValid
    }

    private fun shi(){

    }

}