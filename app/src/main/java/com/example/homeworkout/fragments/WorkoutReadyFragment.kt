package com.example.homeworkout.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.homeworkout.Constants
import com.example.homeworkout.R
import com.example.homeworkout.databinding.FragmentWorkoutReadyBinding
import com.example.homeworkout.models.Exercise
import com.example.homeworkout.models.Workout
import com.example.homeworkout.models.WorkoutType
import com.example.homeworkout.models.WorkoutsList
import com.example.homeworkout.viewmodels.ExerciseViewModel
import com.example.homeworkout.viewmodels.WorkoutsListViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class WorkoutReadyFragment : Fragment(),TextToSpeech.OnInitListener {

        private lateinit var binding: FragmentWorkoutReadyBinding

        //private val viewModel:WorkoutsListViewModel by viewModels()

       private val exerciseViewmodel:ExerciseViewModel by viewModels()

        val args:WorkoutReadyFragmentArgs by navArgs()

    private var tts:TextToSpeech? = null

    private var workout_position:Int = 0
   private var wr_list = ArrayList<Workout>()
    private var wt_list = ArrayList<WorkoutType>()
    private var current_exercise_position = 0
    private var total_duration = 0

       private var readyTimer:CountDownTimer? = null
       private var readyProgress:Int = 0

    private var exerciseTimer:CountDownTimer? = null
    private var exerciseProgress:Int = 0

    private var restTimer:CountDownTimer? = null
    private var restProgress:Int = 0

    private var resumeProgress:Int = 0
    private var quitProgress:Int = 0

    //private lateinit var textToSpeech: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                if(exerciseTimer!=null){
                    exerciseTimer?.cancel()
                     quitProgress = exerciseProgress
                }
                binding.exerciseImage.pauseAnimation()
                quitBottomSheetDialog()
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        binding = FragmentWorkoutReadyBinding.inflate(inflater,container,false)

        tts = TextToSpeech(activity,this)

        return binding.root





    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)








      //  var wr_position = 0


       //  workout_position = args.workoutPosition

        if(args.player == "beginner"){
            wr_list = Constants.getBeginnerWorkouts()
            workout_position = args.workoutPosition
            wt_list = wr_list[workout_position].type
        }else if(args.player == "intermediate"){
            wr_list = Constants.getIntermediateWorkouts()
            workout_position = args.workoutPosition
            wt_list = wr_list[workout_position].type
        }else if(args.player == "advanced"){
            wr_list = Constants.getAdvancedWorkouts()
            workout_position = args.workoutPosition
            wt_list = wr_list[workout_position].type
        }



        setUpReadyWorkout()





    }




    private fun setUpReadyWorkout(){

        binding.workoutImage.visibility = View.VISIBLE
        binding.workoutName.visibility = View.VISIBLE
        binding.frameLayout.visibility = View.VISIBLE
        binding.arrow.visibility = View.VISIBLE
        binding.flExercise.visibility = View.INVISIBLE
        binding.frameLayoutErxercise.visibility = View.INVISIBLE
        binding.exerciseImage.visibility = View.INVISIBLE
        binding.tvExercise.visibility = View.INVISIBLE
        binding.btnPause.visibility = View.INVISIBLE
        binding.btnPrevious.visibility = View.INVISIBLE
        binding.btnSkip.visibility = View.INVISIBLE
        binding.clRestView.visibility = View.INVISIBLE
        binding.backBtn.visibility = View.INVISIBLE
        binding.progressBarExerciseLine.visibility = View.INVISIBLE




        if(readyTimer!=null){
            readyTimer?.cancel()
            readyProgress = 0
        }

        binding.progressBar.progress = readyProgress

        binding.workoutImage.setAnimation(wt_list[current_exercise_position].imageAnimation)
        binding.workoutImage.playAnimation()

        readyTimer = object:CountDownTimer(20000,1000){
            override fun onTick(p: Long) {
                readyProgress++
                binding.progressBar.progress = 20- readyProgress
                binding.tvTimer.text = (20-readyProgress).toString()
                binding.workoutName.text = wt_list[current_exercise_position].name

            }

            override fun onFinish() {

                if(exerciseTimer!=null){
                    exerciseTimer?.cancel()
                    exerciseProgress = 0

                }

                exerciseViewSetup()


            }

        }.start()

        binding.arrow.setOnClickListener {

            if(readyTimer!= null){
                readyTimer?.cancel()
                readyProgress = 0
            }

           exerciseViewSetup()

        }

    }

