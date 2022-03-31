package com.char0dey.calculator.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.char0dey.calculator.domain.MainInteractor

/**
 * @author char0dey 30.03.2022
 */

class MainViewModel(
    private val communication: MainCommunication,
    private val errorCommunication: MainCommunication,
    private val interactor: MainInteractor
) {

    fun observe(owner: LifecycleOwner, observer: Observer<String>) {
        communication.observe(owner, observer)
    }

    fun observeError(owner: LifecycleOwner, observer: Observer<String>) {
        errorCommunication.observe(owner, observer)
    }

    fun plus() {
        interactor.plus().map(communication, errorCommunication)
    }

    fun minus() {
        interactor.minus().map(communication, errorCommunication)
    }

    fun divide() {
        interactor.divide().map(communication, errorCommunication)
    }

    fun multiply() {
        interactor.multiply().map(communication, errorCommunication)
    }

    fun calculate() {
        interactor.calculate().map(communication, errorCommunication)
    } // =

    fun handle(value: String) {
        interactor.handle(value).map(communication, errorCommunication)
    }

    fun clear() {
        interactor.clear().map(communication, errorCommunication)
    }
}