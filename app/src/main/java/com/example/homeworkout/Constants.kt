package com.example.homeworkout

import com.example.homeworkout.models.Workout
import com.example.homeworkout.models.WorkoutType

object Constants {

    const val EXERCISE_DATABASE = "exercise_db"
    const val SHARED_PREFERENCES_NAME = "shared_pref"
    const val METRIC_BMI_VALUE = "metric_bmi_value"
    const val US_BMI_VALUE = "us_bmi_value"
    const val METRIC_WEIGHT = "metric_weight"
    const val METRIC_HEIGHT = "metric_height"
    const val US_WEIGHT = "us_weight"
    const val US_FEET = "us_feet"
    const val US_INCH = "us_inch"
    const val METRIC_UNIT_CLICK = "metric_unit_click"
    const val US_UNIT_CLICK = "us_unit_click"


    fun getBeginnerWorkouts():ArrayList<Workout>{
        val list = ArrayList<Workout>()

        val workoutOne = Workout("ABS BEGINNER",R.drawable.man,R.drawable.abs_db, arrayListOf(WorkoutType("JUMPING JACKS",R.raw.jumping_jacks),WorkoutType("ABDOMINAL CRUNCHES",R.raw.leg_raises),
            WorkoutType("RUSSIAN TWIST",R.raw.russisan_twists), WorkoutType("HEEL TOUCH",R.raw.squats_kick), WorkoutType("LEG RAISES",R.raw.leg_raises), WorkoutType
                ("PLANK",R.raw.plank), WorkoutType
                ("COBRA TOUCH",R.raw.stag), WorkoutType
                ("SPINE LUMBER TWIST STRETCH LEFT",R.raw.russisan_twists), WorkoutType
                ("SPINE LUMBER TWIST STRETCH RIGHT",R.raw.plank)))

        list.add(workoutOne)


        val workoutTwo = Workout("CHEST BEGINNER",R.drawable.chest,R.drawable.chest_db, arrayListOf(WorkoutType("JUMPING JACKS",R.raw.jumping_jacks),WorkoutType("INCLINE PUSHUPS",R.raw.stag),
            WorkoutType("WIDE ARM PUSHUPS",R.raw.lunges), WorkoutType
                ("PUSHUPS",R.raw.russisan_twists), WorkoutType("TRICEPS DIPS",R.raw.triceps_dips), WorkoutType("KNEE PUSH UPS",R.raw.squats_kick), WorkoutType
                ("PLANK",R.raw.plank), WorkoutType
                ("COBRA TOUCH",R.raw.leg_raises), WorkoutType
                ("COBRA STRETCH",R.raw.russisan_twists)))

        list.add(workoutTwo)

        val workoutThree = Workout("LEG BEGINNER", R.drawable.cycle,R.drawable.leg,arrayListOf(WorkoutType("SIDE HOP",R.raw.triceps_dips),WorkoutType("SQUATS",R.raw.stag),
            WorkoutType("SIDE LYING LEG LIFT LEFT",R.raw.jumping_jacks), WorkoutType
                ("SIDE LYING LEG LIFT RIGHT",R.raw.squats_kick), WorkoutType("BACKWARD LUNGE",R.raw.lunges), WorkoutType("DONKEY KICKS LEFT",R.raw.plank), WorkoutType
                ("DONKEY KICKS RIGHT",R.raw.jumping_jacks), WorkoutType
                ("KNEE TO CHEST STRETCH LEFT",R.raw.lunges), WorkoutType
                ("KNEE TO CHEST STRETCH RIGHT",R.raw.squats_kick)))

        list.add(workoutThree)

        val workoutFour = Workout("ARM BEGINNER",R.drawable.arm,R.drawable.arms, arrayListOf(WorkoutType("ARM RAISES",R.raw.jumping_jacks),WorkoutType("SIDE ARM RAISE",R.raw.russisan_twists),
            WorkoutType("TRICEPS DIPS",R.raw.triceps_dips), WorkoutType
                ("ARM CIRCLES CLOCKWISE",R.raw.jumping_jacks), WorkoutType("ARM CIRCLES COUNTER CLOCKWISE",R.raw.russisan_twists), WorkoutType("DOIAMOND PUSHUPS",R.raw.stag), WorkoutType
                ("JUMPING JACKS",R.raw.jumping_jacks), WorkoutType
                ("CHEST PRESS PULSE",R.raw.lunges), WorkoutType
                ("DIAGONAL PLANK",R.raw.plank)))

        list.add(workoutFour)

        val workoutFive = Workout("SHOULDER AND BACK BEGINNER ",R.drawable.back,R.drawable.shoulder, arrayListOf(WorkoutType("ARM RAISES",R.raw.squats_kick),WorkoutType("SIDE ARM RAISE",R.raw.jumping_jacks),
            WorkoutType("TRICEPS DIPS",R.raw.triceps_dips), WorkoutType
                ("ARM CIRCLES CLOCKWISE",R.raw.jumping_jacks), WorkoutType("ARM CIRCLES COUNTER CLOCKWISE",R.raw.triceps_dips), WorkoutType("DOIAMOND PUSHUPS",R.raw.stag), WorkoutType
                ("JUMPING JACKS",R.raw.squats_kick), WorkoutType
                ("KCHEST PRESS PULSE",R.raw.lunges), WorkoutType
                ("DIAGONAL PLANK",R.raw.plank)))

        list.add(workoutFive)

        return list
    }

