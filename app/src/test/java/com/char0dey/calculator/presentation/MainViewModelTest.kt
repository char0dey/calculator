package com.char0dey.calculator.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.char0dey.calculator.data.MainRepository
import com.char0dey.calculator.data.Part
import com.char0dey.calculator.domain.MainInteractor
import org.junit.Assert.assertEquals
import org.junit.Test

class MainViewModelTest {

    @Test
    fun test() {

        val success = TestCommunication()
        val viewModel = MainViewModel(
            success,
            TestCommunication(),
            MainInteractor.Base(
                MainRepository.Base(
                    Part.Base(),
                    Part.Base()
                )
            )
        )

        assertEquals("", success.text)

        viewModel.handle("12")
        assertEquals("12", success.text)

        viewModel.plus()
        assertEquals("12 + ", success.text)

        viewModel.handle("34")
        assertEquals("12 + 34", success.text)

        viewModel.calculate()
        assertEquals("12\n + \n34\n\n = 46", success.text)

    }

    private inner class TestCommunication : MainCommunication {

        var text = ""

        override fun observe(lifecycleOwner: LifecycleOwner, observer: Observer<String>) {

        }

        override fun map(value: String) {
            text = value
        }

    }
}