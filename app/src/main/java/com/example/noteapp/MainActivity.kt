package com.example.noteapp


import android.os.Bundle // import para los Strings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteapp.data.Apunte
import com.example.noteapp.ui.theme.NoteappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteappTheme {
                // Estado para guardar lo que el usuario escribe en el campo de texto
                var texto by remember { mutableStateOf("") }
                val context = LocalContext.current

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FormularioApunte(
                        textoApunte = texto,
                        onTextoChange = { nuevoTexto -> texto = nuevoTexto },
                        onGuardarClick = {
                            if (texto.isBlank()) {
                                Toast.makeText(context, R.string.note_empty, Toast.LENGTH_SHORT).show()
                                val dbPath = context.getDatabasePath(Apunte.DB_NAME).absolutePath
                                Toast.makeText(context,  dbPath, Toast.LENGTH_SHORT).show()
                            } else {
                                // Aquí irá la lógica para guardar en la Base de Datos
                                Toast.makeText(context, R.string.note_was_saved, Toast.LENGTH_SHORT).show()
                                texto = "" // Limpia el campo después de guardar
                            }
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun FormularioApunte(
    textoApunte: String,
    onTextoChange: (String) -> Unit,
    onGuardarClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = textoApunte,
            onValueChange = onTextoChange,
            label = { Text(stringResource(R.string.enter_new_note)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = onGuardarClick) {
            Text(text = stringResource(R.string.save))
        }
    }
}

