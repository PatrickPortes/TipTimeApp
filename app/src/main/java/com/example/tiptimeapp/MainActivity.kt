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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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

    //ViewModel:
    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //supportActionBar?.hide()

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
            validateFields(etCost.text.toString())
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

        // Inicialize o ViewModel
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
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

            val tipAmount = myViewModel.calculateTip20(resultTip)
            roundUpTip(tipAmount)

        } else if (radioButtonTwo.isChecked) {

            val tipAmount = myViewModel.calculateTip18(resultTip)
            roundUpTip(tipAmount)

        } else if (radioButtonThree.isChecked) {

            val tipAmount = myViewModel.calculateTip15(resultTip)
            roundUpTip(tipAmount)

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

            // Update the state in the ViewModel
            myViewModel.tipResult = result

        } else{
            txtResult.text = "Tip: $ $result"
            
            // Update the state in the ViewModel
            myViewModel.tipResult = result
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // Save the current state of the ViewModel
        outState.putDouble("tipResult", myViewModel.tipResult)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        // Restore the state of the ViewModel
        myViewModel.tipResult = savedInstanceState.getDouble("tipResult", 0.0)
        updateTipResult()
    }

    @SuppressLint("SetTextI18n")
    private fun updateTipResult() {
        // Update the UI with the state stored in the ViewModel
        txtResult.text = "Tip: $ ${myViewModel.tipResult}"
    }

}