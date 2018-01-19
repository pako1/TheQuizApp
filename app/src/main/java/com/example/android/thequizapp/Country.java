package com.example.android.thequizapp;



public class Country {

    String image;
    String answer;


    public Country(){}

    public Country(String image,String answer) {
        this.image = image;
        this.answer = answer;
    }


    public String getCountryAnswer() {
        return answer;
    }

    public void setCountryAnswer(String answer) {
        this.answer = answer;
    }


    public void setImage(String image) {
        this.image = image;
    }


    public String getImage() {
        return image;
    }
}
