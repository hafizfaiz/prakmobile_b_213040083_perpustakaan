package id.ac.unpas.perpustakaan.ui.screens

sealed class NavScreen(val route: String) {
    object Home : NavScreen("home")
    object Add : NavScreen("add")
    object Edit : NavScreen("edit") {
        const val routeWithArgument: String = "edit/{id}"
        const val argument0: String = "id"
    }
    object List : NavScreen("list")
    object Login : NavScreen("login")

    object FormMembership : NavScreen("form_membership")

    object AddBookRequest : NavScreen("add_BookRequest")

    object EditBookRequest : NavScreen("edit_BookRequest") {
        const val routeWithArgument: String = "edit_BookRequest/{id}"
        const val argument0: String = "id"
    }
    object FormBookRequest : NavScreen("form_membership")

    object AddMembership : NavScreen("add_membership")

    object EditMembership : NavScreen("edit_membership") {
        const val routeWithArgument: String = "edit_membership/{id}"
        const val argument0: String = "id"
    }
}
