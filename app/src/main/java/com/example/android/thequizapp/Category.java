package com.example.android.thequizapp;


public class Category {

    private String tittle;
    private String subtittle;
    private int image;
    private boolean isImageChanged;


    public Category(String tittle, String subtittle, int image, boolean isImageChanged) {
        this.tittle = tittle;
        this.subtittle = subtittle;
        this.image = image;
        this.isImageChanged = isImageChanged;
    }

    public boolean isImageChanged() {
        return isImageChanged;
    }

    public void setImageChanged(boolean imageChanged) {
        isImageChanged = imageChanged;
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
