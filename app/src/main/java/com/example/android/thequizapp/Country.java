package com.example.android.thequizapp;


public class Country {

    String image;
    String answer;

    Country() {
    }

    public Country(String image, String answer) {
        this.image = image;
        this.answer = answer;
    }

    String getCountryAnswer() {
        return answer;
    }

    void setCountryAnswer(String answer) {
        this.answer = answer;
    }


    public void setImage(String image) {
        this.image = image;
    }


    public String getImage() {
        return image;
    }
}
