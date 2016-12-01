package com.epicodus.movieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.movieTitleEditText) EditText mMovieTitleEditText;
    @Bind(R.id.movieTitleSearchButton) Button mMovieTitleSearchButton;
    @Bind(R.id.actorEditText) EditText mActorEditText;
    @Bind(R.id.actorSearchButton) Button mActorSearchButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mMovieTitleSearchButton.setOnClickListener(this);
        mActorSearchButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == mMovieTitleSearchButton) {
            String title = mMovieTitleEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, MovieActivity.class);
            intent.putExtra("title", title);
            startActivity(intent);
        }

        if(v == mActorSearchButton) {
            String actor = mActorEditText.getText().toString();
            Log.d("testing", actor);
            Intent intent = new Intent(MainActivity.this, ActorActivity.class);
            intent.putExtra("actor", actor);
            startActivity(intent);
        }
    }
}
