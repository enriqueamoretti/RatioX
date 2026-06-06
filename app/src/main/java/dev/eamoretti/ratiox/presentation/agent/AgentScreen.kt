package dev.eamoretti.ratiox.presentation.agent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.eamoretti.ratiox.ui.theme.RatioXTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("RatioX", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text("Asistente IA", fontSize = 12.sp)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                },
                actions = {
                    Box(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(RatioXColors.NavyLight)
                    ) {
                        Icon(
                            Icons.Default.Send,
                            contentDescription = "Chat",
                            tint = Color.White,
                            modifier = Modifier
                                .size(20.dp)
                                .align(Alignment.Center)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = RatioXColors.Navy,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Escribe tu pregunta...") },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(20.dp),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                IconButton(onClick = { /* TODO: Handle send */ }) {
                    Icon(Icons.Default.Send, contentDescription = "Enviar", tint = RatioXColors.Navy)
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .background(RatioXColors.Background)
        ) {
            Text("Preguntas rápidas:", fontWeight = FontWeight.Bold, color = RatioXColors.TextMuted)
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(4) { index ->
                    val text = when (index) {
                        0 -> "¿Cuánto he gastado este mes?"
                        1 -> "¿Cómo puedo ahorrar más?"
                        2 -> "Analiza mis gastos"
                        else -> "Consejos financieros"
                    }
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = RatioXColors.ActionGray)
                    ) {
                        Text(text, modifier = Modifier.padding(12.dp), color = RatioXColors.NavyText)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Column {
                    Text(
                        "¡Hola! Soy RatioX, tu asistente financiero. Puedo ayudarte a analizar tus gastos, darte consejos personalizados y responder preguntas sobre tus finanzas. ¿En qué puedo ayudarte hoy?",
                        color = RatioXColors.NavyText
                    )
                    Text(
                        "02:05",
                        color = RatioXColors.TextMuted,
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
            }
        }
    }
}

private object RatioXColors {
    val Navy = Color(0xFF1E2B3C)
    val NavyLight = Color(0xFF344356)
    val NavyText = Color(0xFF0C1A2B)
    val HeaderMuted = Color(0xFFD4E0EF)
    val TextMuted = Color(0xFF526176)
    val NavMuted = Color(0xFF7A8493)
    val Background = Color(0xFFFAFAFB)
    val ActionGray = Color(0xFFE3E6EA)
    val Income = Color(0xFF2EAA45)
    val Expense = Color(0xFFE65F6E)
    val GreenSoft = Color(0xFFE9F6EC)
    val RedSoft = Color(0xFFFDEDEF)
}

@Preview(showBackground = true)
@Composable
fun AgentScreenPreview() {
    RatioXTheme {
        AgentScreen(navController = rememberNavController())
    }
}
