package com.nocountry.blinkpad.view.inicio
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.nocountry.blinkpad.R
import com.nocountry.blinkpad.ui.theme.BlinkPadTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MateriasContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp - 50.dp
    val image = painterResource(R.drawable.background)
    val roundShape = RoundedCornerShape(10.dp)
    Box(
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )


        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(
                text = "MATERIAS CARGADAS",
                fontSize = 20.sp,
                lineHeight = 16.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = modifier
                    .background(
                        shape = RoundedCornerShape(15.dp),
                        color = Color(android.graphics.Color.parseColor("#22FFFFFF"))
                    )
                    .padding(15.dp)
            ) {

                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .height(35.dp)
                        .padding(0.dp)
                        .background(color = Color.White),

                    horizontalArrangement = Arrangement.SpaceBetween,

                    verticalAlignment = Alignment.CenterVertically
                ){

                    Spacer(modifier = Modifier.height(16.dp))
                    Icon(painter = painterResource(R.drawable.iic_search), contentDescription = "")

                    BasicTextField(value = "Buscar en el texto", onValueChange = {}, modifier = Modifier.fillMaxWidth(.9f))
                    Icon(painter = painterResource(R.drawable.ic_mic), contentDescription = "")

                    Spacer(modifier = Modifier.height(16.dp))
                }
                Spacer(modifier = Modifier.height(10.dp))

                LazyColumn( verticalArrangement = Arrangement.spacedBy(4.dp)){

                    items(5) { index ->

                        Spacer(modifier = Modifier.height(5.dp))
                        Box(
                            modifier = modifier
                                .fillMaxWidth()
                                .background(Color.Black, shape = roundShape)
                                .padding(8.dp)
                        ){

                            Column(
                                modifier = Modifier
                                    .background(Color.Black)
                                    .align(Alignment.TopStart)
                                    .fillMaxWidth()
                            )
                            {
                                Text(text = "Materia $index",
                                    color = Color.White,
                                    style = TextStyle(
                                        fontSize = 16.sp
                                    )
                                )
                                Text(text = "Horario",
                                    color = Color.White,
                                    style = TextStyle(
                                        fontSize = 14.sp
                                    )
                                )
                                Text(text = "Aula",
                                    color = Color.White,
                                    style = TextStyle(
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .align(Alignment.BottomEnd),
                                verticalArrangement = Arrangement.Bottom
                            )
                            {
                                Row(
                                    modifier = Modifier
                                        .background(Color.White, shape = roundShape)
                                        .padding(5.dp)
                                )
                                {
                                    Button(
                                        onClick = {
                                            val intent = Intent(Intent.ACTION_VIEW).apply {
                                                setData(Uri.parse("https://chat.whatsapp.com/K5YMb5na1TFFXemLUcMz8I"))
                                                setPackage("com.whatsapp")
//                                                setType("text/plain")
//                                                putExtra("jid", "C16-117-m-kotlin(no country)@s.whatsapp.com")
//                                                putExtra(Intent.EXTRA_TEXT, "BlinkPad: ")
                                            }

                                            startActivity(context, intent, null)
//
                                             },
                                        modifier = Modifier
                                            .size(25.dp)
                                            .padding(0.dp),
                                        contentPadding = PaddingValues(0.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(android.graphics.Color.parseColor("#FFFFFF")),
                                            contentColor = Color(android.graphics.Color.parseColor("#000000"))
                                        )
                                    ) {
                                        Icon(
                                            painter = painterResource(R.drawable.ic_user),
                                            contentDescription = "",
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                    Box(modifier = Modifier
                                        .size(4.dp)
                                        .border(
                                            8.dp,
                                            Color.Red,
                                            shape = RoundedCornerShape(50.dp)
                                        )
                                    )
                                    Button(
                                        onClick = {
                                           // startActivity(context, intent, null)
                                        },
                                        modifier = Modifier
                                            .size(25.dp)
                                            .padding(0.dp),
                                        contentPadding = PaddingValues(0.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(android.graphics.Color.parseColor("#FFFFFF")),
                                            contentColor = Color(android.graphics.Color.parseColor("#000000"))
                                        )
                                    ) {
                                        Icon(
                                            painter = painterResource(R.drawable.ic_clip),
                                            contentDescription = "",
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                    Box(modifier = Modifier
                                        .size(4.dp)
                                        .border(
                                            8.dp,
                                            Color.Red,
                                            shape = RoundedCornerShape(50.dp)
                                        )
                                    )

                                    Button(
                                        onClick = {
                                            val intent = Intent(Intent.ACTION_MAIN).apply {
                                                addCategory(Intent.CATEGORY_APP_CALENDAR)
                                                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                            }

                                            startActivity(context, intent, null)
                                        },
                                        modifier = Modifier
                                            .size(27.dp)
                                            .padding(0.dp),
                                        contentPadding = PaddingValues(0.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(android.graphics.Color.parseColor("#FFFFFF")),
                                            contentColor = Color(android.graphics.Color.parseColor("#000000"))
                                        )
                                    ) {
                                        Icon(
                                            painter = painterResource(R.drawable.ic_calendar),
                                            contentDescription = "",
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                    Box(modifier = Modifier
                                        .size(4.dp)
                                        .border(
                                            8.dp,
                                            Color.Red,
                                            shape = RoundedCornerShape(50.dp)
                                        )
                                    )

                                }
                            }

                        }
                    }
                }
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistroScreenPreview() {
    BlinkPadTheme {
        MateriasContent()
    }
}