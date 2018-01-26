package com.example.android.thequizapp;

import android.content.Intent;
import android.os.Bundle;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Random;

public class PuzzleActivity extends BaseClass {

    PuzzleGame tpGame = new PuzzleGame();
    int moves = 0;
    int resource = 0;
    TextView Skip;
    ImageButton home;
    TextView score;
    TextView info;
    int mScore = 300;
    int skiptimes = 4;
    Button unsolve;

    public ImageButton[][] buttonArray = new ImageButton[PuzzleGame.BOARD_ROW][PuzzleGame.BOARD_COL];

    /*Both xVal and yVal are meant to hold the actual x and y
     *coordinates of the buttons on the screen
     */
    private float[][] xVal = new float[PuzzleGame.BOARD_ROW][PuzzleGame.BOARD_COL];
    private float[][] yVal = new float[PuzzleGame.BOARD_ROW][PuzzleGame.BOARD_COL];

    /*tempButtonArray and numArray are used as part of the
     *shuffling process in order to shuffle the order of the buttons*/

    ImageButton[] tempButtonArray = new ImageButton[PuzzleGame.BOARD_SIZE];
    int[] numArray = new int[PuzzleGame.BOARD_SIZE];

    /*buttonPressRow and buttonPressCol are meant to map
     *button ID's to their row and column within the buttonArray*/

