package dev.eamoretti.ratiox.presentation.accounts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cuentas", fontWeight = FontWeight.Bold) },
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
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Routes.NewAccount) },
                shape = CircleShape,
                containerColor = RatioXColors.Navy
            ) {
                Icon(Icons.Default.Add, contentDescription = "Añadir cuenta", tint = Color.White)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(RatioXColors.Background)
        ) {
            TotalBalanceCard(totalBalance = "S/ 5,300.50", accountsInfo = "3 de 4 cuentas incluidas")
            AccountList()
        }
    }
}

@Composable
private fun TotalBalanceCard(totalBalance: String, accountsInfo: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = RatioXColors.Navy)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Saldo total", color = RatioXColors.HeaderMuted, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(totalBalance, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(accountsInfo, color = RatioXColors.HeaderMuted, fontSize = 12.sp)
        }
    }
}

@Composable
private fun AccountList() {
    val accounts = listOf(
        Account("BCP Principal", "Cuenta corriente", "S/ 1,850.50", Icons.Default.AccountBalanceWallet, false, false),
        Account("Efectivo", "Efectivo", "S/ 250.00", Icons.Default.Money, false, false),
        Account("Ahorro vacaciones", "Cuenta de ahorro", "S/ 3,200.00", Icons.Default.Savings, false, false),
        Account("Visa Gold", "Tarjeta de crédito", "-S/ 450.00", Icons.Default.CreditCard, true, true)
    )

    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(accounts) { account ->
            AccountItem(account)
        }
    }
}

@Composable
private fun AccountItem(account: Account) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(RatioXColors.ActionGray)
            ) {
                Icon(
                    account.icon,
                    contentDescription = null,
                    tint = RatioXColors.Navy,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(account.name, fontWeight = FontWeight.Bold, color = RatioXColors.NavyText)
                    if (account.notIncluded) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(RatioXColors.ActionGray)
                                .padding(horizontal = 4.dp, vertical = 2.dp)
                        ) {
                            Text("No incluida", fontSize = 10.sp, color = RatioXColors.TextMuted)
                        }
                    }
                }
                Text(account.type, fontSize = 12.sp, color = RatioXColors.TextMuted)
            }
            Text(
                account.balance,
                fontWeight = FontWeight.Bold,
                color = if (account.isNegative) RatioXColors.Expense else RatioXColors.NavyText
            )
        }
    }
}

private data class Account(
    val name: String,
    val type: String,
    val balance: String,
    val icon: ImageVector,
    val notIncluded: Boolean,
    val isNegative: Boolean
)

@Preview(showBackground = true)
@Composable
fun AccountsScreenPreview() {
    RatioXTheme {
        AccountsScreen(navController = rememberNavController())
    }
}
