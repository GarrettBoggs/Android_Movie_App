package com.epicodus.movieapp;

/**
 * Created by Guest on 12/1/16.
 */
public class Constants {

    public static final String MOVIE_KEY = BuildConfig.MOVIE_KEY;
    public static final String MOVIE_BASE_URL = "https://api.themoviedb.org/3/search/movie?api_key= " + MOVIE_KEY + "&language=en-US&query=Hot%20Rod&page=1&include_adult=false&primary_release_year=2008";
    public static final String MOVIE_TITLE_QUERY = "title";
}
