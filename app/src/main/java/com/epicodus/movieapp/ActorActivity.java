package com.epicodus.movieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.movieapp.adapters.MovieListAdapter;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ActorActivity extends AppCompatActivity {

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private MovieListAdapter mAdapter;

    public ArrayList<Movie> mActors = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String actor = intent.getStringExtra("actor");

        getActors(actor);
    }

    private void getActors (String actor) {
        final ActorService actorService = new ActorService();
        actorService.findActors(actor, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) { e.printStackTrace(); }

            @Override
            public void onResponse(Call call, Response response) {
                mActors = actorService.processResults(response);

                ActorActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new MovieListAdapter(getApplicationContext(), mActors);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(ActorActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                    }
                });

            }
        });
    }
}
