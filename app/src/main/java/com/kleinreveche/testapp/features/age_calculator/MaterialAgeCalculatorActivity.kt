package com.kleinreveche.testapp.features.age_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.color.DynamicColors
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.CompositeDateValidator
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import com.kleinreveche.testapp.R
import java.text.SimpleDateFormat
import java.util.*

class MaterialAgeCalculatorActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var tvSelectedDateInMinutes: TextView? = null
    private var tvSelectedDateInHours: TextView? = null
    private var tvSelectedDateInDays: TextView? = null

    private val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
    private val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
    private val currentDateInMinutes = currentDate?.time?.div(60000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DynamicColors.applyToActivityIfAvailable(this)
        setContentView(R.layout.activity_material_age_calculator)

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
            mBtnShowDatePicker()
        }
    }

    private fun mBtnShowDatePicker() {
        val calendar = Calendar.getInstance()
        val endDate = currentDate!!.time.minus(86400000)
        val calendarConstraintBuilder = CalendarConstraints.Builder().setOpenAt(endDate).setEnd(endDate)
        val validators = ArrayList<CalendarConstraints.DateValidator>()
        validators.add(DateValidatorPointBackward.before(endDate))
        calendarConstraintBuilder.setValidator(CompositeDateValidator.allOf(validators))

        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText(R.string.app_calc_datepicker_title)
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .setCalendarConstraints(calendarConstraintBuilder.build())
            .build()

        datePicker.addOnPositiveButtonClickListener {
            calendar.timeInMillis = it
            val selectedDate = sdf.parse(sdf.format(datePicker.selection))

            currentDate.let {
                val selectedDateInMinutes = selectedDate?.time?.div(60000)

                val differenceInMinutes =
                    currentDateInMinutes?.minus(selectedDateInMinutes!!)
                val selectedDateInHours =
                    differenceInMinutes?.div(60)
                val selectedDateInDays =
                    selectedDateInHours?.div(24)

                val selectedDateFormatted = sdf.format(selectedDate!!).toString()
                tvSelectedDate?.text = selectedDateFormatted
                tvSelectedDateInMinutes?.text = differenceInMinutes.toString()
                tvSelectedDateInHours?.text = selectedDateInHours.toString()
                tvSelectedDateInDays?.text = selectedDateInDays.toString()

                Toast.makeText(
                    this,
                    "Selected Birthdate is $selectedDateFormatted", Toast.LENGTH_LONG
                ).show()
            }

        }

        datePicker.show(supportFragmentManager, datePicker.tag)
    }

}
