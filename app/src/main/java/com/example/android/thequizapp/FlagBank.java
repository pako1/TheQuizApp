package com.example.android.thequizapp;


import android.content.Context;
import android.database.SQLException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class FlagBank {

    // declare list of Flag objects
    private List<Country> list = new ArrayList<>();
    private DataBaseHelper myDataBaseHelper;


    // method returns number of countries in list
    int getFlagLength() {
        return list.size();
    }

    // method returns country flags from list based on list index
    String getCountryImage(int a) {
        return list.get(a).getImage();
    }

    //  method returns correct answer for the country based on list index
    String getFlagAnswer(int a) {
        return list.get(a).getCountryAnswer();
    }


    void initCountries(Context context) {

        myDataBaseHelper = new DataBaseHelper(context);

        try {

            myDataBaseHelper.createDataBase();
        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {

            myDataBaseHelper.openDataBase();

        } catch (SQLException sqle) {

            throw new Error("couldnt open database");

        }
        //get questions/choices/answers from database
        list = myDataBaseHelper.getAllCountriesList();

    }
}

