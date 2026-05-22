package com.example.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ui.RestoranViewModel
import com.example.ui.cart.CartScreen
import com.example.ui.detail.DetailMenuScreen
import com.example.ui.editprofile.EditProfileScreen
import com.example.ui.home.HomeScreen
import com.example.ui.menu.MenuScreen
import com.example.ui.profile.ProfileScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: RestoranViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) + fadeIn() },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) + fadeOut() },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) + fadeIn() },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) + fadeOut() }
    ) {
        // Home Screen Route
        composable("home") {
            HomeScreen(navController = navController, viewModel = viewModel)
        }

        // Orders / Menu Screen Route
        composable("menu") {
            MenuScreen(navController = navController, viewModel = viewModel)
        }

        // Food Detail Screen Route (parsed dynamically)
        composable(
            route = "menu/{menuId}",
            arguments = listOf(
                navArgument("menuId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val menuId = backStackEntry.arguments?.getString("menuId") ?: ""
            DetailMenuScreen(menuId = menuId, navController = navController, viewModel = viewModel)
        }

        // Cart Screen Route
        composable("cart") {
            CartScreen(navController = navController, viewModel = viewModel)
        }

        // Profile Screen Route
        composable("profile") {
            ProfileScreen(navController = navController, viewModel = viewModel)
        }

        // Edit Profile Screen Route
        composable("edit_profile") {
            EditProfileScreen(navController = navController, viewModel = viewModel)
        }
    }
}
