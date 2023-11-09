package com.example.tiptimeapp

import android.R
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.tiptimeapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    //viewBinding:
    private lateinit var binding: ActivityMainBinding

    private lateinit var toolbar: Toolbar
    private lateinit var etCost: EditText
    private lateinit var radioSelected: RadioGroup
    private lateinit var switch: Switch
    private lateinit var btCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //viewBinding:
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startingComponents()

        //Setting the Title:
        toolbar.title = "Tip Time"

        //Setting Switch Initial State with Kotlin:
        switch.isChecked = false

        //Calculate
        btCalculate.setOnClickListener {
            TODO()
        }
    }

    private fun startingComponents() {
        toolbar = binding.toolbar
        etCost = binding.editTextCost
        radioSelected = binding.radioGroup
        switch = binding.switch1
        btCalculate  = binding.buttonCalculate
    }
}