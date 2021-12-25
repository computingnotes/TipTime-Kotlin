package dev.tutorials.tiptime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.tutorials.tiptime.databinding.ActivityMainBinding

import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {

        val costOfService = binding.costOfService.text.toString()
        val costDecimal = costOfService.toDoubleOrNull()
        if(costDecimal == null){
            binding.tipResult.text = ""
            return
        }


        val percentage = when (binding.tipOptions.checkedRadioButtonId){
            R.id.option1 -> .20
            R.id.option2 -> 0.18
            else -> 0.15
        }

        var tip = percentage * costDecimal


        val roundUp = binding.switchButton.isChecked

        if(roundUp){
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.tipResult.text = getString(R.string.tip_result, formattedTip)
    }
}