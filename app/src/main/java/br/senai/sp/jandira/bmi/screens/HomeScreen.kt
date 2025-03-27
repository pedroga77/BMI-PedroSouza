package br.senai.sp.jandira.bmi.screens

import android.content.Context
import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.bmi.R

@Composable
fun HomeScreen(navegacao: NavHostController?) {
   
   var nameState = remember  {
       mutableStateOf("")

   }

    var isErrorState = remember {
        mutableStateOf(false)
    }
   
//Abrir ou criar um arquivo ShsredPredferences
    val context = LocalContext.current
    val userFile = context
        .getSharedPreferences("userFile", Context.MODE_PRIVATE)
        //Abrir o arquivo de forma privado

    //Colocar o arquivo em modo de edição
    val editor = userFile.edit()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        Color(0xFFFFFFFF),
                        Color(0xFFB7B7B7),
                        Color(0xFF797979),
                        Color(0xFF595959),
                        Color(0xFF3D3D3D),
                        Color(0xFF343434),
                        Color(0xFF020202),
                    )
                )
            )
    ){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(
                    R.drawable.calculadora
                ),
                contentDescription = stringResource(
                    R.string.logo
                ),
                modifier = Modifier
                    .padding(top = 32.dp)
            )
            Text(
                text = stringResource(
                    R.string.welcome
                ),
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),

                shape = RoundedCornerShape(
                    topStart = 48.dp,
                    topEnd = 48.dp
                ),
                colors = CardDefaults
                    .cardColors(
                        containerColor = Color.White
                    )

            ){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.End
                ) {
                    Column (
                        modifier = Modifier

                            .fillMaxWidth()
                    ){
                        Text(
                            text = stringResource(
                                R.string.your_name

                            ),
                            fontSize = 24.sp
                        )
                        TextField(
                            value = nameState.value,
                            onValueChange = {
                            nameState.value = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                capitalization = KeyboardCapitalization.Words
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.RemoveRedEye,
                                    contentDescription = "",
                                    tint = Color(0xFFFFFFFF)
                                )

                            },
                            trailingIcon ={
                                if(isErrorState.value) {
                                    Icon(
                                        imageVector = Icons.Default.Error,
                                        contentDescription = "",
                                        tint = Color.Red
                                    )
                                }
                            },
                            isError = isErrorState.value,
                            supportingText = {
                                if (isErrorState.value){
                                    Text(
                                        text = stringResource(R.string.name_error_message)
                                    )
                                }


                            }


                        )
                    }
                    Button(
                        onClick = {
                            if(nameState.value.isEmpty()){
                                isErrorState.value = true
                            }else{
                                editor.putString("user_name", nameState.value)
                                editor.apply()
                                navegacao?.navigate("dados")
                                 }

                            },
                                shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = stringResource(
                                R.string.next
                            ),
                            fontSize = 22.sp,
                        )
                    }
                }
            }


        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
   //HomeScreen()
}