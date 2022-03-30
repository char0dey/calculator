package com.char0dey.calculator.domain

import com.char0dey.calculator.data.CalculationState
import com.char0dey.calculator.data.MainRepository
import com.char0dey.calculator.data.Operation

/**
 * @author char0dey 30.03.2022
 */

interface MainInteractor {

    fun plus() : Result

    fun minus() : Result

    fun divide() : Result

    fun multiply() : Result

    fun calculate() : Result // =

    fun handle(value: String) : Result

    class Base(private val repository: MainRepository): MainInteractor {
        override fun plus(): Result {
            if (repository.isLeftPartEmpty()){
                return Result.Nothing
            }

            if (repository.state() == CalculationState.LEFT_PART_DONE){
                repository.saveOperation(Operation.PLUS)
                return Result.Success(repository.leftPart() + repository.operation())
            }

            if (repository.state() == CalculationState.DONE){
                return Result.Nothing
            }

            repository.saveOperation(Operation.PLUS)
            return Result.Success(repository.leftPart() + repository.operation())

        }

        override fun minus(): Result {
            TODO("Not yet implemented")
        }

        override fun divide(): Result {
            TODO("Not yet implemented")
        }

        override fun multiply(): Result {
            TODO("Not yet implemented")
        }

        override fun calculate(): Result {
            TODO("Not yet implemented")
        }

        override fun handle(value: String): Result {
            TODO("Not yet implemented")
        }

    }
}

sealed class Result {
    data class Success(val value: String): Result()
    data class Error(val message: String): Result()
    object Nothing: Result()
}