    HashMap<Integer, Integer> buttonPressRow = new HashMap<Integer, Integer>();
    HashMap<Integer, Integer> buttonPressCol = new HashMap<Integer, Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);
        setup();
        initializeButtonArray();

        final TypedArray imgs = getResources().obtainTypedArray(R.array.flags);
        final Random rand = new Random();
        final int rndInt = rand.nextInt(imgs.length());
        resource = imgs.getResourceId(rndInt, 0);

        addImageToPuzzle(resource);
        disableAllButtons();


        Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound();
                skipPuzzle();

            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound();
                Intent returnIntent = new Intent(PuzzleActivity.this, MenuActivity.class);
                startActivity(returnIntent);
                finish();
            }
        });

        unsolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (skiptimes==0){
                    unsolvableScore();
                    goToScore();
                }
                else if (skiptimes>0){
                    Toast.makeText(PuzzleActivity.this,"You have more skips",Toast.LENGTH_SHORT).show();
                }
            }
        });




    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        /*Both xVal and yVal are initialized here instead of
		 *onCreate because UI is not drawn yet in onCreate thus
		 *you cannot get the coordinates of buttons*/
        for (int i = 0; i < PuzzleGame.BOARD_ROW; i++) {
            for (int j = 0; j < PuzzleGame.BOARD_COL; j++) {
                xVal[i][j] = buttonArray[i][j].getX();
                yVal[i][j] = buttonArray[i][j].getY();
            }
        }

        shuffle();
    }

    public void enableAllButtons() {
        for (int i = 0; i < PuzzleGame.BOARD_ROW; i++) {
            for (int j = 0; j < PuzzleGame.BOARD_COL; j++) {
                buttonArray[i][j].setEnabled(true);
            }
        }
    }

    public void disableAllButtons() {

        for (int i = 0; i < PuzzleGame.BOARD_ROW; i++) {
            for (int j = 0; j < PuzzleGame.BOARD_COL; j++) {
                buttonArray[i][j].setEnabled(false);
            }
        }
    }

    public void addImageToPuzzle(int resource) {

        Bitmap bmap = BitmapFactory.decodeResource(getResources(), resource);
        Bitmap bMapScaled = Bitmap.createScaledBitmap(bmap, 750, 750, true);

        Bitmap[] bMapArray = new Bitmap[PuzzleGame.BOARD_SIZE];

		/*Here the scaled 750x750 image is being split up into 9
		 *250x250 blocks, these blocks are then set as the image on
		 *the various buttons*/

        bMapArray[0] = Bitmap.createBitmap(bMapScaled, 0, 0, 250, 250);
        bMapArray[1] = Bitmap.createBitmap(bMapScaled, 250, 0, 250, 250);
        bMapArray[2] = Bitmap.createBitmap(bMapScaled, 500, 0, 250, 250);
        bMapArray[3] = Bitmap.createBitmap(bMapScaled, 0, 250, 250, 250);
        bMapArray[4] = Bitmap.createBitmap(bMapScaled, 250, 250, 250, 250);
        bMapArray[5] = Bitmap.createBitmap(bMapScaled, 500, 250, 250, 250);
        bMapArray[6] = Bitmap.createBitmap(bMapScaled, 0, 500, 250, 250);
        bMapArray[7] = Bitmap.createBitmap(bMapScaled, 250, 500, 250, 250);
        bMapArray[8] = Bitmap.createBitmap(bMapScaled, 500, 500, 250, 250);

        //Leave first button empty and fill out rest of buttons with images
        buttonArray[0][1].setImageBitmap(bMapArray[1]);
        buttonArray[0][2].setImageBitmap(bMapArray[2]);
        buttonArray[1][0].setImageBitmap(bMapArray[3]);
        buttonArray[1][1].setImageBitmap(bMapArray[4]);
        buttonArray[1][2].setImageBitmap(bMapArray[5]);
        buttonArray[2][0].setImageBitmap(bMapArray[6]);
        buttonArray[2][1].setImageBitmap(bMapArray[7]);
        buttonArray[2][2].setImageBitmap(bMapArray[8]);

    }


    public void initializeButtonArray() {
        buttonArray[0][0] = findViewById(R.id.zero);
        buttonArray[0][1] = findViewById(R.id.one);
        buttonArray[0][2] = findViewById(R.id.two);
        buttonArray[1][0] = findViewById(R.id.three);
        buttonArray[1][1] = findViewById(R.id.four);
        buttonArray[1][2] = findViewById(R.id.five);
        buttonArray[2][0] = findViewById(R.id.six);
        buttonArray[2][1] = findViewById(R.id.seven);
        buttonArray[2][2] = findViewById(R.id.eight);


    }

    public void shuffle() {

        moves = 0;

        //enable the tiles so they can be clicked
        enableAllButtons();

        initializeButtonArray();

        final TypedArray imgs = getResources().obtainTypedArray(R.array.flags);
        final Random randResource = new Random();
        final int rndInt = randResource.nextInt(imgs.length());
        resource = imgs.getResourceId(rndInt, 0);

        addImageToPuzzle(resource);

        //clear hashmaps denoting relationship between button and location
        buttonPressRow.clear();
        buttonPressCol.clear();

        initializeTempArrays();

        //shuffle array
        Random rand = new Random();
        for (int i = 0; i < PuzzleGame.BOARD_SIZE; i++) {
            int randomPos = rand.nextInt(PuzzleGame.BOARD_SIZE);
            int temp = numArray[i];
            ImageButton tempButton = tempButtonArray[i];
            numArray[i] = numArray[randomPos];
            tempButtonArray[i] = tempButtonArray[randomPos];
            numArray[randomPos] = temp;
            tempButtonArray[randomPos] = tempButton;
        }

        //set buttons in random order and update UI accordingly
        int tempIndex = 0;
        for (int i = 0; i < PuzzleGame.BOARD_ROW; i++) {
            for (int j = 0; j < PuzzleGame.BOARD_COL; j++) {
                tpGame.mBoard[i][j] = numArray[tempIndex];
                buttonArray[i][j] = tempButtonArray[tempIndex];

                if (tpGame.mBoard[i][j] == 0) {
                    tpGame.setEmptySpaceRow(i);
                    tpGame.setEmptySpaceCol(j);
                }

                tempIndex++;

                buttonPressRow.put(buttonArray[i][j].getId(), i);
                buttonPressCol.put(buttonArray[i][j].getId(), j);

                buttonArray[i][j].setX(xVal[i][j]);
                buttonArray[i][j].setY(yVal[i][j]);
            }
        }

    }


    public void resetImages() {
        moves = 0;
        buttonArray[0][0].setImageBitmap(null);
        buttonArray[0][1].setImageBitmap(null);
        buttonArray[0][2].setImageBitmap(null);
        buttonArray[1][0].setImageBitmap(null);
        buttonArray[1][1].setImageBitmap(null);
        buttonArray[1][2].setImageBitmap(null);
        buttonArray[2][0].setImageBitmap(null);
        buttonArray[2][1].setImageBitmap(null);
        buttonArray[2][2].setImageBitmap(null);
    }


    public void setup() {
        unsolve = findViewById(R.id.unsolve);
        Skip   = findViewById(R.id.skipPuzzle);
        home   = findViewById(R.id.Puzzlehome);
        score  = findViewById(R.id.puzzle_score300);
        info   = findViewById(R.id.info);

    }

    public void skipPuzzle() {
        if (skiptimes > 0) {
            skiptimes--;
            mScore = Integer.parseInt(score.getText().toString());
            mScore = mScore - 15;
            score.setText(String.valueOf(mScore));
            if(Integer.parseInt(score.getText().toString())<0){
                score.setText(String.valueOf(0));
            }
            if (score.getText().toString().equals("0") ) {
                goToScore();
            }
            resetImages();
            shuffle();
        } else {
            Toast.makeText(PuzzleActivity.this, "You have used all your skips", Toast.LENGTH_SHORT).show();
        }

    }

    private void goToScore() {
        if(mScore<0){
            mScore = 0;
        }
        Intent scoreIntent = new Intent(PuzzleActivity.this, ScoreActivity.class);
        scoreIntent.putExtra("score", mScore);
        scoreIntent.putExtra("position", 3);
        startActivity(scoreIntent);
        finish();
    }


    public void checkIfGameOver() {
        if (tpGame.isComplete()) {
            goToScore();
        }
    }

    public void initializeTempArrays() {
		/*initialize both temp arrays with integers/buttons
		 *0 to boardSize*/

        for (int i = 0; i < numArray.length; i++) {
            numArray[i] = i;
        }

        tempButtonArray[0] = buttonArray[0][0];
        tempButtonArray[1] = buttonArray[0][1];
        tempButtonArray[2] = buttonArray[0][2];
        tempButtonArray[3] = buttonArray[1][0];
        tempButtonArray[4] = buttonArray[1][1];
        tempButtonArray[5] = buttonArray[1][2];
        tempButtonArray[6] = buttonArray[2][0];
        tempButtonArray[7] = buttonArray[2][1];
        tempButtonArray[8] = buttonArray[2][2];
    }

    public void updateMap(int id, int row, int col) {
        buttonPressRow.put(id, PuzzleGame.emptySpaceRow);
        buttonPressCol.put(id, PuzzleGame.emptySpaceCol);
        buttonPressRow.put(R.id.zero, row);
        buttonPressCol.put(R.id.zero, col);
    }

    public void UISwap(int row, int col) {
        buttonArray[row][col].setX(xVal[PuzzleGame.emptySpaceRow][PuzzleGame.emptySpaceCol]);
        buttonArray[row][col].setY(yVal[PuzzleGame.emptySpaceRow][PuzzleGame.emptySpaceCol]);
        buttonArray[PuzzleGame.emptySpaceRow][PuzzleGame.emptySpaceCol].setX(xVal[row][col]);
        buttonArray[PuzzleGame.emptySpaceRow][PuzzleGame.emptySpaceCol].setY(yVal[row][col]);
    }

    public void buttonArraySwap(int row, int col) {
        ImageButton temp = buttonArray[row][col];
        buttonArray[row][col] = buttonArray[PuzzleGame.emptySpaceRow][PuzzleGame.emptySpaceCol];
        buttonArray[PuzzleGame.emptySpaceRow][PuzzleGame.emptySpaceCol] = temp;
    }



    public void unsolvableScore(){
        mScore = Integer.parseInt(score.getText().toString());
        mScore = 30;
    }
    public void calculateScore() {

        moves++;
        info.setText(String.valueOf(moves));

        mScore = Integer.parseInt(score.getText().toString());
        mScore = mScore - 2;
        score.setText(String.valueOf(mScore));
        if(Integer.parseInt(score.getText().toString())<0){
            score.setText(String.valueOf(0));
        }
        if (score.getText().toString().equals("0") ) {
            goToScore();
        }
    }


    public void ButtonOnClick(View v) {
        int buttonRow;
        int buttonCol;

        if (v.getId() != R.id.zero) {
            buttonRow = buttonPressRow.get(v.getId());
            buttonCol = buttonPressCol.get(v.getId());

            if (tpGame.isAdjacent(buttonRow, buttonCol)) {

                //update button mappings
                updateMap(v.getId(), buttonRow, buttonCol);

                //switch button locations on UI
                UISwap(buttonRow, buttonCol);

                //switch button locations in array to maintain synchronization
                buttonArraySwap(buttonRow, buttonCol);

                //switch in underlying integer array to maintain synchronization
                tpGame.setMove(buttonRow, buttonCol);
                tpGame.setEmptySpaceRow(buttonRow);
                tpGame.setEmptySpaceCol(buttonCol);

                calculateScore();
                checkIfGameOver();
            }
        }
    }
}

