package com.example.android.gma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;



import cz.msebera.android.httpclient.Header;


import static com.loopj.android.http.AsyncHttpClient.log;

public class User_Details extends AppCompatActivity {
    public static String name;
    static TextView tv1;
    static TextView tv2;
    static TextView tv3;
    static TextView tv4;
    static TextView tv5;
    static TextView tv6;
    static TextView tv7;
    static TextView tv8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__details);
        tv1=  findViewById(R.id.username_input);
        tv2=findViewById(R.id.dept_input);
        tv3=findViewById(R.id.year_input);
        tv4=findViewById(R.id.section_input);
        tv5=findViewById(R.id.role_input);

        tv7=findViewById(R.id.name_input);

        display();
    }

    public static void display()
    {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("username",MainActivity.dummy_username);
        params.add("password",MainActivity.dummy_password);
        client.post("https://quizkrieg.000webhostapp.com/gmadisplay_details.php",params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                name=new String(responseBody);
                log.v("name",name);
                String[] data = name.split("_");

                tv1.setText("" + MainActivity.dummy_username );


                tv2.setText("" + data[1] );

                tv3.setText("" + data[2] );


                tv4.setText("" + data[3] );



                tv5.setText("" + data[4] );





                tv7.setText("" + data[0] );



            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });




    }


}
