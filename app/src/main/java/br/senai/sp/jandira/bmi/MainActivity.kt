package br.senai.sp.jandira.bmi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.bmi.screens.HomeScreen
import br.senai.sp.jandira.bmi.screens.UserDataa
import br.senai.sp.jandira.bmi.ui.theme.BMITheme
import br.senai.sp.jandira.bmiresultscreen.screens.BmiResult

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BMITheme {
                var navegacao = rememberNavController()
                NavHost(
                    navController = navegacao,
                    startDestination = "home"

                ){
                    composable(route = "home"){ HomeScreen(navegacao)}
                    composable(route = "dados"){ UserDataa(navegacao) }
                    composable(route = "resultBmi") { BmiResult(navegacao)}
                }
                }
            }
        }
    }

