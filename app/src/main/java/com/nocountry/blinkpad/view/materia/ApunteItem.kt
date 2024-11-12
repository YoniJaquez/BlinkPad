package com.nocountry.blinkpad.view.materia

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nocountry.blinkpad.data.repository.DataApunte
import com.nocountry.blinkpad.ui.theme.BlinkPadTheme
import com.nocountry.blinkpad.view.components.AttachBar

@Composable
fun ApunteItem(apunte: DataApunte) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .border(
                width = 1.dp, color = Color(0xFFCFCFFC),
                shape = RoundedCornerShape(size = 16.dp)
            )
            .width(160.dp)
            .height(168.dp)
            .background(
                color = Color(0x334E4E61),
                shape = RoundedCornerShape(size = 16.dp)
            )

    ) {
        Row(
            modifier = Modifier
                .width(200.dp)
                .height(50.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Titulo: " + apunte.title,
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFFFFFF)
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .width(500.dp)
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = apunte.date ?: "Fecha no cargada",
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFFFFFF)
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row (
            modifier = Modifier
                .width(400.dp)
                .height(50.dp)
                .padding(end = 16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.End){
            AttachBar(apunte = apunte)
        }
    }
}

private val apunteDemo = DataApunte(
    title = "Demo",
    text = "Esto es Una Prueba",
    attachments= listOf("archivo1.pdf", "archivo2.pdf"), // Lista de archivos adjuntos
    contacts = listOf("Juan Perez"), // Lista de contactos referenciados
    scheduledDate = "15/02/2024" // Fecha agendada en el calendario (puede ser nullable si no siempre está presente)
)

@Preview(backgroundColor = 0xFF1E1F22)
@Composable
fun ApunteItemPreview() {
    BlinkPadTheme {
        ApunteItem(apunteDemo)
    }
}

