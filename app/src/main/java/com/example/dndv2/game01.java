package com.example.dndv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class game01 extends AppCompatActivity {

    Character userChar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game01);

        Intent intent = getIntent();
        userChar = (Character)intent.getSerializableExtra("Character");
        Log.i("Character: ", userChar.toString());
    }
}