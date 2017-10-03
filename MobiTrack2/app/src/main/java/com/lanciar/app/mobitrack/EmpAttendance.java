package com.lanciar.app.mobitrack;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class EmpAttendance extends AppCompatActivity {
    EditText ed;
    TextView fdate;
    TextView sdate;
    Button sub;
    TextView hr;
    String eid="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_attendance);
        ed=(EditText)findViewById(R.id.id);
        fdate=(TextView)findViewById(R.id.fdate);
        sdate=(TextView)findViewById(R.id.sdate);
        sub=(Button)findViewById(R.id.sub);
        hr=(TextView)findViewById(R.id.hr);
        fdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mYear;
                int mMonth;
                int mDay;
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(EmpAttendance.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                fdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        sdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mYear;
                int mMonth;
                int mDay;
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(EmpAttendance.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                sdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eid=ed.getText().toString();
                String s=fdate.getText().toString();
                String e=sdate.getText().toString();
               MyAttendanceService myAttendanceService=new MyAttendanceService();
                myAttendanceService.execute(s,e,eid);
            }
        });
    }

    private class MyAttendanceService extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller1 caller1=new WebServiceCaller1();
            caller1.setSoapObject("myattendance");
            caller1.addProperty("fdate",strings[0]);
            caller1.addProperty("sdate",strings[1]);
            caller1.addProperty("eid",strings[2]);
            caller1.callWebService();
            return caller1.getResponse();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            long milliseconds=Long.parseLong(s);
            long seconds = milliseconds / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            long days = hours / 24;
            String time =hours % 24 + " H :" + minutes % 60 + " M :" + seconds % 60+ " S";

            hr.setText( time);
        }
    }
}
