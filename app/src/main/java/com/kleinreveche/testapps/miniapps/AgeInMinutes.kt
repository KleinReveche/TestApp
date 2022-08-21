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
    private var tvSelectedDateInHours : TextView? = null
    private var tvSelectedDateInDays : TextView? = null

    private val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
    private val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
    private val currentDateInMinutes = currentDate.time / 60000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age_in_minutes)

        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvSelectedDateInMinutes = findViewById(R.id.tvSelectedDateInMinutes)
        tvSelectedDateInHours = findViewById(R.id.tvSelectedDateInHours)
        tvSelectedDateInDays = findViewById(R.id.tvSelectedDateInDays)

        var defaultDateInMinutes = currentDateInMinutes - 17723520
        var defaultDateInHours = defaultDateInMinutes / 60
        var defaultDateInDays = defaultDateInHours / 24

        tvSelectedDateInMinutes?.text = defaultDateInMinutes?.toString()
        tvSelectedDateInHours?.text = defaultDateInHours.toString()
        tvSelectedDateInDays?.text = defaultDateInDays.toString()

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


// Change this to MaterialDatePickerDialog

        val dpd = DatePickerDialog(this,
            { _, selectedYear, selectedMonth, SelectedDay ->
                Toast.makeText(this,
                    "Selected Birthdate is 0${selectedMonth + 1}/$SelectedDay/$selectedYear",
                    Toast.LENGTH_LONG).show()

                val selectedDate = "0${selectedMonth + 1}/$SelectedDay/$selectedYear"
                
                val theDate = sdf.parse(selectedDate)

               theDate?.let{
                   val selectedDateInMinutes = theDate.time / 60000

                   currentDate?.let {
                       
                       val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                       val selectedDateInHours = differenceInMinutes / 60
                       val selectedDateInDays = selectedDateInHours / 24

                       tvSelectedDate?.text = selectedDate
                       tvSelectedDateInMinutes?.text = differenceInMinutes.toString()
                       tvSelectedDateInHours?.text = selectedDateInHours.toString()
                       tvSelectedDateInDays?.text = selectedDateInDays.toString()
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
