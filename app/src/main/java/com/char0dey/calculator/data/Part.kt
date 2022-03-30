/**
 * @author char0dey on 30.03.2022.
 */

package com.char0dey.calculator.data

interface Part {
    fun isEmpty(): Boolean

    fun clear()

    fun update(value: String)
}