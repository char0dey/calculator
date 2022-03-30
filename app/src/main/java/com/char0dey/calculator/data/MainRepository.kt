package com.char0dey.calculator.data

/**
 * @author char0dey 30.03.2022
 */

interface MainRepository {

        fun clear()

        fun isLeftPartEmpty(): Boolean

        fun isRightPartEmpty(): Boolean

        fun updatePart(value: String)

        fun state(): CalculationState

        fun saveOperation(operation: Operation) // update state to left part done

        fun leftPart(): String

        fun operation(): String
}