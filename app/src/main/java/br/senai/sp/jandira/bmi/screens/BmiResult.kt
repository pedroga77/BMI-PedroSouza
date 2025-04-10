package br.senai.sp.jandira.bmiresultscreen.screens

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambdaNInstance
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.bmi.R
import br.senai.sp.jandira.bmi.calcs.bmiCalculate
import br.senai.sp.jandira.bmi.model.BmiStatus
import br.senai.sp.jandira.bmi.screens.components.BmiLevel
import br.senai.sp.jandira.bmi.utils.convertNumberToLocale
import java.util.Locale


@Composable
fun BmiResult(navegacao: NavHostController?){
    val context = LocalContext.current

    val userFile = context
        .getSharedPreferences("userFile", Context.MODE_PRIVATE)

    val userWeight = userFile.getInt("user_weight", 0)
    val userHeight = userFile.getFloat("user_height", 0.0f)
    val userAge = userFile.getInt("user_age", 0)

    val bmi = bmiCalculate(userWeight, userHeight.toDouble().div(100))


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        Color(color = 0xFF000000),
                        Color(color = 0xFF9300FF)
                    )
                )
            ),
        contentAlignment = Alignment.BottomCenter
    )
    {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .height(100.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.next),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .width(width = 700.dp),
                shape = RoundedCornerShape(
                    topStart = 32.dp,
                    topEnd = 32.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),

                ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Card(
                        modifier = Modifier
                            .size(100.dp),
                        shape = CircleShape,
                        border = BorderStroke(5.dp, color = bmi.bmiColor),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )

                    )   {
                        Column (
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        )   {
                            Text(
                               text = String.format(
                                   Locale.getDefault(),
                                   "%.1f",
                                   bmi.bmi.second
                               )
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = bmi.bmi.first,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                    Card(
                        modifier = Modifier
                            .width(300.dp)
                            .height(80.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Column(
                                modifier = Modifier
                                    .width(70.dp)
                                    .height(60.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = stringResource(R.string.age),
                                    color = Color.Gray,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                                Text(
                                    text = "$userAge",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            }
                            VerticalDivider()
                            Column(
                                modifier = Modifier
                                    .width(70.dp)
                                    .height(60.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = stringResource(R.string.weight),
                                    color = Color.Gray,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                                Text(
                                    text =  "$userWeight",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            }
                            VerticalDivider()
                            Column(
                                modifier = Modifier
                                    .width(70.dp)
                                    .height(60.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = stringResource(R.string.height),
                                    color = Color.Gray,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                                Text(
                                    text = String.format(Locale.getDefault(), "%.2f", userHeight),
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .background(Color.LightGray)
                    ){
                        BmiLevel(
                            bulletColor = colorResource(R.color.light_blue),
                            leftText = stringResource(R.string.underweight),
                            rightText = "< ${convertNumberToLocale(18.5)}",
                            isFilled = if (bmi.bmiStatus == BmiStatus.UNDER_WEIGHT) true else false
                        )
                        BmiLevel(
                            bulletColor = colorResource(R.color.light_green),
                            leftText = stringResource(R.string.underweight),
                            rightText = "< ${convertNumberToLocale(18.5)}" ,
                            isFilled = if (bmi.bmiStatus == BmiStatus.UNDER_WEIGHT) true else false
                        )
                        BmiLevel(
                            bulletColor = colorResource(R.color.yellow),
                            leftText = stringResource(R.string.underweight),
                            rightText = "< ${convertNumberToLocale(18.5)}",
                            isFilled = if (bmi.bmiStatus == BmiStatus.UNDER_WEIGHT) true else false

                        )
                        BmiLevel(
                            bulletColor = colorResource(R.color.light_orange),
                            leftText = stringResource(R.string.underweight),
                            rightText = "< ${convertNumberToLocale(18.5)}",
                            isFilled = if (bmi.bmiStatus == BmiStatus.UNDER_WEIGHT) true else false

                        )
                        BmiLevel(
                            bulletColor = colorResource(R.color.dark_orange),
                            leftText = stringResource(R.string.underweight),
                            rightText = "< ${convertNumberToLocale(18.5)}",
                            isFilled = if (bmi.bmiStatus == BmiStatus.UNDER_WEIGHT) true else false

                        )
                        BmiLevel(
                            bulletColor = colorResource(R.color.red),
                            leftText = stringResource(R.string.underweight),
                            rightText = "< ${convertNumberToLocale(18.5)}",
                            isFilled = if (bmi.bmiStatus == BmiStatus.UNDER_WEIGHT) true else false

                        )

                    }
                    HorizontalDivider()
                    Button(
                        onClick = {
                            navegacao?.navigate("dados")
                        },
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(color = 0xFF000000)
                        )
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxSize(),
                            textAlign = TextAlign.Center,
                            text = stringResource(R.string.new_calculate),
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp
                        )
                    }
                }
            }
        }
    }
}
@Preview(showSystemUi = true)
@Composable
private fun BMIResultPreview() {
    BmiResult(null)
}