package com.example.android.thequizapp;

import android.content.Context;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class QuestionBank{

    // declare list of Question objects
    private List<Question> list = new ArrayList<>();
    private DataBaseHelper myDataBaseHelper;


    // method returns number of questions in list
    public int getLength(){
        return list.size();
    }

    // method returns question from list based on list index
    public String getQuestion(int a) {
        return list.get(a).getQuestion();
    }

    // method return a single multiple choice item for question based on list index,
    // based on number of multiple choice item in the list - 1, 2, 3 or 4
    // as an argument
    public String getChoice(int index, int num) {
        return list.get(index).getChoice(num-1);
    }

    //  method returns correct answer for the question based on list index
    public String getCorrectAnswer(int a) {
        return list.get(a).getAnswer().trim();
    }



    public void initQuestions(Context context) {


        myDataBaseHelper = new DataBaseHelper(context);

        try {

            myDataBaseHelper.createDataBase();
        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {

            myDataBaseHelper.openDataBase();

        }catch(SQLException sqle){

            throw new Error("couldnt open database");

        }

        //get questions/choices/answers from database
        list = myDataBaseHelper.getAllQuestionsList();

    }




}
