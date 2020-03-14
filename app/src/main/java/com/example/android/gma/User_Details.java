package com.example.android.gma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.example.android.gma.MainActivity.dummy_password;
import static com.example.android.gma.MainActivity.dummy_username;
import static com.loopj.android.http.AsyncHttpClient.log;

public class User_Details extends AppCompatActivity {
    public static String name,LVarea;
    static TextView tv1;
    static TextView tv2;
    static TextView tv3;
    static TextView tv4;
    static TextView tv5;
    static TextView tv6;
    static TextView tv7;
    static TextView tv8;
    //ArrayList<String>name_ar=new ArrayList<String>();
    //ArrayList<String>state_ar=new ArrayList<String>();
    //ArrayList<String>city_ar=new ArrayList<String>();
    //ArrayList<String>area_ar=new ArrayList<String>();
    //ArrayList<String>contact_ar=new ArrayList<String>();
    //ArrayList<String>billno_ar=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__details);
        tv1=  findViewById(R.id.username_input);
        tv2=findViewById(R.id.dept_input);
        tv3=findViewById(R.id.year_input);
        tv4=findViewById(R.id.section_input);
        tv5=findViewById(R.id.role_input);
        //tv6=findViewById(R.id.billno_input);
        tv7=findViewById(R.id.name_input);
        //tv8=findViewById(R.id.wateravl_input);
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
                //LVarea=data[3];


                tv5.setText("" + data[4] );


                //tv6.setText("" + data[5] );


                tv7.setText("" + data[0] );

                //tv8.setText("" + data[6] + " litres");
                //Toast.makeText(User_Details.this,new String(responseBody),Toast.LENGTH_LONG).show();
                //JSONObject jsonObject = null;
                //try {

                //JSONArray jsonArray =new JSONArray(new String(responseBody));
                //for(int i=0;i<jsonArray.getJSONArray(0).length();i++) {
                //  Toast.makeText(User_Details.this, jsonArray.get(i).toString(), Toast.LENGTH_LONG).show();
                // name_ar.add(jsonArray.getJSONArray(0).get(i).toString());
                //state_ar.add(jsonArray.getJSONArray(1).get(i).toString());
                //city_ar.add(jsonArray.getJSONArray(2).get(i).toString());
                //area_ar.add(jsonArray.getJSONArray(3).get(i).toString());
                //contact_ar.add(jsonArray.getJSONArray(4).get(i).toString());
                //billno_ar.add(jsonArray.getJSONArray(5).get(i).toString());
                //  }
                //} catch (JSONException e) {
                //e.printStackTrace();
                //}

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });




    }


}
