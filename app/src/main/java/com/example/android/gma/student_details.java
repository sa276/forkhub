package com.example.android.gma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.example.android.gma.User_Details.LVarea;
import static com.example.android.gma.User_Details.tv1;
import static com.example.android.gma.User_Details.tv5;
import static com.example.android.gma.User_Details.tv6;
import static com.loopj.android.http.AsyncHttpClient.log;

public class student_details extends AppCompatActivity {
    public String LVarea = "Kuttralam";
    public String name, name_to_extract;
    public int size;
    public String getName_to_extract;

    public ArrayList<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        TextView bt1 = findViewById(R.id.update);

        if (MainActivity.first != 'S') {
            bt1.setVisibility(View.GONE);
        }


        display();
    }

    public void display() {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("username", MainActivity.dummy_username);
        params.add("password", MainActivity.dummy_password);
        client.post("https://quizkrieg.000webhostapp.com/gmasection.php", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                LVarea = new String(responseBody);
                log.v("name", LVarea);
                displayall();
                // Toast.makeText(User_Page.this,new String(responseBody),Toast.LENGTH_LONG).show();
                //tv.setText("" + name + "!!!");

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }

    public void displayall() {
        name_to_extract = Alldetails.name_to_extract;
        display_alldetails(name_to_extract);
        log.v("name", name_to_extract);
    }


    public void display_alldetails(String name_to_extract) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("username", MainActivity.dummy_username);
        params.add("password", MainActivity.dummy_password);
        params.add("name", name_to_extract);
        client.post("https://quizkrieg.000webhostapp.com/gmadisplay_all_details.php", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                name = new String(responseBody);
                log.v("name", name);
                String[] data2 = name.split("_");
                //Intent i=new Intent(student_details.this,student_details.class);
                //startActivity(i);
                //new AlertDialog.Builder(Alldetails.this)
                //.setTitle("DETAILS")
                //.setMessage("NAME                           " + data2[0] +"\n" + "ROLL NUMBER          " + data2[1] + "\n" + "DEPARTMENT           " + data2[2] + "\n" + "YEAR                            "
                // + data2[3] + "\n" + "SECTION                     " + data2[4]
                //+"\n" + "SUBJECT 1          " + data2[5] + "\n" + "PERIODICAL 1           " + data2[6] +"\n" + "PERIODICAL 2              " + data2[7] + "\n" + "GRACE MARKS          " + data2[8]
                //     + "\n" + "REASON                      " + data2[9]
                //)

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.


                // A null listener allows the button to dismiss the dialog and take no further action.
                //.setNegativeButton(android.R.string.yes, null)

                //.show();

                TextView tv1 = (TextView) findViewById(R.id.name_input);
                TextView tv2 = (TextView) findViewById(R.id.roll_input);
                TextView tv3 = (TextView) findViewById(R.id.cgpa_input);
                TextView tv4 = (TextView) findViewById(R.id.contact_input);
                TextView tv5 = (TextView) findViewById(R.id.gm_input);
                TextView tv6 = (TextView) findViewById(R.id.reason_input);

                TextView tv7 = (TextView) findViewById(R.id.s1p1marks_input);
                TextView tv8 = (TextView) findViewById(R.id.s2p1marks_input);
                TextView tv9 = (TextView) findViewById(R.id.s3p1marks_input);

                TextView tv10 = (TextView) findViewById(R.id.s1p2marks_input);
                TextView tv11 = (TextView) findViewById(R.id.s2p2marks_input);
                TextView tv12 = (TextView) findViewById(R.id.s3p2marks_input);

                TextView tv13 = (TextView) findViewById(R.id.s1p3marks_input);
                TextView tv14 = (TextView) findViewById(R.id.s2p3marks_input);
                TextView tv15 = (TextView) findViewById(R.id.s3p3marks_input);

                TextView tv16=findViewById(R.id.s1ca_input);
                TextView tv17=findViewById(R.id.s2ca_input);
                TextView tv18=findViewById(R.id.s3ca_input);

                ImageView iv1 = (ImageView) findViewById(R.id.user_image);

                tv1.setText("" + data2[0]);
                tv2.setText("" + data2[1]);
                tv3.setText("" + data2[15]);
                tv4.setText("" + data2[14]);
                tv5.setText("" + data2[16]);
                tv6.setText("" + data2[17]);

                tv7.setText("" + data2[5]);
                tv8.setText("" + data2[8]);
                tv9.setText("" + data2[11]);

                tv10.setText("" + data2[6]);
                tv11.setText("" + data2[9]);
                tv12.setText("" + data2[12]);

                tv13.setText("" + data2[7]);
                tv14.setText("" + data2[10]);
                tv15.setText("" + data2[13]);

                tv16.setText("" + data2[18]);
                tv17.setText("" + data2[19]);
                tv18.setText("" + data2[20]);


                if (data2[1].equals("CB.EN.U4CSE17601")) {
                    iv1.setImageResource(R.drawable.aakash);
                } else if (data2[1].equals("CB.EN.U4CSE17609")) {
                    iv1.setImageResource(R.drawable.s9);
                }
                else if (data2[1].equals("CB.EN.U4CSE17663")) {
                    iv1.setImageResource(R.drawable.s63);
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });


    }

    public void update(View view) {
        Intent i = new Intent(this, marks_updation.class);
        startActivity(i);
    }

}



