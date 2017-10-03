package com.lanciar.app.mobitrack;

import android.os.AsyncTask;

class loc_service extends AsyncTask<String,String,String> {

    @Override
    protected String doInBackground(String... strings) {
        WebServiceCaller1 caller1=new WebServiceCaller1();
        caller1.setSoapObject("loca");
        caller1.addProperty("quer",strings[0]);
        caller1.callWebService();
        return caller1.getResponse();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);



            }
        }