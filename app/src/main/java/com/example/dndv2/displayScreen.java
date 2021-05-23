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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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
    String currentUserID;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_screen);
        loadingbar = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        userRef = FirebaseDatabase.getInstance().getReference().child("Characters");
        currentUserID = mAuth.getCurrentUser().getUid();
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

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot)
            {
                if(!snapshot.hasChild(currentUserID))
                {
                    namePopup();
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error)
            {
                String message = error.getMessage();
                Toast.makeText(displayScreen.this, "Firebase error: " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void namePopup()
    {
        //opens up a dialogue box instead of a layout for the win screen
        LayoutInflater layoutInflater = LayoutInflater.from(this);

        View promptView = layoutInflater.inflate(R.layout.name_enter, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set prompts.xml to be the layout file of the alertdialog builder
        alertDialogBuilder.setView(promptView);

        // setup a dialog window
        nameET = (EditText)promptView.findViewById(R.id.name);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String name = String.valueOf(nameET.getText());
                        createCharcter(name);
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

    private void createCharcter(String name)
    {
        loadingbar("Creating new Character: " + name, "Please wait while your character is created");


        userRef = FirebaseDatabase.getInstance().getReference().child("Characters");
        Character player = new Character(name);
        userRef.child(currentUserID).setValue(player).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(displayScreen.this, "character created!", Toast.LENGTH_SHORT).show();
                    loadingbar.dismiss();
                }
                else
                {
                    errorMessage(task);
                }
            }
        });
    }

    private void errorMessage(@NonNull Task<AuthResult> task) {
        String message = task.getException().getMessage();
        Toast.makeText(displayScreen.this, "Error: " + message, Toast.LENGTH_SHORT).show();
        loadingbar.dismiss();
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