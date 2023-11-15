package com.example.lesson1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import kotlin.math.round

class MainActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var resultTextView: TextView
    private lateinit var enterRubles: EditText
    private lateinit var getValueButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = findViewById(R.id.currencySpinner)
        resultTextView = findViewById(R.id.result)
        enterRubles = findViewById(R.id.editTextRubles)
        getValueButton = findViewById(R.id.getValue)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.currencyName,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter

        // не до конца поняла, нужен ли тут Listener или нет
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {

            }

            override fun onNothingSelected(parentView: AdapterView<*>) {

            }
        }

        getValueButton.setOnClickListener {
            updateResult()
        }
    }

    // проверка корректности ввода не нужна, поскольку я использовала компонент Number(Decimal), не пропускающий неккоректный ввод
    private fun updateResult() {
        val selectedItem = spinner.selectedItem.toString()
        val rubles = enterRubles.text.toString().toDoubleOrNull() ?: 0.0
        val result = convertCurrency(rubles, selectedItem)
        val roundedResult = round(result * 1000) / 1000

        resultTextView.text = "Результат: $roundedResult $selectedItem"
    }

    private fun convertCurrency(rubles: Double, currency: String): Double {
        return when (currency) {
            "Доллар" -> rubles * 0.011
            "Евро" -> rubles * 0.0102
            "Иена" -> rubles * 1.6626
            "Вона" -> rubles * 14.5622
            else -> rubles
        }
    }

}