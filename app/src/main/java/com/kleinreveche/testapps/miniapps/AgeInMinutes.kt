package com.kleinreveche.testapps.miniapps

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.kleinreveche.testapps.R
import java.text.SimpleDateFormat
import java.util.*

class AgeInMinutes : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null
    private var tvSelectedDateInMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age_in_minutes)

        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvSelectedDateInMinutes = findViewById(R.id.tvSelectedDateInMinutes)

        val btnBirthdatePicker : Button = findViewById(R.id.btnBirthdatePicker)
        btnBirthdatePicker.setOnClickListener{
            clickBirthdatePicker()
        }

    }

    private fun clickBirthdatePicker(){

        val myCalendar = Calendar.getInstance()
        val currentYear = myCalendar.get(Calendar.YEAR)
        val currentMonth = myCalendar.get(Calendar.MONTH)
        val currentDay = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            { view, selectedYear, selectedMonth, SelectedDay ->
                Toast.makeText(this,
                    "Selected Birthdate is 0${selectedMonth + 1}/$SelectedDay/$selectedYear",
                    Toast.LENGTH_LONG).show()

                val selectedDate = "0${selectedMonth + 1}/$SelectedDay/$selectedYear"
                val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)

               theDate?.let{
                   val selectedDateInMinutes = theDate.time / 60000
                   val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                   currentDate?.let {
                       val currentDateInMinutes = currentDate.time / 60000
                       val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                       tvSelectedDate?.text = selectedDate
                       tvSelectedDateInMinutes?.text = differenceInMinutes.toString()
                   }
               }

            },
            currentYear,
            currentMonth,
            currentDay
            )

        dpd.datePicker.maxDate = System.currentTimeMillis() -  86400000
        dpd.show()

    }

}