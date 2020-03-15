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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class Grace_mark_update extends AppCompatActivity {
    EditText marks,reason;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grace_mark_update);

        marks=(EditText) findViewById(R.id.grace_marks_input);
        reason=(EditText) findViewById(R.id.reason_input);

        ImageView iv1=findViewById(R.id.user_logo);
        iv1.setVisibility(View.GONE);

        progressDialog = new ProgressDialog(this);

        TextView tv1=findViewById(R.id.name_input);
        tv1.setText("" + MainActivity.dummy_username);


    }

    public void update (View view)
    {
        final String str_marks=marks.getText().toString();
        String str_reason=reason.getText().toString();

        if(TextUtils.isEmpty(str_marks))
        {
            Toast.makeText(this, "Please enter valid marks", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(str_reason))
        {
            Toast.makeText(this, "Please enter valid reason", Toast.LENGTH_SHORT).show();
        }
        else
        {
            progressDialog.setMessage("Updating...");
            progressDialog.show();

            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            params.add("username", MainActivity.dummy_username);
            params.add("password", MainActivity.dummy_password);
            params.add("name",GM_update.name_to_extract);
            params.add("marks", str_marks);
            params.add("reason", str_reason);

            client.post("https://quizkrieg.000webhostapp.com/grace_mark_update.php", params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                    progressDialog.dismiss();
                    //Toast.makeText(request.this, new String(responseBody), Toast.LENGTH_LONG).show();

                    new AlertDialog.Builder(Grace_mark_update.this)
                            .setTitle("ALERT")
                            .setMessage("Marks Successfully updated" )


                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent i = new Intent(Grace_mark_update.this, User_Page.class);
                                    startActivity(i);

                                }
                            })




                            .show();

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
        }
    }

}
