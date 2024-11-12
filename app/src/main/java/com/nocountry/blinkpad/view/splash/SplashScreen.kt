package com.nocountry.blinkpad.view.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nocountry.blinkpad.R

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun Splash() {
    Column(
        modifier = Modifier
            .background(Color(0xFF124961))
            .fillMaxSize(), verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.weight(1f))


        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 30.dp),
            fontSize = 58.sp,
            text = "BlinkPad",
            color = Color(0xFFEBEBEB),
            fontFamily = FontFamily(Font(R.font.righteous_regular))
        )

        Spacer(modifier = Modifier.height(10.dp))

        Image(
            modifier = Modifier
                .size(68.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.logo), contentDescription = "logo"
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 30.dp),
            fontSize = 28.sp,
            text = "Loading...",
            color = Color(0xFFEBEBEB),
            fontFamily = FontFamily(Font(R.font.righteous_regular))
        )
    }
}