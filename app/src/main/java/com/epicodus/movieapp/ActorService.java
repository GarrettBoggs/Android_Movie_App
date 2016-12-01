package com.epicodus.movieapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.RequestException;
import okhttp3.internal.http.StreamAllocation;

/**
 * Created by Guest on 12/1/16.
 */
public class ActorService {

    public static void findActors(String actor, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.ACTOR_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.MOVIE_TITLE_QUERY, actor);
        urlBuilder.addQueryParameter("api_key", Constants.MOVIE_KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Movie> processResults(Response response){
        ArrayList<Movie> movies = new ArrayList<>();

        try {
            String jsonData = response.body().string();

            if(response.isSuccessful()) {
                JSONObject tmdbJSON = new JSONObject(jsonData);
                JSONArray resultsJSON = tmdbJSON.getJSONArray("results");
                JSONObject firstJSON = resultsJSON.getJSONObject(0);
                JSONArray moviesJSON = firstJSON.getJSONArray("known_for");
                String stringJSON = moviesJSON.toString();
                Log.d("results", stringJSON);
                for(int i = 0; i < moviesJSON.length(); i++){
                    JSONObject movieJSON = moviesJSON.getJSONObject(i);

                    String title = movieJSON.getString("original_title");
                    String poster = movieJSON.getString("poster_path");
                    String synopsis = movieJSON.getString("overview");

                    Movie screen = new Movie(title,poster,synopsis);
                    movies.add(screen);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }

        return movies;
    }
}
