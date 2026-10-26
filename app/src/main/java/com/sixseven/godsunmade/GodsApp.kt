package com.sixseven.godsunmade

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sixseven.godsunmade.ui.components.BottomItem
import com.sixseven.godsunmade.ui.components.GodsBackground
import com.sixseven.godsunmade.ui.components.GodsBottomBar
import com.sixseven.godsunmade.ui.components.GodsHeader
import com.sixseven.godsunmade.ui.screens.CharacterDetailScreen
import com.sixseven.godsunmade.ui.screens.CharactersScreen
import com.sixseven.godsunmade.ui.screens.GamesScreen
import com.sixseven.godsunmade.ui.screens.HomeScreen
import com.sixseven.godsunmade.ui.screens.LoginScreen
import com.sixseven.godsunmade.ui.screens.StoryScreen
import com.sixseven.godsunmade.ui.screens.WikiScreen

object Routes {
    const val HOME = "home"
    const val GAMES = "games"
    const val STORY = "story"
    const val CHARACTERS = "characters"
    const val CHARACTER_DETAIL = "character/{id}"
    const val WIKI = "wiki"
    const val WIKI_PATTERN = "wiki?categoria={categoria}"
    const val LOGIN = "login"

    fun character(id: String) = "character/$id"
}

private val bottomItems = listOf(
    BottomItem("INICIO", Routes.HOME),
    BottomItem("JUEGOS", Routes.GAMES),
    BottomItem("HISTORIA", Routes.STORY),
    BottomItem("PERSONAJES", Routes.CHARACTERS),
    BottomItem("WIKI", Routes.WIKI)
)

private fun NavHostController.navigateTop(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
fun GodsApp() {
    val nav = rememberNavController()
    val entry by nav.currentBackStackEntryAsState()
    val route = entry?.destination?.route
    val isLogin = route == Routes.LOGIN
    val selectedRoute = when {
        route == Routes.CHARACTER_DETAIL -> Routes.CHARACTERS
        route?.startsWith(Routes.WIKI) == true -> Routes.WIKI
        else -> route
    }

    // Rutas con parámetros (ej. wiki?categoria=REGIONES) se abren como destino nuevo;
    // las demás se tratan como pestañas.
    val go: (String) -> Unit = { target ->
        if (target.contains("?")) {
            nav.navigate(target) { launchSingleTop = true }
        } else {
            nav.navigateTop(target)
        }
    }

    GodsBackground {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                GodsHeader(
                    showLogin = !isLogin,
                    onLoginClick = {
                        nav.navigate(Routes.LOGIN) { launchSingleTop = true }
                    }
                )
            },
            bottomBar = {
                if (!isLogin) {
                    GodsBottomBar(
                        items = bottomItems,
                        selectedRoute = selectedRoute,
                        onSelect = { nav.navigateTop(it) }
                    )
                }
            }
        ) { padding ->
            val contentModifier = Modifier.padding(padding)

            NavHost(
                navController = nav,
                startDestination = Routes.HOME
            ) {
                composable(Routes.HOME) {
                    HomeScreen(onNavigate = go, modifier = contentModifier)
                }
                composable(Routes.GAMES) {
                    GamesScreen(onNavigate = go, modifier = contentModifier)
                }
                composable(Routes.STORY) {
                    StoryScreen(modifier = contentModifier)
                }
                composable(Routes.CHARACTERS) {
                    CharactersScreen(
                        onCharacterClick = { nav.navigate(Routes.character(it)) },
                        modifier = contentModifier
                    )
                }
                composable(Routes.CHARACTER_DETAIL) {
                    CharacterDetailScreen(
                        onBack = { nav.popBackStack() },
                        modifier = contentModifier
                    )
                }
                composable(
                    route = Routes.WIKI_PATTERN,
                    arguments = listOf(
                        navArgument("categoria") {
                            type = NavType.StringType
                            defaultValue = ""
                        }
                    )
                ) {
                    WikiScreen(modifier = contentModifier)
                }
                composable(Routes.LOGIN) {
                    LoginScreen(
                        onSubmit = { nav.popBackStack() },
                        modifier = contentModifier
                    )
                }
            }
        }
    }
}