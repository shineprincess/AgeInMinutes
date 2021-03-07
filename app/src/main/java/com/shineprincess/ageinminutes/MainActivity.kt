package com.shineprincess.ageinminutes

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
//import kotlinx.android.synthetic.main.content_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener {
            view -> clickDataPicker(view)
//            Toast.makeText(this,"Button Works",Toast.LENGTH_LONG).show()
        }

    }

    fun clickDataPicker(view: View) {

        /**
         * This Gets a calendar using the default time zone and locale.
         * The calender returned is based on the current time
         * in the default time zone with the default.
         * view -> displayed on the screen
         */
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
                    view, selectedYear, selectedMonth, selectedDayOfMonth ->
//                Toast.makeText(this,"DatePicker Works",Toast.LENGTH_LONG).show()


                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"

                tvSelectedDate.setText(selectedDate)

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                /** Here we have get the time in milliSeconds from Date object
                 * And as we know the formula as milliseconds can be converted to second by dividing it by 1000.
                 * And the seconds can be converted to minutes by dividing it by 60.
                 * So now in the selected date into minutes.
                 * its from jan 1970
                 */
                val selectedDateInMinutes = theDate!!.time / 6000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateToMinutes = currentDate!!.time / 6000

                val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes

                tvSelectedDateInMinutes.setText(differenceInMinutes.toString())

            }
            ,year,month,day)

        // 86400000 is milliseconds of 24 Hours. Which is used to restrict the user to select today and future day.
            dpd.datePicker.setMaxDate(Date().time - 86400000)
            dpd.show()
    }
}

