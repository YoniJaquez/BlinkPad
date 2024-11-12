package com.nocountry.blinkpad.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nocountry.blinkpad.R
import com.nocountry.blinkpad.ui.theme.BlinkPadTheme

@Composable
fun searchBar(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .height(35.dp)
            .padding(0.dp)
            .background(color = Color.White),

        horizontalArrangement = Arrangement.SpaceBetween,

        verticalAlignment = Alignment.CenterVertically
    ) {

        Spacer(modifier = Modifier.height(16.dp))
        IconButton(onClick = { /*TODO*/ },
            modifier = Modifier.padding(8.dp), // Modificador opcional para establecer márgenes u otras propiedades
            content = {
                Icon(
                    painter = painterResource(R.drawable.iic_search),
                    contentDescription = "Search Icon"
                )
            }
        )

        var searchText by remember { mutableStateOf("") }
        BasicTextField(
            value = if (searchText.isEmpty()) "Buscar en el texto"
                else searchText,
            onValueChange = {searchText = it},
            modifier = Modifier.fillMaxWidth(.9f)
        )

        IconButton(onClick = { /*TODO*/ },
            modifier = Modifier.padding(8.dp), // Modificador opcional para establecer márgenes u otras propiedades
            content = {
                Icon(
                    painter = painterResource(R.drawable.ic_mic),
                    contentDescription = "Search Icon"
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
    Spacer(modifier = Modifier.height(10.dp))

}
@Preview(backgroundColor = 0xFF1E1F22)
@Composable
fun SearchBarPreview() {
    BlinkPadTheme {
        searchBar()
    }
}