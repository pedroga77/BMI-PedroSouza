package br.senai.sp.jandira.bmi.calcs

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import br.senai.sp.jandira.bmi.model.Bmi
import br.senai.sp.jandira.bmi.model.BmiStatus
import kotlin.math.pow

fun bmiCalculate(weight: Int, height: Double): Bmi{

   //val bmiResult = weight / height.pow(2)
   val bmiResult = weight.div(height.pow(2))

    when{
        bmiResult < 18.5 ->
            return Bmi(
                bmi = Pair("", bmiResult),
                bmiColor = Color.Cyan,
                bmiStatus = BmiStatus.UNDER_WEIGHT
            )
        bmiResult >= 18.5 && bmiResult < 25 ->
            return Bmi(
                bmi = Pair("", bmiResult),
                bmiColor = Color.Green,
                bmiStatus = BmiStatus.NORMAL
            )
        bmiResult >= 25.0 && bmiResult < 30.0 ->
            return Bmi(
                bmi = Pair("", bmiResult),
                bmiColor = Color.LightGray,
                bmiStatus = BmiStatus.OVER_WEIGHT
            )
        bmiResult >= 30.0&& bmiResult < 35.0 ->
            return Bmi(
            bmi = Pair("", bmiResult),
            bmiColor = Color.Cyan,
            bmiStatus = BmiStatus.OBESITY_1
        )
        bmiResult >= 35.0 && bmiResult < 40 ->
            return Bmi(
            bmi = Pair("", bmiResult),
            bmiColor = Color.Cyan,
            bmiStatus = BmiStatus.OBESITY_2
        )
        else ->
            return Bmi(
            bmi = Pair("", bmiResult),
            bmiColor = Color.Cyan,
            bmiStatus = BmiStatus.OBESITY_3
            )
    }

}