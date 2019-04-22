package com.example.fragments;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Vocabluari {

    private static int RESULT = 0;
    private ArrayList value ;
    private ArrayList translete ,RightWord;

    public String work(ArrayList value, final ArrayList translete, TextView right_word, final Button FalseWordOne, final Button FalseWordTwo, final Button FalseWordThree) {

        ArrayList false_word = new ArrayList();

        Random random = new Random();
        int item =  random.nextInt(value.size());
        final int word_index = value.indexOf(value.get(item)); //отримали індекс нашого рандомного слова

        false_word.add(0, translete.get(word_index));

        int r = random.nextInt(translete.size());
        int d = random.nextInt(translete.size());



        Log.d("Fab", String.valueOf(r));
        Log.d("Fab", String.valueOf(d));



            if(translete.get(r) != translete.get(word_index) && translete.get(d) != translete.get(word_index)){
                false_word.add(1,translete.get(r));
                false_word.add(2,translete.get(d));

            }


        Collections.shuffle(false_word);

        right_word.setText((CharSequence) value.get(word_index));
        try {
            FalseWordOne.setText((CharSequence) false_word.get(0));
            FalseWordTwo.setText((CharSequence) false_word.get(1));
            FalseWordThree.setText((CharSequence) false_word.get(2));
        }
        catch (IndexOutOfBoundsException e){
           r = random.nextInt(translete.size());
            d = random.nextInt(translete.size());

            if(translete.get(r) != translete.get(word_index) && translete.get(d) != translete.get(word_index)){
                false_word.add(1,translete.get(r));
                false_word.add(2,translete.get(d));

                FalseWordOne.setText((CharSequence) false_word.get(0));
                FalseWordTwo.setText((CharSequence) false_word.get(1));
                FalseWordThree.setText((CharSequence) false_word.get(2));
            }


        }
         final String rightWord = (String) translete.get(word_index);

         FalseWordOne.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(translete.get(word_index) == FalseWordOne.getText()){
                     RESULT++;
                     FalseWordOne.setBackgroundColor(Color.GREEN);

                    // String r = Integer.toString(RESULT);
                     //result.setText(r);

                 }else FalseWordOne.setBackgroundColor(Color.RED);
             }
         });

        FalseWordTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(translete.get(word_index) == FalseWordTwo.getText()){
                    RESULT++;
                    FalseWordTwo.setBackgroundColor(Color.GREEN);


                    // String r = Integer.toString(RESULT);
                    //result.setText(r);

                }else FalseWordTwo.setBackgroundColor(Color.RED);
            }
        });

        FalseWordThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(translete.get(word_index) == FalseWordThree.getText()){
                    RESULT++;
                    FalseWordThree.setBackgroundColor(Color.GREEN);

                    // String r = Integer.toString(RESULT);
                    //result.setText(r);

                }else FalseWordThree.setBackgroundColor(Color.RED);
            }
        });


         return String.valueOf(RESULT);



    }


}



