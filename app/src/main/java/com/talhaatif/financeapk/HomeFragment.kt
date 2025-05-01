package com.talhaatif.financeapk

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.talhaatif.financeapk.databinding.FragmentHomeBinding
import com.talhaatif.financeapk.firebase.Util
import com.talhaatif.financeapk.models.BudgetModel
import com.talhaatif.financeapk.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var pieChart: PieChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        pieChart = binding.pieChart

        setupObservers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(checkAllParameters()){

            binding.fab.setOnClickListener {

                val intent = Intent(requireActivity(), AddTransactions::class.java)
                startActivity(intent)

            }
        }
    }

    private fun checkAllParameters(): Boolean {
        return isAdded && context != null
    }

    private fun setupObservers() {
        homeViewModel.budgetData.observe(viewLifecycleOwner) { budget ->
            val currency = homeViewModel.currencyType.value ?: "COP"
            updateUIWithCurrency(budget, currency)
            setupPieChart(budget)
        }

        homeViewModel.currencyType.observe(viewLifecycleOwner) { currency ->
            val budget = homeViewModel.budgetData.value
            updateUIWithCurrency(budget, currency)
        }
    }

    private fun updateUIWithCurrency(budget: BudgetModel?, currency: String) {
        if (budget == null) return

        binding.tvBalanceAmount.text = "$currency ${budget.balance}"
        binding.tvIncomeAmount.text = "$currency ${budget.income}"
        binding.tvExpenseAmount.text = "$currency ${budget.expense}"
    }

    private fun setupPieChart(budget: BudgetModel?) {
        if (budget == null) return

        // Crear entradas para el gráfico de pastel
        val entries = mutableListOf<PieEntry>()
        if (budget.income > 0) entries.add(PieEntry(budget.income.toFloat(), "Ingresos"))
        if (budget.expense > 0) entries.add(PieEntry(budget.expense.toFloat(), "Gastos"))

        // Configurar el conjunto de datos
        val dataSet = PieDataSet(entries, "").apply {
            colors = listOf(Color.parseColor("#6A66FF"), Color.parseColor("#554FF6")) // Colores actualizados
            valueTextSize = 14f
            valueTextColor = Color.WHITE
            yValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE // Colocar valores dentro de las secciones
            xValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE // Colocar etiquetas fuera de las secciones
            valueLinePart1Length = 0.5f // Longitud de la primera parte de la línea que conecta la etiqueta
            valueLinePart2Length = 0.4f // Longitud de la segunda parte de la línea
            valueLineColor = Color.WHITE // Color de la línea que conecta la etiqueta
            sliceSpace = 3f // Espacio entre secciones para mejor visibilidad
        }

        // Configurar los datos del gráfico
        val pieData = PieData(dataSet).apply {
            setValueFormatter(PercentFormatter(pieChart)) // Mostrar valores como porcentaje
        }

        // Configurar el gráfico de pastel
        pieChart.apply {
            data = pieData
            description.isEnabled = false // Desactivar descripción
            setUsePercentValues(true) // Mostrar valores como porcentaje
            isDrawHoleEnabled = true // Habilitar agujero en el centro
            holeRadius = 50f // Ajustar tamaño del agujero
            transparentCircleRadius = 55f // Agregar círculo transparente alrededor del agujero
            setHoleColor(Color.WHITE) // Color del fondo del agujero
            setEntryLabelColor(Color.BLACK) // Color del texto de las etiquetas
            setEntryLabelTextSize(12f) // Tamaño de texto de etiquetas
            setEntryLabelTypeface(Typeface.DEFAULT_BOLD) // Etiquetas en negrita
            legend.textColor = Color.BLACK // Color del texto de la leyenda
            legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM // Leyenda en la parte inferior
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER // Centrar la leyenda
            legend.orientation = Legend.LegendOrientation.HORIZONTAL // Leyenda en orientación horizontal
            legend.yEntrySpace = 10f // Espacio vertical entre elementos de la leyenda
            legend.xEntrySpace = 20f // Espacio horizontal entre elementos de la leyenda
            legend.formSize = 12f // Tamaño de los íconos de la leyenda
            animateY(1000, Easing.EaseInOutQuad) // Agregar animación
            invalidate() // Refrescar el gráfico
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}