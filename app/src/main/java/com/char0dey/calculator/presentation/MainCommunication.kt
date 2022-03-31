/**
 * @author char0dey on 31.03.2022.
 */

package com.char0dey.calculator.presentation

interface MainCommunication : Communication<String> {
    class Base : Communication.Abstract<String>(), MainCommunication
}