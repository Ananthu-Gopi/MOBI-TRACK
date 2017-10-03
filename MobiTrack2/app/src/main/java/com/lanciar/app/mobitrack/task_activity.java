package com.lanciar.app.mobitrack;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class task_activity extends AppCompatActivity {
    TextView tsktype;
    TextView tskdesc;
    String tasktype[] = null;
    String taskdesc[] = null;
    Button rprt ;
    String x="";
    String y="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_activity);
        tsktype = (TextView)findViewById(R.id.tsktype);
        tskdesc = (TextView)findViewById(R.id.des);
        rprt = (Button)findViewById(R.id.rep);
        x=getIntent().getStringExtra("asid");
        y=getIntent().getStringExtra("eid");
        //Toast.makeText(this, ""+x, Toast.LENGTH_SHORT).show();
        TaskService taskService = new TaskService();
        taskService.execute(x);
        rprt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(task_activity.this,report.class);
                intent.putExtra("asgid",x);
                intent.putExtra("empid",y);
                intent.putExtra("tasktype",tasktype[0]);
                startActivity(intent);
            }
        });
    }

    private class TaskService extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller1 caller1 = new WebServiceCaller1();
            caller1.setSoapObject("taskactivity");
            caller1.addProperty("asid",strings[0]);
            caller1.callWebService();
            String s=caller1.getResponse();
            return s;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            JSONArray cast = null;
            try {
                cast = new JSONArray(s);
                tasktype = new String[cast.length()];
                taskdesc = new String[cast.length()];

                int i = 0;
                for (int j = 0; j < cast.length(); j++, i++) {
                    JSONObject actor = cast.getJSONObject(j);
                    tasktype[i] = actor.getString("tasktype");
                    taskdesc[i] = actor.getString("taskdesc");

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            tsktype.setText(tasktype[0]);
            tskdesc.setText(taskdesc[0]);
        }
    }
}
