package net.danielonline.Essentials.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q {
    public static String Random;

    static {
        // https://stackoverflow.com/a/31193401/10160296
        List myList = new ArrayList();

        // populate the list
        myList.add("\"Every man has his secret sorrows which the world knows not; and often times we call a man cold when he is only sad.\" -Henry Wadsworth Longfellow");
        myList.add("\"I came, I saw, I conquered.\" - Julius Ceasar");
        myList.add("\"Our greatest glory is not in never falling, but in rising every time we fall.\" - Confucius");
        myList.add("\"History will be kind for me for I intend to write it.\" - Winston Churchill");
        myList.add("\"If you are neutral in situations of injustice, you have chosen the side of the oppressor. If an elephant has its foot on the tail of a mouse and you say that you are neutral, the mouse will not appreciate your neutrality.\" - Desmond Tutu");
        myList.add("\"History is a relentless master. It has no present, only the past rushing into the future. To try to hold fast is to be swept aside.\" - John F. Kennedy");
        myList.add("\"Those who do not remember the past are condemned to repeat it.\" - George Santayana"); // plan: make a random quote appear on startup
        myList.add("\"A pint of sweat, saves a gallon of blood.\" - George S. Patton");
        myList.add("\"History will be kind for me for I intend to write it.\" - Winston Churchill");
        myList.add("E");
        myList.add("\"Every man has his secret sorrows which the world knows not; and often times we call a man cold when he is only sad.\" -Henry Wadsworth Longfellow");
        myList.add("\"I came, I saw, I conquered.\" - Julius Ceasar");
        myList.add("\"Our greatest glory is not in never falling, but in rising every time we fall.\" - Confucius");
        myList.add("\"History will be kind for me for I intend to write it.\" - Winston Churchill");
        myList.add("E");
        myList.add("\"Every man has his secret sorrows which the world knows not; and often times we call a man cold when he is only sad.\" -Henry Wadsworth Longfellow");
        myList.add("\"I came, I saw, I conquered.\" - Julius Ceasar");
        myList.add("\"Our greatest glory is not in never falling, but in rising every time we fall.\" - Confucius");
        myList.add("\"History will be kind for me for I intend to write it.\" - Winston Churchill");
        myList.add("E");
        myList.add("\"Every man has his secret sorrows which the world knows not; and often times we call a man cold when he is only sad.\" -Henry Wadsworth Longfellow");
        myList.add("\"I came, I saw, I conquered.\" - Julius Ceasar");
        myList.add("\"Our greatest glory is not in never falling, but in rising every time we fall.\" - Confucius");
        myList.add("\"History will be kind for me for I intend to write it.\" - Winston Churchill");
        myList.add("E");
        myList.add("\"Every man has his secret sorrows which the world knows not; and often times we call a man cold when he is only sad.\" -Henry Wadsworth Longfellow");
        myList.add("\"I came, I saw, I conquered.\" - Julius Ceasar");
        myList.add("\"Our greatest glory is not in never falling, but in rising every time we fall.\" - Confucius");
        myList.add("\"History will be kind for me for I intend to write it.\" - Winston Churchill");
        myList.add("E");


        // shuffle the list
        Collections.shuffle(myList);

        System.out.println("Collection after shuffle: "+ myList);
    }
}
