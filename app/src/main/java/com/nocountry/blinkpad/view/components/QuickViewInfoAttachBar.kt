package com.nocountry.blinkpad.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nocountry.blinkpad.R
import com.nocountry.blinkpad.ui.theme.BlinkPadTheme
import com.nocountry.blinkpad.data.repository.DataApunte

@Composable
fun AttachBar(apunte: DataApunte){
    Row(
        modifier = Modifier
            .padding(1.dp)
            .width(90.dp)
            .height(32.dp)
            .background(
                Color.White,
                RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box (
            modifier = Modifier
                .clickable { /*TODO al hacer clic abrir lista de contactos*/ }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_contact),
                contentDescription = "Contactos",
                modifier = Modifier.size(24.dp)
            )
            if (!apunte.contacts.isNullOrEmpty()) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 2.dp, end = 2.dp)
                        .size(4.dp)
                        .border(
                            8.dp,
                            Color.Red,
                            shape = RoundedCornerShape(50.dp)
                        )
                )
            }
        }
        Spacer(modifier = Modifier.width(3.dp))
        Box (
            modifier = Modifier
                .clickable { /*TODO al hacer clic abrir Files en la carpeta de los archivos adj*/ }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_clip),
                contentDescription = "Archivos adjuntos",
                modifier = Modifier.size(24.dp)
            )
            if (!apunte.attachments.isNullOrEmpty()) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 2.dp, end = 2.dp)
                        .size(4.dp)
                        .border(
                            8.dp,
                            Color.Red,
                            shape = RoundedCornerShape(50.dp)
                        )
                )
            }
        }

        Spacer(modifier = Modifier.width(3.dp))
        Box (
            modifier = Modifier
                .clickable { /*TODO al hacer clic abrir Google Calendar*/ }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_calendar_note),
                contentDescription = "Fechas agendadas",
                modifier = Modifier.size(24.dp)
            )
            if (!apunte.scheduledDate.isNullOrEmpty()) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 2.dp, end = 2.dp)
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

private val apunteDemo = DataApunte(
    title = "Demo",
    text = "Esto es Una Prueba",
    attachments= listOf("archivo1.pdf", "archivo2.pdf"), // Lista de archivos adjuntos
    contacts = listOf("Juan Perez"), // Lista de contactos referenciados
    scheduledDate = "15/02/2024" // Fecha agendada en el calendario (puede ser nullable si no siempre está presente)
)

@Preview(backgroundColor = 0xFF1E1F22)
@Composable
fun AttachBarPreview() {
    BlinkPadTheme {
        AttachBar(apunteDemo)
    }
}