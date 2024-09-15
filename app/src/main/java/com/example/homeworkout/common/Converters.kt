package com.example.homeworkout.common

import androidx.room.TypeConverter
import com.example.homeworkout.models.Exercise
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {





        @TypeConverter
        fun toQuizTypeJson(meaning: MutableList<Exercise>) : String {
            return Gson().toJson(
                meaning,
                object : TypeToken<MutableList<Exercise>>(){}.type
            ) ?: "[]"
        }

        @TypeConverter
        fun fromQuizTypeJson(json: String):MutableList<Exercise>{
            return (Gson().fromJson<MutableList<Exercise>>(
                json,
                object: TypeToken<MutableList<Exercise>>(){}.type
            ) ?: emptyList()) as MutableList<Exercise>
        }

    }
