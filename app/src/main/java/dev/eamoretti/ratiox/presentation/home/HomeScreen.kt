package dev.eamoretti.ratiox.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.eamoretti.ratiox.presentation.navigation.Routes
import dev.eamoretti.ratiox.ui.theme.RatioXColors
import dev.eamoretti.ratiox.ui.theme.RatioXTheme

@Composable
fun RatioXHomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = RatioXColors.Background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.weight(1f)) {
                Box {
                    HomeHeader()
                    BalanceCard(
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .padding(top = 92.dp)
                    )
                }
                Spacer(modifier = Modifier.height(88.dp))
                QuickActionsSection(
                    actions = listOf(
                        QuickAction("Preguntar a RatioX", Icons.Default.ChatBubbleOutline, true) {
                            navController.navigate(Routes.Agent)
                        },
                        QuickAction("Gestionar cuentas", Icons.Default.CreditCard, false) {
                            navController.navigate(Routes.Accounts)
                        },
                        QuickAction("Añadir movimiento", Icons.Default.Add, false) {
                            navController.navigate(Routes.NewMovement)
                        }
                    )
                )
            }

            RatioXBottomBar()
        }
    }
}

@Composable
private fun HomeHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(148.dp)
            .clip(RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp))
            .background(RatioXColors.Navy)
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 18.dp, top = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "RatioX Finanzas",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Enero 2026",
                    color = RatioXColors.HeaderMuted,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Configuración",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
private fun BalanceCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 18.dp, vertical = 20.dp)) {
            Text(
                text = "Balance del mes",
                color = RatioXColors.TextMuted,
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(22.dp))
            Text(
                text = "S/ 2,217.61",
                color = RatioXColors.Income,
                fontSize = 25.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(26.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(42.dp)
            ) {
                BalanceIndicator(
                    label = "Ingresos",
                    value = "S/ 3,350.00",
                    icon = Icons.Default.ArrowDownward,
                    iconBackground = RatioXColors.GreenSoft,
                    iconTint = RatioXColors.Income,
                    modifier = Modifier.weight(1f)
                )
                BalanceIndicator(
                    label = "Gastos",
                    value = "S/ 1,132.39",
                    icon = Icons.Default.ArrowUpward,
                    iconBackground = RatioXColors.RedSoft,
                    iconTint = RatioXColors.Expense,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun BalanceIndicator(
    label: String,
    value: String,
    icon: ImageVector,
    iconBackground: Color,
    iconTint: Color,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(horizontalArrangement = Arrangement.spacedBy(9.dp)) {
            Box(
                modifier = Modifier
                    .size(25.dp)
                    .clip(CircleShape)
                    .background(iconBackground)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier
                        .size(13.dp)
                        .padding(1.dp)
                        .align(Alignment.Center)
                )
            }
            Text(
                text = label,
                color = RatioXColors.TextMuted,
                fontSize = 10.sp,
                modifier = Modifier.padding(top = 6.dp)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = value,
            color = iconTint,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun QuickActionsSection(actions: List<QuickAction>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 23.dp)
    ) {
        Text(
            text = "Acciones rápidas",
            color = RatioXColors.TextMuted,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(14.dp))
        actions.forEach { action ->
            QuickActionRow(action = action)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
private fun QuickActionRow(action: QuickAction) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(53.dp)
            .clip(RoundedCornerShape(7.dp))
            .background(if (action.primary) RatioXColors.Navy else RatioXColors.ActionGray)
            .padding(horizontal = 13.dp)
            .clickable(onClick = action.onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(31.dp)
                .clip(CircleShape)
                .background(if (action.primary) RatioXColors.NavyLight else Color.White)
        ) {
            Icon(
                imageVector = action.icon,
                contentDescription = null,
                tint = if (action.primary) Color.White else RatioXColors.Navy,
                modifier = Modifier
                    .size(18.dp)
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.size(13.dp))
        Text(
            text = action.title,
            color = if (action.primary) Color.White else RatioXColors.NavyText,
            fontSize = 13.sp,
            fontWeight = if (action.primary) FontWeight.Bold else FontWeight.SemiBold
        )
    }
}

@Composable
private fun RatioXBottomBar() {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.navigationBars),
        containerColor = Color.White,
        tonalElevation = 0.dp
    ) {
        val items = listOf(
            BottomItem("Inicio", Icons.Default.Home, true),
            BottomItem("Movimientos", Icons.Default.Menu, false),
            BottomItem("Categorías", Icons.Default.LocalOffer, false),
            BottomItem("Reportes", Icons.Default.BarChart, false)
        )

        items.forEach { item ->
            NavigationBarItem(
                selected = item.selected,
                onClick = {},
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        modifier = Modifier.size(22.dp)
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = 8.sp,
                        fontWeight = if (item.selected) FontWeight.Bold else FontWeight.Medium
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = RatioXColors.Navy,
                    selectedTextColor = RatioXColors.Navy,
                    unselectedIconColor = RatioXColors.NavMuted,
                    unselectedTextColor = RatioXColors.NavMuted,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Immutable
private data class QuickAction(
    val title: String,
    val icon: ImageVector,
    val primary: Boolean,
    val onClick: () -> Unit
)

@Immutable
private data class BottomItem(
    val label: String,
    val icon: ImageVector,
    val selected: Boolean
)

@Preview(showBackground = true, widthDp = 315, heightDp = 670)
@Composable
fun RatioXHomeScreenPreview() {
    RatioXTheme {
        RatioXHomeScreen(navController = rememberNavController())
    }
}