private  fun exerciseViewSetup(){
    binding.workoutImage.visibility = View.INVISIBLE
    binding.workoutName.visibility = View.INVISIBLE
    binding.frameLayout.visibility = View.INVISIBLE
    binding.arrow.visibility = View.INVISIBLE
    binding.readyToGo.visibility = View.INVISIBLE
    binding.clRestView.visibility = View.INVISIBLE
    binding.flExercise.visibility = View.VISIBLE
    binding.frameLayoutErxercise.visibility = View.VISIBLE
    binding.exerciseImage.visibility = View.VISIBLE
    binding.tvExercise.visibility = View.VISIBLE
    binding.btnPause.visibility = View.VISIBLE
    binding.btnPrevious.visibility = View.VISIBLE
    binding.btnSkip.visibility = View.VISIBLE
    binding.progressBarExerciseLine.visibility = View.VISIBLE
    binding.backBtn.visibility = View.VISIBLE

    binding.progressBarExerciseLine.progress = current_exercise_position + 1
    binding.progressBarExerciseLine.max = wt_list.size



  //  tts!!.speak(Constants.getBeginnerWorkouts()[workout_position].type[current_exercise_position].name,TextToSpeech.QUEUE_FLUSH,null,null)


    if(exerciseTimer!=null){
        exerciseTimer?.cancel()
        exerciseProgress = 0
    }



    binding.progressBarExercise.progress = exerciseProgress
    binding.exerciseImage.setAnimation(wt_list[current_exercise_position].imageAnimation)
    binding.exerciseImage.playAnimation()
    speak()

    exerciseTimer = object:CountDownTimer(30000,1000){
        override fun onTick(p0: Long) {
            exerciseProgress++
            binding.progressBarExercise.progress = 30-(exerciseProgress )
            binding.tvTimerErxercise.text = (30-exerciseProgress).toString()
            binding.tvExercise.text = wt_list[current_exercise_position].name

        }

        override fun onFinish() {
            total_duration += exerciseProgress
            navigatingToFinishFragment(total_duration)
        }

    }.start()

    if(current_exercise_position == 0){
        binding.btnPrevious.setTextColor(Color.parseColor("#808080"))
    }else{
        binding.btnPrevious.setTextColor(Color.parseColor("#FFFFFF"))
    }

    binding.btnSkip.setOnClickListener {

        total_duration += exerciseProgress

        if(restTimer!=null){
            restTimer?.cancel()
            restProgress = 0
        }
        if(exerciseTimer!=null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

      navigatingToFinishFragment(total_duration)
    }
    binding.btnPrevious.setOnClickListener {
        if(current_exercise_position == 0){
            Toast.makeText(activity,"No previous exercises..!",Toast.LENGTH_SHORT).show()

        }else{

            current_exercise_position--
            if(restTimer!=null){
                restTimer?.cancel()
                restProgress = 0
            }
            if(exerciseTimer!=null){
                exerciseTimer?.cancel()
                exerciseProgress = 0
            }
            restTimeSetup()
        }
    }
    binding.btnPause.setOnClickListener {


        if(exerciseTimer!= null){
            exerciseTimer?.cancel()
            resumeProgress = exerciseProgress

        }
        binding.exerciseImage.pauseAnimation()

        bottomSheetDialogForPause()

    }
    binding.backBtn.setOnClickListener {
        quitBottomSheetDialog()
    }

}
    @SuppressLint("SetTextI18n")
    private fun restTimeSetup(){

        binding.workoutImage.visibility = View.INVISIBLE
        binding.workoutName.visibility = View.INVISIBLE
        binding.frameLayout.visibility = View.INVISIBLE
        binding.arrow.visibility = View.INVISIBLE
        binding.readyToGo.visibility = View.INVISIBLE
        binding.frameLayoutErxercise.visibility = View.INVISIBLE
        binding.exerciseImage.visibility = View.INVISIBLE
        binding.tvExercise.visibility = View.INVISIBLE
        binding.btnPause.visibility = View.INVISIBLE
        binding.btnPrevious.visibility = View.INVISIBLE
        binding.btnSkip.visibility = View.INVISIBLE
        binding.clRestView.visibility = View.VISIBLE
        binding.flExercise.visibility = View.INVISIBLE
        binding.backBtn.visibility = View.INVISIBLE
        binding.progressBarExerciseLine.visibility = View.INVISIBLE


        if(restTimer!=null){
            restTimer?.cancel()
            restProgress = 0
        }
        if(exerciseTimer!=null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }


        //speak("rest")
        binding.lottieView.setAnimation(wt_list[current_exercise_position].imageAnimation)
        binding.lottieView.playAnimation()

        restTimer = object:CountDownTimer(20000,1000){
            override fun onTick(p0: Long) {
                restProgress++

                val durationInSeconds = 20-restProgress // Replace with your actual duration value
                val minutes = durationInSeconds / 60
                val seconds = durationInSeconds % 60
                val durationString = String.format("%02d:%02d", minutes, seconds)

                binding.tvRestTimer.text = durationString
                binding.tvNextExercise.text = wt_list[current_exercise_position].name
               // binding.tvNextExerciseCount.text = (current_exercise_position+1).toString()
            }

            override fun onFinish() {
                exerciseViewSetup()
            }

        }.start()
        binding.restSkipBtn.setOnClickListener {
            if(restTimer!=null){
                restTimer?.cancel()
                restProgress = 0
            }
            if(exerciseTimer!=null){
                exerciseTimer?.cancel()
                exerciseProgress = 0
            }
            exerciseViewSetup()
        }
        binding.tvNextExerciseCount.text = (current_exercise_position+1).toString() + "/" + "${Constants.getBeginnerWorkouts()[workout_position].type.size}"
        binding.tvNextExercise.text = wt_list[current_exercise_position].name

    }

    override fun onDestroy() {
        super.onDestroy()

        if(restTimer!= null){
            restTimer?.cancel()
            restProgress = 0
        }

        if(exerciseTimer!= null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }


        if(readyTimer!= null){
            readyTimer?.cancel()
            restProgress = 0
        }
      //  insertExercise()
        if(tts!=null){
            tts!!.stop()
            tts!!.shutdown()
        }
        binding.exerciseImage.cancelAnimation()
        binding.workoutImage.cancelAnimation()
    }

    private fun navigatingToFinishFragment(time:Int){
        if(current_exercise_position<wt_list.size-1){
            current_exercise_position++
            restTimeSetup()
        }else{
            if(exerciseTimer!= null){
                exerciseTimer?.cancel()
                exerciseProgress = 0
            }

            if(restTimer!= null){
                restTimer?.cancel()
                restProgress = 0
            }
            insertExercise(time)



        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun insertExercise(totalTime:Int){

        if(exerciseTimer!= null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        if(restTimer!= null){
            restTimer?.cancel()
            restProgress = 0
        }

        val time = Calendar.getInstance().time
        val timeFormatter = SimpleDateFormat("HH:mm a")
        val currentTime = timeFormatter.format(time)
        val calendar = Calendar.getInstance()
        val dateFormatter = SimpleDateFormat("dd/MMM/yyyy")
        val curntDate =  dateFormatter.format(calendar.time)
        val cal = Calendar.getInstance()
        val current_week_number = cal.get(Calendar.WEEK_OF_YEAR)
        cal.set(Calendar.DAY_OF_WEEK, calendar.firstDayOfWeek)
        val startDate = cal.time
        val startDateString = dateFormatter.format(startDate)
        cal.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY)
        val lastDay = cal.time
        val lastDateString  = dateFormatter.format(lastDay)

        val name =  wr_list[workout_position].name
        val exerciseList = ArrayList<Exercise>()
        val workouts = 5
       // val calorie = 80
        val duration = 20
        val image_db = wr_list[workout_position].imageDB
         val met = 30.0
        val weight = 60.0

        val calorie = ((met*weight*3.5)/(totalTime.toDouble())).toInt()

        val durationInSeconds = totalTime // Replace with your actual duration value
        val minutes = durationInSeconds / 60
        val seconds = durationInSeconds % 60
      //  val durationString = String.format("%02d:%02d", minutes, seconds)

        val exercise = Exercise(name,image_db,current_week_number,curntDate,currentTime,totalTime,calorie)
 //exerciseList.add(exercise)


 val workout =  WorkoutsList(current_week_number,curntDate,startDateString, lastDateString,workouts,duration,calorie,exerciseList)

       exerciseViewmodel.insertExercise(exercise)


        val bundle = Bundle().apply {
            putSerializable("workout",workout)
            putSerializable("exercise",exercise)
        }

        if(findNavController().currentDestination?.id == R.id.workoutReadyFragment){
            findNavController().navigate(R.id.action_workoutReadyFragment_to_finishFragment,bundle)}



        // exerciseViewModel.insertExercise(exercise)
    }
    @SuppressLint("InflateParams")
    private fun bottomSheetDialogForPause(){
        val bottomDialogLayout = layoutInflater.inflate(R.layout.bottom_dialog_sheet_pause_btn,null)
              val dialogView = BottomSheetDialog(requireContext(),R.style.BottomSheetDialogTheme)
        dialogView.setContentView(bottomDialogLayout)
        dialogView.show()
        dialogView.setCancelable(false)
        bottomDialogLayout.findViewById<View>(R.id.restart_btn).setOnClickListener {

            if(exerciseTimer!= null){
                exerciseTimer?.cancel()
                exerciseProgress = 0
            }
            binding.exerciseImage.pauseAnimation()

            exerciseViewSetup()
            dialogView.cancel()

        }
          bottomDialogLayout.findViewById<View>(R.id.quit_btn).setOnClickListener {
              val bundle = Bundle().apply{
                  putInt("workout",workout_position)
                  putString("player",args.player)
              }

              findNavController().navigate(R.id.action_workoutReadyFragment_to_workoutTypeFragment,bundle)
              dialogView.cancel()
          }
        bottomDialogLayout.findViewById<View>(R.id.resume_btn).setOnClickListener {

            if(exerciseTimer!= null){
                exerciseTimer?.cancel()
            }

       binding.exerciseImage.pauseAnimation()

            exerciseProgress = resumeProgress
            exerciseViewSetupTwo()
            dialogView.cancel()
        }
    }

    @SuppressLint("InflateParams")
    private fun quitBottomSheetDialog(){
        val bottomDialog = layoutInflater.inflate(R.layout.quit_bottom_sheet,null)
        val dialogView = BottomSheetDialog(requireContext(),R.style.BottomSheetDialogTheme)
        dialogView.setContentView(bottomDialog)
        dialogView.show()
        dialogView.setCancelable(false)
        bottomDialog.findViewById<View>(R.id.take_a_look_btn).setOnClickListener {
            val bundle = Bundle().apply{
                putInt("workout",workout_position)
                putString("player",args.player)
            }

            findNavController().navigate(R.id.action_workoutReadyFragment_to_workoutTypeFragment,bundle)
            dialogView.cancel()
        }
        bottomDialog.findViewById<View>(R.id.too_hard).setOnClickListener {

            val bundle = Bundle().apply{
                putInt("workout",workout_position)
                putString("player",args.player)
            }

            findNavController().navigate(R.id.action_workoutReadyFragment_to_workoutTypeFragment,bundle)
            dialogView.cancel()
        }
        bottomDialog.findViewById<View>(R.id.dont_know).setOnClickListener {
            val bundle = Bundle().apply{
                putInt("workout",workout_position)
                putString("player",args.player)
            }

            findNavController().navigate(R.id.action_workoutReadyFragment_to_workoutTypeFragment,bundle)
            dialogView.cancel()
        }
        bottomDialog.findViewById<View>(R.id.btn_quit_two).setOnClickListener {
            val bundle = Bundle().apply{
                putInt("workout",workout_position)
                putString("player",args.player)
            }

            findNavController().navigate(R.id.action_workoutReadyFragment_to_workoutTypeFragment,bundle)
            dialogView.cancel()
        }
        bottomDialog.findViewById<View>(R.id.iv_back).setOnClickListener {
            dialogView.cancel()
            exerciseProgress = quitProgress
            exerciseViewSetupTwo()
        }

    }
    private  fun exerciseViewSetupTwo(){
        binding.workoutImage.visibility = View.INVISIBLE
        binding.workoutName.visibility = View.INVISIBLE
        binding.frameLayout.visibility = View.INVISIBLE
        binding.arrow.visibility = View.INVISIBLE
        binding.readyToGo.visibility = View.INVISIBLE
        binding.clRestView.visibility = View.INVISIBLE
        binding.frameLayoutErxercise.visibility = View.VISIBLE
        binding.exerciseImage.visibility = View.VISIBLE
        binding.tvExercise.visibility = View.VISIBLE
        binding.btnPause.visibility = View.VISIBLE
        binding.btnPrevious.visibility = View.VISIBLE
        binding.btnSkip.visibility = View.VISIBLE
        binding.backBtn.visibility = View.VISIBLE
        binding.flExercise.visibility = View.VISIBLE


        binding.exerciseImage.setAnimation(wt_list[current_exercise_position].imageAnimation)
        binding.exerciseImage.playAnimation()

        if(restTimer!= null){
            restTimer?.cancel()
            restProgress = 0
        }


        exerciseTimer = object:CountDownTimer((30000-(exerciseProgress*1000)).toLong(),
           1000
        ){
            override fun onTick(p0: Long) {
                exerciseProgress++
                binding.progressBarExercise.progress = 30-exerciseProgress
                binding.tvTimerErxercise.text = (30-exerciseProgress).toString()
                binding.tvExercise.text = wt_list[current_exercise_position].name

            }

            override fun onFinish() {

                navigatingToFinishFragment(total_duration)
            }

        }.start()

        if(current_exercise_position == 0){
            binding.btnPrevious.setTextColor(Color.parseColor("#808080"))
        }else{
            binding.btnPrevious.setTextColor(Color.parseColor("#000000"))
        }

        binding.btnSkip.setOnClickListener {

            if(restTimer!=null){
                restTimer?.cancel()
                restProgress = 0
            }
            if(exerciseTimer!=null){
                exerciseTimer?.cancel()
                exerciseProgress = 0
            }
            navigatingToFinishFragment(total_duration)
        }
        binding.btnPrevious.setOnClickListener {
            if(current_exercise_position == 0){
                Toast.makeText(activity,"No previous exercises..!",Toast.LENGTH_SHORT).show()

            }else{

                current_exercise_position--
                if(restTimer!=null){
                    restTimer?.cancel()
                    restProgress = 0
                }
                if(exerciseTimer!=null){
                    exerciseTimer?.cancel()
                    exerciseProgress = 0
                }
                restTimeSetup()
            }
        }
        binding.btnPause.setOnClickListener {
            resumeProgress = exerciseProgress

            binding.exerciseImage.pauseAnimation()

            bottomSheetDialogForPause()
        }
        binding.backBtn.setOnClickListener {
            quitBottomSheetDialog()
        }

    }

    override fun onInit(status: Int) {
                   if(status == TextToSpeech.SUCCESS){
                       val output = tts?.setLanguage(Locale.US)

                       if(output == TextToSpeech.LANG_MISSING_DATA || output == TextToSpeech.LANG_NOT_SUPPORTED){
                           Log.e("tts","lang not supported")
                       }

                   }else{
                       Toast.makeText(context,"Initialization failed",Toast.LENGTH_SHORT).show()
                   }
    }
    private fun speak(){
        val text = binding.tvExercise.text
        tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,null)
    }


}
