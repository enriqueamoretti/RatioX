package dev.eamoretti.ratiox.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.eamoretti.ratiox.presentation.accounts.AccountsScreen
import dev.eamoretti.ratiox.presentation.accounts.NewAccountScreen
import dev.eamoretti.ratiox.presentation.agent.AgentScreen
import dev.eamoretti.ratiox.presentation.home.RatioXHomeScreen
import dev.eamoretti.ratiox.presentation.movements.NewMovementScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Home) {
        composable<Routes.Home> {
            RatioXHomeScreen(navController = navController)
        }
        composable<Routes.Agent> {
            AgentScreen(navController = navController)
        }
        composable<Routes.Accounts> {
            AccountsScreen(navController = navController)
        }
        composable<Routes.NewAccount> {
            NewAccountScreen(navController = navController)
        }
        composable<Routes.NewMovement> {
            NewMovementScreen(navController = navController)
        }
    }
}
