package com.example.tiptimeapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    var tipResult: Double = 0.0

    fun calculateTip20(tip: Double): Double {
        return tip * 0.20
    }

    fun calculateTip18(tip: Double): Double {
        return tip * 0.18
    }

    fun calculateTip15(tip: Double): Double {
        return tip * 0.15
    }

}