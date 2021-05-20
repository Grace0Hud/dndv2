package com.example.dndv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class displayScreen extends AppCompatActivity 
{

    private FirebaseAuth mAuth;
    private DatabaseReference userRef;
    private EditText nameET;
    private ProgressDialog loadingbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_screen);
        mAuth = FirebaseAuth.getInstance();
        userRef = FirebaseDatabase.getInstance().getReference().child("Characters");
    }
    
    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        
        if(currentUser == null)
        {
            sendUserToLogin();
        }
        else
        {
            checkUserExistance();
        }
    }

    private void checkUserExistance()
    {
        final String currentUserID = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        Log.e("crash?", "------2-------");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot)
            {
                Log.e("crash?", "------3-------");
                if(!snapshot.hasChild(currentUserID))
                {
                    Log.e("crash?", "------4-------");
                    namePopup();
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error)
            {
                String message = error.getMessage();
                Log.e("crash?", message);
            }
        });
    }

    private void namePopup()
    {
        //opens up a dialogue box instead of a layout for the win screen
        LayoutInflater layoutInflater = LayoutInflater.from(this);

        View promptView = layoutInflater.inflate(R.layout.name_enter, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        Log.e("crash?", "------5-------");
        nameET = (EditText)findViewById(R.id.name);
        String name = String.valueOf(nameET.getText());
        // set prompts.xml to be the layout file of the alertdialog builder
        alertDialogBuilder.setView(promptView);

        Log.e("crash?", "------6-------");
        // setup a dialog window
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alertD = alertDialogBuilder.create();

        alertD.show();
    }

    private void sendUserToLogin()
    {
        Intent login = new Intent(this, MainActivity.class);
        startActivity(login);
        finish();
    }
    public void loadingbar(String title, String message)
    {
        loadingbar.setTitle(title);
        loadingbar.setMessage(message);
        loadingbar.show();
        loadingbar.setCanceledOnTouchOutside(true);
    }
}