    fun getIntermediateWorkouts():ArrayList<Workout>{
        val list = ArrayList<Workout>()

        val workoutOne = Workout("ABS INTERMEDIATE",R.drawable.man,R.drawable.abs_db, arrayListOf(WorkoutType("ABDOMINAL CRUNCHES",R.raw.leg_raises),
            WorkoutType("RUSSIAN TWIST",R.raw.russisan_twists), WorkoutType("HEEL TOUCH",R.raw.squats), WorkoutType("LEG RAISES",R.raw.leg_raises), WorkoutType
                ("PLANK",R.raw.plank), WorkoutType
                ("COBRA TOUCH",R.raw.pushups), WorkoutType
                ("SPINE LUMBER TWIST STRETCH LEFT",R.raw.russisan_twists), WorkoutType
                ("SPINE LUMBER TWIST STRETCH RIGHT",R.raw.plank)))

        list.add(workoutOne)


        val workoutTwo = Workout("CHEST INTERMEDIATE",R.drawable.chest,R.drawable.chest_db, arrayListOf(WorkoutType("JUMPING JACKS",R.raw.jumping_jacks),WorkoutType("INCLINE PUSHUPS",R.raw.pushups),
            WorkoutType("WIDE ARM PUSHUPS",R.raw.pushups), WorkoutType
                ("PUSHUPS",R.raw.pushups), WorkoutType("TRICEPS DIPS",R.raw.triceps_dips), WorkoutType("KNEE PUSH UPS",R.raw.pushups), WorkoutType
                ("PLANK",R.raw.plank), WorkoutType
                ("COBRA TOUCH",R.raw.leg_raises), WorkoutType
                ("COBRA STRETCH",R.raw.russisan_twists)))

        list.add(workoutTwo)

        val workoutThree = Workout("LEG INTERMEDIATE", R.drawable.cycle,R.drawable.leg,arrayListOf(WorkoutType("SIDE HOP",R.raw.triceps_dips),WorkoutType("SQUATS",R.raw.triceps_dips),
            WorkoutType("SIDE LYING LEG LIFT LEFT",R.raw.jumping_jacks), WorkoutType
                ("SIDE LYING LEG LIFT RIGHT",R.raw.squats), WorkoutType("BACKWARD LUNGE",R.raw.lunges), WorkoutType("DONKEY KICKS LEFT",R.raw.plank), WorkoutType
                ("DONKEY KICKS RIGHT",R.raw.jumping_jacks), WorkoutType
                ("KNEE TO CHEST STRETCH LEFT",R.raw.lunges), WorkoutType
                ("KNEE TO CHEST STRETCH RIGHT",R.raw.squats)))

        list.add(workoutThree)

        val workoutFour = Workout("ARM INTERMEDIATE",R.drawable.arm,R.drawable.arms, arrayListOf(WorkoutType("ARM RAISES",R.raw.jumping_jacks),WorkoutType("SIDE ARM RAISE",R.raw.russisan_twists),
            WorkoutType("TRICEPS DIPS",R.raw.triceps_dips), WorkoutType
                ("ARM CIRCLES CLOCKWISE",R.raw.jumping_jacks), WorkoutType("ARM CIRCLES COUNTER CLOCKWISE",R.raw.russisan_twists), WorkoutType("DOIAMOND PUSHUPS",R.raw.pushups), WorkoutType
                ("JUMPING JACKS",R.raw.jumping_jacks), WorkoutType
                ("CHEST PRESS PULSE",R.raw.lunges), WorkoutType
                ("DIAGONAL PLANK",R.raw.plank)))

        list.add(workoutFour)

        val workoutFive = Workout("SHOULDER AND BACK INTERMEDIATE ",R.drawable.back, R.drawable.shoulder,arrayListOf(WorkoutType("ARM RAISES",R.raw.squats),WorkoutType("SIDE ARM RAISE",R.raw.jumping_jacks),
            WorkoutType("TRICEPS DIPS",R.raw.triceps_dips), WorkoutType
                ("ARM CIRCLES CLOCKWISE",R.raw.jumping_jacks), WorkoutType("ARM CIRCLES COUNTER CLOCKWISE",R.raw.triceps_dips), WorkoutType("DOIAMOND PUSHUPS",R.raw.pushups), WorkoutType
                ("JUMPING JACKS",R.raw.jumping_jacks), WorkoutType
                ("KCHEST PRESS PULSE",R.raw.lunges), WorkoutType
                ("DIAGONAL PLANK",R.raw.plank)))

        list.add(workoutFive)

        return list
    }

