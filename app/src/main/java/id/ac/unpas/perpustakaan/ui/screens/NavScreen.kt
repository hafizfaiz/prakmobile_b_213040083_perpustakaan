package id.ac.unpas.perpustakaan.ui.screens

sealed class NavScreen (val route: String) {
    object Home : NavScreen("home")
    object List : NavScreen("list")
    object FormMembership : NavScreen("form_membership")
    object AddBook : NavScreen("addBook")
    object AddBookRequest : NavScreen("addBookRequest")
    object AddMembership : NavScreen("addMembership")
    object EditBook : NavScreen("editBook") {
        const val routeWithArgument: String = "editBook/{id}"
        const val argument0 : String = "id"
    }
    object EditBookRequest : NavScreen("editBookRequest") {
        const val routeWithArgument: String = "editBookRequest/{id}"
        const val argument0 : String = "id"
    }

    object EditMembership : NavScreen("editMembership") {
        const val routeWithArgument: String = "editMembership/{id}"
        const val argument0 : String = "id"
    }
    object ListBook : NavScreen("listBook")
    object ListBookRequest : NavScreen("listBookRequest")
    object ListMembership : NavScreen("listMembership")
    object Login : NavScreen("login")
}