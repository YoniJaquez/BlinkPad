package com.nocountry.blinkpad.view.materia

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.nocountry.blinkpad.R
import com.nocountry.blinkpad.data.repository.DataApunte
import com.nocountry.blinkpad.data.repository.DataMateria
import com.nocountry.blinkpad.ui.theme.BlinkPadTheme
import com.nocountry.blinkpad.view.components.searchBar

private val listaApuntes : MutableList<DataApunte> = mutableListOf()
private val materia = DataMateria("Informática", "254", "Lu y Ju 20 a 22"
)

class MateriaFragment : Fragment() {

    @Composable
    fun MateriaFragmentScreen(modifier: Modifier = Modifier) {
        val image = painterResource(R.drawable.background)

        demo(apunteDemo)
        demo(apunteDemo2)
        demo(apunteDemo3)

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = image,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = modifier
                            .fillMaxSize()
                            .padding(
                                top = 50.dp,
                                start = 20.dp,
                                end = 20.dp)
                    ) {
                        Text(
                            text = materia.nombre,
                            fontSize = 20.sp,
                            lineHeight = 16.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        searchBar()

                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top

                        ) {

                            LazyVerticalGrid(GridCells.Fixed(2)) {
                                items(listaApuntes.size) { apunte ->
                                    // Contenido de cada apunte, similar al código dentro del bloque Box anterior
                                    ApunteItem(apunte = listaApuntes[apunte])
                                }
                            }
                        }
                    }
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

    private val apunteDemo2 = DataApunte(
        title = "Demo2",
        text = "Esto es Una Prueba",
        contacts = listOf("Juan Perez"), // Lista de contactos referenciados
        scheduledDate = "15/02/2024" // Fecha agendada en el calendario (puede ser nullable si no siempre está presente)
    )

    private val apunteDemo3 = DataApunte(
        title = "Demo3",
        text = "Esto es Una Prueba",
        scheduledDate = "15/02/2024" // Fecha agendada en el calendario (puede ser nullable si no siempre está presente)
    )
    private fun demo(apunte: DataApunte){
        listaApuntes.add(apunte)
    }


    @Preview(showBackground = true)
    @Composable
    fun MateriaFragmentPreview() {
        BlinkPadTheme {
            MateriaFragmentScreen()
        }
    }
}