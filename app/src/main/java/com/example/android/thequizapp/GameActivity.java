package com.example.android.thequizapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import java.util.ArrayList;
import java.util.List;


public class GameActivity extends BaseClass {


    RecyclerView recyclerView;
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
                new Category(getString(R.string.Arts), getString(R.string.artknowledge), R.drawable.categoryarts, true)
        );

        categoriesList.add(new Category(getString(R.string.Geography), getString(R.string.geographyKnowledge), R.drawable.categorygeography, true)
        );

        categoriesList.add(new Category(getString(R.string.Technology), getString(R.string.GeekKnowledge), R.drawable.categorytechnology, false)
        );

        categoriesList.add(new Category(getString(R.string.Mytholohgy), getString(R.string.mythologyknowledge), R.drawable.categorymythology, false)
        );


        categoriesList.add(new Category(getString(R.string.History), getString(R.string.historyknowledge), R.drawable.categoryhistory, false)
        );

        categoriesList.add(new Category(getString(R.string.CategoryMusic), getString(R.string.musicknowledge), R.drawable.categorymusic, false)
        );

        categoriesList.add(new Category(getString(R.string.Sports), getString(R.string.sportknowledge), R.drawable.sports, false)
        );

        adapter = new CategoriesAdapter(this, categoriesList);
        runAnimation(recyclerView,0);



        adapter.setOnItemClickListener(new CategoriesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                clickSound();
                if (position == 0) {
                    Intent Quizintent = new Intent(getApplicationContext(), QuizActivity.class);
                    Quizintent.putExtra("position", position);
                    startActivity(Quizintent);
                } else if (position == 1) {

                    Intent LogoIntent = new Intent(getApplicationContext(), FlagActivity.class);
                    LogoIntent.putExtra("position", position);
                    startActivity(LogoIntent);

                } else if (position == 2) {

                    Intent Quizintent = new Intent(getApplicationContext(), MultipleChActivity.class);
                    Quizintent.putExtra("position", position);
                    startActivity(Quizintent);

                } else if (position == 3) {
                    Intent Quizintent = new Intent(getApplicationContext(), PuzzleActivity.class);
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

    private void runAnimation(RecyclerView recyclerView, int type) {
        Context context = recyclerView.getContext();
        LayoutAnimationController controller;

            controller = AnimationUtils.loadLayoutAnimation(context,R.anim.layout_falldown);

             recyclerView.setAdapter(adapter);
             recyclerView.getAdapter().notifyDataSetChanged();
             recyclerView.setLayoutAnimation(controller);
             recyclerView.scheduleLayoutAnimation();


    }


    @Override
    protected void onPause() {
        super.onPause();
        basePause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Music();
        Sound();
    }


}
