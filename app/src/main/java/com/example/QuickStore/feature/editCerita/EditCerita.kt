package com.example.QuickStore.feature.editCerita

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.QuickStore.feature.main.components.buttonComp.BackPage
import com.example.QuickStore.feature.main.route.Screen
import com.example.QuickStore.ui.theme.Black1
import com.example.QuickStore.ui.theme.Black10
import com.example.QuickStore.ui.theme.Black4
import com.example.QuickStore.ui.theme.Black5
import com.example.QuickStore.ui.theme.Black6
import com.example.QuickStore.ui.theme.Purple10
import com.example.QuickStore.ui.theme.Purple6
import com.example.QuickStore.ui.theme.Purple9
import kotlinx.coroutines.delay

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EditCerita(navController: NavController, cerita_id: String) {
    val viewModel : EditCeritaViewModel = viewModel()

    LaunchedEffect(key1 = true){
        viewModel.getCeritabyId(cerita_id)
    }

    val judul = remember { mutableStateOf(viewModel.cerita.value?.judul ?: "") }
    val cerita = remember { mutableStateOf(viewModel.cerita.value?.isi ?: "") }
    val selectedItems = remember { mutableStateOf(listOf<String>()) }

    val categories = listOf(
        "Romantis", "Keluarga", "Karir", "Hubungan",
        "Finansial", "Makanan", "Pendidikan", "Inspirasi",
        "Psikologi", "Fantasi", "Lainnya"
    )

    val selectedImageUri = remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
    ) { uri: Uri? ->
        selectedImageUri.value = uri
    }

    val isFormFilled = judul.value.isNotBlank() && cerita.value.isNotBlank() && selectedItems.value.isNotEmpty()

    LaunchedEffect(viewModel.errMsg.value) {
        if (viewModel.errMsg.value.isNotEmpty()) {
            delay(3000)
            viewModel.errMsg.value = ""
        }
    }

    LaunchedEffect(key1 = viewModel.isSuccess.value){
        if(viewModel.isSuccess.value == true){
            navController.navigate(Screen.Cerita.route) {
                popUpTo(Screen.BuatCerita.route) {
                    inclusive = true
                }
            }
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Black1)
        .padding(16.dp)){
        LazyColumn(modifier = Modifier.fillMaxSize()){
            item {
                BackPage(
                    onClick = {
                        navController.navigate(Screen.Cerita.route) {
                            popUpTo(Screen.EditCerita.route) { inclusive = true }
                        }
                    },
                    text = "Edit Cerita",
                )
                Spacer(modifier = Modifier.height(32.dp))
                Box(modifier = Modifier
                    .then(
                        Modifier.width(100.dp)
                    )
                    .height(100.dp)
                    .border(width = 1.dp, color = Black6, shape = RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        launcher.launch("image/*")
                    }, contentAlignment = Alignment.Center,)
                {
                    if (selectedImageUri.value != null) {
                        Image(
                            painter = rememberAsyncImagePainter(selectedImageUri.value),
                            contentDescription = "Selected Image",
                            modifier = Modifier.width(100.dp),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Image(
                            painter = rememberAsyncImagePainter(viewModel.cerita.value?.gambar ?: ""),
                            contentDescription = "Selected Image",
                            modifier = Modifier.width(100.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Judul cerita",
                    style = MaterialTheme.typography.titleLarge,
                    color = Purple10
                )
                Spacer(modifier = Modifier.height(16.dp))
                JudulField(value = judul.value, onValueChange = {judul.value = it})
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Isi cerita",
                    style = MaterialTheme.typography.titleLarge,
                    color = Purple10
                )
                Spacer(modifier = Modifier.height(16.dp))
                CeritaField(value = cerita.value, onValueChange = {cerita.value = it})
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Kategori",
                    style = MaterialTheme.typography.titleLarge,
                    color = Purple10
                )
                Spacer(modifier = Modifier.height(16.dp))
                FlowRow(
                    Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    categories.forEach { category ->
                        val isSelected = selectedItems.value.contains(category)
                        Text(
                            text = category,
                            color = if(isSelected) Black1 else Purple6,
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier
                                .border(1.dp, color = Purple9, shape = RoundedCornerShape(100))
                                .background(
                                    color = if (isSelected) Purple9 else Black1,
                                    shape = RoundedCornerShape(100)
                                )
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .clickable {
                                    val newSelections = selectedItems.value.toMutableList()
                                    if (isSelected) {
                                        newSelections.remove(category)
                                    } else if (newSelections.size < 3) {
                                        newSelections.add(category)
                                    }
                                    selectedItems.value = newSelections
                                }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(
                            color = if (isFormFilled && !viewModel.isLoading.value) Purple6 else Black4,
                            shape = RoundedCornerShape(100)
                        )
                        .clickable(enabled = isFormFilled && !viewModel.isLoading.value)
                        {
                            viewModel.editCerita(
                                id = cerita_id,
                                gambar = if (selectedImageUri.value == null){
                                    selectedImageUri.value?.toString() ?: null
                                }else{
                                    selectedImageUri.value?.toString() ?: null
                                },
                                isi = cerita.value,
                                judul = judul.value,
                                kategori = selectedItems
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Unggah",
                        color = Black1,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp), contentAlignment = Alignment.Center){
                    Text(text = viewModel.errMsg.value,style = MaterialTheme.typography.bodySmall, color = Color.Red)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JudulField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(20.dp)),

        value = value,
        shape = RoundedCornerShape(20.dp),
        onValueChange = {
            onValueChange(it)
        },
        placeholder = { Text("Tulis judul ceritamu", color = Black6, style = MaterialTheme.typography.labelMedium) },
        textStyle = TextStyle(color = Black10),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Black5,
            focusedBorderColor = Purple6
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CeritaField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.White, shape = RoundedCornerShape(20.dp)),

        value = value,
        shape = RoundedCornerShape(20.dp),
        onValueChange = {
            onValueChange(it)
        },
        placeholder = { Text("Tulis judul ceritamu", color = Black6, style = MaterialTheme.typography.labelMedium) },
        textStyle = TextStyle(color = Black10),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Black5,
            focusedBorderColor = Purple6
        )
    )
}