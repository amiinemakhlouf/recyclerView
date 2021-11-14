package com.example.hindi.Views

import android.app.Activity
import android.content.Context
import android.content.Intent

object Utils {
    fun switchActivity(activity1: Activity,activity2:Activity)
    {
        Intent(activity1,activity2::class.java).also {
            activity1.startActivity(it)
            activity1.finish()
        }
    }
}