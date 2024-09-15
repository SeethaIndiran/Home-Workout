package com.example.homeworkout.di

import android.content.Context
import androidx.room.Room
import com.example.homeworkout.Constants
import com.example.homeworkout.Constants.SHARED_PREFERENCES_NAME
import com.example.homeworkout.db.ExerciseDao
import com.example.homeworkout.db.ExerciseDatabase
import com.example.homeworkout.db.GraphDao
import com.example.homeworkout.db.WorkoutsListDao
import com.example.homeworkout.models.ExerciseRepository
import com.example.homeworkout.models.GraphRepository
import com.example.homeworkout.models.WorkoutsListRepository
import com.example.homeworkout.repositories.ExerciseRepositoryImpl
import com.example.homeworkout.repositories.GraphRepositoryImpl
import com.example.homeworkout.repositories.WorkoutsListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getExerciseDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(app,ExerciseDatabase::class.java, Constants.EXERCISE_DATABASE)
            .build()



    @Singleton
    @Provides
    fun getGraphDao(db: ExerciseDatabase) = db.getGraphDao()

    @Singleton
    @Provides
    fun getWorkoutsListDao(db:ExerciseDatabase) = db.getWorkoutsListDao()


    @Singleton
    @Provides
    fun getExerciseDao(db: ExerciseDatabase) = db.getExerciseDa0()



    @Singleton
    @Provides
    fun getGraphRepository(dao:GraphDao):GraphRepository{
        return GraphRepositoryImpl(dao)
    }

    @Singleton
    @Provides
    fun getWorkoutsListRepository(dao:WorkoutsListDao):WorkoutsListRepository{
        return WorkoutsListRepositoryImpl(dao)
    }

    @Singleton
    @Provides
    fun getExerciseRepository(dao:ExerciseDao):ExerciseRepository{
        return ExerciseRepositoryImpl(dao)
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext app:Context) =
        app.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

}