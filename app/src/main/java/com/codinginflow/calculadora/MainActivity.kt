package com.codinginflow.calculadora



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var num1 = 0.0
    private var num2 = 0.0
    private var operacion = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultadoText.text = "0"
        operacion = SIN_OPERACION

        buttonUno.setOnClickListener { numberPressed("1") }
        buttonDos.setOnClickListener { numberPressed("2") }
        buttonTres.setOnClickListener { numberPressed("3") }
        buttonCuatrp.setOnClickListener { numberPressed("4") }
        buttonCinco.setOnClickListener { numberPressed("5") }
        buttonSeis.setOnClickListener { numberPressed("6") }
        buttonSiete.setOnClickListener { numberPressed("7") }
        buttonOcho.setOnClickListener { numberPressed("8") }
        buttonNueve.setOnClickListener { numberPressed("9") }
        buttonCero.setOnClickListener { numberPressed("0") }
        buttonPunto.setOnClickListener { numberPressed(".") }

        buttonBorrar.setOnClickListener { resetAll() }

        buttonSuma.setOnClickListener { operationPressed(SUMA) }
        buttonMenos.setOnClickListener { operationPressed(RESTA) }
        buttonMultiplica.setOnClickListener { operationPressed(MULTIPLICACION) }
        buttonDivision.setOnClickListener { operationPressed(DIVISION) }

        buttonIgual.setOnClickListener { resolvePressed() }
    }

    private fun numberPressed(num: String){
        if(resultadoText.text == "0" && num != ".") {
            resultadoText.text = "$num"
        } else {
            resultadoText.text = "${resultadoText.text}$num"
        }

        if(operacion == SIN_OPERACION){
            num1 = resultadoText.text.toString().toDouble()
        } else {
            num2 = resultadoText.text.toString().toDouble()
        }
    }

    private fun operationPressed(operacion: Int){
        this.operacion = operacion
        num1 = resultadoText.text.toString().toDouble()

        resultadoText.text = "0"
    }

    private fun resolvePressed(){

        val result = when(operacion) {
            SUMA -> num1 + num2
            RESTA -> num1 - num2
            MULTIPLICACION -> num1 * num2
            DIVISION -> num1 / num2
            else -> 0
        }

        num1 = result as Double

        resultadoText.text = if("$result".endsWith(".0")) { "$result".replace(".0","") } else { "%.2f".format(result) }
    }

    private fun resetAll(){
        resultadoText.text = "0"
        num1 = 0.0
        num2 = 0.0
    }

    companion object {
        const val SUMA = 1
        const val RESTA = 2
        const val MULTIPLICACION = 3
        const val DIVISION = 4
        const val SIN_OPERACION = 0
    }
}