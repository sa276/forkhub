package com.example.android.gma;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

import static com.loopj.android.http.AsyncHttpClient.log;

public class Interval extends AppCompatActivity {
    public static String o_max,o_min,aplus_max,aplus_min,a_max,a_min,bplus_max,bplus_min,b_max,b_min,c_max,c_min,d_max,d_min,p_max,p_min,f_max,f_min;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interval);

        progressDialog = new ProgressDialog(this);

        TextView tv1=findViewById(R.id.subject);
        if(MainActivity.fourth=='N')
        {
            tv1.setText("Networks");
        }
        else if(MainActivity.fourth=='S')
        {
            tv1.setText("Software Engineering");
        }
        else if(MainActivity.fourth=='C')
        {
            tv1.setText("Compiler Design");
        }
    }


    public void update(View view)
    {
        EditText et1=findViewById(R.id.o_min);
        EditText et2=findViewById(R.id.o_max);

        EditText et3=findViewById(R.id.aplus_min);
        EditText et4=findViewById(R.id.aplus_max);

        EditText et5=findViewById(R.id.a_min);
        EditText et6=findViewById(R.id.a_max);

        EditText et7=findViewById(R.id.bplus_min);
        EditText et8=findViewById(R.id.bplus_max);

        EditText et9=findViewById(R.id.b_min);
        EditText et10=findViewById(R.id.b_max);

        EditText et11=findViewById(R.id.c_min);
        EditText et12=findViewById(R.id.c_max);


        EditText et15=findViewById(R.id.p_min);
        EditText et16=findViewById(R.id.p_max);

        EditText et17=findViewById(R.id.f_min);
        EditText et18=findViewById(R.id.f_max);

        o_min=et1.getText().toString();
        o_max=et2.getText().toString();


        aplus_min=et3.getText().toString();
        aplus_max=et4.getText().toString();

        a_min=et5.getText().toString();
        a_max=et6.getText().toString();

        bplus_min=et7.getText().toString();
        bplus_max=et8.getText().toString();

        b_min=et9.getText().toString();
        b_max=et10.getText().toString();

        c_min=et11.getText().toString();
        c_max=et12.getText().toString();


        p_min=et15.getText().toString();
        p_max=et16.getText().toString();

        f_min=et17.getText().toString();
        f_max=et18.getText().toString();

        log.e("omin",o_min);
        log.e("omax",o_max);
        log.e("bmin",b_min);
        log.e("amin",a_min);

        if(MainActivity.fourth=='S') {
            if (TextUtils.isEmpty(o_max) || TextUtils.isEmpty(o_min) || TextUtils.isEmpty(aplus_max) || TextUtils.isEmpty(aplus_min) ||
                    TextUtils.isEmpty(a_min) || TextUtils.isEmpty(a_max) || TextUtils.isEmpty(bplus_max) || TextUtils.isEmpty(bplus_min) ||
                    TextUtils.isEmpty(b_max) || TextUtils.isEmpty(b_min) || TextUtils.isEmpty(c_max) || TextUtils.isEmpty(p_max) ||
                    TextUtils.isEmpty(p_min) || TextUtils.isEmpty(f_max) || TextUtils.isEmpty(f_min)) {
                Toast.makeText(Interval.this, "Some details are missing", Toast.LENGTH_LONG).show();
            } else {

                progressDialog.setMessage("Updating Intervals...");
                progressDialog.show();
                //String type = "register";
                //BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                //backgroundWorker.execute(type,str_fn,str_contact,str_head,str_addr,str_consumption,str_un,str_password,str_age);
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();

                params.add("username", MainActivity.dummy_username);
                params.add("password", MainActivity.dummy_password);
                params.add("name", "Software");
                params.add("o_min", o_min);
                params.add("o_max", o_max);
                params.add("aplus_min", aplus_min);
                params.add("aplus_max", aplus_max);
                params.add("a_min", a_min);
                params.add("a_max", a_max);
                params.add("bplus_min", bplus_min);
                params.add("bplus_max", bplus_max);
                params.add("b_min", b_min);
                params.add("b_max", b_max);
                params.add("c_min", c_min);
                params.add("c_max", c_max);

                params.add("p_min", p_min);
                params.add("p_max", p_max);
                params.add("f_min", f_min);
                params.add("f_max", f_max);


                client.post("https://quizkrieg.000webhostapp.com/gma_s1_interval.php", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                        progressDialog.dismiss();
                        //Toast.makeText(request.this, new String(responseBody), Toast.LENGTH_LONG).show();

                        new AlertDialog.Builder(Interval.this)
                                .setTitle("ALERT")
                                .setMessage("Intervals updated successfully !")

                                // Specifying a listener allows you to take an action before dismissing the dialog.
                                // The dialog is automatically dismissed when a dialog button is clicked.
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Continue with delete operation
                                        Intent i = new Intent(Interval.this, User_Page.class);
                                        startActivity(i);

                                    }
                                })

                                // A null listener allows the button to dismiss the dialog and take no further action.


                                .show();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });
            }
        }

        else if(MainActivity.fourth=='N')
        {

            if (TextUtils.isEmpty(o_max) || TextUtils.isEmpty(o_min) || TextUtils.isEmpty(aplus_max) || TextUtils.isEmpty(aplus_min) ||
                    TextUtils.isEmpty(a_min) || TextUtils.isEmpty(a_max) || TextUtils.isEmpty(bplus_max) || TextUtils.isEmpty(bplus_min) ||
                    TextUtils.isEmpty(b_max) || TextUtils.isEmpty(b_min) || TextUtils.isEmpty(c_max) || TextUtils.isEmpty(p_max) ||
                    TextUtils.isEmpty(p_min) || TextUtils.isEmpty(f_max) || TextUtils.isEmpty(f_min)) {
                Toast.makeText(Interval.this, "Some details are missing", Toast.LENGTH_LONG).show();
            }
            else {

                progressDialog.setMessage("Updating Intervals...");
                progressDialog.show();
                //String type = "register";
                //BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                //backgroundWorker.execute(type,str_fn,str_contact,str_head,str_addr,str_consumption,str_un,str_password,str_age);
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();

                params.add("username", MainActivity.dummy_username);
                params.add("password", MainActivity.dummy_password);
                params.add("name", "Networks");
                params.add("o_min", o_min);
                params.add("o_max", o_max);
                params.add("aplus_min", aplus_min);
                params.add("aplus_max", aplus_max);
                params.add("a_min", a_min);
                params.add("a_max", a_max);
                params.add("bplus_min", bplus_min);
                params.add("bplus_max", bplus_max);
                params.add("b_min", b_min);
                params.add("b_max", b_max);
                params.add("c_min", c_min);
                params.add("c_max", c_max);

                params.add("p_min", p_min);
                params.add("p_max", p_max);
                params.add("f_min", f_min);
                params.add("f_max", f_max);


                client.post("https://quizkrieg.000webhostapp.com/gma_s2_interval.php", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                        progressDialog.dismiss();
                        //Toast.makeText(request.this, new String(responseBody), Toast.LENGTH_LONG).show();

                        new AlertDialog.Builder(Interval.this)
                                .setTitle("ALERT")
                                .setMessage("Intervals updated successfully !")

                                // Specifying a listener allows you to take an action before dismissing the dialog.
                                // The dialog is automatically dismissed when a dialog button is clicked.
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Continue with delete operation
                                        Intent i = new Intent(Interval.this, User_Page.class);
                                        startActivity(i);

                                    }
                                })

                                // A null listener allows the button to dismiss the dialog and take no further action.


                                .show();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });
            }
        }

        else if(MainActivity.fourth=='C') {

            if (TextUtils.isEmpty(o_max) || TextUtils.isEmpty(o_min) || TextUtils.isEmpty(aplus_max) || TextUtils.isEmpty(aplus_min) ||
                    TextUtils.isEmpty(a_min) || TextUtils.isEmpty(a_max) || TextUtils.isEmpty(bplus_max) || TextUtils.isEmpty(bplus_min) ||
                    TextUtils.isEmpty(b_max) || TextUtils.isEmpty(b_min) || TextUtils.isEmpty(c_max) || TextUtils.isEmpty(p_max) ||
                    TextUtils.isEmpty(p_min) || TextUtils.isEmpty(f_max) || TextUtils.isEmpty(f_min)) {
                Toast.makeText(Interval.this, "Some details are missing", Toast.LENGTH_LONG).show();
            } else {


                progressDialog.setMessage("Updating Intervals...");
                progressDialog.show();
                //String type = "register";
                //BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                //backgroundWorker.execute(type,str_fn,str_contact,str_head,str_addr,str_consumption,str_un,str_password,str_age);
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();

                params.add("username", MainActivity.dummy_username);
                params.add("password", MainActivity.dummy_password);
                params.add("name", "Networks");
                params.add("o_min", o_min);
                params.add("o_max", o_max);
                params.add("aplus_min", aplus_min);
                params.add("aplus_max", aplus_max);
                params.add("a_min", a_min);
                params.add("a_max", a_max);
                params.add("bplus_min", bplus_min);
                params.add("bplus_max", bplus_max);
                params.add("b_min", b_min);
                params.add("b_max", b_max);
                params.add("c_min", c_min);
                params.add("c_max", c_max);
                params.add("p_min", p_min);
                params.add("p_max", p_max);
                params.add("f_min", f_min);
                params.add("f_max", f_max);


                client.post("https://quizkrieg.000webhostapp.com/gma_s3_interval.php", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                        progressDialog.dismiss();
                        //Toast.makeText(request.this, new String(responseBody), Toast.LENGTH_LONG).show();

                        new AlertDialog.Builder(Interval.this)
                                .setTitle("ALERT")
                                .setMessage("Intervals updated successfully !")

                                // Specifying a listener allows you to take an action before dismissing the dialog.
                                // The dialog is automatically dismissed when a dialog button is clicked.
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Continue with delete operation
                                        Intent i = new Intent(Interval.this, User_Page.class);
                                        startActivity(i);

                                    }
                                })

                                // A null listener allows the button to dismiss the dialog and take no further action.


                                .show();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });
            }
        }
        }



    }


