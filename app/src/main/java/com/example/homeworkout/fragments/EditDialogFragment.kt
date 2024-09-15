package com.example.homeworkout.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.homeworkout.Constants
import com.example.homeworkout.databinding.FragmentEditDialogBinding
import com.example.homeworkout.models.GraphModel
import com.example.homeworkout.viewmodels.GraphViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ClassCastException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.math.roundToInt

@AndroidEntryPoint
class EditDialogFragment : DialogFragment() {

    private  lateinit var mBinding:FragmentEditDialogBinding

    private val graphViewModel: GraphViewModel by viewModels()

    companion object {
        private const val METRIC_UNITS_VIEW = "METRIC_UNITS_VIEW"
        private const val US_UNITS_VIEW = "US_UNITS_VIEW"
    }

    private var currentVisibleView: String = METRIC_UNITS_VIEW

    @Inject
    lateinit var sharedPref: SharedPreferences

    private var firstClick = false

    private var firstClickUS = false

    private var updateListener: UpdateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding= FragmentEditDialogBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val metricUnitClick= sharedPref.getBoolean(Constants.METRIC_UNIT_CLICK,true)
        val usUnitClick = sharedPref.getBoolean(Constants.US_UNIT_CLICK,false)
        if(metricUnitClick && !usUnitClick){
            val metricBmi = sharedPref.getFloat(Constants.METRIC_BMI_VALUE,0f).toString()
updateListener!!.onUpdateUI(metricBmi)
        }else if(!metricUnitClick && usUnitClick){
            val usBmi = sharedPref.getFloat(Constants.US_BMI_VALUE,0f).toString()
  updateListener!!.onUpdateUI(usBmi)
        }

showView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            updateListener = targetFragment as UpdateListener
        }catch(e:ClassCastException){
            throw ClassCastException("parent")
        }

    }


    private fun showView(){
    currentVisibleView = METRIC_UNITS_VIEW

    val metricUnitClicked = sharedPref.getBoolean(Constants.METRIC_UNIT_CLICK,true)
    val usUnitClicked = sharedPref.getBoolean(Constants.US_UNIT_CLICK,false)

    currentVisibleView = if(metricUnitClicked && !usUnitClicked){
         METRIC_UNITS_VIEW

    }else{
        US_UNITS_VIEW
    }

    if(currentVisibleView == METRIC_UNITS_VIEW) {

        var state = false
        val metricWeight = sharedPref.getString(Constants.METRIC_WEIGHT,"")
        val metricHeight = sharedPref.getString(Constants.METRIC_HEIGHT,"")

        metricsUnitsView()

        mBinding.dialogMetricWeight.setText(metricWeight)
        mBinding.dialogMetricHeight.setText(metricHeight)

        mBinding.btnLb.setOnClickListener {
            state = true
            firstClick = true
            usFullView()
        }
        mBinding.btnKg.setOnClickListener {
            state = false
            metricFullView()
        }
        mBinding.btnCm.setOnClickListener {
            state = false
            metricFullView()

        }
        mBinding.btnIn.setOnClickListener {
            state = true
            firstClick = true
            usFullView()
        }
        mBinding.btnCancelMetric.setOnClickListener {
            //dialogs.cancel()
            dismiss()
        }
        mBinding.btnSaveMetric.setOnClickListener {

           // btnClicked = true



            if(!state){

                sharedPref.edit().putBoolean(Constants.US_UNIT_CLICK,false).apply()
                sharedPref.edit().putBoolean(Constants.METRIC_UNIT_CLICK,true).apply()
                calculateMetricBMIUnits()
                val bmi = sharedPref.getFloat(Constants.METRIC_BMI_VALUE,0f).toString()

         updateListener!!.onUpdateUI(bmi)



            }else{
                sharedPref.edit().putBoolean(Constants.US_UNIT_CLICK,true).apply()
                sharedPref.edit().putBoolean(Constants.METRIC_UNIT_CLICK,false).apply()
                calculateUSBMIUnits()
                val bmiUs = sharedPref.getFloat(Constants.US_BMI_VALUE,0f).toString()
                updateListener!!.onUpdateUI(bmiUs)

            }

            Toast.makeText(activity,"Details saved successfully!", Toast.LENGTH_SHORT).show()
         dismiss()
        }


    }

    else {

        var states = false

        val usWeight = sharedPref.getString(Constants.US_WEIGHT,"")
        val usFeet =  sharedPref.getString(Constants.US_FEET,"")
        val usInch = sharedPref.getString(Constants.US_INCH,"")

        usUnitsView()

        mBinding.dialogMetricWeightUs.setText(usWeight)
        mBinding.dialogMetricHeightFeet.setText(usFeet)
        mBinding.dialogMetricHeightInchUs.setText(usInch)

        mBinding.btnKg.setOnClickListener {
            firstClickUS = true
            states = true
            metricFullViewForMetricUnits()
        }

        mBinding.btnLb.setOnClickListener {
            states = false
            usFullViewForUSUnits()

        }

        mBinding.btnCm.setOnClickListener {
            firstClickUS = true
            states = true
            metricFullViewForMetricUnits()

        }
        mBinding.btnIn.setOnClickListener {
            states= false
            usFullViewForUSUnits()
        }
        mBinding.btnCancelMetric.setOnClickListener {
           // dialog.cancel()
            dismiss()
        }
        mBinding.btnSaveMetric.setOnClickListener {

            if(states){

                sharedPref.edit().putBoolean(Constants.US_UNIT_CLICK,false).apply()
                sharedPref.edit().putBoolean(Constants.METRIC_UNIT_CLICK,true).apply()
                calculateMetricBMIUnits()
                val bmiMet = sharedPref.getFloat(Constants.METRIC_BMI_VALUE,0f).toString()
                updateListener!!.onUpdateUI(bmiMet)


            }else{

                sharedPref.edit().putBoolean(Constants.US_UNIT_CLICK,true).apply()
                sharedPref.edit().putBoolean(Constants.METRIC_UNIT_CLICK,false).apply()
                calculateUSBMIUnits()
                val bmiMet = sharedPref.getFloat(Constants.US_BMI_VALUE,0f).toString()
                updateListener!!.onUpdateUI(bmiMet)

            }

            Toast.makeText(activity,"Details saved successfully!", Toast.LENGTH_SHORT).show()
         //   dialogs.cancel()
            dismiss()
        }

    }


  //  dialogs.show()
}
private fun metricsUnitsView(){
    mBinding.dialogMetricWeight.visibility = View.VISIBLE
    mBinding.tvDialogWeightTitle.visibility = View.VISIBLE
    mBinding.tvDialogHeightTitle.visibility = View.VISIBLE
    mBinding.dialogMetricHeight.visibility = View.VISIBLE
    mBinding.tvDialogWeightTitleUs.visibility = View.GONE
    mBinding.dialogMetricWeightUs.visibility = View.GONE
    mBinding.tvDialogHeightTitleUs.visibility = View.GONE
    mBinding.dialogMetricHeightFeet.visibility = View.GONE
    mBinding.dialogMetricHeightInchUs.visibility = View.GONE
}
private fun usUnitsView(){
    mBinding.dialogMetricWeight.visibility = View.GONE
    mBinding.tvDialogWeightTitle.visibility = View.GONE
    mBinding.tvDialogHeightTitle.visibility = View.GONE
    mBinding.dialogMetricHeight.visibility = View.GONE
    mBinding.tvDialogWeightTitleUs.visibility = View.VISIBLE
    mBinding.dialogMetricWeightUs.visibility = View.VISIBLE
    mBinding.tvDialogHeightTitleUs.visibility = View.VISIBLE
    mBinding.dialogMetricHeightFeet.visibility = View.VISIBLE
    mBinding.dialogMetricHeightInchUs.visibility = View.VISIBLE
}
private fun metricFullView(){
    val weightUsUnit = mBinding.dialogMetricWeightUs.text.toString()
    val usUnitFeet = mBinding.dialogMetricHeightFeet.text.toString()
    val usUnitInch = mBinding.dialogMetricHeightInchUs.text.toString()

    val metricWeight = sharedPref.getString(Constants.METRIC_WEIGHT,"")
    val metricHeight = sharedPref.getString(Constants.METRIC_HEIGHT,"")
    metricsUnitsView()
    if(  !firstClick){
        mBinding.dialogMetricWeight.setText(metricWeight)
        mBinding.dialogMetricHeight.setText( metricHeight)
    }else{
        val totalInches =(( usUnitFeet.toInt() * 12) + (usUnitInch.toInt()))
        val cm = (totalInches * 2.54).toString()
        if(weightUsUnit.isNotEmpty()){
            mBinding.dialogMetricWeight.setText (((weightUsUnit ).toDouble()/ 2.2046).toString())
        }else{
            mBinding.dialogMetricWeight.setText("")
        }
        if(usUnitFeet.isNotEmpty() && usUnitInch.isNotEmpty()){
            mBinding.dialogMetricHeight.setText( cm)
        }else{
            mBinding.dialogMetricHeight.setText( "")
        }



    }
}
private fun usFullView(){
    usUnitsView()

    val hintWeightMetric = mBinding.dialogMetricWeight.text.toString()
    val hintHeightMetric = mBinding.dialogMetricHeight.text.toString()

    if(hintWeightMetric.isNotEmpty()){
        mBinding.dialogMetricWeightUs.setText(((hintWeightMetric ).toDouble() * 2.20462).toString())
    }else{
        mBinding.dialogMetricWeightUs.setText("")
    }

    if(hintHeightMetric.isNotEmpty()){
        val totalInches = (hintHeightMetric).toDouble().div(2.54).roundToInt()
        mBinding.dialogMetricHeightFeet.setText((totalInches / 12).toString())
        mBinding.dialogMetricHeightInchUs.setText((totalInches % 12).toString())
    }else{
        mBinding.dialogMetricHeightFeet.setText("")  // Set empty if input is empty
        mBinding.dialogMetricHeightInchUs.setText("")
    }


}

