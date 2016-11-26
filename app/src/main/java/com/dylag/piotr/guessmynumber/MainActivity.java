package com.dylag.piotr.guessmynumber;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int myNumber, userNumber, guessCount=0;
    TextView textView;
    TextView textView2;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);
        sharedPreferences = getSharedPreferences("com.dylag.piotr.guessmynumber", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


    }


    public void newGame(View view) {

        Random rand = new Random();
        myNumber = rand.nextInt((100-0)+1)+0;
        guessCount = 0;

        textView.setText("Times guessed: " + guessCount);
    }

    public void takeTheGuess(View view) {
        guessCount++;
        EditText editText = (EditText)findViewById(R.id.editText);
        userNumber = Integer.parseInt(editText.getText().toString());
    String message = "";

        if (userNumber>myNumber){
            message = "My number is less than yours";
        }
        else if(userNumber<myNumber) {
            message = "My number is bigger than yours";
        }
            else if(userNumber==myNumber){
                message = "Congrats! You guessed my number";
            if(guessCount < sharedPreferences.getInt("BestScore", 100)){
                editor.putInt("BestScore", guessCount);
                editor.commit();

                }

            }

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, message, duration);
        toast.show();
        textView.setText("Times guessed: " + guessCount);



    }
    }

