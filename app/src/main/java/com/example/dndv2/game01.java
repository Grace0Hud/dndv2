package com.example.dndv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.util.ArrayList;

public class game01 extends AppCompatActivity {

    Character userChar;
    TextView nameTV, messageTV;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game01);

        Intent intent = getIntent();
        userChar = (Character)intent.getSerializableExtra("Character");
        Log.i("Character: ", userChar.toString());

        nameTV = (TextView)findViewById(R.id.nameofspeaker);
        messageTV = (TextView)findViewById(R.id.words);

        StoryMessage m1 = new StoryMessage("Narrator",
                "Our story begins in the Woodpecker Inn, a warm homely tavern");
        StoryMessage m2 = new StoryMessage("Amvir",
                "Ya want a meal? We got sand worm. Everything else has been having trouble reaching us lately.");
        StoryMessage m3 = new StoryMessage("Amvir",
                "Shipments to and from Tart keep getting attacked. I hear by the undead.");
        StoryMessage[] story = {m1, m2, m3};
        i=0;
        Button nextbtn = (Button)findViewById(R.id.nextbtn);
        nextbtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                story(story, i);
            }
        });
    }

    private void story(StoryMessage[] story, int i)
    {
        if(i < story.length)
        {
            nameTV.setText(story[i].getSpeaker());
            messageTV.setText(story[i].getMessage());
            this.i++;
        }
        else
        {
            Toast.makeText(this, "END", Toast.LENGTH_SHORT).show();
        }

    }
}