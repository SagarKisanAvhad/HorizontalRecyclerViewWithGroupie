package com.delaroystudios.snaprecyclerview;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.delaroystudios.snaprecyclerview.api.Client;
import com.delaroystudios.snaprecyclerview.api.Service;
import com.delaroystudios.snaprecyclerview.item.HeaderItem;
import com.delaroystudios.snaprecyclerview.item.MovieItem;
import com.delaroystudios.snaprecyclerview.model.Movie;
import com.delaroystudios.snaprecyclerview.model.MoviesResponse;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Section;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

  private Section popularMovieSection, topRatedMovieSection;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    GroupAdapter groupAdapter = new GroupAdapter();
    popularMovieSection = new Section(new HeaderItem(R.string.popular));
    topRatedMovieSection = new Section(new HeaderItem(R.string.top_rated));

    groupAdapter.add(0, popularMovieSection);
    groupAdapter.add(1, topRatedMovieSection);

    RecyclerView firstRecyclerView = findViewById(R.id.rv_movie);
    LinearLayoutManager firstManager =
        new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
    firstRecyclerView.setLayoutManager(firstManager);
    firstRecyclerView.setAdapter(groupAdapter);

    loadPopular();
    loadTopRated();
  }

  private void loadPopular() {

    try {
      if (BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()) {
        Toast.makeText(getApplicationContext(), "Please obtain your API Key from themoviedb.org",
            Toast.LENGTH_SHORT).show();
        return;
      }
      Client Client = new Client();
      Service apiService = Client.getClient().create(Service.class);
      Call<MoviesResponse> call = apiService.getPopularMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN);
      call.enqueue(new Callback<MoviesResponse>() {
        @Override
        public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
          List<Movie> movies = response.body().getResults();
          if (response.isSuccessful()) {
            if (response.body() != null) {

              //GroupAdapter groupAdapter = new GroupAdapter();

              //Section section = new Section();
              for (Movie movie : movies) {
                popularMovieSection.add(new MovieItem(movie));
              }
              //groupAdapter.add(section);
              /*MultiSnapRecyclerView firstRecyclerView =
                  findViewById(R.id.first_recycler_view);
              LinearLayoutManager firstManager =
                  new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,
                      false);
              firstRecyclerView.setLayoutManager(firstManager);
              firstRecyclerView.setAdapter(groupAdapter);*/
            }
          }
        }

        @Override
        public void onFailure(Call<MoviesResponse> call, Throwable t) {
          Log.d("Error", t.getMessage());
          Toast.makeText(getApplicationContext(), "Error fetching trailer data", Toast.LENGTH_SHORT)
              .show();
        }
      });
    } catch (Exception e) {
      Log.d("Error", e.getMessage());
      Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }
  }

  private void loadTopRated() {

    try {
      if (BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()) {
        Toast.makeText(getApplicationContext(), "Please obtain your API Key from themoviedb.org",
            Toast.LENGTH_SHORT).show();
        return;
      }
      Client Client = new Client();
      Service apiService = Client.getClient().create(Service.class);
      Call<MoviesResponse> call = apiService.getTopRatedMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN);
      call.enqueue(new Callback<MoviesResponse>() {
        @Override
        public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
          if (response.isSuccessful()) {
            if (response.body() != null) {
              List<Movie> movies = response.body().getResults();

              //GroupAdapter groupAdapter = new GroupAdapter();
              //Section section = new Section();
              for (Movie movie : movies) {
                topRatedMovieSection.add(new MovieItem(movie));
              }

              //groupAdapter.add(topRatedMovieSection);

              /*MultiSnapRecyclerView secondRecyclerView =
                  findViewById(R.id.second_recycler_view);
              LinearLayoutManager secondManager =
                  new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,
                      false);
              secondRecyclerView.setLayoutManager(secondManager);
              secondRecyclerView.setAdapter(groupAdapter);*/
            }
          }
        }

        @Override
        public void onFailure(Call<MoviesResponse> call, Throwable t) {
          Log.d("Error", t.getMessage());
          Toast.makeText(getApplicationContext(), "Error fetching trailer data", Toast.LENGTH_SHORT)
              .show();
        }
      });
    } catch (Exception e) {
      Log.d("Error", e.getMessage());
      Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }
  }
}
