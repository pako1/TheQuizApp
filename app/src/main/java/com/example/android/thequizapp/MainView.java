/*
package com.example.android.thequizapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import java.io.IOException;
import java.io.InputStream;


public class MainView extends View {
    private  Context context;
    private  Bitmap back;
    private  Paint paint;
    private  int tileWidth;
    private int tileHeight;
    private Bitmap[] bitmapTiles;
    private int[][] dataTiles;
    private Board tilesBoard;
    private final int COL=3;
    private final int ROW=3;
    private  boolean isSuccess;
    private int[][] dir={
            {-1,0},
            {0,-1},
            {1,0},
            {0,1}
    };
    Dialog myDialog;
    Button btnOk;
    Button restart;


    public MainView(Context context)
    {
        super(context);
        this.context=context;
        paint=new Paint();
        paint.setAntiAlias(true);
        init();
        startGame();
        myDialog = new Dialog(context);
        log(PuzzleActivity.getScreenWidth()+","+ PuzzleActivity.getScreenHeight());
    }


    private  void init()
    {
        AssetManager assetManager= context.getAssets();
        try {
            InputStream assetInputStream=assetManager.open("big.jpg");
            Bitmap bitmap=BitmapFactory.decodeStream(assetInputStream);
            back=Bitmap.createScaledBitmap(bitmap, PuzzleActivity.getScreenWidth(), PuzzleActivity.getScreenHeight(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        tileWidth=back.getWidth()/COL;
        tileHeight=back.getHeight()/ROW;
        bitmapTiles =new Bitmap[COL*ROW];
        int idx=0;
        for(int i=0;i<ROW;i++)
        {
            for(int j=0;j<COL;j++)
            {
                bitmapTiles[idx++]=Bitmap.createBitmap(back,j*tileWidth,i*tileHeight,tileWidth,tileHeight);
            }
        }
    }


    private void startGame()
    {
        tilesBoard =new Board();
        dataTiles= tilesBoard.createRandomBoard(ROW,COL);
        isSuccess=false;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GRAY);
        for(int i=0;i<ROW;i++) {
            for (int j = 0; j < COL; j++) {
                int idx=dataTiles[i][j];
                if(idx==ROW*COL-1&&!isSuccess)
                    continue;
                canvas.drawBitmap(bitmapTiles[idx],j*tileWidth,i*tileHeight,paint);
            }
        }
    }


    private Point xyToIndex(int x,int y)
    {
        int extraX=x%tileWidth>0?1:0;
        int extraY=x%tileWidth>0?1:0;
        int col=x/tileWidth+extraX;
        int row=y/tileHeight+extraY;

        return new Point(col-1,row-1);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN) {
            Point point = xyToIndex((int) event.getX(), (int) event.getY());

            for(int i=0;i<dir.length;i++)
            {
                int newX=point.getX()+dir[i][0];
                int newY=point.getY()+dir[i][1];

                if(newX>=0&&newX<COL&&newY>=0&&newY<ROW){
                    if(dataTiles[newY][newX]==COL*ROW-1)
                    {
                        int temp=dataTiles[point.getY()][point.getX()];
                        dataTiles[point.getY()][point.getX()]=dataTiles[newY][newX];
                        dataTiles[newY][newX]=temp;
                        invalidate();
                        if(tilesBoard.isSuccess(dataTiles)){
                            isSuccess=true;
                            invalidate();
                            showDialog();
                        }
                    }
                }
            }
        }
        return true;
    }

    private void showDialog() {
        myDialog.setContentView(R.layout.custom_dialog_puzzle);
        restart = myDialog.findViewById(R.id.restart);
        btnOk   = myDialog.findViewById(R.id.ok);


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            goToScore();
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                startGame();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
        myDialog.setCancelable(false);

    }



    private  void log(String log){
        System.out.println("------------------->MainView:"+log);
    }


    private void goToScore(){
        Intent scoreIntent= new Intent(getContext(),ScoreActivity.class);
        scoreIntent.putExtra("score",100);
        scoreIntent.putExtra("position",3);
        getContext().startActivity(scoreIntent);
    }

}*/
