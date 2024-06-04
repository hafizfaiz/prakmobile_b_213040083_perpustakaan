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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onExitClick: () -> Unit) {
    val navController = rememberNavController()
    val currentRoute = remember {
        mutableStateOf("")
    }
    val isMembershipFormVisible = currentRoute.value == NavScreen.FormMembership.route

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Perpustakaan") },
                navigationIcon = {
                    if (currentRoute.value != NavScreen.Login.route) {
                        Image(
                            painterResource(id = R.drawable.baseline_home_24),
                            contentDescription = "Menu",
                            colorFilter = ColorFilter.tint(Color.White),
                            modifier = Modifier.clickable {
                                if (currentRoute.value == NavScreen.FormMembership.route) {
                                    navController.navigate(NavScreen.Login.route) // Navigasi ke halaman login saat form membership ditampilkan
                                } else {
                                    navController.navigate(NavScreen.Home.route)
                                }
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
            if (currentRoute.value != NavScreen.Login.route && !isMembershipFormVisible) {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painterResource(id = R.drawable.baseline_add_24),
                            contentDescription = "Tambah",
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                            modifier = Modifier.clickable {
                                navController.navigate(NavScreen.Add.route)
                            }.weight(0.5f)
                        )
                        Image(
                            painterResource(id = R.drawable.baseline_remove_24),
                            contentDescription = "Lihat",
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                            modifier = Modifier.clickable {
                                navController.navigate(NavScreen.List.route)
                            }.weight(0.5f)
                        )
                    }
                }
            }

        },
        floatingActionButton = {
            if (currentRoute.value == NavScreen.Home.route) {
                FloatingActionButton(onClick = { navController.navigate(NavScreen.Add.route) }) {
                    Image(painterResource(id = R.drawable.baseline_add_24), contentDescription = "Add")
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End
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
                    navController.navigate(NavScreen.List.route)
                }
            }

            composable(NavScreen.List.route) {
                currentRoute.value = NavScreen.List.route
                ListBookScreen(modifier = Modifier.padding(innerPadding)) { id ->
                    navController.navigate("${NavScreen.Edit.route}/$id")
                }
            }
            composable(NavScreen.List.route) {
                currentRoute.value = NavScreen.List.route
                ListMembershipScreen(modifier = Modifier.padding(innerPadding)) { id ->
                    navController.navigate("${NavScreen.EditMembership.route}/$id")
                }
            }
            composable(NavScreen.List.route) {
                currentRoute.value = NavScreen.List.route
                ListBookRequestScreen(modifier = Modifier.padding(innerPadding)) { id ->
                    navController.navigate("${NavScreen.EditMembership.route}/$id")
                }
            }
            composable(NavScreen.Add.route) {
                currentRoute.value = NavScreen.Add.route
                FormBookScreen(modifier = Modifier.padding(innerPadding))
            }

            composable(NavScreen.Edit.routeWithArgument,
                arguments = listOf(navArgument(NavScreen.Edit.argument0) { type = NavType.StringType })) { backStackEntry ->
                val id = backStackEntry.arguments?.getString(NavScreen.Edit.argument0) ?: return@composable

                currentRoute.value = NavScreen.Edit.route
                FormBookScreen(modifier = Modifier.padding(innerPadding), id = id)
            }

            composable(NavScreen.AddMembership.route) {
                currentRoute.value = NavScreen.AddMembership.route
                FormBookRequest(modifier = Modifier.padding(innerPadding))
            }

            composable(NavScreen.EditMembership.routeWithArgument,
                arguments = listOf(navArgument(NavScreen.EditMembership.argument0) { type = NavType.StringType })) { backStackEntry ->
                val id = backStackEntry.arguments?.getString(NavScreen.EditMembership.argument0) ?: return@composable

                currentRoute.value = NavScreen.EditMembership.route
                FormBookRequest(modifier = Modifier.padding(innerPadding), id = id)
            }

            composable(NavScreen.FormMembership.route) {
                currentRoute.value = NavScreen.FormMembership.route
                FormBookRequest(modifier = Modifier.padding(innerPadding))
            }

            composable(NavScreen.AddMembership.route) {
                currentRoute.value = NavScreen.AddMembership.route
                FormMembership(modifier = Modifier.padding(innerPadding))
            }

            composable(NavScreen.EditMembership.routeWithArgument,
                arguments = listOf(navArgument(NavScreen.EditMembership.argument0) { type = NavType.StringType })) { backStackEntry ->
                val id = backStackEntry.arguments?.getString(NavScreen.EditMembership.argument0) ?: return@composable

                currentRoute.value = NavScreen.EditMembership.route
                FormMembership(modifier = Modifier.padding(innerPadding), id = id)
            }

            composable(NavScreen.FormMembership.route) {
                currentRoute.value = NavScreen.FormMembership.route
                FormMembership(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}
