/*
---MidTerm Exam of Jav-1001---
    Ruby Chaulagain
    A00278592
 */

package com.example.conversionsolution

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Conversion spinner initialization
        val conversion_list = arrayOf("Select", "Weight", "Length", "Distance" )
        val conversion_spinner = findViewById<Spinner>(R.id.conversion_spinner)
        var conversion_adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, conversion_list
        )
        conversion_spinner.adapter = conversion_adapter

        // Initialize adapter arrays for different units
        val weight_units = arrayOf("kg", "lb")
        val length_units = arrayOf("m", "cm")
        val distance_units = arrayOf("km", "mi")
        val nothing_selected = arrayOf("None")
        val conversion1 = findViewById<Spinner>(R.id.conversion1)
        val conversion2 = findViewById<Spinner>(R.id.conversion2)


        var weight_adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, weight_units
        )

        var length_adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, length_units
        )

        var distance_adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, distance_units
        )

        var nothing_adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, nothing_selected
        )


        var conversionSpinnerPosition = ""

        // Conversion spinner selection listener
        conversion_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                conversionSpinnerPosition = parent?.getItemAtPosition(position).toString()
                // Check which conversion_spinner is selected and display other spinner accordingly
                when(position){
                    1 -> {
                        conversion1.adapter = weight_adapter
                        conversion2.adapter = weight_adapter
                    }
                    2 -> {
                        conversion1.adapter = length_adapter
                        conversion2.adapter = length_adapter
                    }
                    3 -> {
                        conversion1.adapter = distance_adapter
                        conversion2.adapter = distance_adapter
                    }
                    else -> {
                        conversion1.adapter = nothing_adapter
                        conversion2.adapter = nothing_adapter
                    }
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // TODO: Handle when no conversion is selected
            }


        }

        val input1 = findViewById<TextView>(R.id.input1)
        val input2 = findViewById<TextView>(R.id.input2)
        var conversionUnits1 = ""
        var conversionUnits2 = ""

            // Conversion1 spinner selection listener
            conversion1.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    // Get selected spinner value from conversion1 spinner
                    conversionUnits1 = parent?.getItemAtPosition(position).toString()

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                   // TODO: Handle when no conversion unit is selected for conversion1
                }
            }

                // Conversion2 spinner selection listener
                conversion2.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        // Get selected spinner value from conversion2 spinner
                        conversionUnits2 = parent?.getItemAtPosition(position).toString()


                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        // TODO: Handle when no conversion unit is selected for conversion2
                    }
                }

        val submit = findViewById<Button>(R.id.submit)
        // Perform operations when the button is clicked
        submit.setOnClickListener {
            var error = findViewById<TextView>(R.id.error)
            // Get input1 value as a string
            var inputString1 = input1.text.toString()

            // Check if 'Select' is selected in conversion_spinner
            if(conversionSpinnerPosition=="Select"){
                if(inputString1.isNotEmpty()){
                error.setText("Please select a unit.")
                input2.setText("")
            }
                else{
                    error.setText("Please select a unit.")
                    input2.setText("")
                }
            }
            // If anything is selected other than 'Select' in conversion_spinner
            else {
                // Check if inputString1 is not empty
                if (inputString1.isNotEmpty()) {
                    val inputInt1 = inputString1.toInt()
                    if (conversionUnits1 == conversionUnits2) {
                        input2.setText("$inputInt1")
                    }
                    // Conversion from km to mi or mi to km
                    if (conversionUnits1 == "km" && conversionUnits2 == "mi") {
                        var result = inputInt1 * 0.621
                        input2.setText("$result")
                    }
                    if (conversionUnits1 == "mi" && conversionUnits2 == "km") {
                        var result = inputInt1 * 1.609
                        input2.setText("$result")
                    }

                    // Conversion from kg to lb or lb to kg
                    if (conversionUnits1 == "kg" && conversionUnits2 == "lb") {
                        var result = inputInt1 * 2.205
                        input2.setText("$result")
                    }
                    if (conversionUnits1 == "lb" && conversionUnits2 == "kg") {
                        var result = inputInt1 * 0.453
                        input2.setText("$result")
                    }

                    // Conversion from m to cm or cm to m
                    if (conversionUnits1 == "m" && conversionUnits2 == "cm") {
                        var result = inputInt1 * 100.0
                        input2.setText("$result")
                    }
                    if (conversionUnits1 == "cm" && conversionUnits2 == "m") {
                        var result = inputInt1 * 0.01
                        input2.setText("$result")
                    }

                    error.setText("")
                }
                // If inputString1 is empty

                else{
                    error.setText("Enter a number.")
                    input2.setText("")
                }

            }

        }
    }

}


