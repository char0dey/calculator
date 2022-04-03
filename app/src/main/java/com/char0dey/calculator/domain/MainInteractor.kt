package com.char0dey.calculator.domain

import com.char0dey.calculator.data.CalculationOperation
import com.char0dey.calculator.data.CalculationState.*
import com.char0dey.calculator.data.MainRepository
import com.char0dey.calculator.data.Operation
import com.char0dey.calculator.presentation.Communication
import java.util.*

/**
 * @author char0dey 30.03.2022
 */

interface MainInteractor {

    fun plus(): Result

    fun minus(): Result

    fun divide(): Result

    fun multiply(): Result

    fun calculate(): Result // =

    fun handle(value: String): Result

    fun clear(): Result

    fun operation(operation: String): Result

    class Base(private val repository: MainRepository) : MainInteractor {

        private val handleOperation: HandleOperation = HandleOperation.Base(repository)

        override fun plus(): Result {
            return handleOperation.handle(Operation.Plus)
        }

        override fun minus(): Result {
            return handleOperation.handle(Operation.Minus)
        }

        override fun divide(): Result {
            return handleOperation.handle(Operation.Divide)
        }

        override fun multiply(): Result {
            return handleOperation.handle(Operation.Multiply)
        }

        override fun calculate(): Result {
            if (repository.isLeftPartEmpty() || repository.isRightPartEmpty()) {
                return Result.Nothing
            }

            return try {

                val left = repository.leftPart()
                val operation = repository.operation()
                val right = repository.rightPart()

                val value = repository.calculate().toString()

                val text = "$left\n$operation\n$right\n\n = $value"

                Result.Success(text)
            } catch (e: Exception) {
                Result.Error(e)
            }

        }

        override fun handle(value: String): Result {
            return if (repository.compareState(INITIAL) || repository.compareState(LEFT_PART_PRESENT)) {
                repository.updateLeftPart(value)
                Result.Success(repository.leftPart())
            } else {
                repository.updateRightPart(value)
                Result.Success(repository.leftPart() + repository.operation() + repository.rightPart())
            }
        }

        override fun clear(): Result {
            if (repository.compareState(INITIAL)) {
                return Result.Nothing
            }

            if (repository.compareState(LEFT_PART_PRESENT)) {
                val value = repository.leftPart()
                val new = value.dropLast(1)

                return if (new.isEmpty()) {
                    repository.clearLeft()
                    Result.Success("0")
                } else {
                    repository.updateLeftPart(new, false)
                    Result.Success(repository.leftPart())
                }
            }

            if (repository.compareState(OPERATION_PRESENT)) {
                repository.clearOperation()
                return Result.Success(repository.leftPart())
            }

            val value = repository.rightPart()
            val new = value.dropLast(1)

            return if (new.isEmpty()) {
                repository.clearRight()
                Result.Success(repository.leftPart() + repository.operation())
            } else {
                repository.updateRightPart(new, false)
                Result.Success(repository.leftPart() + repository.operation() + repository.rightPart())
            }
        }

        override fun operation(operation: String): Result {
            return when (CalculationOperation.valueOf(operation.uppercase(Locale.getDefault()))) {
                CalculationOperation.PLUS -> plus()
                CalculationOperation.MINUS -> minus()
                CalculationOperation.DIVIDE -> divide()
                CalculationOperation.MULTIPLY -> multiply()
                CalculationOperation.CLEAR -> clear()
                CalculationOperation.CALCULATE -> calculate()
            }
        }

        private interface HandleOperation {

            fun handle(operation: Operation): Result

            class Base(
                private val repository: MainRepository
            ) : HandleOperation {

                override fun handle(operation: Operation) =
                    if (repository.compareState(LEFT_PART_PRESENT) ||
                        repository.compareState(OPERATION_PRESENT)
                    ) {
                        repository.changeOperation(operation)
                        Result.Success(repository.leftPart() + repository.operation())
                    } else {
                        Result.Nothing
                    }


            }

        }

    }
}

sealed class Result {

    abstract fun map(
        success: Communication<String>,
        fail: Communication<String>
    )

    data class Success(private val value: String) : Result() {
        override fun map(success: Communication<String>, fail: Communication<String>) =
            success.map(value)
    }

    data class Error(private val e: Exception) : Result() {
        override fun map(success: Communication<String>, fail: Communication<String>) =
            fail.map(e.message ?: "error")
    }

    object Nothing : Result() {
        override fun map(success: Communication<String>, fail: Communication<String>) = Unit
    }
}