private fun usFullViewForUSUnits(){

    val usWeight = sharedPref.getString(Constants.US_WEIGHT,"")
    val usFeet = sharedPref.getString(Constants.US_FEET,"")
    val usInch = sharedPref.getString(Constants.US_INCH,"")
    usUnitsView()
    if(  !firstClickUS){
        mBinding.dialogMetricWeightUs.setText(usWeight)
        mBinding.dialogMetricHeightFeet.setText( usFeet)
        mBinding.dialogMetricHeightInchUs.setText(usInch)
    }else{
        val metWeight = mBinding.dialogMetricWeight.text.toString()
        val metHeight = mBinding.dialogMetricHeight.text.toString()

        if(metWeight.isNotEmpty()){
            mBinding.dialogMetricWeightUs.setText (((metWeight ).toDouble()* 2.2046).toString())
        }else{
            mBinding.dialogMetricWeightUs.setText("")
        }

       if(metHeight.isNotEmpty()){
           val totalInch = (metHeight).toDouble().div(2.54).roundToInt()
           mBinding.dialogMetricHeightFeet.setText((totalInch / 12).toString())
           mBinding.dialogMetricHeightInchUs.setText((totalInch % 12).toString())
       }else{
           mBinding.dialogMetricHeightFeet.setText("")
           mBinding.dialogMetricHeightInchUs.setText("")
       }

    }
}

