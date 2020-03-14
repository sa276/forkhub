package com.example.android.gma;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

import static com.loopj.android.http.AsyncHttpClient.log;

public class marks_updation extends AppCompatActivity {
public static  int p1_marks,p2_marks,es_marks,at_marks,ca_marks;
public static String p1,p2,es,at,ca;
public static String f1,f2,f3;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks_updation);
        progressDialog = new ProgressDialog(this);

        ImageView iv1=findViewById(R.id.user_logo);
        iv1.setVisibility(View.GONE);

        TextView tv1=findViewById(R.id.name_input);
        tv1.setText("" + MainActivity.dummy_username);


    }

    public void update(View view)
    {
        TextView tv1=(TextView)findViewById(R.id.p1_input);
        TextView tv2=(TextView)findViewById(R.id.p2_input);
        TextView tv3=(TextView)findViewById(R.id.es_input);
        TextView tv4=(TextView)findViewById(R.id.attendance_input);
        TextView tv5=(TextView)findViewById(R.id.ca_input);

        p1=tv1.getText().toString();
        p2=tv2.getText().toString();
        es=tv3.getText().toString();
        at=tv4.getText().toString();
        ca=tv5.getText().toString();

        log.e("p1",p1);
        log.e("p2",p2);
        log.e("es",es);
        log.e("at",at);
        p1_marks=Integer.parseInt(p1);
        p2_marks=Integer.parseInt(p2);
        es_marks=Integer.parseInt(es);
        at_marks=Integer.parseInt(at);
        ca_marks=Integer.parseInt(ca);
        if(p1_marks > 50 || TextUtils.isEmpty(p1))
        {
            Snackbar.make(view,"Invalid Periodical 1 marks", Snackbar.LENGTH_LONG).setAction("ACTION",null).show();
        }
        else if(p2_marks>50 || TextUtils.isEmpty(p2))
        {
            Snackbar.make(view,"Invalid Periodical 2 Marks",Snackbar.LENGTH_LONG).setAction("ACTION",null).show();
        }
        else if(es_marks>100 || TextUtils.isEmpty(es))
        {
            Snackbar.make(view,"Invalid End Semester Marks",Snackbar.LENGTH_LONG).setAction("ACTION",null).show();
        }
        else if(at_marks>5 || TextUtils.isEmpty(at))
        {
            Snackbar.make(view,"Invalid Attendance Marks",Snackbar.LENGTH_LONG).setAction("ACTION",null).show();
        }
        else if(ca_marks>20 || TextUtils.isEmpty(ca))
        {
            Snackbar.make(view,"Invalid CA Marks",Snackbar.LENGTH_LONG).setAction("ACTION",null).show();
        }


        else
        {
            log.e("first",MainActivity.first+"");
            log.e("ffth",MainActivity.fifth+"");

            if((MainActivity.first=='S') && (MainActivity.fifth=='S'))
            {
                progressDialog.setMessage("Updating...");
                progressDialog.show();
                //String type = "register";
                //BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                //backgroundWorker.execute(type,str_fn,str_contact,str_head,str_addr,str_consumption,str_un,str_password,str_age);
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.add("username", MainActivity.dummy_username);
                params.add("password", MainActivity.dummy_password);
                Alldetails.name_to_extract.trim();
                params.add("name",Alldetails.name_to_extract);
                params.add("p1", p1);
                params.add("p2", p2);
                params.add("es",es);
                params.add("at",at );
                params.add("ca",ca);

                client.post("https://quizkrieg.000webhostapp.com/gmas1_update.php", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                        progressDialog.dismiss();
                        //Toast.makeText(request.this, new String(responseBody), Toast.LENGTH_LONG).show();

                        new AlertDialog.Builder(marks_updation.this)
                                .setTitle("ALERT")
                                .setMessage("Marks Successfully updated for " + Alldetails.name_to_extract)

                                // Specifying a listener allows you to take an action before dismissing the dialog.
                                // The dialog is automatically dismissed when a dialog button is clicked.
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Continue with delete operation
                                        Intent i = new Intent(marks_updation.this, User_Page.class);
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







            else if((MainActivity.first=='S') && (MainActivity.fifth=='A'))
            {
                progressDialog.setMessage("Updating...");
                progressDialog.show();
                //String type = "register";
                //BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                //backgroundWorker.execute(type,str_fn,str_contact,str_head,str_addr,str_consumption,str_un,str_password,str_age);
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.add("username", MainActivity.dummy_username);
                params.add("password", MainActivity.dummy_password);
                Alldetails.name_to_extract.trim();
                params.add("name",Alldetails.name_to_extract);
                params.add("p1", p1);
                params.add("p2", p2);
                params.add("es",es);
                params.add("at",at );
                params.add("ca",ca);

                client.post("https://quizkrieg.000webhostapp.com/gmas2_update.php", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                        progressDialog.dismiss();
                        //Toast.makeText(request.this, new String(responseBody), Toast.LENGTH_LONG).show();

                        new AlertDialog.Builder(marks_updation.this)
                                .setTitle("ALERT")
                                .setMessage("Marks Successfully updated for " + Alldetails.name_to_extract)

                                // Specifying a listener allows you to take an action before dismissing the dialog.
                                // The dialog is automatically dismissed when a dialog button is clicked.
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Continue with delete operation
                                        Intent i = new Intent(marks_updation.this, User_Page.class);
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






            if((MainActivity.first=='S') && (MainActivity.fifth=='P'))
            {
                progressDialog.setMessage("Updating...");
                progressDialog.show();
                //String type = "register";
                //BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                //backgroundWorker.execute(type,str_fn,str_contact,str_head,str_addr,str_consumption,str_un,str_password,str_age);
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.add("username", MainActivity.dummy_username);
                params.add("password", MainActivity.dummy_password);
                Alldetails.name_to_extract.trim();
                params.add("name",Alldetails.name_to_extract);
                params.add("p1", p1);
                params.add("p2", p2);
                params.add("es",es);
                params.add("at",at );
                params.add("ca",ca);

                client.post("https://quizkrieg.000webhostapp.com/gmas3_update.php", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                        progressDialog.dismiss();
                        //Toast.makeText(request.this, new String(responseBody), Toast.LENGTH_LONG).show();

                        new AlertDialog.Builder(marks_updation.this)
                                .setTitle("ALERT")
                                .setMessage("Marks Successfully updated for " + Alldetails.name_to_extract)

                                // Specifying a listener allows you to take an action before dismissing the dialog.
                                // The dialog is automatically dismissed when a dialog button is clicked.
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Continue with delete operation
                                        Intent i = new Intent(marks_updation.this, User_Page.class);
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


