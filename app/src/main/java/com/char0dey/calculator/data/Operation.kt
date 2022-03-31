/**
 * @author char0dey on 30.03.2022.
 */

package com.char0dey.calculator.data

import java.math.BigInteger

sealed class Operation {

    abstract fun calculate(left: Part, right: Part): BigInteger

    object Plus : Operation() {
        override fun calculate(left: Part, right: Part): BigInteger {
            return left.value().add(left.value())
        }

        override fun toString(): String {
            return " + "
        }
    }

    object Minus : Operation() {
        override fun calculate(left: Part, right: Part): BigInteger {
            return left.value().subtract(left.value())
        }

        override fun toString(): String {
            return " - "
        }
    }

    object Divide : Operation() {
        override fun calculate(left: Part, right: Part): BigInteger {
            return left.value().divide(left.value())
        }

        override fun toString(): String {
            return " / "
        }
    }

    object Multiply : Operation() {
        override fun calculate(left: Part, right: Part): BigInteger {
            return left.value().multiply(left.value())
        }

        override fun toString(): String {
            return " * "
        }
    }

    object None : Operation() {
        override fun calculate(left: Part, right: Part): BigInteger {
            return BigInteger.ZERO
        }
    }
}