    fun getAdvancedWorkouts():ArrayList<Workout>{
        val list = ArrayList<Workout>()


        val workoutOne = Workout("ABS ADVANCED",R.drawable.man,R.drawable.abs_db, arrayListOf(WorkoutType("DUMPING JACKS",R.raw.jumping_jacks),WorkoutType("ABDOMINAL CRUNCHES",R.raw.leg_raises),
            WorkoutType("RUSSIAN TWIST",R.raw.russisan_twists), WorkoutType("HEEL TOUCH",R.raw.squats), WorkoutType("LEG RAISES",R.raw.leg_raises), WorkoutType
                ("PLANK",R.raw.plank), WorkoutType
                ("COBRA TOUCH",R.raw.pushups), WorkoutType
                ("SPINE LUMBER TWIST STRETCH LEFT",R.raw.russisan_twists), WorkoutType
                ("SPINE LUMBER TWIST STRETCH RIGHT",R.raw.plank)))

        list.add(workoutOne)


        val workoutTwo = Workout("CHEST ADVANCED",R.drawable.chest,R.drawable.chest_db, arrayListOf(WorkoutType("JUMPING JACKS",R.raw.jumping_jacks),
            WorkoutType("WIDE ARM PUSHUPS",R.raw.pushups), WorkoutType
                ("PUSHUPS",R.raw.pushups), WorkoutType("TRICEPS DIPS",R.raw.triceps_dips), WorkoutType("KNEE PUSH UPS",R.raw.pushups), WorkoutType
                ("PLANK",R.raw.plank), WorkoutType
                ("COBRA TOUCH",R.raw.leg_raises), WorkoutType
                ("COBRA STRETCH",R.raw.russisan_twists)))

        list.add(workoutTwo)

        val workoutThree = Workout("LEG ADVANCED", R.drawable.cycle,R.drawable.leg,arrayListOf(WorkoutType("SIDE HOP",R.raw.triceps_dips),WorkoutType("SQUATS",R.raw.triceps_dips),
            WorkoutType("SIDE LYING LEG LIFT LEFT",R.raw.jumping_jacks), WorkoutType
                ("SIDE LYING LEG LIFT RIGHT",R.raw.squats), WorkoutType("BACKWARD LUNGE",R.raw.lunges), WorkoutType("DONKEY KICKS LEFT",R.raw.plank), WorkoutType
                ("DONKEY KICKS RIGHT",R.raw.jumping_jacks), WorkoutType
                ("KNEE TO CHEST STRETCH LEFT",R.raw.lunges), WorkoutType
                ("KNEE TO CHEST STRETCH RIGHT",R.raw.squats)))

        list.add(workoutThree)

        val workoutFour = Workout("ARM ADVANCED",R.drawable.arm,R.drawable.arms, arrayListOf(WorkoutType("ARM RAISES",R.raw.jumping_jacks),WorkoutType("SIDE ARM RAISE",R.raw.russisan_twists),
            WorkoutType("TRICEPS DIPS",R.raw.triceps_dips), WorkoutType
                ("ARM CIRCLES CLOCKWISE",R.raw.jumping_jacks), WorkoutType("ARM CIRCLES COUNTER CLOCKWISE",R.raw.russisan_twists), WorkoutType("DOIAMOND PUSHUPS",R.raw.pushups), WorkoutType
                ("JUMPING JACKS",R.raw.jumping_jacks), WorkoutType
                ("CHEST PRESS PULSE",R.raw.lunges), WorkoutType
                ("DIAGONAL PLANK",R.raw.plank)))

        list.add(workoutFour)

        val workoutFive = Workout("SHOULDER AND BACK ADVANCED ",R.drawable.back,R.drawable.shoulder, arrayListOf(WorkoutType("ARM RAISES",R.raw.squats),WorkoutType("SIDE ARM RAISE",R.raw.jumping_jacks),
            WorkoutType("TRICEPS DIPS",R.raw.triceps_dips), WorkoutType
                ("ARM CIRCLES CLOCKWISE",R.raw.jumping_jacks), WorkoutType("ARM CIRCLES COUNTER CLOCKWISE",R.raw.triceps_dips), WorkoutType("DOIAMOND PUSHUPS",R.raw.pushups), WorkoutType
                ("JUMPING JACKS",R.raw.jumping_jacks), WorkoutType
                ("KCHEST PRESS PULSE",R.raw.lunges), WorkoutType
                ("DIAGONAL PLANK",R.raw.plank)))

        list.add(workoutFive)

        return list
    }
fun getFatBurningWorkouts(): ArrayList<Workout> {
        val list = ArrayList<Workout>()


        val workoutOne = Workout("ABS ADVANCED",R.drawable.man,R.drawable.abs_db, arrayListOf(WorkoutType("DUMPING JACKS",R.raw.jumping_jacks),WorkoutType("ABDOMINAL CRUNCHES",R.raw.leg_raises),
            WorkoutType("RUSSIAN TWIST",R.raw.russisan_twists), WorkoutType("HEEL TOUCH",R.raw.squats), WorkoutType("LEG RAISES",R.raw.leg_raises), WorkoutType
                ("PLANK",R.raw.plank), WorkoutType
                ("COBRA TOUCH",R.raw.pushups), WorkoutType
                ("SPINE LUMBER TWIST STRETCH LEFT",R.raw.russisan_twists), WorkoutType
                ("SPINE LUMBER TWIST STRETCH RIGHT",R.raw.plank)))

        list.add(workoutOne)


        val workoutTwo = Workout("CHEST ADVANCED",R.drawable.chest,R.drawable.chest_db, arrayListOf(WorkoutType("JUMPING JACKS",R.raw.jumping_jacks),
            WorkoutType("WIDE ARM PUSHUPS",R.raw.pushups), WorkoutType
                ("PUSHUPS",R.raw.pushups), WorkoutType("TRICEPS DIPS",R.raw.triceps_dips), WorkoutType("KNEE PUSH UPS",R.raw.pushups), WorkoutType
                ("PLANK",R.raw.plank), WorkoutType
                ("COBRA TOUCH",R.raw.leg_raises), WorkoutType
                ("COBRA STRETCH",R.raw.russisan_twists)))

        list.add(workoutTwo)

        val workoutThree = Workout("LEG ADVANCED", R.drawable.cycle,R.drawable.leg,arrayListOf(WorkoutType("SIDE HOP",R.raw.triceps_dips),WorkoutType("SQUATS",R.raw.triceps_dips),
            WorkoutType("SIDE LYING LEG LIFT LEFT",R.raw.jumping_jacks), WorkoutType
                ("SIDE LYING LEG LIFT RIGHT",R.raw.squats), WorkoutType("BACKWARD LUNGE",R.raw.lunges), WorkoutType("DONKEY KICKS LEFT",R.raw.plank), WorkoutType
                ("DONKEY KICKS RIGHT",R.raw.jumping_jacks), WorkoutType
                ("KNEE TO CHEST STRETCH LEFT",R.raw.lunges), WorkoutType
                ("KNEE TO CHEST STRETCH RIGHT",R.raw.squats)))

        list.add(workoutThree)

        val workoutFour = Workout("ARM ADVANCED",R.drawable.arm,R.drawable.arms, arrayListOf(WorkoutType("ARM RAISES",R.raw.jumping_jacks),WorkoutType("SIDE ARM RAISE",R.raw.russisan_twists),
            WorkoutType("TRICEPS DIPS",R.raw.triceps_dips), WorkoutType
                ("ARM CIRCLES CLOCKWISE",R.raw.jumping_jacks), WorkoutType("ARM CIRCLES COUNTER CLOCKWISE",R.raw.russisan_twists), WorkoutType("DOIAMOND PUSHUPS",R.raw.pushups), WorkoutType
                ("JUMPING JACKS",R.raw.jumping_jacks), WorkoutType
                ("CHEST PRESS PULSE",R.raw.lunges), WorkoutType
                ("DIAGONAL PLANK",R.raw.plank)))

        list.add(workoutFour)



        return list
    }

