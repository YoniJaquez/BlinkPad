package com.nocountry.blinkpad.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.nocountry.blinkpad.R

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DialogCrearMateria(
    email: String = "",
) {
    Dialog(onDismissRequest = {}) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            border = BorderStroke(3.dp, Color.White),
            shape = RoundedCornerShape(12.dp)
        ) {
            Box(modifier = Modifier){
                Image(
                    modifier = Modifier.matchParentSize(),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = R.drawable.background),
                    contentDescription = ""
                )
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 15.dp, bottom = 10.dp, end = 10.dp, start = 10.dp),
                        fontSize = 29.sp,
                        fontWeight = FontWeight.ExtraBold,
                        text = "Crear materia",
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    TextField(
                        shape = RoundedCornerShape(20.dp),
                        value = "",
                        onValueChange = {
                            it
                        },
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .border(0.3.dp, Color(0x43F0F0F0), shape = RoundedCornerShape(24.dp)),
                        placeholder = {
                            Text(
                                text = "Nombre de la materia",
                                color = Color(0xCCE0E0E0)
                            )
                        },
                        singleLine = true,
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Color(0xCDFFFFFF),
                            disabledTextColor = Color.White

                        )
                    )
                    TextField(
                        shape = RoundedCornerShape(20.dp),
                        value = "",
                        onValueChange = {
                            it
                        },
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .border(0.3.dp, Color(0x43F0F0F0), shape = RoundedCornerShape(24.dp)),
                        placeholder = {
                            Text(
                                text = "Nombre del maestro",
                                color = Color(0xCCE0E0E0)
                            )
                        },
                        singleLine = true,
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Color(0xCDFFFFFF),
                            disabledTextColor = Color.White

                        )
                    )



                    TextField(
                        shape = RoundedCornerShape(20.dp),
                        value = "",
                        onValueChange = {
                            it
                        },
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .border(0.3.dp, Color(0x43F0F0F0), shape = RoundedCornerShape(24.dp)),
                        placeholder = {
                            Text(
                                text = "Aula",
                                color = Color(0xCCE0E0E0)
                            )
                        },
                        singleLine = true,
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Color(0xCDFFFFFF),
                            disabledTextColor = Color.White

                        )
                    )

                    Card (
                        modifier = Modifier.size(100.dp),
                        elevation = CardDefaults.elevatedCardElevation(12.dp),
                        shape = RoundedCornerShape(12)
                    ){

                    }
                    Button(
                        modifier= Modifier.padding(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        ),
                        onClick = { /*TODO*/ }) {
                        Text(text = "Subir horario", color = Color.Black)
                        Icon(imageVector = Icons.Default.Upload, contentDescription = "", tint = Color.Black)
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            modifier = Modifier.padding(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White
                            ),
                            onClick = {

                            }) {
                            Text(text = "Cancelar", color = Color.Black)
                        }
                        Button(
                            modifier = Modifier.padding(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White
                            ),
                            onClick = {

                            }) {
                            Text(text = "Guardar", color = Color.Black)
                        }

                    }
                }

            }
        }
    }
}