package com.example.android.thequizapp;



class QuestionsLibrary {



    private String mQuestion[] = {

            "who are you?",
            "what are you?",
            "you?",
            "are you tziano mamo?",
            "people are insane?",
            "where are you from?"

    };


    private String mChoices[][] ={{"fi","mari","ele","koko"}
,{"kota","pouli","psari","keftes"} ,{"yes","yeaah","no","not sure"} ,{"yeah","yeah","yeaah","why not"},{"no","sure","yeaah","legend"},{"greek","german","pak","fisf"}};


    private String mCorrectAns[] = {"fi","kota","yes","yeah","no","greek"};

    // pairnw erwtisi apo pinaka
    public String getQuestion(int a){
        return  mQuestion[a];
    }

    public String getChoice1(int a){
        return mChoices[a][0];
    }

    public String getChoice2(int a){
        return mChoices[a][1];    }

    public String getChoice3(int a){
        return  mChoices[a][2];
    }

    public String getChoice4(int a){
        return  mChoices[a][3];
    }

    public String getCorrectAnswer(int a){
        return mCorrectAns[a];
    }

    public int getLength(){
        return mQuestion.length;
    }






}
