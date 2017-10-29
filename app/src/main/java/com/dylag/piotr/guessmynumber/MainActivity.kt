package com.dylag.piotr.guessmynumber

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Random

class MainActivity : AppCompatActivity() {

    internal var myNumber: Int = 0
    internal var userNumber: Int = 0
    internal var guessCount = 0
    internal lateinit var textView: TextView
    internal lateinit var textView2: TextView
    internal lateinit var sharedPreferences: SharedPreferences
    internal lateinit var editor: SharedPreferences.Editor
    internal var startbt: Button? = null



    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

        textView = findViewById(R.id.textView) as TextView
        textView2 = findViewById(R.id.textView2) as TextView
        sharedPreferences = getSharedPreferences("com.dylag.piotr.guessmynumber", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    private fun init() {
        startbt = findViewById(R.id.starbt) as Button
    }


    fun start(view: View) {
        val intent = Intent(this, gameviewcode::class.java)
        startActivity(intent)
    }


    fun newGame(view: View) {

        val rand = Random()
        myNumber = rand.nextInt(100 - 0 + 1) + 0
        guessCount = 0

        textView.text = "Times guessed: " + guessCount
    }

    fun takeTheGuess(view: View) {
        guessCount++
        val editText = findViewById(R.id.editText) as EditText?
        userNumber = Integer.parseInt(editText!!.text.toString())
        var message = ""

        if (userNumber > myNumber) {
            message = "My number is less than yours"
        } else if (userNumber < myNumber) {
            message = "My number is bigger than yours"
        } else if (userNumber == myNumber) {
            message = "Congrats! You guessed my number"
            if (guessCount < sharedPreferences.getInt("BestScore", 100)) {
                editor.putInt("BestScore", guessCount)
                editor.commit()

            }

        }

        val context = applicationContext
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(context, message, duration)
        toast.show()
        textView.text = "Times guessed: " + guessCount


    }


}

