package com.lanciar.app.mobitrack;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class visitreport_desc extends AppCompatActivity {
    TextView rid;
    TextView date;
    TextView descr;
    String x;
    String daterep[]=null;
    String repdesc[]=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitreport_desc);

        rid=(TextView)findViewById(R.id.rep);
        date=(TextView)findViewById(R.id.date);
        descr=(TextView)findViewById(R.id.descri);
        x=getIntent().getStringExtra("repid");
        ReportService reportService=new ReportService();
        reportService.execute(x);
    }

    private class ReportService extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller1 caller1=new WebServiceCaller1();
            caller1.setSoapObject("reportdesc");
            caller1.addProperty("rid",strings[0]);
            caller1.callWebService();
            String s=caller1.getResponse();
            return s;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            JSONArray cast=null;
            try{
                cast = new JSONArray(s);
                daterep = new String[cast.length()];
                repdesc = new String[cast.length()];
                JSONObject actor = cast.getJSONObject(0);
                daterep[0] = actor.getString("date");
                repdesc[0] = actor.getString("descr");
            }catch (Exception e){

            }
            rid.setText(x);
            date.setText(daterep[0]);
            descr.setText(repdesc[0]);
        }
    }
}
