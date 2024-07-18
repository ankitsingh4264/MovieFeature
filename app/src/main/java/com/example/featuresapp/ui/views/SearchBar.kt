package com.example.featuresapp.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.featuresapp.R
import com.example.featuresapp.ui.theme.popBlack
import kotlinx.coroutines.flow.MutableStateFlow


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchFlow: MutableStateFlow<String>,
    filterClicked: () -> Unit,
    showFilter: Boolean
) {

    var currentSearchValue  by remember { mutableStateOf("") }

    LaunchedEffect(key1 = currentSearchValue) {
        searchFlow.emit(currentSearchValue)
    }

    Column(
        modifier
            .fillMaxWidth()
            .padding(start = 20.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search_magnifier),
                contentDescription = "search icon",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(15.dp))
            // SearchTextField()
            TextField(value = currentSearchValue,
                modifier = Modifier.weight(1f,true),
                maxLines = 1,
                placeholder = {
                  Text(text = "Search movies here", color = popBlack.copy(alpha = .5f))
                },
                onValueChange = {
                currentSearchValue = it

            },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
            )
            if (showFilter) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter_icon),
                    contentDescription = "search icon",
                    modifier = Modifier
                        .size(35.dp)
                        .padding(end = 15.dp)
                        .clickable {
                            filterClicked.invoke()
                        }
                )
            }



        }
    }

}

@Preview(showBackground = true)
@Composable
private fun SearchBarPreview() {
    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        searchFlow = MutableStateFlow(""),
        filterClicked = {

        },
        showFilter = true
    )
}
