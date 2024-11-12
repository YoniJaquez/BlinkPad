package com.nocountry.blinkpad.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.nocountry.blinkpad.R

@Composable
fun DialogErroLogin(
    email: String = "",
    positiveAction: () -> Unit,
    negativeAction: () -> Unit
) {
    Dialog(onDismissRequest = {}) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            border = BorderStroke(3.dp, Color.White),
            shape = RoundedCornerShape(12.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = R.drawable.background),
                    contentDescription = ""
                )
                Column(modifier = Modifier.fillMaxSize()) {

                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 15.dp, bottom = 10.dp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        text = "Error al iniciar sesión",
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier.padding(end = 20.dp, start = 20.dp, top = 10.dp),
                        fontSize = 14.sp,
                        color = Color.White,
                        text = "No encontramos ninguna cuenta con $email. Prueba con otro correo electrónico o si no tienes cuenta, registrate",
                        textAlign = TextAlign.Justify
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            modifier = Modifier.padding(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White
                            ),
                            onClick = { positiveAction() }) {
                            Text(text = "Reintentar", color = Color.Black)
                        }
                        Button(
                            modifier = Modifier.padding(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White
                            ),
                            onClick = {
                                negativeAction()
                            }) {
                            Text(text = "Crear cuenta", color = Color.Black)
                        }

                    }
                }

            }
        }
    }
}