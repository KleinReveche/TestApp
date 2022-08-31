package com.kleinreveche.testapp.features.age_calculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.kleinreveche.testapp.R
import java.text.SimpleDateFormat
import java.util.*

class AgeCalculatorLegacyActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var tvSelectedDateInMinutes: TextView? = null
    private var tvSelectedDateInHours: TextView? = null
    private var tvSelectedDateInDays: TextView? = null

    private val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
    private val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
    private val currentDateInMinutes = currentDate?.time?.div(60000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age_calculator)

        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvSelectedDateInMinutes = findViewById(R.id.tvSelectedDateInMinutes)
        tvSelectedDateInHours = findViewById(R.id.tvSelectedDateInHours)
        tvSelectedDateInDays = findViewById(R.id.tvSelectedDateInDays)

        currentDate?.let {
            val defaultDateInMinutes = currentDateInMinutes?.minus(17723520)
            val defaultDateInHours = defaultDateInMinutes?.div(60)
            val defaultDateInDays = defaultDateInHours?.div(24)

            tvSelectedDateInMinutes?.text = defaultDateInMinutes.toString()
            tvSelectedDateInHours?.text = defaultDateInHours.toString()
            tvSelectedDateInDays?.text = defaultDateInDays.toString()
        }

        val btnBirthdatePicker: Button = findViewById(R.id.btnBirthdatePicker)
        btnBirthdatePicker.setOnClickListener {
            clickBirthdatePicker()
        }
    }

    private fun clickBirthdatePicker() {

        val myCalendar = Calendar.getInstance()
        val currentYear = myCalendar.get(Calendar.YEAR)
        val currentMonth = myCalendar.get(Calendar.MONTH)
        val currentDay = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, SelectedDay ->
                Toast.makeText(
                    this,
                    "Selected Birthdate is 0${selectedMonth + 1}/$SelectedDay/$selectedYear",
                    Toast.LENGTH_LONG
                ).show()

                val selectedDate = "0${selectedMonth + 1}/$SelectedDay/$selectedYear"

                val theDate = sdf.parse(selectedDate)

                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000

                    currentDate?.let {

                        val differenceInMinutes =
                            currentDateInMinutes?.minus(selectedDateInMinutes)
                        val selectedDateInHours =
                            differenceInMinutes?.div(60)
                        val selectedDateInDays =
                            selectedDateInHours?.div(24)

                        tvSelectedDate?.text =
                            selectedDate
                        tvSelectedDateInMinutes?.text =
                            differenceInMinutes.toString()
                        tvSelectedDateInHours?.text =
                            selectedDateInHours.toString()
                        tvSelectedDateInDays?.text =
                            selectedDateInDays.toString()
                    }
                }

            },
            currentYear,
            currentMonth,
            currentDay
        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }

}

const val AgeCalculatorLegacyFeatureRoute = "ageCalculatorLegacy"