    fun getPicksForYou():ArrayList<Workout>{
        val list = ArrayList<Workout>()

        val workoutOne = Workout("HIIT intermediate",R.drawable.man,R.drawable.abs_db, arrayListOf(WorkoutType("ABDOMINAL CRUNCHES",R.raw.leg_raises),
            WorkoutType("RUSSIAN TWIST",R.raw.russisan_twists), WorkoutType("HEEL TOUCH",R.raw.squats), WorkoutType("LEG RAISES",R.raw.leg_raises), WorkoutType
                ("PLANK",R.raw.plank), WorkoutType
                ("COBRA TOUCH",R.raw.pushups), WorkoutType
                ("SPINE LUMBER TWIST STRETCH LEFT",R.raw.russisan_twists), WorkoutType
                ("SPINE LUMBER TWIST STRETCH RIGHT",R.raw.plank)))

        list.add(workoutOne)


        val workoutTwo = Workout("HIIT killer core",R.drawable.chest,R.drawable.chest_db, arrayListOf(WorkoutType("JUMPING JACKS",R.raw.jumping_jacks),WorkoutType("INCLINE PUSHUPS",R.raw.pushups),
            WorkoutType("WIDE ARM PUSHUPS",R.raw.pushups), WorkoutType
                ("PUSHUPS",R.raw.pushups), WorkoutType("TRICEPS DIPS",R.raw.triceps_dips), WorkoutType("KNEE PUSH UPS",R.raw.pushups), WorkoutType
                ("PLANK",R.raw.plank), WorkoutType
                ("COBRA TOUCH",R.raw.leg_raises), WorkoutType
                ("COBRA STRETCH",R.raw.russisan_twists)))

        list.add(workoutTwo)

        val workoutThree = Workout("Fat burning HIIT", R.drawable.cycle,R.drawable.leg,arrayListOf(WorkoutType("SIDE HOP",R.raw.triceps_dips),WorkoutType("SQUATS",R.raw.triceps_dips),
            WorkoutType("SIDE LYING LEG LIFT LEFT",R.raw.jumping_jacks), WorkoutType
                ("SIDE LYING LEG LIFT RIGHT",R.raw.squats), WorkoutType("BACKWARD LUNGE",R.raw.lunges), WorkoutType("DONKEY KICKS LEFT",R.raw.plank), WorkoutType
                ("DONKEY KICKS RIGHT",R.raw.jumping_jacks), WorkoutType
                ("KNEE TO CHEST STRETCH LEFT",R.raw.lunges), WorkoutType
                ("KNEE TO CHEST STRETCH RIGHT",R.raw.squats)))

        list.add(workoutThree)

        val workoutFour = Workout("Build wider shoulders",R.drawable.arm,R.drawable.arms, arrayListOf(WorkoutType("ARM RAISES",R.raw.jumping_jacks),WorkoutType("SIDE ARM RAISE",R.raw.russisan_twists),
            WorkoutType("TRICEPS DIPS",R.raw.triceps_dips), WorkoutType
                ("ARM CIRCLES CLOCKWISE",R.raw.jumping_jacks), WorkoutType("ARM CIRCLES COUNTER CLOCKWISE",R.raw.russisan_twists), WorkoutType("DOIAMOND PUSHUPS",R.raw.pushups), WorkoutType
                ("JUMPING JACKS",R.raw.jumping_jacks), WorkoutType
                ("CHEST PRESS PULSE",R.raw.lunges), WorkoutType
                ("DIAGONAL PLANK",R.raw.plank)))

        list.add(workoutFour)



        return list
    }


