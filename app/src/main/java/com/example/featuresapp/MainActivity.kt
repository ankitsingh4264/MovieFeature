package com.example.featuresapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.craft.projectx.data.UsageData
import com.craft.projectx.presentation.home_screen.HomeScreen
import com.craft.projectx.utils.DummyUsage
import com.example.featuresapp.data.Result
import com.example.featuresapp.ui.theme.FeaturesAppTheme
import com.example.featuresapp.ui.views.FilterBottomSheet
import com.example.featuresapp.ui.views.FilterType
import com.example.featuresapp.ui.views.MovieVerticalList
import com.example.featuresapp.ui.views.SearchBar
import com.example.featuresapp.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<SearchViewModel>()
    private val searchFlow = MutableStateFlow<String>("")
    private var showBs by mutableStateOf<Boolean>(false)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
              val scope = rememberCoroutineScope()
//            if (showBs) {
//                FilterBottomSheet(
//                    yearFilterSelected = viewModel.filters.contains(FilterType.YEAR),
//                    ratingFilterSelected = viewModel.filters.contains(FilterType.RATING),
//
//                    onDismiss = { filterType, applied ->
//                        if (applied && filterType != null) {
//                            viewModel.filters.add(filterType)
//                        } else if (filterType != null) {
//                            viewModel.filters.remove(filterType)
//                        }
//
//                        viewModel.setFilters()
//                        scope.launch {
//                            delay(500)
//                            showBs = false
//                        }
//                    }
//                )
//            }

            var gridOn by remember { mutableStateOf(false) }
            FeaturesAppTheme {
                Scaffold(
//                    floatingActionButton = {
//                        Box(
//                            modifier = Modifier
//                                .background(Color.Black.copy(.4f), shape = CircleShape)
//                                .padding(8.dp)
//                        ) {
//                            val fabImage =
//                                if (gridOn) R.drawable.ic_grid_off else R.drawable.ic_grid_on
//                            Icon(
//                                painter = painterResource(id = fabImage),
//                                contentDescription = null,
//                                modifier = Modifier.clickable {
//                                    gridOn = !gridOn
//                                },
//                            )
//                        }
//
//                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    val usage = DummyUsage.dummyUsage
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding),
                        usageData = usage)

//                    Column(
//
//                    ) {
//
////
////                        Greeting(
////                            name = "Movie Search App",
////                            modifier = Modifier.padding(8.dp)
////                        )
////                        SearchBar(
////                            modifier = Modifier.fillMaxWidth(),
////                            searchFlow, filterClicked = {
////                                showBs = true
////                            },
////                            showFilter = viewModel.currentList.isNotEmpty()
////                        )
////
////                        when (val movieList = viewModel.movieListState.value) {
////                            is Result.Success -> {
////                                MovieVerticalList(
////                                    list = movieList.data,
////                                    modifier = Modifier.padding(top = 20.dp),
////                                    isGrid = gridOn,
////                                    onNextPageCall = {
////                                        viewModel.getSearchedMovies(viewModel.lastSearched, true)
////                                    }
////                                )
////                            }
////
////                            is Result.Error -> {
////                                Box(modifier = Modifier.fillMaxSize()) {
////                                    Column(
////                                        modifier = Modifier.align(Alignment.Center),
////                                        horizontalAlignment = Alignment.CenterHorizontally
////                                    ) {
////                                        Icon(
////                                            painter = painterResource(id = R.drawable.ic_error),
////                                            contentDescription = "error"
////                                        )
////                                        Text(
////                                            movieList.msg ?: getString(R.string.error_generic),
////                                            modifier = Modifier.padding(top = 15.dp)
////                                        )
////                                    }
////
////                                }
////                            }
////
////                            is Result.Loading -> {
////                                Box(modifier = Modifier.fillMaxSize()) {
////                                    CircularProgressIndicator(Modifier.align(Alignment.Center))
////                                }
////                            }
////
////                            Result.Initial -> {
////                                Box(modifier = Modifier.fillMaxSize()) {
////                                    Text(
////                                        "Welcome Search Something",
////                                        modifier = Modifier
////                                            .padding(top = 15.dp)
////                                            .align(Alignment.Center),
////                                        style = TextStyle(
////                                            fontSize = 20.sp
////                                        )
////                                    )
////                                }
////                            }
////                        }
//
//                    }
                }
            }
        }
//        collectSearchValues()
    }


    @OptIn(FlowPreview::class)
    private fun collectSearchValues() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchFlow.debounce(200).collectLatest {
                    viewModel.getSearchedMovies(it)
                }
            }

        }

    }

}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FeaturesAppTheme {
        Greeting("Android")
    }
}



