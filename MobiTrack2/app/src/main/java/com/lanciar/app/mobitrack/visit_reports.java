package com.lanciar.app.mobitrack;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class visit_reports extends Activity implements AdapterView.OnItemClickListener {

    ListView lview;
    ListViewAdapter1 lviewAdapter;
    String id="";
    String repid[]=null;
    String empid[]=null;
    Activity context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_reports);
        id=getIntent().getStringExtra("eid");
        ReportService reportService=new ReportService();
        reportService.execute(id);
        lview = (ListView) findViewById(R.id.listView2);


        lview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(visit_reports.this,visitreport_desc.class);
        intent.putExtra("repid",repid[i]);
        startActivity(intent);
    }

    private class ReportService extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller1 caller1=new WebServiceCaller1();
            caller1.setSoapObject("visitsReport");
            caller1.addProperty("mid",strings[0]);
            caller1.callWebService();
            return caller1.getResponse();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            JSONArray cast = null;
            try {
                cast = new JSONArray(s);
                repid = new String[cast.length()];
                empid = new String[cast.length()];

                int i = 0;
                for (int j = 0; j < cast.length(); j++, i++) {
                    JSONObject actor = cast.getJSONObject(j);
                    repid[i] = actor.getString("repid");
                    empid[i] = actor.getString("empid");

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            lviewAdapter = new ListViewAdapter1(context,repid, empid);
            System.out.println("adapter => "+lviewAdapter.getCount());
            lview.setAdapter(lviewAdapter);
        }
    }
}
