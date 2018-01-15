package com.example.android.thequizapp;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import junit.framework.Assert;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    //The Android's default system path of application database.
    private static String DB_PATH = "/data/data/com.example.android.thequizapp/databases/";


    private SQLiteDatabase myDataBase;
    private final Context myContext;
    private static final String QUESTION = "question";
    private static final String CHOICE1 = "choice1";
    private static final String CHOICE2 = "choice2";
    private static final String CHOICE3 = "choice3";
    private static final String CHOICE4 = "choice4";
    private static final String ANSWER = "answer";
    private static final String TABLE_QUESTION = "QuestionBank";



    public DataBaseHelper(Context context) {
        super(context, context.getResources().getString(R.string.db_name), null, 1);
        this.myContext = context;

    }

    /**
     * Creates a empty database on the system and rewrites it with  a database.
     */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (!dbExist) {
            //By calling this method and empty database will be created into the default system path
            // application so we are gonna be able to overwrite that database with a database.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + myContext.getString(R.string.db_name);
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {

            //database does't exist yet.

        }

        if (checkDB != null) {

            checkDB.close();

        }


        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {

        //Open  local db as the input stream
        InputStream myInput = myContext.getAssets().open(myContext.getResources().getString(R.string.db_name));

        // Path to the just created empty db
        String outFileName = DB_PATH + myContext.getString(R.string.db_name);

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + myContext.getString(R.string.db_name);
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//         sqLiteDatabase.execSQL(TABLE_QUESTION); // create question table

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
           sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLE_QUESTION + "'"); //drop table
          // onCreate(sqLiteDatabase);
    }





    // public helper methods to access and get content from the database.
    public List<Question> getAllQuestionsList() {
        List<Question> questionArrayList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_QUESTION;
        Log.i("Table name",TABLE_QUESTION);
        Cursor c = sqLiteDatabase.rawQuery(selectQuery, null);

        // looping through all records and adding to the list
        if (c.moveToFirst()) {
            do {
                Question question = new Question();

                String questText= c.getString(c.getColumnIndex(QUESTION)).trim();
                question.setQuestion(questText);

                String choice1Text= c.getString(c.getColumnIndex(CHOICE1)).trim();
                question.setChoice(0,choice1Text);

                String choice2Text= c.getString(c.getColumnIndex(CHOICE2)).trim();
                question.setChoice(1,choice2Text);

                String choice3Text= c.getString(c.getColumnIndex(CHOICE3)).trim();
                question.setChoice(2,choice3Text);

                String choice4Text= c.getString(c.getColumnIndex(CHOICE4)).trim();
                question.setChoice(3,choice4Text);

                String answerText= c.getString(c.getColumnIndex(ANSWER)).trim();
                question.setAnswer(answerText);

                // adding to Questions list
                questionArrayList.add(question);
            } while (c.moveToNext());
            Collections.shuffle(questionArrayList);
        }
        return questionArrayList;
    }



}
