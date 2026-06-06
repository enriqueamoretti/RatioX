package dev.eamoretti.ratiox.presentation.movements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.eamoretti.ratiox.ui.theme.RatioXColors
import dev.eamoretti.ratiox.ui.theme.RatioXTheme
import androidx.compose.foundation.Canvas

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewMovementScreen(navController: NavController) {
    var movementType by remember { mutableStateOf("Ingreso") }
    val isIncome = movementType == "Ingreso"

    var amount by remember { mutableStateOf(TextFieldValue("S/ 0.00")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var date by remember { mutableStateOf(TextFieldValue("06/06/2026")) }
    var isFixed by remember { mutableStateOf(false) }
    var notes by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nuevo movimiento", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = RatioXColors.Navy,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(RatioXColors.Background)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text("Tipo de movimiento", color = RatioXColors.TextMuted, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                MovementTypeSelector(selectedType = movementType, onTypeSelected = { movementType = it })

                Spacer(modifier = Modifier.height(16.dp))
                Text(if (isIncome) "Cuenta destino" else "Cuenta origen", color = RatioXColors.TextMuted, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Spinner(label = "BCP Principal")

                Spacer(modifier = Modifier.height(16.dp))
                Text("Categoría", color = RatioXColors.TextMuted, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Spinner(label = "Selecciona una categoría")

                Spacer(modifier = Modifier.height(16.dp))
                Text("Monto *", color = RatioXColors.TextMuted, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = amount,
                    onValueChange = { amount = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = if (isIncome) RatioXColors.Income else RatioXColors.Expense,
                        unfocusedBorderColor = RatioXColors.ActionGray
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text("Descripción *", color = RatioXColors.TextMuted, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    placeholder = { Text("Ej: Compra supermercado") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = if (isIncome) RatioXColors.Income else RatioXColors.Expense,
                        unfocusedBorderColor = RatioXColors.ActionGray
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text("Fecha", color = RatioXColors.TextMuted, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = date,
                    onValueChange = { date = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = if (isIncome) RatioXColors.Income else RatioXColors.Expense,
                        unfocusedBorderColor = RatioXColors.ActionGray
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = isFixed,
                        onCheckedChange = { isFixed = it },
                        colors = CheckboxDefaults.colors(checkedColor = if (isIncome) RatioXColors.Income else RatioXColors.Expense)
                    )
                    Text("Movimiento fijo (recurrente)", color = RatioXColors.NavyText)
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text("Nota (opcional)", color = RatioXColors.TextMuted, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    placeholder = { Text("Añade detalles adicionales...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = if (isIncome) RatioXColors.Income else RatioXColors.Expense,
                        unfocusedBorderColor = RatioXColors.ActionGray
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text("Adjuntar imagen (opcional)", color = RatioXColors.TextMuted, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                ImageUploader()
            }

            Button(
                onClick = { /* TODO: Handle save movement */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = RatioXColors.Navy),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Guardar movimiento", modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@Composable
private fun MovementTypeSelector(selectedType: String, onTypeSelected: (String) -> Unit) {
    val types = listOf("Ingreso", "Gasto")
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        types.forEach { type ->
            val isSelected = type == selectedType
            val backgroundColor = when {
                isSelected && type == "Ingreso" -> RatioXColors.GreenSoft
                isSelected && type == "Gasto" -> RatioXColors.RedSoft
                else -> Color.White
            }
            val borderColor = when {
                isSelected && type == "Ingreso" -> RatioXColors.Income
                isSelected && type == "Gasto" -> RatioXColors.Expense
                else -> RatioXColors.ActionGray
            }
            val textColor = if (isSelected) borderColor else RatioXColors.NavyText

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(backgroundColor)
                    .border(1.dp, borderColor, RoundedCornerShape(8.dp))
                    .clickable { onTypeSelected(type) }
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(type, color = textColor, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
private fun Spinner(label: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .border(1.dp, RatioXColors.ActionGray, RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(label, modifier = Modifier.weight(1f), color = RatioXColors.NavyText)
            Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown", tint = RatioXColors.TextMuted)
        }
    }
}

@Composable
private fun ImageUploader() {
    val stroke = Stroke(width = 2f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRoundRect(color = RatioXColors.ActionGray, style = stroke)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Default.CameraAlt, contentDescription = "Upload", tint = RatioXColors.TextMuted)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Subir imagen", color = RatioXColors.TextMuted, fontSize = 12.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewMovementScreenPreview() {
    RatioXTheme {
        NewMovementScreen(navController = rememberNavController())
    }
}
