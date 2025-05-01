package com.talhaatif.financeapk

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.tabs.TabLayout
import com.talhaatif.financeapk.databinding.ActivityAddTransactionsBinding
import com.talhaatif.financeapk.dialog.CategoryPickerDialog
import com.talhaatif.financeapk.dialog.CustomProgressDialog
import com.talhaatif.financeapk.firebase.Util
import com.talhaatif.financeapk.firebase.Variables
import com.talhaatif.financeapk.models.Category
import com.talhaatif.financeapk.viewmodel.AddTransactionViewModel
import java.text.SimpleDateFormat
import java.util.*
import android.graphics.drawable.ColorDrawable
class AddTransactions : AppCompatActivity() {
    private lateinit var binding: ActivityAddTransactionsBinding
    private lateinit var viewModel: AddTransactionViewModel
    private val utils = Util()
    private var selectedDate = ""
    private lateinit var selectedCategory: Category
    private lateinit var progressDialog: CustomProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTransactionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = CustomProgressDialog(this)
        progressDialog.setMessage("Entrando...")

        // Date picker logic
        binding.btnDatePicker.setOnClickListener {
            showDatePicker()
        }

        // List of categories
        val categories = listOf(
            Category("Comida", R.drawable.ic_food),
            Category("Transporte", R.drawable.ic_baseline_directions_transit_24),
            Category("Compras", R.drawable.ic_baseline_shopping_cart_24),
            Category("Entretenimiento", R.drawable.ic_entertain),
            Category("Salud", R.drawable.ic_health),
            Category("Vicio", R.drawable.maria),
            Category("BCSPN", R.drawable.ic_entertain)
        )

        // Set up the category picker button
        binding.btnCategoryPicker.setOnClickListener {
            val dialog = CategoryPickerDialog(this, categories) { category ->
                selectedCategory = category
                // Actualizar UI
                binding.selectedCategory.text = category.name

                // Aplicar color de tinte
                val tintColor = Variables.categoryTintMap[category.name] ?: Color.BLACK
                binding.btnCategoryPicker.setColorFilter(tintColor)
                binding.btnCategoryPicker.imageTintList = ColorStateList.valueOf(tintColor)
                binding.btnCategoryPicker.setImageResource(category.imageResId)
            }

            // Configuración adicional para el fondo blanco
            dialog.window?.setBackgroundDrawableResource(android.R.color.white)

            // Opcional: Si usas MaterialComponents
            dialog.window?.setDimAmount(0.2f) // Opacidad del fondo exterior

            dialog.show()
        }


        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> { // Expense Tab
                        binding.tvAmount.text = "-$"
                        // Change text color of TextView and EditText to red
                        binding.tvAmount.setTextColor(ContextCompat.getColor(this@AddTransactions, R.color.rojo))
                        binding.etAmount.setTextColor(ContextCompat.getColor(this@AddTransactions, R.color.rojo))
                        binding.tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this@AddTransactions, R.color.rojo))
                        binding.tabLayout.setTabTextColors(
                            ContextCompat.getColor(this@AddTransactions, R.color.white), // Unselected color
                            ContextCompat.getColor(this@AddTransactions, R.color.rojo) // Selected color
                        )
                    }
                    1 -> { // Income Tab
                        // Change text color of TextView and EditText to green
                        binding.tvAmount.setTextColor(ContextCompat.getColor(this@AddTransactions, R.color.verde))
                        binding.etAmount.setTextColor(ContextCompat.getColor(this@AddTransactions, R.color.verde))
                        binding.tvAmount.text = "+$"
                        binding.tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this@AddTransactions, R.color.verde))
                        binding.tabLayout.setTabTextColors(
                            ContextCompat.getColor(this@AddTransactions, R.color.white),
                            ContextCompat.getColor(this@AddTransactions, R.color.verde)
                        )
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })


        viewModel = ViewModelProvider(this).get(AddTransactionViewModel::class.java)

        binding.btnAddRecord.setOnClickListener {
            val currencyType = utils.getLocalData(this, "currency")
            val amount = binding.etAmount.text.toString().trim() + " " + currencyType
            val notes = binding.etNotes.text.toString().trim()
            val transactionType = if (binding.tabLayout.selectedTabPosition == 0) "Expense" else "Income"

            if (amount.isEmpty() || selectedDate.isEmpty() || transactionType.isEmpty()) {
                Toast.makeText(this, "LLene todos los campos por favor", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (transactionType.isEmpty()) {
                Toast.makeText(this, "Tipo de transaccion no seleccionada", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            progressDialog.setMessage("Añadiendo Transaccion...")
            progressDialog.setCancelable(false) // Evitar que el usuario cierre el cuadro de diálogo
            progressDialog.show()

            viewModel.addTransaction(this, amount, transactionType, selectedDate, notes, category = selectedCategory.name.lowercase())
        }

        // Observe transaction status
        viewModel.transactionState.observe(this) { result ->
            result.onSuccess {
                progressDialog.dismiss()
                Toast.makeText(this, "Transaction añadida satisfactoriamente", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            result.onFailure { error ->
                progressDialog.dismiss()
                Toast.makeText(this, "error al añadir transaccion: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDatePicker() {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Seleccionar fecha")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds()) // Default selection to today
                .build()

        datePicker.addOnPositiveButtonClickListener { selection ->
            val calendar = Calendar.getInstance().apply {
                timeInMillis = selection
            }

            selectedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
            binding.tvDate.text = selectedDate
        }

        datePicker.show(supportFragmentManager, "DATE_PICKER")
    }
}