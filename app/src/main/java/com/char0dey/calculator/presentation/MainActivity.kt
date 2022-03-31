package com.char0dey.calculator.presentation

/**
 * @author char0dey 30.03.2022
 */

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.char0dey.calculator.data.MainRepository
import com.char0dey.calculator.data.Part
import com.char0dey.calculator.databinding.ActivityMainBinding
import com.char0dey.calculator.domain.MainInteractor
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val viewModel = MainViewModel(
        MainCommunication.Base(),
        MainCommunication.Base(),
        MainInteractor.Base(
            MainRepository.Base(
                Part.Base(),
                Part.Base()
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.observe(this) {
            binding.textView.text = it
        }

        viewModel.observeError(this) {
            Snackbar.make(binding.textView, it, Snackbar.LENGTH_SHORT).show()
        }

        binding.button0.setOnClickListener {
            viewModel.handle(binding.button0.text.toString())
        }

        binding.button1.setOnClickListener {
            viewModel.handle(binding.button1.text.toString())
        }

        binding.button2.setOnClickListener {
            viewModel.handle(binding.button2.text.toString())
        }

        binding.button3.setOnClickListener {
            viewModel.handle(binding.button3.text.toString())
        }

        binding.button4.setOnClickListener {
            viewModel.handle(binding.button4.text.toString())
        }

        binding.button5.setOnClickListener {
            viewModel.handle(binding.button5.text.toString())
        }

        binding.button6.setOnClickListener {
            viewModel.handle(binding.button6.text.toString())
        }

        binding.button7.setOnClickListener {
            viewModel.handle(binding.button7.text.toString())
        }

        binding.button8.setOnClickListener {
            viewModel.handle(binding.button8.text.toString())
        }

        binding.button9.setOnClickListener {
            viewModel.handle(binding.button9.text.toString())
        }

        //
        binding.plusButton.setOnClickListener {
            viewModel.plus()
        }

        binding.minusButton.setOnClickListener {
            viewModel.minus()
        }

        binding.multiplyButton.setOnClickListener {
            viewModel.multiply()
        }

        binding.divideButton.setOnClickListener {
            viewModel.divide()
        }

        binding.resultButton.setOnClickListener {
            viewModel.calculate()
        }

        binding.clearButton.setOnClickListener {
            viewModel.clear()
        }

    }
}