private fun metricFullViewForMetricUnits(){
    metricsUnitsView()

    val hintWeightMetric = mBinding.dialogMetricWeightUs.text.toString()
    val hintHeightMetricFeet = mBinding.dialogMetricHeightFeet.text.toString()
    val hintHeightMetricInch = mBinding.dialogMetricHeightInchUs.text.toString()

    val totalInches =(( hintHeightMetricFeet.toInt() * 12) + (hintHeightMetricInch.toInt()))
    val cm = (totalInches * 2.54).toString()

    if(hintWeightMetric.isNotEmpty()){
        mBinding.dialogMetricWeight.setText(((hintWeightMetric ).toDouble() / 2.20462).toString())
    }else{
        mBinding.dialogMetricWeight.setText("")
    }

    if(hintHeightMetricFeet.isNotEmpty() && hintHeightMetricInch.isNotEmpty()){
        mBinding.dialogMetricHeight.setText(cm)
    }else{
        mBinding.dialogMetricHeight.setText("")
    }



}

    private fun calculateMetricBMIUnits() {


        if (validateMetricUnits()) {
            mBinding.btnSaveMetric.isEnabled = true
            val weightValue: Float =
                mBinding.dialogMetricWeight.text.toString().toFloat()

            sharedPref.edit().putString(Constants.METRIC_WEIGHT,mBinding.dialogMetricWeight.text.toString()).apply()

            val heightValue: Float = mBinding.dialogMetricHeight.text.toString().toFloat()/100

            sharedPref.edit().putString(Constants.METRIC_HEIGHT,mBinding.dialogMetricHeight.text.toString()).apply()

            updateGraphEntriesToDatabase(weightValue)
//updateChartDelayed(weightValue)
            val bmi = weightValue / (heightValue * heightValue)
            sharedPref.edit().putFloat(Constants.METRIC_BMI_VALUE,bmi).apply()


        } else {

            sharedPref.edit().putString(Constants.METRIC_WEIGHT,"").apply()
            sharedPref.edit().putString(Constants.METRIC_HEIGHT,"").apply()
            sharedPref.edit().putFloat(Constants.METRIC_BMI_VALUE,0f).apply()
            updateGraphEntriesToDatabase(0f)
            Toast.makeText(activity, "Please enter valid details", Toast.LENGTH_SHORT)
                .show()
            mBinding.btnSaveMetric.isEnabled = false
        }

    }


    @SuppressLint("SimpleDateFormat")
    private fun calculateUSBMIUnits() {


        if(validateUSUnits()){
            val usUNitHeightValueFeet: String =
                mBinding.dialogMetricHeightFeet.text.toString()

            sharedPref.edit().putString(Constants.US_FEET,usUNitHeightValueFeet).apply()

            val usUNitHeightValueInch: String =
                mBinding.dialogMetricHeightInchUs.text.toString()
            sharedPref.edit().putString(Constants.US_INCH,usUNitHeightValueInch).apply()

            val usUNitWeightValue: Float =
                mBinding.dialogMetricWeightUs.text.toString().toFloat()
            sharedPref.edit().putString(Constants.US_WEIGHT,usUNitWeightValue.toString()).apply()

            val heightValue = usUNitHeightValueInch.toFloat() + (usUNitHeightValueFeet.toFloat() *12)

            val kg = (usUNitWeightValue.toDouble() * 0.45359237).toInt()

            updateGraphEntriesToDatabase(kg.toFloat())
//updateChartDelayed(usUNitWeightValue)



            val bmi = 703* (usUNitWeightValue / (heightValue * heightValue))

            sharedPref.edit().putFloat(Constants.US_BMI_VALUE,bmi).apply()



        }else{

            Toast.makeText(activity, "Please enter valid details", Toast.LENGTH_SHORT)
                .show()
        }

    }

    private fun updateGraphEntriesToDatabase(weightValue: Float) {

        val calendar = Calendar.getInstance()
        val dateFormatter = SimpleDateFormat("dd/MMM", Locale.getDefault())
        val currentDate = dateFormatter.format(calendar.time)

        val lbs = (weightValue.toDouble() * 2.2045).toFloat()

        graphViewModel.getAllEntries().observe(viewLifecycleOwner, Observer { entries ->
            entries?.let {
                var isUpdated = false
                for (item in it) {
                    try {
                        // Parse the stored date string into a Date object
                        val storedDate = dateFormatter.parse(item.date)
                        val currentParsedDate = dateFormatter.parse(currentDate)

                        // Compare the two dates (ignoring time)
                        if (storedDate != null && currentParsedDate != null && storedDate == currentParsedDate) {
                            // If the date matches, update the existing entry
                            item.weight = weightValue
                            item.lbWeight = lbs
                            graphViewModel.updateGraphEntries(item)
                            isUpdated = true
                            break  // Exit the loop once the entry is updated
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                // If no matching date was found, insert a new entry
                if (!isUpdated) {
                    graphViewModel.insertGraphEntries(GraphModel(weightValue, lbs, currentDate))
                }
            }
        })
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



    interface UpdateListener {
        fun onUpdateUI(value: String)
    }



}