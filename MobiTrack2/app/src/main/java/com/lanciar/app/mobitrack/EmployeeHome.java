package com.lanciar.app.mobitrack;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeHome extends AppCompatActivity {
    Button sout;
    Button punch;
    Button mytask;
    Button attnd;
    SharedPreferences prefs ;
    SharedPreferences pref ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_home);
        prefs= getSharedPreferences("attend", MODE_PRIVATE);
        pref= getSharedPreferences("log", MODE_PRIVATE);
        final String x=getIntent().getStringExtra("eid");
        String at = prefs.getString(x,"out");
        sout = (Button)findViewById(R.id.signout);
        mytask=(Button)findViewById(R.id.task);
        attnd=(Button)findViewById(R.id.attnd);
        punch= (Button) findViewById(R.id.button3);
        if(at.equals("out"))
            punch.setText("punch in");
        else
            punch.setText("punch out");

        punch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                punch.setText("pun");

                Date d=new Date();
                SimpleDateFormat sd=new SimpleDateFormat("dd-MM-yyyy");
                String sdate=sd.format(d);
                SimpleDateFormat ssd=new SimpleDateFormat("hh:mm:ss");
                String stime=ssd.format(d);
                AttendenceService attendenceService=new AttendenceService();


                SharedPreferences.Editor editor = prefs.edit();
                SharedPreferences.Editor editors = pref.edit();


                String at = prefs.getString(x,"out");
                String dat=pref.getString(x,"");
                if(dat.equals(sdate) && at.equals("out"))
                    at="out";
                else if(at.equals("in"))
                    at="in";

                if(at.equalsIgnoreCase("in")) {
                    punch.setText("punch in");
                    editor.putString(x,"out");
                    editor.commit();
                    attendenceService.execute("i",x,stime,sdate);
                    Intent i=new Intent(EmployeeHome.this,locations.class);
                    stopService(i);

                }
                    else {
                    Intent i = new Intent(EmployeeHome.this,locations.class);
                    startService(i);
                    punch.setText("punch out");
                    editor.putString(x,"in");
                    editor.commit();
                    editors.putString(x,sdate);
                    editors.commit();
                    attendenceService.execute("u",x,stime,sdate);

                }

            }
        });
        mytask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeHome.this,my_task.class);

                intent.putExtra("eid",x);
                startActivity(intent);
            }
        });
        sout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(EmployeeHome.this,Login.class);
//                startActivity(intent);
                finish();
                
            }
        });
        attnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(EmployeeHome.this,MyAttendance.class);
                intent.putExtra("eid",x);
                startActivity(intent);
            }
        });

    }


    class AttendenceService extends AsyncTask<String,String,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller1 call=new WebServiceCaller1();
            call.setSoapObject("emp_attendance");
            call.addProperty("check",strings[0]);
            call.addProperty("eid",strings[1]);
            call.addProperty("time",strings[2]);
            call.addProperty("date",strings[3]);

            call.callWebService();
            String d=call.getResponse();
            return d;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.equals("true")){
                Toast.makeText(EmployeeHome.this, "Done", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(EmployeeHome.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
