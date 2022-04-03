package com.char0dey.calculator.presentation

/**
 * @author char0dey 30.03.2022
 */

import android.os.Bundle
import android.widget.Button
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

        setListeners(binding)
    }

    private fun setListeners(binding: ActivityMainBinding) {
        with(binding) {
            button0.setKeyboardListener()
            button1.setKeyboardListener()
            button2.setKeyboardListener()
            button3.setKeyboardListener()
            button4.setKeyboardListener()
            button5.setKeyboardListener()
            button6.setKeyboardListener()
            button7.setKeyboardListener()
            button8.setKeyboardListener()
            button9.setKeyboardListener()

            plusButton.setKeyboardOperationListener()
            minusButton.setKeyboardOperationListener()
            multiplyButton.setKeyboardOperationListener()
            divideButton.setKeyboardOperationListener()
            resultButton.setKeyboardOperationListener()
            clearButton.setKeyboardOperationListener()
        }
    }

    private fun Button.setKeyboardListener() = setOnClickListener {
        viewModel.handle(text.toString())
    }

    private fun Button.setKeyboardOperationListener() = setOnClickListener {
        viewModel.operation(tag.toString())
    }

}