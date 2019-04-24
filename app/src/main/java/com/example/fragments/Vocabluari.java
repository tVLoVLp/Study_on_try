package com.example.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Vocabluari {

    private static int RESULT = 0;
    private static int count;
    private ArrayList value ;
    private ArrayList translete ,RightWord;
    Context context;

    public int work(final ArrayList value, final ArrayList translete, final TextView right_word,
                       final Button FalseWordOne, final Button FalseWordTwo,
                       final Button FalseWordThree, final Button FalseWordFour, final Button Next , final TextView text_result,
                       final TextView count_text, final TextView size,final TextView wishes,final Button finish,
                       final Button retry) {

        ArrayList false_word = new ArrayList();

        Random random = new Random();
        int item =  random.nextInt(value.size());
        final int word_index = value.indexOf(value.get(item)); //отримали індекс нашого рандомного слова

        false_word.add(0, translete.get(word_index));

        int r = random.nextInt(translete.size());
        int d = random.nextInt(translete.size());
        int i=random.nextInt(translete.size());


        finish.setVisibility(View.GONE);
        retry.setVisibility(View.GONE);
        Log.d("Fab", String.valueOf(r));
        Log.d("Fab", String.valueOf(d));



            if(translete.get(r) != translete.get(word_index) && translete.get(d) != translete.get(word_index)&& translete.get(i)!=translete.get(word_index)){
                false_word.add(1,translete.get(r));
                false_word.add(2,translete.get(d));
                false_word.add(3,translete.get(i));

            }


        Collections.shuffle(false_word);

        right_word.setText((CharSequence) value.get(word_index));
        try {
            FalseWordOne.setText((CharSequence) false_word.get(0));
            FalseWordTwo.setText((CharSequence) false_word.get(1));
            FalseWordThree.setText((CharSequence) false_word.get(2));
            FalseWordFour.setText((CharSequence) false_word.get(3));
        }
        catch (IndexOutOfBoundsException e){
           r = random.nextInt(translete.size());
            d = random.nextInt(translete.size());
            i = random.nextInt(translete.size());

            if(translete.get(r) != translete.get(word_index) && translete.get(d) != translete.get(word_index)&& translete.get(i)!=translete.get(word_index)){
                false_word.add(1,translete.get(r));
                false_word.add(2,translete.get(d));
                false_word.add(3,translete.get(i));

                FalseWordOne.setText((CharSequence) false_word.get(0));
                FalseWordTwo.setText((CharSequence) false_word.get(1));
                FalseWordThree.setText((CharSequence) false_word.get(2));
                FalseWordFour.setText((CharSequence) false_word.get(3));
            }


        }
         final String rightWord = (String) translete.get(word_index);

         FalseWordOne.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 count++;
                 if(translete.get(word_index) == FalseWordOne.getText()){
                     RESULT++;
                     FalseWordOne.setBackgroundColor(Color.GREEN);
                     FalseWordOne.setClickable(false);
                     FalseWordTwo.setClickable(false);
                     FalseWordThree.setClickable(false);
                     FalseWordFour.setClickable(false);


                    // String r = Integer.toString(RESULT);
                     //result.setText(r);

                 }else {FalseWordOne.setBackgroundColor(Color.RED);
                     FalseWordOne.setClickable(false);
                     FalseWordTwo.setClickable(false);
                     FalseWordThree.setClickable(false);
                     FalseWordFour.setClickable(false);
                 }
             }
         });

        FalseWordTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                if(translete.get(word_index) == FalseWordTwo.getText()){
                    RESULT++;
                    FalseWordTwo.setBackgroundColor(Color.GREEN);
                    FalseWordOne.setClickable(false);
                    FalseWordTwo.setClickable(false);
                    FalseWordThree.setClickable(false);
                    FalseWordFour.setClickable(false);


                    // String r = Integer.toString(RESULT);
                    //result.setText(r);

                }else {FalseWordTwo.setBackgroundColor(Color.RED);
                    FalseWordOne.setClickable(false);
                    FalseWordTwo.setClickable(false);
                    FalseWordThree.setClickable(false);
                    FalseWordFour.setClickable(false);
                }
            }
        });

        FalseWordThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                if(translete.get(word_index) == FalseWordThree.getText()){
                    RESULT++;
                    FalseWordThree.setBackgroundColor(Color.GREEN);
                    FalseWordOne.setClickable(false);
                    FalseWordTwo.setClickable(false);
                    FalseWordThree.setClickable(false);
                    FalseWordFour.setClickable(false);

                    // String r = Integer.toString(RESULT);
                    //result.setText(r);

                }else {FalseWordThree.setBackgroundColor(Color.RED);
                    FalseWordOne.setClickable(false);
                    FalseWordTwo.setClickable(false);
                    FalseWordThree.setClickable(false);
                    FalseWordFour.setClickable(false);
                }
            }
        });
        FalseWordFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if(translete.get(word_index)==FalseWordFour.getText()){
                    RESULT++;
                    FalseWordFour.setBackgroundColor(Color.GREEN);
                    FalseWordOne.setClickable(false);
                    FalseWordTwo.setClickable(false);
                    FalseWordThree.setClickable(false);
                    FalseWordFour.setClickable(false);
                }else {
                    FalseWordFour.setBackgroundColor(Color.RED);
                    FalseWordOne.setClickable(false);
                    FalseWordTwo.setClickable(false);
                    FalseWordThree.setClickable(false);
                    FalseWordFour.setClickable(false);
                }
            }
        });
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           FalseWordOne.setClickable(true);
            FalseWordTwo.setClickable(true);
            FalseWordThree.setClickable(true);
            FalseWordFour.setClickable(true);

            FalseWordOne.setBackgroundColor(Color.BLUE);
            FalseWordTwo.setBackgroundColor(Color.BLUE);
            FalseWordThree.setBackgroundColor(Color.BLUE);
            FalseWordFour.setBackgroundColor(Color.BLUE);
                finish.setVisibility(View.GONE);
                retry.setVisibility(View.GONE);

                Vocabluari next_vob = new Vocabluari();
                next_vob.work(value, translete, right_word, FalseWordOne, FalseWordTwo, FalseWordThree,FalseWordFour, Next, text_result,count_text,size,wishes,finish,retry);

            }
        });

        count_text.setText(String.valueOf(count));
        size.setText(String.valueOf(value.size()));
        if (count == value.size()) {


            right_word.setText((R.string.result));
            text_result.setText(String.valueOf(RESULT));
            if(RESULT>=value.size()*0.75)
                wishes.setText(R.string.advise_1_1);
            else if (RESULT>=value.size()*0.5&&RESULT<value.size()*0.75)
                wishes.setText(R.string.average);
            else wishes.setText(R.string.bad);

            finish.setVisibility(View.VISIBLE);
            retry.setVisibility(View.VISIBLE);
            FalseWordOne.setVisibility(View.GONE);
            FalseWordTwo.setVisibility(View.GONE);
            FalseWordThree.setVisibility(View.GONE);
            FalseWordFour.setVisibility(View.GONE);
            Next.setVisibility(View.GONE);
           /* retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count=0;
                    RESULT=0;
                    /*Vocabluari new_vob = new Vocabluari();
                    new_vob.work(value, translete, right_word, FalseWordOne, FalseWordTwo, FalseWordThree,FalseWordFour, Next, text_result,count_text,size,wishes,finish,retry);

                    Intent intent =new Intent(this,TestActivity.class);
                    context.startActivity(intent);*/
                }
        return RESULT;
            }//);

        }













