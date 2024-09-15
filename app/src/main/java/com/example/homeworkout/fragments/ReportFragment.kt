package com.example.homeworkout.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.homeworkout.Constants
import com.example.homeworkout.R
import com.example.homeworkout.common.MyDayViewDecorator
import com.example.homeworkout.databinding.DialogBmiEditBinding
import com.example.homeworkout.databinding.FragmentReportBinding
import com.example.homeworkout.models.GraphModel
import com.example.homeworkout.viewmodels.ExerciseViewModel
import com.example.homeworkout.viewmodels.GraphViewModel
import com.example.homeworkout.viewmodels.WorkoutsListViewModel
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import com.prolificinteractive.materialcalendarview.CalendarMode
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dialog_bmi_edit.*
import kotlinx.android.synthetic.main.fragment_report.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class ReportFragment : Fragment(), EditDialogFragment.UpdateListener {

    private lateinit var binding:FragmentReportBinding

    private lateinit var mBinding: DialogBmiEditBinding

 private lateinit var dialog:Dialog

    private val graphViewModel:GraphViewModel by viewModels()

    private val workoutViewModel:WorkoutsListViewModel by viewModels()

    private val exerciseViewModel:ExerciseViewModel by viewModels()

    private val fragment:FinishFragment ? = null

    @Inject
    lateinit var sharedPref:SharedPreferences



    private var firstClick = false

    var firstClickUS = false

    var btnClicked = false

    var weight_update  :Float = 0f

    private val handler = Handler()
    private var runnable: Runnable? = null

    var state = true



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
        binding=FragmentReportBinding.inflate(inflater,container,false)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarExercise)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Report"


        val us_bmi_value  = sharedPref.getFloat(Constants.US_BMI_VALUE,0f)

        populatingValues()


       // showCharts()
        if(state){
            showCharts()
        }else{
            showLbsCharts()
        }
        if(state){
            binding.lbsTitle.text = "Lbs"
        }else{
            binding.lbsTitle.text  = "Kg"
        }



        showCurrentWeek()
        val metric_unit_click= sharedPref.getBoolean(Constants.METRIC_UNIT_CLICK,true)
        val us_unit_click = sharedPref.getBoolean(Constants.US_UNIT_CLICK,false)
        if(metric_unit_click && !us_unit_click){
            val metric_bmi = sharedPref.getFloat(Constants.METRIC_BMI_VALUE,0f).toString()
            binding.tvBmiValue.text = metric_bmi
            setPieChart(metric_bmi.toFloat())
            displayBMIResults(metric_bmi.toFloat())
        }else if(!metric_unit_click && us_unit_click){
            val us_bmi = sharedPref.getFloat(Constants.US_BMI_VALUE,0f).toString()
            binding.tvBmiValue.text = us_bmi
            setPieChart(us_bmi.toFloat())
            displayBMIResults(us_bmi.toFloat())
        }

        binding.btnBmiEdit.setOnClickListener {

           // state = true
          //  showBMIEditDialog()
           // binding.lineChart.invalidate()
           // restartFragment()
            val dialogFragment = EditDialogFragment()
            dialogFragment.setTargetFragment(this,0)
            dialogFragment.show(requireFragmentManager(), "MyDialogFragment")

        }

        binding.lbsTitle.setOnClickListener {
            // binding.lineChart.invalidate()
            state = !state

          if(state){
             showCharts()
          }else{
              showLbsCharts()
          }
            if(state){
                binding.lbsTitle.text = "Lbs"
            }else{
                binding.lbsTitle.text  = "Kg"
            }

        }





    }

    private fun showCharts(){

        graphViewModel.getAllEntries().observe(viewLifecycleOwner, Observer {
            it?.let {

                chartsTest(it)


            }
        })

    }

    private fun showLbsCharts(){
        graphViewModel.getAllEntries().observe(viewLifecycleOwner, Observer {
            it?.let {
                chartsTestLbs(it)
            }
        })
    }

    private fun populatingValues(){
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

    @SuppressLint("SimpleDateFormat")
    private fun showCurrentWeek(){
        binding.prolificCalendarView.topbarVisible = false
        binding.prolificCalendarView.selectionColor = ContextCompat.getColor(requireContext(),R.color.white)
        binding.prolificCalendarView.state().edit()
            .setCalendarDisplayMode(CalendarMode.WEEKS)
            .commit()
      exerciseViewModel.getExercises().observe(viewLifecycleOwner, Observer {
          for(exercise in it){

                  val stringDate = exercise.date
              val formatter = SimpleDateFormat("dd/MMM/yyyy")
              val date: Date = formatter.parse(stringDate) as Date
              val calender = Calendar.getInstance()
              calender.time = date
              val color = ContextCompat.getDrawable(requireContext(),R.drawable.datereport_background)

              val decorator = color?.let { it1 -> MyDayViewDecorator(calender, it1) }
              binding.prolificCalendarView.addDecorator(decorator)

          }
      })

binding.prolificCalendarView.setHeaderTextAppearance(R.style.CalendarWidgetHeader)
        binding.prolificCalendarView.setWeekDayTextAppearance(R.style.CalendarWidgetHeader)
        binding.prolificCalendarView.setDateTextAppearance(R.style.CalendarWidgetHeader)

    }



    private fun setPieChart(bmi: Float){

        val entries : ArrayList<PieEntry> = ArrayList()

        entries.add(PieEntry(15f,"Very severely underweight"))
        entries.add(PieEntry(16f,"Severely underweight"))
        entries.add(PieEntry(18.5f,"Underweight"))
        entries.add(PieEntry(25f,"Normal"))
        entries.add(PieEntry(30f,"Overweight"))
        entries.add(PieEntry(35f,"Obese Class | Moderately Overweight"))
        entries.add(PieEntry(40f,"Obese Class || Severely Overweight"))
        entries.add(PieEntry(100f,"Obese Class ||| Very Severely Overweight"))

        val colors = ArrayList<Int>()
        colors.add(Color.BLUE)
        colors.add(Color.RED)
        colors.add(Color.GREEN)
        colors.add(Color.YELLOW)
        colors.add(Color.DKGRAY)
        colors.add(Color.MAGENTA)
        colors.add(Color.LTGRAY)
        colors.add(Color.GREEN)


        val pieDataSet = PieDataSet(entries,"BMI Value ")

        pieDataSet.colors = colors
        pieDataSet.sliceSpace = 1f

        val pieData = PieData(pieDataSet)
        pieData.setDrawValues(true)
        pieData.setValueFormatter(PercentFormatter(binding.bmiPieChart))
        pieData.setValueTextColor(Color.BLACK)
        pieData.setValueTextSize(12f)
        binding.bmiPieChart.data = pieData
       binding.bmiPieChart.invalidate()

        binding.bmiPieChart.highlightValue(highlightBMI(bmi),0,false)
        binding.bmiPieChart.description.isEnabled = true
        binding.bmiPieChart.description.text = "BMI Value"
        binding.bmiPieChart.setDrawCenterText(true)
        binding.bmiPieChart.setUsePercentValues(false)
        binding.bmiPieChart.centerText = "BMI Value"
        binding.bmiPieChart.setCenterTextSize(24f)
        binding.bmiPieChart.setDrawEntryLabels(false)

     //  val legend = binding.bmiPieChart.legend
       // legend.isEnabled = true
        //legend.textSize = 12f
       // legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        //legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        //legend.orientation = Legend.LegendOrientation.VERTICAL
        //legend.setDrawInside(false)




        // binding.bmiPieChart.holeRadius = 3f
        binding.bmiPieChart.animateY(2000)
       // binding.bmiPieChart.setBackgroundColor(R.color.white)


    }
    private fun highlightBMI(bmi:Float):Float{
        if (bmi.compareTo(15f) <= 0) {
           return 0f
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0) {

            return 1f
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0) {

            return 2f
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0) {
            return 3f
        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0) {

            return 4f
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0) {

            return 5f
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0) {
            return 6f
        } else {

            return 7f
        }
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
            bmiDescription = "Oops! You really need to take better care of yourself!!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0) {

            bmiLabel = "Obese class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take better care of yourself!!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0) {

            bmiLabel = "Obese class || (Severely obese)"
            bmiDescription = "Oops! You really need to take better care of yourself!!"
        } else {

            bmiLabel = "Obese class ||| ( Very Severely obese)"
            bmiDescription = "Oops! You really need to take better care of yourself!!"
        }

        val bmiValue = BigDecimal(bmi.toDouble())
            .setScale(2, RoundingMode.HALF_EVEN).toString()

       binding.bmiDescription.text = bmiLabel
    }

    @SuppressLint("SimpleDateFormat")
    fun chartsTest(it:List<GraphModel>){
        val lineChart = binding.lineChart
        val entries = ArrayList<Entry>()
        val arrayListX = ArrayList<String>()

                for(entry in it.indices){
                    val graphModel = it[entry]
                    arrayListX.add(graphModel.date)
                    entries.add(Entry(entry.toFloat(),graphModel.weight))


                    val xAxis = lineChart.xAxis
                    // xAxis.labelCount = 3
                    xAxis.valueFormatter =IndexAxisValueFormatter(arrayListX)
                    // xAxis.granularity = 1f
                    //  xAxis.isGranularityEnabled = true

                    val dataSet = LineDataSet(entries, "Weight")

                    dataSet.lineWidth = 2f
                    dataSet.color = Color.parseColor("#2697f4")
                    dataSet.valueTextSize  = 12f
                    dataSet.valueTextColor = Color.BLACK
                    dataSet.setDrawCircles(false)
                    //  dataSet.setDrawCircleHole(true)
                    //  dataSet.circleRadius = 7f
                    //   dataSet.circleHoleRadius = 4f
                    //   dataSet.setCircleColor(Color.BLUE)
                    //   dataSet.circleHoleColor = Color.parseColor("#f426df")
                    dataSet.setDrawFilled(true)
                    dataSet.fillColor = Color.parseColor("#2697f4")
                    dataSet.setDrawValues(true)
                    //  dataSet.fillDrawable = ContextCompat.getDrawable(requireContext(),R.drawable.background_round)
                    dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER

                    val line_data = LineData(dataSet)

                    lineChart.data = line_data


                 //  lineChart.invalidate()
                    //  lineChart.notifyDataSetChanged()
                    lineChart.setVisibleXRangeMaximum(4f)
                    lineChart.moveViewToX(0f)
                    //lineChart.moveViewToX(1f)
                    //  lineChart.scrollX
                   // lineChart.invalidate()
                   // lineChart.notifyDataSetChanged()
                   // lineChart.animateXY(3000,3000)
                    xAxis.setDrawGridLines(false)
                    xAxis.position = XAxis.XAxisPosition.BOTTOM
                    lineChart.apply {
                        description.isEnabled = false
                        setTouchEnabled(true)
                        setDrawGridBackground(false)
                        isDragEnabled = true
                        setScaleEnabled(true)
                        setPinchZoom(true)
                        setDrawBorders(false)
                    }
                }

    }

    @SuppressLint("SimpleDateFormat")
    fun chartsTestLbs(it:List<GraphModel>){
        val lineChart = binding.lineChart
        val entries = ArrayList<Entry>()
        val arrayListX = ArrayList<String>()

        for(entry in it.indices){
            val graphModel = it[entry]
            arrayListX.add(graphModel.date)
            entries.add(Entry(entry.toFloat(),graphModel.lbWeight))


            val xAxis = lineChart.xAxis
            // xAxis.labelCount = 3
            xAxis.valueFormatter =IndexAxisValueFormatter(arrayListX)
            // xAxis.granularity = 1f
            //  xAxis.isGranularityEnabled = true

            val dataSet = LineDataSet(entries, "Weight")

            dataSet.lineWidth = 2f
            dataSet.color = Color.parseColor("#2697f4")
            dataSet.valueTextSize  = 12f
            dataSet.valueTextColor = Color.BLACK
            dataSet.setDrawCircles(false)
            //  dataSet.setDrawCircleHole(true)
            //  dataSet.circleRadius = 7f
            //   dataSet.circleHoleRadius = 4f
            //   dataSet.setCircleColor(Color.BLUE)
            //   dataSet.circleHoleColor = Color.parseColor("#f426df")
            dataSet.setDrawFilled(true)
            dataSet.fillColor = Color.parseColor("#2697f4")
            dataSet.setDrawValues(true)
            //  dataSet.fillDrawable = ContextCompat.getDrawable(requireContext(),R.drawable.background_round)
            dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER

            val line_data = LineData(dataSet)

            lineChart.data = line_data


            //  lineChart.invalidate()
            //  lineChart.notifyDataSetChanged()
            lineChart.setVisibleXRangeMaximum(4f)
            lineChart.moveViewToX(0f)
            //lineChart.moveViewToX(1f)
            //  lineChart.scrollX
            // lineChart.invalidate()
            // lineChart.notifyDataSetChanged()
            // lineChart.animateXY(3000,3000)
            xAxis.setDrawGridLines(false)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            lineChart.apply {
                description.isEnabled = false
                setTouchEnabled(true)
                setDrawGridBackground(false)
                isDragEnabled = true
                setScaleEnabled(true)
                setPinchZoom(true)
                setDrawBorders(false)
            }
        }

    }





    private fun validateMetricUnits(): Boolean {
        var isValid = true


        if (mBinding.dialogMetricWeight.text.toString().isEmpty()) {
            isValid = false
        } else if (mBinding.dialogMetricHeight.text.toString().isEmpty()) {

            isValid = false
        }
        return isValid
    }

    private fun validateUSUnits(): Boolean {
        var isValid = true

        when {
            mBinding.dialogMetricWeightUs.text.toString().isEmpty() -> {
                isValid = false
            }

            mBinding.dialogMetricHeightFeet.text.toString().isEmpty() -> {
                isValid = false
            }

            mBinding.dialogMetricHeightInchUs.text.toString().isEmpty() -> {
                isValid = false
            }
        }


        return isValid
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onUpdateUI(value: String) {
   binding.tvBmiValue.text = value
        setPieChart(value.toFloat())
        displayBMIResults(value.toFloat())
       showCharts()
        restartFragment()
    }

    // Inside your activity or hosting fragment

    // Step 1: Obtain a reference to the FragmentManager
    // or childFragmentManager if you're in a fragment

    // Step 2: Create the restartFragment() function
    private fun restartFragment() {
       // private val fragmentManager = supportFragmentManager
        val currentFragment = fragmentManager?.findFragmentById(R.id.reportFragment)

        if (currentFragment != null) {
            // Step 3: Remove the existing fragment
            fragmentManager?.beginTransaction()?.remove(currentFragment)?.commit()

            // Step 4: Create a new instance of the fragment
            val newFragment = ReportFragment()

            // Step 5: Replace the fragment in the container
            fragmentManager?.beginTransaction()?.replace(R.id.reportFragment, newFragment, "YOUR_FRAGMENT_TAG")?.commit()
        }
    }



}









