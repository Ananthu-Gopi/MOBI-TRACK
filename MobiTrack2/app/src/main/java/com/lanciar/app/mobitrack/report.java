package com.lanciar.app.mobitrack;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class report extends AppCompatActivity {
    String asid="";
    String eid="";
    String tsktype="";
    TextView typ;
    EditText desc;
    Button sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        asid=getIntent().getStringExtra("asgid");
        eid=getIntent().getStringExtra("empid");
        tsktype=getIntent().getStringExtra("tasktype");
        typ = (TextView)findViewById(R.id.type);
        desc = (EditText)findViewById(R.id.rdesc);
        sub = (Button)findViewById(R.id.subm);
        typ.setText(tsktype);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String descr = desc.getText().toString();

                Calendar c = Calendar.getInstance();
                System.out.println("Current time => " + c.getTime());

                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c.getTime());
                //Toast.makeText(report.this, ""+formattedDate, Toast.LENGTH_SHORT).show();
                ReportService reportService = new ReportService();
                reportService.execute(asid,formattedDate,eid,descr);
            }
        });
    }

    private class ReportService extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
        WebServiceCaller1 caller1 = new WebServiceCaller1();
            caller1.setSoapObject("report");
            caller1.addProperty("asgid",strings[0]);
            caller1.addProperty("date",strings[1]);
            caller1.addProperty("empid",strings[2]);
            caller1.addProperty("reports",strings[3]);
            caller1.callWebService();
            String s= caller1.getResponse();
            return s;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.equals("true")){
                Toast.makeText(report.this, "Submitted", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(report.this, "Failed", Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    }
}
