package com.example.dndv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
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

import java.lang.invoke.ConstantCallSite;

/**
 * link to kaban board: https://docs.google.com/spreadsheets/d/1bDCZd-A317ZPMwXX4LJmkg1AMy7mqXyuz8Pd6ddEv6Y/edit#gid=0
 */
public class MainActivity extends AppCompatActivity
{

    private EditText passET, emailET;//signin

    private EditText signupPassET, signUpREPassET, signupEmailET, signUpNameET;
    boolean signUpFinished = false;

    private FirebaseAuth mAuth;
    private ProgressDialog loadingbar;
    Button signin, signup;

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
                        loadingbar.dismiss();
                    }
                    else
                    {
                        String message = task.getException().getMessage();
                        Toast.makeText(MainActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                        loadingbar.dismiss();
                    }
                }
            });
        }
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
