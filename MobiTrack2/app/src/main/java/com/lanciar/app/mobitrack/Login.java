package com.lanciar.app.mobitrack;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    SharedPreferences sharedpreferences;

    EditText e1;
    EditText e2;
    Button button;

//    String usename[]={"test","hello"};
//    String pass[]={"test","hello"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1= (EditText) findViewById(R.id.logname);
        e2= (EditText) findViewById(R.id.logpass);
        button= (Button) findViewById(R.id.logbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name=e1.getText().toString();
                String passs=e2.getText().toString();
                LoginService loginService=new LoginService();
                loginService.execute(name,passs);
            }
        });


    }




    private class LoginService extends AsyncTask<String,String,String> {


        @Override
        protected String doInBackground(String... strings) {


            WebServiceCaller1 caller1 = new WebServiceCaller1();
            caller1.setSoapObject("Login");
            caller1.addProperty("username",strings[0]);
            caller1.addProperty("password",strings[1]);
            caller1.callWebService();
            String s=caller1.getResponse();
            return s;


        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            sharedpreferences = getSharedPreferences("login", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedpreferences.edit();

            editor.putString("id",e1.getText().toString());
            editor.commit();

            if(s!=null)
            {
                if(s.equals("Manager:true"))
                {
                      Intent intent=new Intent(Login.this,ManagerHome.class);
                      String id=e1.getText().toString();
                      intent.putExtra("id",id);
                      startActivity(intent);

                }
                else if(s.equals("Emp:true"))
                {
                    Intent intent1=new Intent(Login.this,EmployeeHome.class);
                    String sss=e1.getText().toString();
                    intent1.putExtra("eid",sss);
                    startActivity(intent1);

                }
                else if(s.equals("false"))
                {
                    Toast.makeText(Login.this,"Invalid User",Toast.LENGTH_SHORT).show();
                }

            }
            else
            {
                Toast.makeText(Login.this,"Error",Toast.LENGTH_SHORT).show();
            }

        }
    }
}
