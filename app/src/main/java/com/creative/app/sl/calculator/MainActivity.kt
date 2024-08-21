package com.creative.app.sl.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.creative.app.sl.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .systemBarsPadding()
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Calculator()
                }
            }
        }
    }
}

@Composable
fun Calculator() {

    var numberOne by remember { mutableStateOf("") }
    var numberTwo by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var operatorExpand by remember { mutableStateOf(false) }
    var operatorSymbol by remember { mutableStateOf("+") }
    var tabIndex by remember { mutableStateOf(0) }


    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 32.sp,
        color = Color.Red
    )

    //Calculation function
    fun sumCalculation() {
        val inputValueNumberOne = numberOne.toIntOrNull() ?: 0
        val inputValueNumberTwo = numberTwo.toIntOrNull() ?: 0
        when (operatorSymbol) {
            // + Calculation
            "+" -> {
                val result = inputValueNumberOne + inputValueNumberTwo
                outputValue = result.toString()
            }
           // - Calculation
            "-" -> {
                val result = inputValueNumberOne - inputValueNumberTwo
                outputValue = result.toString()
            }
            // - Calculation
            "*" -> {
                val result = inputValueNumberOne * inputValueNumberTwo
                outputValue = result.toString()
            }
            // / Calculation
            "/" -> {
                if (inputValueNumberTwo == 0) {
                    val result = inputValueNumberOne / 1
                    outputValue = result.toString()
                } else {
                    val result = inputValueNumberOne / inputValueNumberTwo
                    outputValue = result.toString()
                }
            }
            // % Calculation
            "%" -> {
                if (inputValueNumberTwo == 0) {
                    val result = 0
                    outputValue = result.toString()
                } else {
                    val result = inputValueNumberOne % inputValueNumberTwo
                    outputValue = result.toString()
                }

            }
        }

    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Mini Calculator",
            style = customTextStyle
        )
        Spacer(modifier = Modifier.height(16.dp))
        // First number text filed
        Row {
            OutlinedTextField(
                value = numberOne,
                onValueChange = {
                    // Limit the input to 5 characters
                    if (it.length <= 5) numberOne = it
                    sumCalculation()
                },
                label = {
                    Text(
                        text = "Enter First Number"
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Dropdown list
        Row {
            Box {
                Button(
                    onClick = {
                        operatorExpand = true
                    }
                ) {
                    Text(
                        text = operatorSymbol
                    )
                }
                DropdownMenu(
                    expanded = operatorExpand,
                    onDismissRequest = { operatorExpand = false }) {
                    DropdownMenuItem(
                        text = {
                            Text(text = "+")
                        },
                        onClick = {
                            operatorExpand = false
                            operatorSymbol = "+"
                            sumCalculation()
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "-")
                        },
                        onClick = {
                            operatorExpand = false
                            operatorSymbol = "-"
                            sumCalculation()
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "*")
                        },
                        onClick = {
                            operatorExpand = false
                            operatorSymbol = "*"
                            sumCalculation()
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "/")
                        },
                        onClick = {
                            operatorExpand = false
                            operatorSymbol = "/"
                            sumCalculation()
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "%")
                        },
                        onClick = {
                            operatorExpand = false
                            operatorSymbol = "%"
                            sumCalculation()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Second number text filed
        Row {
            OutlinedTextField(
                value = numberTwo,
                onValueChange = {
                    // Limit the input to 5 characters
                    if (it.length <= 5) numberTwo = it
                    sumCalculation()
                },
                label = {
                    Text(
                        text = "Enter Second Number"
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Display the output value
        Row {
            Text(
                text = "Result: $outputValue",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun CalculatorPreview() {
    CalculatorTheme {
        Calculator()
    }
}