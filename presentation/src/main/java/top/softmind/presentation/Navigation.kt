package top.softmind.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

private const val ART_COLLECTION_ID_NAV_ARG_NAME = "artCollectionId"

@Composable
fun AppNavHost() {
    val navHostControl = rememberNavController()
    NavHost(
        navController = navHostControl,
        startDestination = AppScreen.LIST.name,
    ) {
        composable(route = AppScreen.LIST.name) {
            OverviewScreen(navHostControl)
        }
        composable(
            route = "${AppScreen.DETAILS.name}/{$ART_COLLECTION_ID_NAV_ARG_NAME}",
            arguments = listOf(navArgument(ART_COLLECTION_ID_NAV_ARG_NAME) { type = NavType.StringType })
        ) { navBackStackEntry ->
            val artCollectionId = navBackStackEntry.arguments?.getString(ART_COLLECTION_ID_NAV_ARG_NAME)
            artCollectionId?.let { id ->
                DetailsSreen(id)
            }
        }
    }
}

internal enum class AppScreen() {
    LIST,
    DETAILS
}