    fun getFastWorkout():ArrayList<Workout>{
        val list = ArrayList<Workout>()

        val workoutOne = Workout("4 MIN Tabata",R.drawable.man,R.drawable.abs_db, arrayListOf(WorkoutType("ABDOMINAL CRUNCHES",R.raw.leg_raises),
            WorkoutType("RUSSIAN TWIST",R.raw.russisan_twists), WorkoutType("HEEL TOUCH",R.raw.squats), WorkoutType("LEG RAISES",R.raw.leg_raises), WorkoutType
                ("PLANK",R.raw.plank), WorkoutType
                ("COBRA TOUCH",R.raw.pushups), WorkoutType
                ("SPINE LUMBER TWIST STRETCH LEFT",R.raw.russisan_twists), WorkoutType
                ("SPINE LUMBER TWIST STRETCH RIGHT",R.raw.plank)))

        list.add(workoutOne)


        val workoutTwo = Workout("Loose brlly fat",R.drawable.chest,R.drawable.chest_db, arrayListOf(WorkoutType("JUMPING JACKS",R.raw.jumping_jacks),WorkoutType("INCLINE PUSHUPS",R.raw.pushups),
            WorkoutType("WIDE ARM PUSHUPS",R.raw.pushups), WorkoutType
                ("PUSHUPS",R.raw.pushups), WorkoutType("TRICEPS DIPS",R.raw.triceps_dips), WorkoutType("KNEE PUSH UPS",R.raw.pushups), WorkoutType
                ("PLANK",R.raw.plank), WorkoutType
                ("COBRA TOUCH",R.raw.leg_raises), WorkoutType
                ("COBRA STRETCH",R.raw.russisan_twists)))

        list.add(workoutTwo)

        val workoutThree = Workout("7 min classic", R.drawable.cycle,R.drawable.leg,arrayListOf(WorkoutType("SIDE HOP",R.raw.triceps_dips),WorkoutType("SQUATS",R.raw.triceps_dips),
            WorkoutType("SIDE LYING LEG LIFT LEFT",R.raw.jumping_jacks), WorkoutType
                ("SIDE LYING LEG LIFT RIGHT",R.raw.squats), WorkoutType("BACKWARD LUNGE",R.raw.lunges), WorkoutType("DONKEY KICKS LEFT",R.raw.plank), WorkoutType
                ("DONKEY KICKS RIGHT",R.raw.jumping_jacks), WorkoutType
                ("KNEE TO CHEST STRETCH LEFT",R.raw.lunges), WorkoutType
                ("KNEE TO CHEST STRETCH RIGHT",R.raw.squats)))

        list.add(workoutThree)

        val workoutFour = Workout("Before workout warmup",R.drawable.arm,R.drawable.arms, arrayListOf(WorkoutType("ARM RAISES",R.raw.jumping_jacks),WorkoutType("SIDE ARM RAISE",R.raw.russisan_twists),
            WorkoutType("TRICEPS DIPS",R.raw.triceps_dips), WorkoutType
                ("ARM CIRCLES CLOCKWISE",R.raw.jumping_jacks), WorkoutType("ARM CIRCLES COUNTER CLOCKWISE",R.raw.russisan_twists), WorkoutType("DOIAMOND PUSHUPS",R.raw.pushups), WorkoutType
                ("JUMPING JACKS",R.raw.jumping_jacks), WorkoutType
                ("CHEST PRESS PULSE",R.raw.lunges), WorkoutType
                ("DIAGONAL PLANK",R.raw.plank)))

        list.add(workoutFour)

        val workoutFive = Workout("7 min abs workout",R.drawable.arm,R.drawable.arms, arrayListOf(WorkoutType("ARM RAISES",R.raw.jumping_jacks),WorkoutType("SIDE ARM RAISE",R.raw.russisan_twists),
            WorkoutType("TRICEPS DIPS",R.raw.triceps_dips), WorkoutType
                ("ARM CIRCLES CLOCKWISE",R.raw.jumping_jacks), WorkoutType("ARM CIRCLES COUNTER CLOCKWISE",R.raw.russisan_twists), WorkoutType("DOIAMOND PUSHUPS",R.raw.pushups), WorkoutType
                ("JUMPING JACKS",R.raw.jumping_jacks), WorkoutType
                ("CHEST PRESS PULSE",R.raw.lunges), WorkoutType
                ("DIAGONAL PLANK",R.raw.plank)))

        list.add(workoutFive)



        return list
    }

}