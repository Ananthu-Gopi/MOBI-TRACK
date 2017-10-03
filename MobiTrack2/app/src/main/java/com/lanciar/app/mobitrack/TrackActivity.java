package com.lanciar.app.mobitrack;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class TrackActivity extends AppCompatActivity {
    EditText tid;
    TextView tdate;
    Button tsub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        tid=(EditText)findViewById(R.id.tid);
        tdate=(TextView) findViewById(R.id.tdate);
        tsub=(Button)findViewById(R.id.tsub);
        tdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mYear;
                int mMonth;
                int mDay;
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(TrackActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                tdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        tsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=tid.getText().toString();
                String date=tdate.getText().toString();
                TrackService trackService=new TrackService();
                trackService.execute(id,date);
            }
        });
    }

    private class TrackService extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller1 caller1=new WebServiceCaller1();
            caller1.setSoapObject("track");
            caller1.addProperty("id",strings[0]);
            caller1.addProperty("date",strings[1]);
            caller1.callWebService();
            return caller1.getResponse();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Intent i = new Intent(TrackActivity.this,MapsActivity.class);
            i.putExtra("val",s);
            startActivity(i);



        }
    }
}
