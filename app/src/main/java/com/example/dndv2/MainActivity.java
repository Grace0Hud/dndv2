package com.example.dndv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

import java.lang.invoke.ConstantCallSite;
import java.util.HashMap;

/**
 * link to kaban board: https://docs.google.com/spreadsheets/d/1bDCZd-A317ZPMwXX4LJmkg1AMy7mqXyuz8Pd6ddEv6Y/edit#gid=0
 */
public class MainActivity extends AppCompatActivity
{

    private EditText passET, emailET;//signin

    private EditText signupPassET, signUpREPassET, signupEmailET, signUpNameET;
    boolean signUpFinished = false;

    private FirebaseAuth mAuth;
    private DatabaseReference userRef;
    private ProgressDialog loadingbar;
    Button signin, signup;
    String currentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        loadingbar = new ProgressDialog(this);

        Button signup = (Button) findViewById(R.id.signupbtn);
        signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changeScreen();
            }
        });

        signin = (Button) findViewById(R.id.signinbtn);
        signin.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                allowUserLogin();
            }
        });
    }

    private void allowUserLogin()
    {
        emailET = (EditText)findViewById(R.id.email);
        passET = (EditText)findViewById(R.id.password);
        String enteredEmail = emailET.getText().toString();
        String enteredPass = passET.getText().toString();

        if(TextUtils.isEmpty(enteredEmail))
        {
            Toast.makeText(this, "enter your email.", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(enteredPass))
        {
            Toast.makeText(this, "enter your password.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingbar("Signing you In", "Please wait as you are logged in");
            mAuth.signInWithEmailAndPassword(enteredEmail, enteredPass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {
                                toDisplayScreen();
                                Toast.makeText(MainActivity.this, "login successful", Toast.LENGTH_SHORT).show();
                                loadingbar.dismiss();
                            }
                            else
                            {
                                errorMessage(task);
                            }
                        }
                    });
        }
    }

    private void login(String enteredEmail, String enteredPass) {
        loadingbar("Signing you In", "Please wait as you are logged in");
        mAuth.signInWithEmailAndPassword(enteredEmail, enteredPass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this, "login successful", Toast.LENGTH_SHORT).show();
                            loadingbar.dismiss();
                        }
                        else
                        {
                            errorMessage(task);
                        }
                    }
                });
    }

    private void toDisplayScreen()
    {
        Intent displayIntent = new Intent(this, com.example.dndv2.displayScreen.class);
        displayIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(displayIntent);
        finish();
    }


    public void changeScreen()//changes screen after player wins
    {
        //opens up a dialogue box instead of a layout for the win screen
        LayoutInflater layoutInflater = LayoutInflater.from(this);

        View promptView = layoutInflater.inflate(R.layout.signup_popup, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        signupEmailET = (EditText)promptView.findViewById(R.id.emailsignup);
        signupPassET = (EditText)promptView.findViewById(R.id.passwordsignup);
        signUpREPassET = (EditText)promptView.findViewById(R.id.retypePassword);
        signUpNameET = (EditText)promptView.findViewById(R.id.name);
        // set prompts.xml to be the layout file of the alertdialog builder
        alertDialogBuilder.setView(promptView);

        // setup a dialog window
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        while(!signUpFinished)
                        {
                            signUp();
                        }
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

    private void signUp()
    {
        signUpFinished = false;
        String newEmail = signupEmailET.getText().toString();
        String newPass = signupPassET.getText().toString();
        String retypePass = signUpREPassET.getText().toString();
        String newName = signUpNameET.getText().toString();


        if(TextUtils.isEmpty(newEmail))
        {
            Toast.makeText(this, "must enter an email", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(newPass))
        {
            Toast.makeText(this, "must enter password", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(retypePass))
        {
            Toast.makeText(this, "must retype password", Toast.LENGTH_SHORT).show();
        }
        else if(!newPass.equals(retypePass))
        {
            Toast.makeText(this, "password and retype password must match", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(newName))
        {
            Toast.makeText(this, "must enter a name", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingbar("Creating New Account", "Please wait while your account is being created");

            mAuth.createUserWithEmailAndPassword(newEmail, newPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(MainActivity.this, "account created", Toast.LENGTH_SHORT).show();
                        Log.e("crash?", "----------1------------");
                        login(newEmail, newPass);
                        Log.e("crash?", "----------2------------");
                        createCharacter(newName);
                        Log.e("crash?", "----------3------------");
                        loadingbar.dismiss();
                    }
                    else
                    {
                        errorMessage(task);
                    }
                }
            });
        }
    }

    private void errorMessage(@NonNull Task<AuthResult> task) {
        String message = task.getException().getMessage();
        Toast.makeText(MainActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
        loadingbar.dismiss();
    }

    private void createCharacter(String name)
    {
        loadingbar("Creating new Character: " + name, "Please wait while your character is created");
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Log.e("crash?", "----------4------------");
       userRef = FirebaseDatabase.getInstance().getReference("User");
        Log.e("crash?", "----------5------------");
        Character player = new Character(name);
        userRef.child("Users").child(uid).setValue(player).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful())
                {
                    toDisplayScreen();
                    Log.e("crash?", "----------6------------");
                    Toast.makeText(MainActivity.this, "character created!", Toast.LENGTH_SHORT).show();
                    loadingbar.dismiss();
                }
                else
                {
                    errorMessage(task);
                }
            }
        });
    }

    public void loadingbar(String title, String message)
    {
        loadingbar.setTitle(title);
        loadingbar.setMessage(message);
        loadingbar.show();
        loadingbar.setCanceledOnTouchOutside(true);
        signUpFinished = true;
    }

}
