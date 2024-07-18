package com.example.featuresapp.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.example.featuresapp.network.model.MovieModel


@Composable
fun HorizontalItem(modifier: Modifier = Modifier, movieModel: MovieModel) {

    Row(
        modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = movieModel.poster,
            contentDescription = "poster",
            imageLoader = ImageLoader(context = LocalContext.current),
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Fit
        )
        Column(modifier = Modifier.padding(start = 10.dp)) {
            Text(
                text = movieModel.title, style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = movieModel.year, style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Rating ${movieModel.rating}", style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    //italic
                    fontStyle = FontStyle.Italic
                )
            )
        }

    }
}

@Composable
fun VerticalItem(modifier: Modifier = Modifier, movieModel: MovieModel) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
        ,
        horizontalAlignment = Alignment.Start,
    ) {

        AsyncImage(
            model = movieModel.poster,
            contentDescription = "poster",
            imageLoader = ImageLoader(context = LocalContext.current),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(start = 10.dp)) {


            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = movieModel.title, style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                ),
                minLines = 2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = movieModel.year, style = TextStyle(
                    fontSize = 10.sp,
                )
            )
            Text(
                text = "Rating ${movieModel.rating}", style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Italic
                )
            )
        }


    }
}