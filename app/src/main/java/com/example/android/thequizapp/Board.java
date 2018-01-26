/*
package com.example.android.thequizapp;



import java.util.Random;


public class Board {
    private static final String TAG ="Board" ;
    private  int[][] array=null;
    private  int row=0;
    private  int col =0;


    private int[][] dir={
            {0,1},
            {1,0},
            {0,-1},
            {-1,0}
    };

    private void createIntegerArray(int row,int col)
    {
        array=new int[row][col];
        int idx=0;
        for(int i=0;i<row;i++)
            for(int j=0;j<col;j++)
                array[i][j]=idx++;
    }


    private Point move(int srcX, int srcY, int xOffset, int yOffset)
    {
        int x=srcX+xOffset;
        int y=srcY+yOffset;
        if(x<0||y<0||x>=col||y>=row)
            return new Point(-1,-1);

        int temp=array[y][x];
        array[y][x]=array[srcY][srcX];
        array[srcY][srcX]=temp;

        return new Point(x,y);
    }


    private  Point getNextPoint(Point src)
    {
        Random rd=new Random();
        int idx=rd.nextInt(4);//产生0~3的随机数
        int xOffset=dir[idx][0];
        int yOffset=dir[idx][1];
        Point newPoint=move(src.getX(),src.getY(),xOffset,yOffset);
        if(newPoint.getX()!=-1&&newPoint.getY()!=-1) {
            return newPoint;
        }

        return getNextPoint(src);
    }


    public int[][] createRandomBoard(int row,int col)
    {
        if(row<2||col<2)
            throw new IllegalArgumentException("行和列都不能小于2");
        this.row=row;
        this.col=col;
        createIntegerArray(row,col);
        int count=0;
        Point tempPoint=new Point(col-1,row-1);
        Random rd=new Random();
        int num=rd.nextInt(100)+20;//产生20~119的随机数
        while (count<num)
        {
            tempPoint=getNextPoint(tempPoint);
            count++;
        }
        return  array;
    }

    public boolean isSuccess(int[][] arr)
    {
        int idx=0;

        for(int i=0;i<arr.length;i++)
        {
            for(int j=0;j<arr[i].length&&idx<row*col-1;j++)
            {

                if(arr[idx/row][idx%col]>arr[(idx+1)/row][(idx+1)%col])
                {

                    return false;

                }
                idx++;
            }

        }
        return  true;
    }
}*/
