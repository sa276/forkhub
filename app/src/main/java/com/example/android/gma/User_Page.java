package com.example.android.gma;

import android.content.Intent;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

import static com.loopj.android.http.AsyncHttpClient.log;

public class User_Page extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public  static  int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.first=='G')
                {
                    Intent i =new Intent(User_Page.this,GM_update.class);
                    startActivity(i);
                }
                else {
                    Snackbar.make(view, "This action is only for Grace Mark Allocator", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        Select();
        setImage();
    }

    private void setImage() {

        ImageView iv1=findViewById(R.id.user_image);
        if(MainActivity.dummy_username.equals("SUB.Sabarish"))
        {
            iv1.setImageResource(R.drawable.sabarish);
        }
        else if(MainActivity.dummy_username.equals("SUB.Abirami"))
        {
            iv1.setImageResource(R.drawable.abirami);
        }
        if(MainActivity.dummy_username.equals("SUB.Padmavathi"))
        {
            iv1.setImageResource(R.drawable.padmavathi);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.user__page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.log_out) {
            Intent i=new Intent(User_Page.this,MainActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id==R.id.grade_generate)
        {
            flag=1;
            Intent i=new Intent(User_Page.this,Alldetails.class);
            startActivity(i);
        }

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_mydetails) {
            flag=0;
            Intent i=new Intent(this,User_Details.class);
            startActivity(i);


        } else if (id == R.id.nav_studdetails) {
            if(MainActivity.first=='G')
            {
                Toast.makeText(User_Page.this,"Sorry, you are not authorized to access this feature !!",Toast.LENGTH_LONG).show();
            }
            else {
                flag=0;
                Intent i = new Intent(this, Alldetails.class);
                startActivity(i);
            }

        }
        else if(id==R.id.raise_issue)
        {
            if(MainActivity.first=='A')
            {
                Intent i=new Intent(User_Page.this,Raise_Issue.class);
                startActivity(i);
            }
            else
            {
                Toast.makeText(User_Page.this, "Only for Class Advisors !!", Toast.LENGTH_LONG).show();
            }
        }

        else if (id == R.id.nav_update) {
            if(MainActivity.first=='A' || MainActivity.first=='C' || MainActivity.first=='E')
            {
                Toast.makeText(User_Page.this,"Sorry, this feature is only for subject handling faculties !!",Toast.LENGTH_LONG).show();
            }
            else
            {
                flag=0;
                Intent i=new Intent(User_Page.this,Alldetails.class);
                startActivity(i);
            }

        } else if (id == R.id.nav_interval) {

            if(MainActivity.first=='C')
            {
                flag=0;
                Intent i=new Intent(User_Page.this,Interval.class);
                startActivity(i);
            }





            else
            {
                Toast.makeText(User_Page.this, "Only for Course Mentors !!", Toast.LENGTH_LONG).show();
            }
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void Select()
    {
        final TextView tv=findViewById(R.id.user);
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("username",MainActivity.dummy_username);
        params.add("password",MainActivity.dummy_password);
        client.post("https://quizkrieg.000webhostapp.com/gmaselection.php",params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String name=new String(responseBody);
                log.v("name",name);

                tv.setText("Mr/Ms " + name + "!!!");

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }
}
