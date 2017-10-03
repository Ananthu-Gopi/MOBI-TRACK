package com.lanciar.app.mobitrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManagerHome extends AppCompatActivity {
    Button asgntask;
    Button sout;
    Button vrep;
    Button eattnd;
    Button track;
    String x="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_home);
        eattnd=(Button)findViewById(R.id.eattnd);
        track=(Button)findViewById(R.id.track);
        vrep=(Button)findViewById(R.id.visitrep);
        asgntask = (Button)findViewById(R.id.assign);
        sout = (Button)findViewById(R.id.signout);
        x=getIntent().getStringExtra("id");
        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ManagerHome.this,TrackActivity.class);
                startActivity(intent);
            }
        });
        eattnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ManagerHome.this,EmpAttendance.class);
                startActivity(intent);
            }
        });
        asgntask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerHome.this,AssignTasks.class);
                startActivity(intent);
            }
        });
        vrep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ManagerHome.this,visit_reports.class);
                intent.putExtra("eid",x);
                startActivity(intent);
            }
        });
        sout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
