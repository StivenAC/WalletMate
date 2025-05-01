package com.talhaatif.financeapk

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment(private val dateSetListener: (year: Int, month: Int, day: Int) -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Utilizar la fecha atual como personalizada
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Cree una nueva instancia de DatePickerDialog con el tema personalizado
        val datePickerDialog = DatePickerDialog(
            requireActivity(),
            { _, selectedYear, selectedMonth, selectedDay ->
                dateSetListener(selectedYear, selectedMonth, selectedDay)
            },
            year,
            month,
            day
        )

        return datePickerDialog
    }
}