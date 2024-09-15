package com.example.bienvenidoalcurso

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            labo03()
        }
    }
}

@Composable
fun labo03() {
    MaterialTheme {
        MainContent()
    }
}

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent() {
    var showDialog by remember { mutableStateOf(false) }
    val items by remember { mutableStateOf(mutableListOf<String>()) }
    var newItem by remember { mutableStateOf("") }
    var snackbarVisible by remember { mutableStateOf(false) }
    //primer contenedor
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Lista de Objetos",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
            )
        },
        floatingActionButton = {
            // Botón flotante para añadir un objeto nuevo
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = Color.White
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Agregar nuevo objeto")
            }
        }
    ) { paddingValues ->
        // Aquí va el contenido del Scaffold
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = Color(0xFFE8F0FA) // Color del fondo
        ) {
            LazyColumn {
                items(items) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Text(
                            text = item,
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
        // Control Snackbar usado nuevamente para descisiones
        if (snackbarVisible) {
            Snackbar(
                action = {
                    TextButton(onClick = { snackbarVisible = false }) {
                        Text("denegado")
                    }
                }
            ) {
                Text("Objeto agrado!")
            }
        }

        // Control usado llamado AlertDialog
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                //presentara un texto que nos da las instrucciones para añadir texto
                title = { Text("Agregar objeto") },
                text = {
                    OutlinedTextField(
                        value = newItem,
                        onValueChange = {
                            newItem = it
                        },
                        label = { Text("Nombre del objeto") }
                    )
                },
                confirmButton = {
                    TextButton(onClick = {
                        items.add(newItem)
                        newItem = ""
                        snackbarVisible = true
                        showDialog = false
                    }) {
                        Text("añadir")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    labo03()
}