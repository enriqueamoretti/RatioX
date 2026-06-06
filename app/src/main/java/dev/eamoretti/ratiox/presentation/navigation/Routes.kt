package dev.eamoretti.ratiox.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    data object Home : Routes()

    @Serializable
    data object Agent : Routes()

    @Serializable
    data object Accounts : Routes()

    @Serializable
    data object NewAccount : Routes()

    @Serializable
    data object NewMovement : Routes()
}
