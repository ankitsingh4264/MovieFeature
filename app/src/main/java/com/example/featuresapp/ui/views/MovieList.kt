package com.example.featuresapp.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.featuresapp.network.model.MovieModel



const val PAGINATION_THRESHOLD = 1

@Composable
fun MovieVerticalList(modifier: Modifier = Modifier,
                      isGrid:Boolean =false,
                      list: List<MovieModel>,
                      onNextPageCall:()->Unit

) {

    val cellCount by remember(isGrid) {
        mutableIntStateOf(
            if (isGrid) 2 else 1
        )
    }
    val state = rememberLazyGridState()

    LazyVerticalGrid(columns = GridCells.Fixed(cellCount),modifier,state =state) {
        itemsIndexed(list) { index, item ->
            if (index == list.size - PAGINATION_THRESHOLD) {
                onNextPageCall()
            }
            if (cellCount == 1) {
                HorizontalItem(
                    movieModel = item, modifier = Modifier
                        .padding(top = 20.dp)
                        .background(
                            color = Color.LightGray.copy(alpha = 0.3f)
                        )
                )
            }else{
                VerticalItem(
                    movieModel = item, modifier = Modifier
                        .padding(top = 20.dp)
                        .padding(horizontal = 20.dp)
                        .background(
                            color = Color.LightGray.copy(alpha = 0.3f)
                        )

                )
            }

        }


    }

}