package com.example.android.thequizapp;

class PuzzleGame {

    int[][] mBoard;
    static final int BOARD_ROW = 3;
    static final int BOARD_COL = 3;
    static final int BOARD_SIZE = BOARD_ROW * BOARD_COL;
    static int emptySpaceRow;
    static int emptySpaceCol;

    PuzzleGame() {
        int counter = 0;
        mBoard = new int[BOARD_ROW][BOARD_COL];
        for (int i = 0; i < BOARD_ROW; i++) {
            for (int j = 0; j < BOARD_COL; j++) {
                mBoard[i][j] = counter;
                counter++;
            }
        }
    }

    void setEmptySpaceRow(int num) {
        emptySpaceRow = num;
    }

    void setEmptySpaceCol(int num) {
        emptySpaceCol = num;
    }

    void setMove(int moveRow, int moveCol) {

        boolean moveMade = false;

        if (moveRow - 1 >= 0) {
            //check if empty space is above
            if ((moveRow - 1) == emptySpaceRow && moveCol == emptySpaceCol) {
                //Exchange values
                mBoard[emptySpaceRow][emptySpaceCol] = mBoard[moveRow][moveCol];
                mBoard[moveRow][moveCol] = 0;
                moveMade = true;
            }
        }
        if (moveRow + 1 <= BOARD_ROW - 1 && !moveMade) {
            //check if empty space is below
            if ((moveRow + 1) == emptySpaceRow && moveCol == emptySpaceCol) {
                //Exchange values
                mBoard[emptySpaceRow][emptySpaceCol] = mBoard[moveRow][moveCol];
                mBoard[moveRow][moveCol] = 0;
                moveMade = true;
            }
        }
        if (moveCol + 1 <= BOARD_COL - 1 && !moveMade) {
            //check if empty space is to the right
            if (moveRow == emptySpaceRow && moveCol + 1 == emptySpaceCol) {
                //Exchange values
                mBoard[emptySpaceRow][emptySpaceCol] = mBoard[moveRow][moveCol];
                mBoard[moveRow][moveCol] = 0;
                moveMade = true;
            }
        }
        if (moveCol - 1 >= 0 && !moveMade) {
            //check if empty space is to the left
            if (moveRow == emptySpaceRow && moveCol - 1 == emptySpaceCol) {
                //Exchange values
                mBoard[emptySpaceRow][emptySpaceCol] = mBoard[moveRow][moveCol];
                mBoard[moveRow][moveCol] = 0;
                moveMade = true;
            }
        }

    }

    boolean isAdjacent(int row, int col) {
        if (row - 1 >= 0) {
            //check if empty space is above
            if ((row - 1) == emptySpaceRow && col == emptySpaceCol) {
                return true;
            }
        }
        if (row + 1 <= BOARD_ROW - 1) {
            //check if empty space is below
            if ((row + 1) == emptySpaceRow && col == emptySpaceCol) {
                return true;
            }
        }
        if (col + 1 <= BOARD_COL - 1) {
            //check if empty space is to the right
            if (row == emptySpaceRow && col + 1 == emptySpaceCol) {
                return true;
            }
        }
        if (col - 1 >= 0) {
            //check if empty space is to the left
            if (row == emptySpaceRow && col - 1 == emptySpaceCol) {
                return true;
            }
        }
        return false;
    }

    boolean isComplete() {
        int counter = 0;
        for (int i = 0; i < BOARD_ROW; i++) {
            for (int j = 0; j < BOARD_COL; j++) {
                if (mBoard[i][j] != counter) return false;
                counter++;
            }
        }
        return true;
    }


}