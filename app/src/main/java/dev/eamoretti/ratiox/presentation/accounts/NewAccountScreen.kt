package dev.eamoretti.ratiox.presentation.accounts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.eamoretti.ratiox.ui.theme.RatioXColors
import dev.eamoretti.ratiox.ui.theme.RatioXTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun NewAccountScreen(navController: NavController) {
    var accountName by remember { mutableStateOf(TextFieldValue("")) }
    var initialBalance by remember { mutableStateOf(TextFieldValue("S/ 0")) }
    var selectedAccountType by remember { mutableStateOf("Cuenta corriente") }
    var selectedIcon by remember { mutableStateOf(Icons.Default.AccountBalance) }
    var selectedColor by remember { mutableStateOf(RatioXColors.Navy) }
    var includeInTotal by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nueva cuenta", fontWeight = FontWeight.Bold) },
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
                .verticalScroll(rememberScrollState())
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Nombre de la cuenta o banco *", color = RatioXColors.TextMuted, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = accountName,
                    onValueChange = { accountName = it },
                    placeholder = { Text("Ej: BBVA, Efectivo, Tarjeta Visa") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = RatioXColors.Navy,
                        unfocusedBorderColor = RatioXColors.ActionGray
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text("Tipo de cuenta *", color = RatioXColors.TextMuted, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                AccountTypeSelector(selectedAccountType) { selectedAccountType = it }

                Spacer(modifier = Modifier.height(16.dp))
                Text("Icono", color = RatioXColors.TextMuted, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                IconSelector(selectedIcon) { selectedIcon = it }

                Spacer(modifier = Modifier.height(16.dp))
                Text("Color identificador", color = RatioXColors.TextMuted, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                ColorSelector(selectedColor) { selectedColor = it }

                Spacer(modifier = Modifier.height(16.dp))
                Text("Saldo inicial", color = RatioXColors.TextMuted, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = initialBalance,
                    onValueChange = { initialBalance = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = RatioXColors.Navy,
                        unfocusedBorderColor = RatioXColors.ActionGray
                    )
                )
                Text(
                    "Las tarjetas de crédito pueden tener saldo negativo",
                    color = RatioXColors.TextMuted,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = includeInTotal,
                        onCheckedChange = { includeInTotal = it },
                        colors = CheckboxDefaults.colors(checkedColor = RatioXColors.Navy)
                    )
                    Column {
                        Text("Incluir esta cuenta en el saldo total", color = RatioXColors.NavyText)
                        Text(
                            "Si desactivas esta opción, el saldo de esta cuenta no afectará tu balance general",
                            color = RatioXColors.TextMuted,
                            fontSize = 12.sp
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { /* TODO: Handle create account */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = RatioXColors.Navy),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Crear cuenta", modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun AccountTypeSelector(selectedType: String, onTypeSelected: (String) -> Unit) {
    val types = listOf("Efectivo", "Cuenta de ahorro", "Cuenta corriente", "Tarjeta de crédito")
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        types.forEach { type ->
            val isSelected = type == selectedType
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (isSelected) RatioXColors.NavyLight else Color.White)
                    .border(
                        1.dp,
                        if (isSelected) RatioXColors.Navy else RatioXColors.ActionGray,
                        RoundedCornerShape(8.dp)
                    )
                    .clickable { onTypeSelected(type) }
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(type, color = if (isSelected) Color.White else RatioXColors.NavyText, fontSize = 12.sp)
            }
        }
    }
}

@Composable
private fun IconSelector(selectedIcon: ImageVector, onIconSelected: (ImageVector) -> Unit) {
    val icons = listOf(
        "Billetera" to Icons.Default.AccountBalanceWallet,
        "Banco" to Icons.Default.AccountBalance,
        "Ahorro" to Icons.Default.Savings,
        "Tarjeta" to Icons.Default.CreditCard
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        icons.forEach { (name, icon) ->
            val isSelected = icon == selectedIcon
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onIconSelected(icon) }
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (isSelected) RatioXColors.NavyLight else Color.White)
                        .border(
                            1.dp,
                            if (isSelected) RatioXColors.Navy else RatioXColors.ActionGray,
                            RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icon, contentDescription = name, tint = if (isSelected) Color.White else RatioXColors.Navy)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(name, fontSize = 12.sp, color = RatioXColors.TextMuted)
            }
        }
    }
}


@Composable
private fun ColorSelector(selectedColor: Color, onColorSelected: (Color) -> Unit) {
    val colors = listOf(
        RatioXColors.Navy, Color(0xFF4A90E2), Color(0xFF7B61FF), Color(0xFFD0021B),
        Color(0xFFF5A623), Color(0xFF417505), Color(0xFFBD10E0), Color(0xFF9013FE)
    )
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(colors) { color ->
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(color)
                    .clickable { onColorSelected(color) }
            ) {
                if (color == selectedColor) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = "Selected",
                        tint = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewAccountScreenPreview() {
    RatioXTheme {
        NewAccountScreen(navController = rememberNavController())
    }
}
