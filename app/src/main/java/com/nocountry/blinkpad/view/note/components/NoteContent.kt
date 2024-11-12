package com.nocountry.blinkpad.view.note.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.nocountry.blinkpad.R
import com.nocountry.blinkpad.view.note.NoteState
import com.nocountry.blinkpad.view.note.NoteViewModel

@Composable
fun NoteContent(
    noteViewModel: NoteViewModel
) {
    val state = noteViewModel.state
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.background),
            contentDescription = "background"
        )
    }
    Body(state = state, noteViewModel = noteViewModel)
}

@Composable
fun Body(state: NoteState, noteViewModel: NoteViewModel) {
    Column(modifier = Modifier.padding(20.dp)) {
        Header()
        Signature(state = state, noteViewModel = noteViewModel)
        TextFieldNote(state = state, noteViewModel = noteViewModel)
    }
}

@Composable
fun Header() {
    Row {
        Text(
            color = Color.White,
            fontSize = 30.sp,
            text = "Apunte",
            fontFamily = FontFamily(Font(R.font.spectralsc_regular))
        )
        Spacer(modifier = Modifier.weight(1f))

        Image(
            modifier = Modifier
                .size(50.dp)
                .padding(6.dp)
                .clickable { },
            painter = painterResource(id = R.drawable.ic_contact_note),
            contentDescription = "person"
        )
        Image(
            modifier = Modifier
                .size(50.dp)
                .padding(6.dp)
                .clickable { },
            painter = painterResource(id = R.drawable.ic_clip_note),
            contentDescription = "person"
        )
        Image(
            modifier = Modifier
                .size(50.dp)
                .padding(6.dp)
                .clickable { },
            painter = painterResource(id = R.drawable.ic_calendar_note),
            contentDescription = "person"
        )
    }
}

@Composable
fun Signature(state: NoteState, noteViewModel: NoteViewModel) {

    var signatureText by remember { mutableStateOf("") }
    var textFieldWidthSize by remember { mutableStateOf(Size.Zero) }
    var signatures =
        listOf(
            "Español",
            "Matemáticas",
            "Historia",
            "Cálculo Diferencial",
            "Ecuaciones Diferenciales",
            "Motodología de la investigación",
            "Otra"
        )

    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = signatureText,
            onValueChange = { signatureText = it },
            readOnly = true,
            singleLine = true,
            enabled = false,
            maxLines = 1,
            shape = RoundedCornerShape(24.dp),
            placeholder = { Text(text = "Materia", color = Color(0x99FFFFFF)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .clickable {
                    noteViewModel.onValueStateExpanded(!state.expandedState)
                }
                .border(0.3.dp, Color(0x43F0F0F0), shape = RoundedCornerShape(24.dp))
                .onGloballyPositioned { coordinates ->
                    textFieldWidthSize = coordinates.size.toSize()
                },
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                disabledTextColor = Color.White,
                errorTextColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color(0xCDFFFFFF)
            ),
            trailingIcon = {
                val image = if (state.expandedState) {
                    Icons.Filled.ArrowDropUp
                } else {
                    Icons.Filled.ArrowDropDown
                }

                IconButton(modifier = Modifier.padding(end = 6.dp), onClick = {
                    noteViewModel.onValueStateExpanded(!state.expandedState)
                }) {
                    Icon(
                        modifier = Modifier.size(80.dp),
                        imageVector = image,
                        contentDescription = "show signatures",
                        tint = Color(0xCCFFFFFF),
                    )
                }
            }
        )

        DropdownMenu(
            expanded = state.expandedState,
            onDismissRequest = {
                noteViewModel.onValueStateExpanded(!state.expandedState)
            },
            modifier = Modifier.width(with(LocalDensity.current) {
                textFieldWidthSize.width.toDp()
            })
        ) {
            signatures.forEach { signature ->
                DropdownMenuItem(
                    text = { Text(text = signature, color = Color(0xFF494949)) },
                    onClick = {
                        noteViewModel.onValueStateExpanded(!state.expandedState)
                        signatureText = signature
                    })
            }
        }
    }
}

@Composable
fun TextFieldNote(
    state: NoteState,
    noteViewModel: NoteViewModel
) {

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            TextField(
                value = state.titleNote,
                onValueChange = {
                    noteViewModel.onChangeValueTitleNote(it)
                },
                shape = RoundedCornerShape(24.dp),
                placeholder = { Text(text = "Título", color = Color(0x99FFFFFF)) },
                singleLine = true,
                maxLines = 1,
                modifier = Modifier
                    .weight(1f)
                    .border(0.3.dp, Color(0x43F0F0F0), shape = RoundedCornerShape(24.dp)),
                colors = TextFieldDefaults.colors(
                    disabledContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    disabledTextColor = Color.White,
                    errorTextColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                ),
            )

            Button(
                onClick = {
                    noteViewModel.saveNote()
                },
                modifier = Modifier
                    .padding(start = 12.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(android.graphics.Color.parseColor("#FF7966")),
                                Color(android.graphics.Color.parseColor("#FF9181")),
                                Color(android.graphics.Color.parseColor("#FF7966"))
                            )
                        ),
                        shape = ButtonDefaults.shape
                    ),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Text(
                    text = "Guardar",
                    color = Color.White,
                    fontSize = 15.sp,
                    lineHeight = 4.sp,
                    modifier = Modifier
                        .height(30.dp)
                        .padding(5.dp)
                )
            }

        }

        TextField(
            value = state.noteDescription,
            onValueChange = {
                noteViewModel.onChangeValueDescriptionNote(it)
            },
            shape = RoundedCornerShape(18.dp),
            placeholder = { Text(text = "Texto del apunte", color = Color(0x991A1A1A)) },
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(top = 8.dp)
                .border(0.3.dp, Color(0x43F0F0F0), shape = RoundedCornerShape(24.dp)),
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Color(0xFFE9E9E9),
                focusedContainerColor = Color(0xFFE9E9E9),
                unfocusedContainerColor = Color(0xFFE9E9E9),
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                disabledTextColor = Color.Black,
                errorTextColor = Color.Red,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color(0x991A1A1A)
            ),
        )

        Search(state = state, noteViewModel = noteViewModel)
    }
}

@Composable
fun Search(state: NoteState, noteViewModel: NoteViewModel) {


    TextField(
        leadingIcon = {
            val image = Icons.Filled.Search

            IconButton(onClick = { }) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = image,
                    contentDescription = "search",
                    tint = Color(0xCC000000),
                )
            }
        },
        value = state.searchText,
        onValueChange = {
            noteViewModel.onChangeValueSearchText(it)
        },
        shape = RoundedCornerShape(18.dp),
        placeholder = { Text(text = "Buscar en el texto", color = Color(0x991A1A1A)) },
        singleLine = true,
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 14.dp)
            .border(0.3.dp, Color(0x43F0F0F0), shape = RoundedCornerShape(24.dp)),
        colors = TextFieldDefaults.colors(
            disabledContainerColor = Color(0xFFE9E9E9),
            focusedContainerColor = Color(0xFFE9E9E9),
            unfocusedContainerColor = Color(0xFFE9E9E9),
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            disabledTextColor = Color.White,
            errorTextColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color(0x991A1A1A)
        ),
        trailingIcon = {
            val image = Icons.Filled.Mic
            IconButton(modifier = Modifier.padding(end = 6.dp), onClick = { }) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = image,
                    contentDescription = "listen",
                    tint = Color(0xCC000000),
                )
            }
        }
    )
}