package com.example.android.thequizapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class MyDataBaseHelper extends SQLiteOpenHelper {


    public static String DATABASE_QUESTION = "questions.db"; // database name

    private static final String TABLE_QUESTION = "QuestionBank";
    private static final String KEY_ID = "ID";
    private static final String QUESTION = "Question";
    private static final String ANSWER = "Answer";
    private static final String CHOICE1 = "Choice1";
    private static final String CHOICE2 = "Choice2";
    private static final String CHOICE3 = "Choice3";
    private static final String CHOICE4 = "Choice4";



    //Query for creating table.
    private static final String CREATE_TABLE_QUESTION = "CREATE TABLE " + TABLE_QUESTION + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + QUESTION + " TEXT,"
            + CHOICE1 + " TEXT" + CHOICE2 + " TEXT" + CHOICE3 + " TEXT" + CHOICE4 + " TEXT" + ANSWER + " TEXT" + ")";





    public MyDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_QUESTION);  // Creates the table.
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + CREATE_TABLE_QUESTION);
        onCreate(sqLiteDatabase);

    }
}
