package com.example.android.gma;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Iterator;

import cz.msebera.android.httpclient.Header;

import static com.loopj.android.http.AsyncHttpClient.log;


public class MainActivity extends AppCompatActivity {


    private ProgressDialog progressDialog;
 //   private DatabaseReference mdatabase;
    public String uname =null, pword = null,type="login";
    public int flag=0;
    public  static  String dummy_username=null,dummy_password=null;
    public static char first;
    public static char fourth;
    public static char fifth;
    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        Context context=getApplicationContext();

        TextView tv1 = (TextView) findViewById(R.id.username_input);
        TextView tv2 = (TextView) findViewById(R.id.password_input);


        saveLoginCheckBox=(CheckBox)findViewById(R.id.checkbox);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            tv1.setText(loginPreferences.getString("username", ""));
            tv2.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }


    }

    public void Login(View view) {
        TextView tv1 = (TextView) findViewById(R.id.username_input);
        TextView tv2 = (TextView) findViewById(R.id.password_input);


        if(networkavailable())
        {
            flag=0;


            dummy_username = tv1.getText().toString();
            dummy_password = tv2.getText().toString();
            first=dummy_username.charAt(0);
            fourth=dummy_username.charAt(3);
            fifth=dummy_username.charAt(4);
            if (TextUtils.isEmpty(dummy_username)) {
                Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(dummy_password)) {
                Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            } else {

                progressDialog.setMessage("Logging in...");
                progressDialog.show();


//                BackgroundWorker backgroundWorker =new BackgroundWorker(this);
//                backgroundWorker.execute(type, dummy_username,dummy_password);
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.add("username",dummy_username);
                params.add("password",dummy_password);

                client.post("https://quizkrieg.000webhostapp.com/gmalogin.php", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String msg=new String(responseBody).trim();
                        log.v("my msg",msg);
                        progressDialog.dismiss();
                        if(msg.equals("failed"))
                        {
                            Toast.makeText(MainActivity.this,"Invalid Credentials",Toast.LENGTH_LONG).show();

                        }
                        else if(msg.equals("success"))
                        {
                            Toast.makeText(MainActivity.this,"Login Success !!!",Toast.LENGTH_LONG).show();
                          Intent i=new Intent(MainActivity.this,User_Page.class);
                            startActivity(i);


                        }


                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });





            }


            if (saveLoginCheckBox.isChecked()) {
                loginPrefsEditor.putBoolean("saveLogin", true);
                loginPrefsEditor.putString("username", dummy_username);
                loginPrefsEditor.putString("password", dummy_password);
                loginPrefsEditor.commit();
            } else {
                loginPrefsEditor.clear();
                loginPrefsEditor.commit();
            }


        }


        else
        {
            Snackbar.make(view,"No Internet Connection",Snackbar.LENGTH_LONG).setAction("ACTION",null).show();
        }

    }

    public boolean networkavailable()
    {
        try{
            ConnectivityManager manager =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netwrokinfo=null;
            if (manager!=null)
            {
                netwrokinfo = manager.getActiveNetworkInfo();
            }
            return  netwrokinfo!=null && netwrokinfo.isConnected();
        }

        catch (NullPointerException e)
        {
            return  false;
        }
    }


}
