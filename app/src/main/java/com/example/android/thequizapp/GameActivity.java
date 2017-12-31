package com.example.android.thequizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Πακος on 20/12/2017.
 */

public class GameActivity extends BaseClass {


    RecyclerView recyclerView; // antikeimeno poy exei to recyclerview (layout)
    CategoriesAdapter adapter;
    List<Category> categoriesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        categoriesList.add(
                 new Category("Arts", "Test your knowledge\n about arts", R.drawable.categoryarts,true)
        );
        categoriesList.add(new Category("Technology", "Are you a real geek?\nprove it!!!", R.drawable.categorytechnology,true)
        );

        categoriesList.add(new Category("Mytholohgy", "May the gods be with you", R.drawable.categorymythology,false)
        );

        categoriesList.add(new Category("Geography", "Let's discover the world", R.drawable.categorygeography,false)
        );

        categoriesList.add(new Category("History", "I'm sure you don't rembember\nthat much", R.drawable.categoryhistory,false)
        );

        categoriesList.add(new Category("Music", "Let's rock or pop ", R.drawable.categorymusic,false)
        );

        categoriesList.add(new Category("Sports", "Muscles and Sports", R.drawable.sports,false)
        );

        adapter = new CategoriesAdapter(this, categoriesList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new CategoriesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                clickSound();
                if (position == 0) {
                    Intent Quizintent = new Intent(getApplicationContext(), QuizActivity.class);
                    Quizintent.putExtra("position", position);
                    startActivity(Quizintent);

                } else if (position == 1) {
                    Intent Quizintent = new Intent(getApplicationContext(), QuizActivity.class);

                    Quizintent.putExtra("position", position);
                    startActivity(Quizintent);
                } else if (position == 2) {
                    Intent Quizintent = new Intent(getApplicationContext(), QuizActivity.class);
                    Quizintent.putExtra("position", position);
                    startActivity(Quizintent);
                } else if (position == 3) {
                    Intent Quizintent = new Intent(getApplicationContext(), QuizActivity.class);
                    Quizintent.putExtra("position", position);
                    startActivity(Quizintent);
                } else if (position == 4) {
                    Intent Quizintent = new Intent(getApplicationContext(), QuizActivity.class);
                    Quizintent.putExtra("position", position);
                    startActivity(Quizintent);
                } else if (position == 5) {
                    Intent Quizintent = new Intent(getApplicationContext(), QuizActivity.class);
                    Quizintent.putExtra("position", position);
                    startActivity(Quizintent);
                } else if (position == 6) {
                    Intent Quizintent = new Intent(getApplicationContext(), QuizActivity.class);
                    Quizintent.putExtra("position", position);
                    startActivity(Quizintent);
                }


            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        basePause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        baseResume();
    }


}
