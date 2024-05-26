package id.ac.unpas.perpustakaan.ui.screens

sealed class NavScreen(val route: String) {
    object Home : NavScreen("home")
    object Add : NavScreen("add")
    object Edit : NavScreen("edit") {
        const val routeWithArgument: String = "edit/{id}"
        const val argument0 : String = "id"
    }
    object List : NavScreen("list")
    object Login : NavScreen("login")
}