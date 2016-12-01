package com.epicodus.movieapp;

/**
 * Created by Guest on 12/1/16.
 */
public class Movie {

    String title;
    String poster;
    String synopsis;

    public Movie( String title , String poster , String synopsis){
        this.title = title;
        this.poster = poster;
        this.synopsis = synopsis;
    }

    public String getTitle(){
        return title;
    }

    public String getPoster(){
        return "https://image.tmdb.org/t/p/original/" + poster;
    }

    public String getSynopsis(){
        return synopsis;
    }
}
