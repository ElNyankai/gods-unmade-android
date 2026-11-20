package com.sixseven.godsunmade.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sixseven.godsunmade.ui.AppViewModelProvider
import com.sixseven.godsunmade.ui.characters.CharacterDetailScreen
import com.sixseven.godsunmade.ui.characters.CharacterDetailViewModel
import com.sixseven.godsunmade.ui.characters.CharactersScreen
import com.sixseven.godsunmade.ui.characters.CharactersViewModel
import com.sixseven.godsunmade.ui.components.AppBottomBar
import com.sixseven.godsunmade.ui.components.BottomDestination
import com.sixseven.godsunmade.ui.home.HomeScreen
import com.sixseven.godsunmade.ui.home.HomeViewModel
import com.sixseven.godsunmade.ui.leaderboard.LeaderboardScreen
import com.sixseven.godsunmade.ui.leaderboard.LeaderboardViewModel
import com.sixseven.godsunmade.ui.story.ChapterDetailScreen
import com.sixseven.godsunmade.ui.story.ChapterDetailViewModel
import com.sixseven.godsunmade.ui.story.StoryScreen
import com.sixseven.godsunmade.ui.story.StoryViewModel

/** Rutas de navegación: destinos de nivel superior y rutas de detalle con argumento {id}. */
object Routes {
    const val HOME = "inicio"
    const val CHARACTERS = "personajes"
    const val STORY = "historia"
    const val LEADERBOARD = "top"
    const val CHARACTER_DETAIL = "personaje/{id}"
    const val CHAPTER_DETAIL = "capitulo/{id}"
    fun character(id: String) = "personaje/$id"
    fun chapter(id: String) = "capitulo/$id"
}

private val bottomDestinations = listOf(
    BottomDestination(Routes.HOME, "Inicio", Icons.Default.Home),
    BottomDestination(Routes.CHARACTERS, "Personajes", Icons.Default.Person),
    BottomDestination(Routes.STORY, "Historia", Icons.AutoMirrored.Filled.List),
    BottomDestination(Routes.LEADERBOARD, "Top", Icons.Default.Star),
)

// Al cambiar de destino de nivel superior: popUpTo guarda su estado, restoreState lo recupera y launchSingleTop evita duplicarlo.
private fun NavHostController.navigateTopLevel(route: String) = navigate(route) {
    popUpTo(graph.findStartDestination().id) { saveState = true }
    launchSingleTop = true
    restoreState = true
}

/**
 * Grafo de navegación de la app con Navigation Compose.
 *
 * - Rutas definidas en [Routes]; cada destino crea su ViewModel con AppViewModelProvider.Factory.
 * - Bottom bar con 4 destinos de nivel superior (Inicio, Personajes, Historia, Top);
 *   navigateTopLevel guarda y restaura el estado de cada uno.
 * - Rutas de detalle: personaje/{id} y capitulo/{id}.
 * - Para cambiar a API/BD solo se modifica AppContainer, no la navegación.
 */
@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    val entry by navController.currentBackStackEntryAsState()
    val route = entry?.destination?.route
    val showBottomBar = bottomDestinations.any { it.route == route }

    Scaffold(
        bottomBar = {
            if (showBottomBar) AppBottomBar(bottomDestinations, route, navController::navigateTopLevel)
        },
    ) { padding ->
        NavHost(navController, startDestination = Routes.HOME, modifier = Modifier.padding(padding)) {
            composable(Routes.HOME) {
                val vm: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
                val state by vm.uiState.collectAsStateWithLifecycle()
                HomeScreen(state, onNavigate = navController::navigateTopLevel)
            }
            composable(Routes.CHARACTERS) {
                val vm: CharactersViewModel = viewModel(factory = AppViewModelProvider.Factory)
                val state by vm.uiState.collectAsStateWithLifecycle()
                CharactersScreen(
                    state = state,
                    onRetry = vm::cargar,
                    onCharacterClick = { navController.navigate(Routes.character(it)) },
                )
            }
            composable(Routes.CHARACTER_DETAIL) {
                val vm: CharacterDetailViewModel = viewModel(factory = AppViewModelProvider.Factory)
                val state by vm.uiState.collectAsStateWithLifecycle()
                CharacterDetailScreen(
                    state = state,
                    onBack = navController::popBackStack,
                    onRetry = vm::cargar,
                )
            }
            composable(Routes.STORY) {
                val vm: StoryViewModel = viewModel(factory = AppViewModelProvider.Factory)
                val state by vm.uiState.collectAsStateWithLifecycle()
                StoryScreen(state, onRetry = vm::cargar, onChapterClick = { navController.navigate(Routes.chapter(it)) })
            }
            composable(Routes.CHAPTER_DETAIL) {
                val vm: ChapterDetailViewModel = viewModel(factory = AppViewModelProvider.Factory)
                val state by vm.uiState.collectAsStateWithLifecycle()
                ChapterDetailScreen(
                    state = state,
                    onBack = navController::popBackStack,
                    onRetry = vm::cargar,
                    onAcceptSpoiler = vm::aceptarSpoiler,
                )
            }
            composable(Routes.LEADERBOARD) {
                val vm: LeaderboardViewModel = viewModel(factory = AppViewModelProvider.Factory)
                val state by vm.uiState.collectAsStateWithLifecycle()
                LeaderboardScreen(state, onRetry = vm::cargar)
            }
        }
    }
}
