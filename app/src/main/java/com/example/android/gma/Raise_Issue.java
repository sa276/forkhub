package com.example.android.gma;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.RadioButton;

import static com.example.android.gma.Grade_generate.sms_contact;

public class Raise_Issue extends AppCompatActivity {
public static String msg="Hi, This is a kind request from the advisor of CSE-F to kindly update the marks for students at the earliest using the GMA app. Thank you !!";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raise__issue);



    }

    public void issue(View view)
    {
        RadioButton rb1=findViewById(R.id.software);
        RadioButton rb2=findViewById(R.id.networks);
        RadioButton rb3=findViewById(R.id.compiler);

        SmsManager sms = null;

        if(rb1.isChecked())
        {
            sms = SmsManager.getDefault();
            sms.sendTextMessage("9677136009", null, msg, null, null);
            new AlertDialog.Builder(Raise_Issue.this)
                    .setTitle("ALERT")
                    .setMessage("Issue has been raised and is notified to the Software engineering faculty")


                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation


                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.


                    .show();
        }

        else if(rb2.isChecked())
        {
            sms = SmsManager.getDefault();
            sms.sendTextMessage("99080996617", null, msg, null, null);
            new AlertDialog.Builder(Raise_Issue.this)
                    .setTitle("ALERT")
                    .setMessage("Issue has been raised and is notified to the Networks faculty")


                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation


                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.


                    .show();
        }

        if(rb3.isChecked())
        {
            sms = SmsManager.getDefault();
            sms.sendTextMessage("9790572539", null, msg, null, null);
            new AlertDialog.Builder(Raise_Issue.this)
                    .setTitle("ALERT")
                    .setMessage("Issue has been raised and is notified to the Compiler Design faculty")


                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation


                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.


                    .show();
        }
    }

}
