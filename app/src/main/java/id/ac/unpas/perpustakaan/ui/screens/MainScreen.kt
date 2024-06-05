package id.ac.unpas.perpustakaan.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import id.ac.unpas.perpustakaan.R
import id.ac.unpas.perpustakaan.ui.screens.BookRequestScreens.FormBookRequest
import id.ac.unpas.perpustakaan.ui.screens.BookRequestScreens.ListBookRequestScreen
import id.ac.unpas.perpustakaan.ui.screens.BookScreens.FormBookScreen
import id.ac.unpas.perpustakaan.ui.screens.BookScreens.ListBookScreen
import id.ac.unpas.perpustakaan.ui.screens.MembershipScreens.FormMembership
import id.ac.unpas.perpustakaan.ui.screens.MembershipScreens.ListMembershipScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onExitClick: () -> Unit){
    val navController = rememberNavController()
    val currentRoute = remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Perpustakaan") },
                navigationIcon = {
                    if (currentRoute.value != NavScreen.Login.route) {
                        Image(
                            painterResource(id = R.drawable.baseline_home_24),
                            contentDescription = "Menu",
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                            modifier = Modifier.clickable {
                                navController.navigate(NavScreen.Home.route)
                            }
                        )
                    }

                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                actions = {
                    Image(
                        painterResource(id = R.drawable.baseline_exit_24),
                        contentDescription = "Exit",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                        modifier = Modifier.clickable {
                            onExitClick()
                        }
                    )
                }
            )
        },
        bottomBar = {
            if (currentRoute.value != NavScreen.Login.route) {
                BottomAppBar (
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ) {
                    Row (modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_book_24), // Gantilah dengan ID drawable Anda
                            contentDescription = "Lihat Buku",
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(NavScreen.ListBook.route)
                                }
                                .weight(0.5f)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.baseline_collections_bookmark_24), // Gantilah dengan ID drawable Anda
                            contentDescription = "Lihat buku pinjaman",
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(NavScreen.ListBookRequest.route)
                                }
                                .weight(0.5f)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.baseline_diversity_1_24), // Gantilah dengan ID drawable Anda
                            contentDescription = "Lihat membership",
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(NavScreen.ListMembership.route)
                                }
                                .weight(0.5f)
                        )
                    }
                }
            }

        },
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = NavScreen.Login.route) {

            composable(NavScreen.Login.route) {
                currentRoute.value = NavScreen.Login.route
                LoginScreen(
                    modifier = Modifier.padding(innerPadding),
                    onLoginClick = {
                        navController.navigate(NavScreen.Home.route)
                    },
                    onRegisterClick = {
                        navController.navigate(NavScreen.FormMembership.route)
                    }
                )
            }

            composable(NavScreen.Home.route) {
                currentRoute.value = NavScreen.Home.route
                HomeScreen(navController = navController, modifier = Modifier.padding(innerPadding)) {
                    navController.navigate(NavScreen.ListBook.route)
                }
            }

            composable(NavScreen.AddBook.route) {
                currentRoute.value = NavScreen.AddBook.route
                FormBookScreen(modifier = Modifier.padding(innerPadding))
            }

            composable(NavScreen.AddMembership.route) {
                currentRoute.value = NavScreen.AddMembership.route
                FormMembership(modifier = Modifier.padding(innerPadding))
            }

            composable(NavScreen.AddBookRequest.route) {
                currentRoute.value = NavScreen.AddBookRequest.route
                FormBookRequest(modifier = Modifier.padding(innerPadding))
            }

            composable(NavScreen.ListBook.route) {
                currentRoute.value = NavScreen.ListBook.route
                ListBookScreen(modifier = Modifier.padding(innerPadding)) { id ->
                    navController.navigate("${NavScreen.EditBook.route}/$id")
                }
            }
            composable(NavScreen.ListBookRequest.route) {
                currentRoute.value = NavScreen.ListBookRequest.route
                ListBookRequestScreen(modifier = Modifier.padding(innerPadding)) { id ->
                    navController.navigate("${NavScreen.EditBookRequest.route}/$id")
                }
            }

            composable(NavScreen.ListMembership.route) {
                currentRoute.value = NavScreen.ListMembership.route
                ListMembershipScreen(modifier = Modifier.padding(innerPadding)) { id ->
                    navController.navigate("${NavScreen.EditMembership.route}/$id")
                }
            }

            composable(NavScreen.EditBook.routeWithArgument,
                arguments = listOf(navArgument(NavScreen.EditBook.argument0) { type = NavType.StringType }))
            { backStackEntry ->
                val id = backStackEntry.arguments?.getString(NavScreen.EditBook.argument0) ?: return@composable

                currentRoute.value = NavScreen.EditBook.route
                FormBookScreen(modifier = Modifier.padding(innerPadding), id = id)
            }

            composable(NavScreen.EditMembership.routeWithArgument,
                arguments = listOf(navArgument(NavScreen.EditMembership.argument0) { type = NavType.StringType }))
            { backStackEntry ->
                val id = backStackEntry.arguments?.getString(NavScreen.EditMembership.argument0) ?: return@composable

                currentRoute.value = NavScreen.EditMembership.route
                FormMembership(modifier = Modifier.padding(innerPadding), id = id)
            }

            composable(NavScreen.EditBookRequest.routeWithArgument,
                arguments = listOf(navArgument(NavScreen.EditBookRequest.argument0) { type = NavType.StringType }))
            { backStackEntry ->
                val id = backStackEntry.arguments?.getString(NavScreen.EditBookRequest.argument0) ?: return@composable

                currentRoute.value = NavScreen.EditBookRequest.route
                FormBookRequest(modifier = Modifier.padding(innerPadding), id = id)
            }

        }

    }
}
