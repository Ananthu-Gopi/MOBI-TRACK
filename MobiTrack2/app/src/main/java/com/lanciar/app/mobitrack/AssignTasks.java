package com.lanciar.app.mobitrack;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;

public class AssignTasks extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView t1;
    EditText eid;
    EditText desc;
    TextView date;
    Button sub;
    Spinner spin;
    String task[];
    ArrayAdapter arrayAdapter;
    String dd="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_tasks);
        t1=(TextView)findViewById(R.id.text);
        eid=(EditText)findViewById(R.id.empid);
        desc=(EditText)findViewById(R.id.desc);
        date=(TextView) findViewById(R.id.datecom);
        sub=(Button)findViewById(R.id.submit);
        spin=(Spinner)findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        SpinService spinService = new SpinService();
        spinService.execute();
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mYear;
                int mMonth;
                int mDay;
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(AssignTasks.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        sub.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String empid = eid.getText().toString();
                String description = desc.getText().toString();
                String dates = date.getText().toString();
                LoginService loginService = new LoginService();
                loginService.execute(empid,description,dates,dd);

            }

        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
       dd=spin.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//        String selected = adapterView.getItemAtPosition(i).toString();
//        spin.setSelection(1);
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }


    private class LoginService extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {

            WebServiceCaller1 caller1 = new WebServiceCaller1();
            caller1.setSoapObject("assigntask");
            caller1.addProperty("employeeid",strings[0]);
            caller1.addProperty("descr",strings[1]);
            caller1.addProperty("compdate",strings[2]);
            caller1.addProperty("tasktype",strings[3]);
            caller1.callWebService();
            return caller1.getResponse();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.equals("true")){
                Toast.makeText(AssignTasks.this,"Insert Successful",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(AssignTasks.this,"Failed",Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    }

    private class SpinService extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller1 caller = new WebServiceCaller1();
            caller.setSoapObject("spinner");
            caller.callWebService();
            return caller.getResponse();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String task[];
            try {
                JSONArray cast = new JSONArray(s);
                task=new String[cast.length()];
                int i = 0;
                for (int j = 0; j < cast.length(); j++, i++) {
                    JSONObject actor = cast.getJSONObject(j);
                    task[i]=actor.getString("tasktype");
                }



                arrayAdapter = new ArrayAdapter(getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        task);
                spin.setAdapter(arrayAdapter);


//                  arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,task);
//                 aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                 spin.setAdapter(aa);
            }
            catch (Exception e)
            {

            }
        }
    }

}
