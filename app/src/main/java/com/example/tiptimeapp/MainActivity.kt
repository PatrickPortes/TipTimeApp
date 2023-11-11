package com.example.tiptimeapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.tiptimeapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    //viewBinding:
    private lateinit var binding: ActivityMainBinding

    private lateinit var toolbar: Toolbar
    private lateinit var etCost: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButtonOne: RadioButton
    private lateinit var radioButtonTwo: RadioButton
    private lateinit var radioButtonThree: RadioButton

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var switch: Switch
    private lateinit var btCalculate: Button
    private lateinit var txtResult: TextView

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

            val tip = etCost.text.toString()

            validateFields(tip)
        }
    }

    private fun startingComponents() {
        toolbar = binding.toolbar
        etCost = binding.editTextCost

        radioGroup = binding.radioGroup
        radioButtonOne = binding.radioOne
        radioButtonTwo = binding.radioTwo
        radioButtonThree = binding.radioThree

        switch = binding.switch1

        btCalculate = binding.buttonCalculate

        txtResult = binding.textViewResult
    }

    private fun validateFields(tip: String) {

        if (tip.isEmpty() || tip == "") {
            Toast.makeText(this, "Please, Add a Cost of Service!!!", Toast.LENGTH_SHORT).show()
        } else {
            calculateTipResult(tip)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun calculateTipResult(tip: String) {

        val resultTip = tip.toDouble()

        if (radioButtonOne.isChecked) {

            //Toast.makeText(this, radioButtonOne.text.toString(), Toast.LENGTH_SHORT).show()
            val tip20 = resultTip * 0.20
            roundUpTip(tip20)

        } else if (radioButtonTwo.isChecked) {

            //Toast.makeText(this, radioButtonTwo.text.toString(), Toast.LENGTH_SHORT).show()
            val tip18 = resultTip * 0.18
            roundUpTip(tip18)

        } else if (radioButtonThree.isChecked) {

            //Toast.makeText(this, radioButtonThree.text.toString(), Toast.LENGTH_SHORT).show()
            val tip15 = resultTip * 0.15
            roundUpTip(tip15)

        } else {
            Toast.makeText(this, "Please, Select How was the Service!!!", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun roundUpTip(result: Double) {

        if (switch.isChecked) {
            //Toast.makeText(this, "Round up Tip!!!", Toast.LENGTH_SHORT).show()

            //Round Up Tip:
            val roundedUp = kotlin.math.ceil(result)
            txtResult.text = "Tip: $ $roundedUp"

        } else{
            txtResult.text = "Tip: $ $result"
        }
    }

}