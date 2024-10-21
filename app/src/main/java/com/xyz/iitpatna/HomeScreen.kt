package com.xyz.iitpatna

import android.widget.ScrollView
import android.widget.Scroller
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

val movieList = mutableListOf<Movie>()
private const val BASE_URL = "https://api.themoviedb.org/"
private const val POPULAR = "3/movie/popular?api_key=ba2e10aeb758b387f655c643fdf11d63"
private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

@Composable
fun HomePage(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.verticalScroll(state = ScrollState(0))) {
        LazyRow {
            items(movieList.size) { index ->
                MovieItem(movieList[index])
            }
        }
        LazyRow {
            items(movieList.size) { index ->
                MovieItem(movieList[index])
            }
        }
        LazyRow {
            items(movieList.size) { index ->
                MovieItem(movieList[index])
            }
        }
        LazyRow {
            items(movieList.size) { index ->
                MovieItem(movieList[index])
            }
        }
        LazyRow {
            items(movieList.size) { index ->
                MovieItem(movieList[index])
            }
        }
        LazyRow {
            items(movieList.size) { index ->
                MovieItem(movieList[index])
            }
        }
        LazyRow {
            items(movieList.size) { index ->
                MovieItem(movieList[index])
            }
        }
        LazyRow {
            items(movieList.size) { index ->
                MovieItem(movieList[index])
            }
        }
    }
}
@Composable
fun MovieItem(movie: Movie) {
    Card(modifier = Modifier.padding(8.dp)) {
        //Text(text = "Movie Title ::::  ${movie.title}")
        AsyncImage(
            model = IMAGE_BASE_URL + movie.poster_path,
            contentDescription = "Movie Poster",
            //modifier = Modifier
        )
    }
}

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface MoviesApiService {
    @GET(POPULAR)
    fun getMoviesData(): Call<MovieData>
}

object MoviesApi {
    val moviesService: MoviesApiService by lazy {
        retrofit.create(MoviesApiService::class.java)
    }
}

fun fetchMoviesData(navcontroller: NavHostController) {

    MoviesApi.moviesService.getMoviesData().enqueue(object : Callback<MovieData>{
        override fun onResponse(call: Call<MovieData>, response: Response<MovieData>) {
            if(response.code() == 200) {
                // UI update with new data
                println("------ Success  ---------")
                println(response.body())

                val movieListData = response.body()?.results ?: emptyList()
                movieList.addAll(movieListData)

                navcontroller.navigate("home_page")

            } else {
                println("Failed to fetch data")
            }
        }

        override fun onFailure(call: Call<MovieData>, t: Throwable) {
            println("Failed to fetch data")
        }
    })

}