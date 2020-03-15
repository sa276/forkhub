package com.example.android.gma;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


import static com.loopj.android.http.AsyncHttpClient.log;

public class Grade_generate extends AppCompatActivity {
    public String LVarea = "Kuttralam";
    public String name, name_to_extract;
    public static String sms_name,sms_contact;
    public static int sgpa;
    public int size;
    public String getName_to_extract;
    private ProgressDialog progressDialog;

    public static int s1_gind,s2_gind,s3_gind;

    String grades[]={"O","A+","A","B+","B","C","P","F"};
    double points[]={10,9.5,9,8,7,6,5};

    public static int s1_at,s2_at,s3_at,s1_p1,s1_p2,s1_es,s2_p1,s2_p2,s2_es,s3_p1,s3_p2,s3_es,s1_ca,s2_ca,s3_ca,gm,s1_cons_mark,s2_cons_mark,s3_cons_mark;

    public static int flag=0;
    public static int check=0;
    public static  int s1o_min,s1o_max,s1aplus_min,s1aplus_max,s1a_min,s1a_max,s1bplus_min,s1bplus_max,s1b_min,s1b_max,s1c_min,s1c_max,s1p_min,s1p_max,s1f_min,s1f_max;
    public static  int s2o_min,s2o_max,s2aplus_min,s2aplus_max,s2a_min,s2a_max,s2bplus_min,s2bplus_max,s2b_min,s2b_max,s2c_min,s2c_max,s2p_min,s2p_max,s2f_min,s2f_max;
    public static  int s3o_min,s3o_max,s3aplus_min,s3aplus_max,s3a_min,s3a_max,s3bplus_min,s3bplus_max,s3b_min,s3b_max,s3c_min,s3c_max,s3p_min,s3p_max,s3f_min,s3f_max;

    public static  String s1grade,s2grade,s3grade;

    public static float s1ce,s2ce,s3ce;

    public static int index=0;

    public static int s1change=0,s2change=0,s3change=0;
    public static int diff1,diff2,diff3,ind;

