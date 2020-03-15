package com.example.android.gma;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


import java.util.ArrayList;


import cz.msebera.android.httpclient.Header;

import static com.loopj.android.http.AsyncHttpClient.log;

public class Alldetails extends AppCompatActivity {
    public String LVarea="Kuttralam";
    public static String name,name_to_extract;
    public int size;

    public ArrayList<String>list=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alldetails);
        display();




    }



    public void display()
    {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("username",MainActivity.dummy_username);
        params.add("password",MainActivity.dummy_password);
        client.post("https://quizkrieg.000webhostapp.com/gmasection.php",params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                LVarea=new String(responseBody);
                log.v("name",LVarea);
                displayall();
                // Toast.makeText(User_Page.this,new String(responseBody),Toast.LENGTH_LONG).show();
                //tv.setText("" + name + "!!!");

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }

    public void displayall()
    {



        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("username",MainActivity.dummy_username);
        params.add("password",MainActivity.dummy_password);
        params.add("area",LVarea);
        client.post("https://quizkrieg.000webhostapp.com/gmaall_details.php",params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                name=new String(responseBody);
                log.v("name",LVarea);

                String[] data = name.split("_");


                ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),
                        R.layout.listview_layout,data );

                ListView listView = (ListView) findViewById(R.id.listview);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        name_to_extract=parent.getItemAtPosition(position).toString();
                        display_alldetails(name_to_extract);
                    }


                });

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });



    }

    public void display_alldetails(String name_to_extract)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("username",MainActivity.dummy_username);
        params.add("password",MainActivity.dummy_password);
        params.add("name",name_to_extract);
        client.post("https://quizkrieg.000webhostapp.com/gmadisplay_all_details.php",params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                name=new String(responseBody);
                log.v("name",name);
                String[] data2 = name.split("_");
                if(User_Page.flag!=1) {
                    Intent i = new Intent(Alldetails.this, student_details.class);
                    startActivity(i);
                }
                else
                {
                    Intent i=new Intent(Alldetails.this,Grade_generate.class);
                    startActivity(i);
                }



            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });





    }






}
