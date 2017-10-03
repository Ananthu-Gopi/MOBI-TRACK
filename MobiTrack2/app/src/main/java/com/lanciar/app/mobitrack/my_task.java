package com.lanciar.app.mobitrack;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;


import android.app.Activity;
import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class my_task extends Activity implements OnItemClickListener
{
    /** Called when the activity is first created. */

    ListView lview;
    ListViewAdapter lviewAdapter;
    String asid[]=null;
    String task[]=null;
    String mid[]=null;
    Activity context=this;
String x="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_task);
        x=getIntent().getStringExtra("eid");
        TaskService taskService = new TaskService();
        taskService.execute(x);
        lview = (ListView) findViewById(R.id.listView2);


        lview.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
        // TODO Auto-generated method stub
        Intent intent=new Intent(my_task.this,task_activity.class);
        intent.putExtra("asid",asid[position]);
        intent.putExtra("eid",x);
        startActivity(intent);
    }

    private class TaskService extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller1 caller1 = new WebServiceCaller1();
            caller1.setSoapObject("mytask");
            caller1.addProperty("empid",strings[0]);
            caller1.callWebService();
            return caller1.getResponse();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            JSONArray cast = null;
            try {
                cast = new JSONArray(s);
                asid = new String[cast.length()];
                task = new String[cast.length()];
                mid=new String[cast.length()];
                int i = 0;
                for (int j = 0; j < cast.length(); j++, i++) {
                    JSONObject actor = cast.getJSONObject(j);
                    task[i] = actor.getString("task_type");
                    mid[i] = actor.getString("managerid");
                    asid[i]= actor.getString("asid");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            lviewAdapter = new ListViewAdapter(context,task, mid);
            System.out.println("adapter => "+lviewAdapter.getCount());
            lview.setAdapter(lviewAdapter);

        }
    }
}