package com.example.android.thequizapp;



public class QuestionsLibrary {


/*
*
* Art Questions
*
* */


    private String mQuestion[] = {

            "who are you?",
            "what are you?",
            "fuck you?",
            "are you tziano mamo?",

    };


    private String mChoices[][] ={{"fi","mari","ele"}
,{"kota","pouli","psari"} ,{"yes","yeaah","no"} ,{"yeah","yeah","yeaah"}};


    private String mCorrectAns[] = {"fi","kota","yes","yeah"};

    // pairnw erwtisi apo pinaka
    public String getQuestion(int a){
        String question = mQuestion[a];
        return question;
    }

    public String getChoice1(int a){
        String choice0= mChoices[a][0];
        return choice0;
    }

    public String getChoice2(int a){
        String choice1= mChoices[a][1];
        return choice1;    }

    public String getChoice3(int a){
        String choice2= mChoices[a][2];
        return choice2;
    }

    public String getCorrectAnswer(int a){
        String answer= mCorrectAns[a];
        return answer;
    }

    public int getLength(){
        return mQuestion.length;
    }






}
