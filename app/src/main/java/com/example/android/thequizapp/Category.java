package com.example.android.thequizapp;

/**
 * Created by Πακος on 22/12/2017.
 */

public class Category {

    private String tittle;
    private String subtittle;
    private int image;

    public Category(String tittle, String subtittle, int image) {
        this.tittle = tittle;
        this.subtittle = subtittle;
        this.image = image;
    }


    public String getTittle() {
        return tittle;
    }

    public String getSubtittle() {
        return subtittle;
    }

    public int getImage() {
        return image;
    }



}