    public ArrayList<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_generate);

        TextView bt1 = findViewById(R.id.update);
        progressDialog = new ProgressDialog(this);

        Button b2=findViewById(R.id.gmalloc);
        b2.setVisibility(View.INVISIBLE);

        Button b3=findViewById(R.id.sms);
        b3.setVisibility(View.INVISIBLE);

        Button b1=findViewById(R.id.calc);

        s2change=0;
        s3change=0;
        s1change=0;

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

                TextView tv1 = (TextView) findViewById(R.id.name_input);
                TextView tv2 = (TextView) findViewById(R.id.roll_input);
                TextView tv3 = (TextView) findViewById(R.id.cgpa_input);
                TextView tv4 = (TextView) findViewById(R.id.contact_input);
                TextView tv5 = (TextView) findViewById(R.id.gm_input);
                TextView tv6 = (TextView) findViewById(R.id.reason_input);


                ImageView iv1 = (ImageView) findViewById(R.id.user_image);

                tv1.setText("" + data2[0]);
                tv2.setText("" + data2[1]);
                tv3.setText("" + data2[15]);
                tv4.setText("" + data2[14]);
                tv5.setText("" + data2[16]);
                tv6.setText("" + data2[17]);

                sms_name=data2[0];
                sms_contact=data2[14];
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




    public void grade_calc(View view) {

        progressDialog.setMessage("Calculating...");
        progressDialog.show();

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("username", MainActivity.dummy_username);
        params.add("password", MainActivity.dummy_password);
        params.add("name", name_to_extract);
        client.post("https://quizkrieg.000webhostapp.com/gma_grade_calc.php", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                progressDialog.dismiss();
                name = new String(responseBody);
                log.v("name", name);
                String[] data3 = name.split("_");


                if ((data3[0].equals("N/A")) || (data3[1].equals("N/A")) || (data3[2].equals("N/A")) || (data3[3].equals("N/A"))
                        || (data3[4].equals("N/A")) || (data3[5].equals("N/A")) || (data3[6].equals("N/A")) || (data3[7].equals("N/A"))
                        || data3[8].equals("N/A") || data3[9].equals("N/A") || data3[10].equals("N/A") || data3[11].equals("N/A")
                        || data3[12].equals(("N/A")) || (data3[13].equals("N/A")) )
                {
                    Toast.makeText(Grade_generate.this,"Some details are missing !!!",Toast.LENGTH_LONG).show();
                }

                else
                {

                    log.e("s1at",data3[0]);
                    s1_at=Integer.parseInt(data3[0]);
                    s1_ca=Integer.parseInt(data3[1]);
                    s2_ca=Integer.parseInt(data3[2]);
                    s3_ca=Integer.parseInt(data3[3]);
                    gm=Integer.parseInt(data3[4]);
                    s1_p1=Integer.parseInt(data3[5]);
                    s1_p2=Integer.parseInt(data3[6]);
                    s1_es=Integer.parseInt(data3[7]);
                    s2_p1=Integer.parseInt(data3[8]);
                    s2_p2=Integer.parseInt(data3[9]);
                    s2_es=Integer.parseInt(data3[10]);
                    s3_p1=Integer.parseInt(data3[11]);
                    s3_p2=Integer.parseInt(data3[12]);
                    s3_es=Integer.parseInt(data3[13]);
                    s2_at=Integer.parseInt(data3[14]);
                    s3_at=Integer.parseInt(data3[15]);

                    float s1_p1f= (float) ((s1_p1/50.0)*15.0);
                    float s1_p2f=(float)((s1_p2/50.0)*15.0);
                    float s1_esf=(float)((s1_es/100.0)*50.0);
                    float s1_cons_markf=s1_p1f +s1_p2f +s1_esf+s1_ca +s1_at;
                    s1_cons_mark= (int) s1_cons_markf;



                    float s2_p1f= (float) ((s2_p1/50.0)*15.0);
                    float s2_p2f=(float)((s2_p2/50.0)*15.0);
                    float s2_esf=(float)((s2_es/100.0)*50.0);
                    float s2_cons_markf=s2_p1f +s2_p2f +s2_esf+s2_ca +s2_at;
                    s2_cons_mark= (int) s2_cons_markf;

                    float s3_p1f= (float) ((s3_p1/50.0)*15.0);
                    float s3_p2f=(float)((s3_p2/50.0)*15.0);
                    float s3_esf=(float)((s3_es/100.0)*50.0);
                    float s3_cons_markf=s3_p1f +s3_p2f +s3_esf+s3_ca +s3_at;
                    s3_cons_mark= (int) s3_cons_markf;




                    TextView tv1=findViewById(R.id.s1cons_marks_input);
                    TextView tv2=findViewById(R.id.s2cons_marks_input);
                    TextView tv3=findViewById(R.id.s3cons_marks_input);

                    tv1.setText("" + s1_cons_mark + "/105");
                    tv2.setText(""+s2_cons_mark+ "/105");
                    tv3.setText(""+s3_cons_mark+ "/105");

                    grade_gen();
                }



            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });


    }

    public void grade_gen()
    {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("username", MainActivity.dummy_username);
        params.add("password", MainActivity.dummy_password);
        params.add("name", name_to_extract);
        client.post("https://quizkrieg.000webhostapp.com/gma_grade_gen.php", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                progressDialog.dismiss();
                name = new String(responseBody);
                log.v("name", name);
                String[] data4 = name.split("_");


                s1o_min=Integer.parseInt(data4[0]);
                s1o_max=Integer.parseInt(data4[1]);
                s1aplus_min=Integer.parseInt(data4[2]);
                s1aplus_max=Integer.parseInt(data4[3]);
                s1a_min=Integer.parseInt(data4[4]);
                s1a_max=Integer.parseInt(data4[5]);
                s1bplus_min=Integer.parseInt(data4[6]);
                s1bplus_max=Integer.parseInt(data4[7]);
                s1b_min=Integer.parseInt(data4[8]);
                s1b_max=Integer.parseInt(data4[9]);
                s1c_min=Integer.parseInt(data4[10]);
                s1c_max=Integer.parseInt(data4[11]);
                s1p_min=Integer.parseInt(data4[12]);
                s1p_max=Integer.parseInt(data4[13]);
                s1f_min=Integer.parseInt(data4[14]);
                s1f_max=Integer.parseInt(data4[15]);




                s2o_min=Integer.parseInt(data4[16]);
                s2o_max=Integer.parseInt(data4[17]);
                s2aplus_min=Integer.parseInt(data4[18]);
                s2aplus_max=Integer.parseInt(data4[19]);
                s2a_min=Integer.parseInt(data4[20]);
                s2a_max=Integer.parseInt(data4[21]);
                s2bplus_min=Integer.parseInt(data4[22]);
                s2bplus_max=Integer.parseInt(data4[23]);
                s2b_min=Integer.parseInt(data4[24]);
                s2b_max=Integer.parseInt(data4[25]);
                s2c_min=Integer.parseInt(data4[26]);
                s2c_max=Integer.parseInt(data4[27]);
                s2p_min=Integer.parseInt(data4[28]);
                s2p_max=Integer.parseInt(data4[29]);
                s2f_min=Integer.parseInt(data4[30]);
                s2f_max=Integer.parseInt(data4[31]);





                s3o_min=Integer.parseInt(data4[32]);
                s3o_max=Integer.parseInt(data4[33]);
                s3aplus_min=Integer.parseInt(data4[34]);
                s3aplus_max=Integer.parseInt(data4[35]);
                s3a_min=Integer.parseInt(data4[36]);
                s3a_max=Integer.parseInt(data4[37]);
                s3bplus_min=Integer.parseInt(data4[38]);
                s3bplus_max=Integer.parseInt(data4[39]);
                s3b_min=Integer.parseInt(data4[40]);
                s3b_max=Integer.parseInt(data4[41]);
                s3c_min=Integer.parseInt(data4[42]);
                s3c_max=Integer.parseInt(data4[43]);
                s3p_min=Integer.parseInt(data4[44]);
                s3p_max=Integer.parseInt(data4[45]);
                s3f_min=Integer.parseInt(data4[46]);
                s3f_max=Integer.parseInt(data4[47]);


                log.e("omin","" + s2o_min);
                log.e("omax","" + s2o_max);
                log.e("aplumin","" + s2aplus_min);
                log.e("aplusmax","" + s2aplus_max);
                log.e("amin","" + s2a_min);
                log.e("amax","" + s2a_max);
                log.e("bplusmin","" + s2bplus_min);
                log.e("bplusmax","" + s2bplus_max);
                log.e("bmin","" + s2b_min);
                log.e("bmax","" + s2b_max);
                log.e("cmin","" + s2c_min);
                log.e("cmax","" + s2c_max);
                log.e("pmin","" + s2p_min);
                log.e("pmax","" + s2p_max);



                TextView tv10=findViewById(R.id.s1_grade);
                TextView tv11=findViewById(R.id.s2_grade);
                TextView tv12=findViewById(R.id.s3_grade);


             if(s1_cons_mark>=s1o_min )
             {

                 s1grade="O";
                 tv10.setBackgroundColor(getResources().getColor(R.color.o));
                 s1ce=30;
                 diff1=0;
                 s1_gind=0;

             }
             else if(s1_cons_mark>=s1aplus_min && s1_cons_mark<=s1aplus_max)
             {
                 s1grade="A+";
                 tv10.setBackgroundColor(getResources().getColor(R.color.a));
                 s1ce= (float) 28.5;
                 diff1=s1o_min-s1_cons_mark;
                 s1_gind=1;
             }
             else if(s1_cons_mark>=s1a_min && s1_cons_mark<=s1a_max)
             {
                 s1grade="A";
                 tv10.setBackgroundColor(getResources().getColor(R.color.a));
                 s1ce=27;
                 diff1=s1aplus_min-s1_cons_mark;
                 s1_gind=2;
             }
             else if(s1_cons_mark>=s1bplus_min && s1_cons_mark<=s1bplus_max)
             {
                 s1grade="B+";
                 tv10.setBackgroundColor(getResources().getColor(R.color.b));
                 s1ce=24;
                 diff1=s1a_min-s1_cons_mark;
                 s1_gind=3;
             }
             else if(s1_cons_mark>=s1b_min && s1_cons_mark<=s1b_max)
             {
                 s1grade="B";
                 tv10.setBackgroundColor(getResources().getColor(R.color.b));
                 s1ce=21;
                 diff1=s1bplus_min-s1_cons_mark;
                 s1_gind=4;
             }
             else if(s1_cons_mark>=s1c_min && s1_cons_mark<=s1c_max)
             {
                 s1grade="C";
                 tv10.setBackgroundColor(getResources().getColor(R.color.c));
                 s1ce=18;
                 diff1=s1b_min-s1_cons_mark;
                 s1_gind=5;
             }
             else if(s1_cons_mark>=s1p_min && s1_cons_mark<=s1p_max)
             {
                 s1grade="P";
                 tv10.setBackgroundColor(getResources().getColor(R.color.p));
                 s1ce=15;
                 diff1=s1c_min-s1_cons_mark;
                 s1_gind=6;
             }
             else if(s1_cons_mark>=s1f_min && s1_cons_mark<=s1f_max)
             {
                 s1grade="F";
                 tv10.setBackgroundColor(getResources().getColor(R.color.f));
                 s1ce=0;
                 diff1=s1p_min-s1_cons_mark;
                 s1_gind=7;
             }
             log.e("grade",s1grade);
             tv10.setText(""+s1grade);






                if(s2_cons_mark>=s2o_min )
                {

                    s2grade="O";
                    tv11.setBackgroundColor(getResources().getColor(R.color.o));
                    s2ce=30;
                    diff2=0;
                    s2_gind=0;

                }
                else if(s2_cons_mark>=s2aplus_min && s2_cons_mark<=s2aplus_max)
                {
                    s2grade="A+";
                    tv11.setBackgroundColor(getResources().getColor(R.color.a));
                    s2ce= (float) 28.5;
                    diff2=s2o_min-s2_cons_mark;
                    s2_gind=1;

                }
                else if(s2_cons_mark>=s2a_min && s2_cons_mark<=s2a_max)
                {
                    s2grade="A";
                    tv11.setBackgroundColor(getResources().getColor(R.color.a));
                    s2ce=27;
                    diff2=s2aplus_min-s2_cons_mark;
                    s2_gind=2;

                }
                else if(s2_cons_mark>=s2bplus_min && s2_cons_mark<=s2bplus_max)
                {
                    s2grade="B+";
                    tv11.setBackgroundColor(getResources().getColor(R.color.b));
                    s2ce=24;
                    diff2=s2a_min-s2_cons_mark;
                    s2_gind=3;

                }
                else if(s2_cons_mark>=s2b_min && s2_cons_mark<=s2b_max)
                {
                    s2grade="B";
                    tv11.setBackgroundColor(getResources().getColor(R.color.b));
                    s2ce=21;
                    diff2=s2bplus_min-s2_cons_mark;
                    s2_gind=4;

                }
                else if(s2_cons_mark>=s2c_min && s2_cons_mark<=49)
                {
                    s2grade="C";
                    tv11.setBackgroundColor(getResources().getColor(R.color.c));
                    s1ce=18;
                    diff2=s2b_min-s2_cons_mark;
                    s2_gind=5;

                }
                else if(s2_cons_mark>=s2p_min && s2_cons_mark<=s2p_max)
                {
                    s2grade="P";
                    tv11.setBackgroundColor(getResources().getColor(R.color.p));
                    s1ce=15;
                    diff2=s2c_min-s2_cons_mark;
                    s2_gind=6;

                }
                else if(s2_cons_mark>=s2f_min && s2_cons_mark<=s2f_max)
                {
                    s2grade="F";
                    tv11.setBackgroundColor(getResources().getColor(R.color.f));
                    s1ce=0;
                    diff2=s2p_min-s2_cons_mark;
                    s2_gind=7;

                }
                log.e("grade",s1grade);
                tv11.setText(""+s2grade);






                if(s3_cons_mark>=s3o_min )
                {

                    s3grade="O";
                    tv12.setBackgroundColor(getResources().getColor(R.color.o));
                    s3ce=40;
                    diff3=0;
                    s3_gind=0;



                }
                else if(s3_cons_mark>=s3aplus_min && s3_cons_mark<=s3aplus_max)
                {
                    s3grade="A+";
                    tv12.setBackgroundColor(getResources().getColor(R.color.a));
                    s3ce=38;
                    diff3=s3o_min-s3_cons_mark;
                    s3_gind=1;
                }
                else if(s3_cons_mark>=s3a_min && s3_cons_mark<=s3a_max)
                {
                    s3grade="A";
                    tv12.setBackgroundColor(getResources().getColor(R.color.a));
                    s3ce=36;
                    diff3=s3aplus_min-s3_cons_mark;
                    s3_gind=2;
                }
                else if(s3_cons_mark>=s3bplus_min && s3_cons_mark<=s3bplus_max)
                {
                    s3grade="B+";
                    tv12.setBackgroundColor(getResources().getColor(R.color.b));
                    s3ce=32;
                    diff3=s3a_min-s3_cons_mark;
                    s3_gind=3;
                }
                else if(s3_cons_mark>=s3b_min && s3_cons_mark<=s3b_max)
                {
                    s3grade="B";
                    tv12.setBackgroundColor(getResources().getColor(R.color.b));
                    s3ce=28;
                    diff3=s3bplus_min-s3_cons_mark;
                    s3_gind=4;
                }
                else if(s3_cons_mark>=s3c_min && s3_cons_mark<=49)
                {
                    s3grade="C";
                    tv12.setBackgroundColor(getResources().getColor(R.color.c));
                    s1ce=24;
                    diff3=s3b_min-s3_cons_mark;
                    s3_gind=5;
                }
                else if(s3_cons_mark>=s3p_min && s3_cons_mark<=s1p_max)
                {
                    s3grade="P";
                    tv12.setBackgroundColor(getResources().getColor(R.color.p));
                    s1ce=20;
                    diff3=s3c_min-s3_cons_mark;
                    s3_gind=6;
                }
                else if(s3_cons_mark>=s3f_min && s3_cons_mark<=s3f_max)
                {
                    s3grade="F";
                    tv12.setBackgroundColor(getResources().getColor(R.color.f));
                    s1ce=0;
                    diff3=s3p_min-s3_cons_mark;
                    s3_gind=7;
                }
                //log.e("grade",s1grade);
                tv12.setText(""+s3grade);

                TextView tv13=findViewById(R.id.s1ce_input);
                TextView tv14=findViewById(R.id.s2ce_input);
                TextView tv15=findViewById(R.id.s3ce_input);

                tv13.setText("" + s1ce);
                tv14.setText(""+ s2ce);
                tv15.setText("" + s3ce);

                tv13.setBackgroundColor(getResources().getColor(R.color.blue));
                tv14.setBackgroundColor(getResources().getColor(R.color.blue));
                tv15.setBackgroundColor(getResources().getColor(R.color.blue));

                Button b3=findViewById(R.id.calc);
                b3.setVisibility(View.INVISIBLE);

                Button b4=findViewById(R.id.gmalloc);
                b4.setVisibility(View.VISIBLE);

                Button b5=findViewById(R.id.sms);
                b5.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });




    }


    public void gmalloc(View view)
    {
        log.e("diff1",""+diff1);
        log.e("diff2",""+diff2);
        log.e("diff3",""+diff3);

        if(gm==0)
        {
            Snackbar.make(view,"Sorry, " + name_to_extract + " does not have grace marks to change the grades",Snackbar.LENGTH_LONG).setAction("ACTION",null).show();
        }

        for(int i=0;i<3;i++)
        {
            if(diff3<=gm && diff3!=0 &&s3change!=1)
            {
                s3_cons_mark=s3_cons_mark+diff3;
                gm=gm-diff3;
                s3change=1;
                s3_gind-=1;

            }
            else if(diff2<=gm && diff2!=0 &&s2change!=1)
            {
                s2_cons_mark=s2_cons_mark+diff2;
                gm=gm-diff2;
                s2change=1;
                s2_gind-=1;

            }
            else if(diff1<=gm && diff1!=0 &&s1change!=1)
            {
                s1_cons_mark=s1_cons_mark+diff1;
                gm=gm-diff1;
                s1change=1;
                s1_gind-=1;
            }
        }
        TextView tv20=findViewById(R.id.s1cons_marks_input);
        TextView tv21=findViewById(R.id.s2cons_marks_input);
        TextView tv22=findViewById(R.id.s3cons_marks_input);
        TextView tv23=findViewById(R.id.gm_input);

        TextView tv24=findViewById(R.id.s1_grade);
        TextView tv25=findViewById(R.id.s2_grade);
        TextView tv26=findViewById(R.id.s3_grade);

        tv20.setText("" + s1_cons_mark +"/105");
        tv21.setText("" + s2_cons_mark+"/105");
        tv22.setText("" + s3_cons_mark+"/105");
        tv23.setText("" + gm);

        tv24.setText("" + grades[s1_gind]);
        tv25.setText("" + grades[s2_gind]);
        tv26.setText("" + grades[s3_gind]);

        log.e("s1g",""+ s1_gind);
        log.e("s2g",""+ s2_gind);
        log.e("s3g",""+ s3_gind);

        flag=2;

        if(s1_gind==0)
        {
            tv24.setBackgroundColor(getResources().getColor(R.color.o));
        }
        else if(s1_gind==1 || s1_gind==2)
        {
            tv24.setBackgroundColor(getResources().getColor(R.color.a));
        }
        else if(s1_gind==3 || s1_gind==4)
        {
            tv24.setBackgroundColor(getResources().getColor(R.color.b));
        }
        else if(s1_gind==5)
        {
            tv24.setBackgroundColor(getResources().getColor(R.color.c));
        }
        else if(s1_gind==6)
        {
            tv24.setBackgroundColor(getResources().getColor(R.color.p));
        }
        else if(s1_gind==7)
        {
            tv24.setBackgroundColor(getResources().getColor(R.color.f));
        }






        if(s2_gind==0)
        {
            tv25.setBackgroundColor(getResources().getColor(R.color.o));
        }
        else if(s2_gind==1 || s2_gind==2)
        {
            tv25.setBackgroundColor(getResources().getColor(R.color.a));
        }
        else if(s2_gind==3 || s2_gind==4)
        {
            tv25.setBackgroundColor(getResources().getColor(R.color.b));
        }
        else if(s2_gind==5)
        {
            tv25.setBackgroundColor(getResources().getColor(R.color.c));
        }
        else if(s2_gind==6)
        {
            tv25.setBackgroundColor(getResources().getColor(R.color.p));
        }
        else if(s2_gind==7)
        {
            tv25.setBackgroundColor(getResources().getColor(R.color.f));
        }






        if(s3_gind==0)
        {
            tv26.setBackgroundColor(getResources().getColor(R.color.o));
        }
        else if(s3_gind==1 || s3_gind==2)
        {
            tv26.setBackgroundColor(getResources().getColor(R.color.a));
        }
        else if(s3_gind==3 || s3_gind==4)
        {
            tv26.setBackgroundColor(getResources().getColor(R.color.b));
        }
        else if(s3_gind==5)
        {
            tv26.setBackgroundColor(getResources().getColor(R.color.c));
        }
        else if(s3_gind==6)
        {
            tv26.setBackgroundColor(getResources().getColor(R.color.p));
        }
        else if(s3_gind==7)
        {
            tv26.setBackgroundColor(getResources().getColor(R.color.f));
        }


            Button b10=findViewById(R.id.sms);
            Button b11=findViewById(R.id.calc);
            b11.setVisibility(View.INVISIBLE);

            Button b12=findViewById(R.id.gmalloc);
            b12.setVisibility(View.INVISIBLE);

            b10.setVisibility(View.VISIBLE);

            if(s3change==1)
            {
                if(s3_gind==0 ||s3_gind==1)
                {
                    s3ce=s3ce+2;
                }
                else
                {
                    s3ce=s3ce+4;
                }
            }
        else if(s2change==1)
        {
            if(s2_gind==0 ||s2_gind==1)
            {
                s2ce= (float) (s2ce+1.5);
            }
            else
            {
                s2ce=s2ce+3;
            }
        }

            else if(s1change==1)
            {
                if(s1_gind==0 || s1_gind==1)
                {
                    s1ce= (float) (s1ce+1.5);
                }
                else
                {
                    s1ce=s1ce+3;
                }
            }
            TextView tv50=findViewById(R.id.s1ce_input);
            TextView tv51=findViewById(R.id.s2ce_input);
            TextView tv52=findViewById(R.id.s3ce_input);

            tv50.setText("" + s1ce);
            tv51.setText(""+s2ce);
            tv52.setText(""+s3ce);

    }

    public void sms(View view)
    {

        log.e("grade1",s1grade);
        log.e("grade2",s2grade);
        log.e("grade3",s3grade);
        name_to_extract=name_to_extract.trim();
        log.e("nametoextract",name_to_extract);
        if(MainActivity.first=='E') {


            sms_contact = sms_contact.trim();
            String msg = "Hey " + sms_name + "!!! The results are out." + "You have recieved the following grades :" + "\n" +
                    "Software Engineering : " + grades[s1_gind] + "\n" +
                    "Networks : " + grades[s2_gind] + "\n" +
                    "Compiler Design : " + grades[s3_gind] + "\n" +
                    "Congratulations !!!";

            SmsManager sms = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.DONUT) {
                sms = SmsManager.getDefault();
                sms.sendTextMessage(sms_contact, null, msg, null, null);
            }


            progressDialog.setMessage("Sending Report...");
            progressDialog.show();

            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            params.add("username", MainActivity.dummy_username);
            params.add("password", MainActivity.dummy_password);
            Alldetails.name_to_extract.trim();
            params.add("name", name_to_extract);

            params.add("s1grade", s1grade);
            params.add("s2grade", s2grade);
            params.add("s2grade", s3grade);




            log.e("contact", sms_contact);


            client.post("https://quizkrieg.000webhostapp.com/grade_update.php", params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                    progressDialog.dismiss();
                    //Toast.makeText(request.this, new String(responseBody), Toast.LENGTH_LONG).show();

                    new AlertDialog.Builder(Grade_generate.this)
                            .setTitle("ALERT")
                            .setMessage("Students table is updated and report is successfully sent to " + Alldetails.name_to_extract)

                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Continue with delete operation
                                    Intent i = new Intent(Grade_generate.this, User_Page.class);
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
        else
        {
            Snackbar.make(view,"Sorry, only exam controllers can send reports",Snackbar.LENGTH_LONG).setAction("ACTION",null).show();
        }

    }


    }



