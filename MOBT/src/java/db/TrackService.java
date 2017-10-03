/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author hp
 */
@WebService(serviceName = "TrackService")
public class TrackService {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Login")
    public String Login(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        //TODO write your implementation code here:
        String response="";
        ConnectionClass con=new ConnectionClass();
        String query="select * from tbl_employee where emp_id='"+username+"' and emp_password='"+password+"' and emp_status=1";
        System.err.println(query);
        ResultSet rs=con.selectCommand(query);
        try {
            if(rs.next())
            {
                String empid=rs.getString("emp_id");
                String did=rs.getString("desig_code");
                System.out.println(""+did);
                if(did.equals("mng"))
                {
                   response="Manager:true"; 
                }
                else if(did.equals("emp"))
                {
                response="Emp:true";
                }
            }
            else
            {
                response="false";
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TrackService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(response);
        
        return ""+response;
        
    }


    /**
     * Web service operation
     */
    @WebMethod(operationName = "spinner")
    public String spinner() {
        //TODO write your implementation code here:
        ConnectionClass con = new ConnectionClass();
        String sel = "select * from tbl_task";
        ResultSet rs=con.selectCommand(sel);
        JSONArray ar = new JSONArray();
        try{
            rs.last();
            JSONObject o[] = new JSONObject[rs.getRow()];
            int i=0;
            rs.beforeFirst();
            while(rs.next()){
                o[i] = new JSONObject();
                o[i].put("tasktype",rs.getString("task_type"));
                ar.put(o[i]);
                 i++;
            }
        }
        catch(Exception e){
            
        }
         return ar.toString();
    }


    /**
     * Web service operation
     */
    @WebMethod(operationName = "assigntask")
    public String assigntask(@WebParam(name = "employeeid") String employeeid, @WebParam(name = "descr") String descr, @WebParam(name = "compdate") String compdate, @WebParam(name = "tasktype") String tasktype) {
       String response="";
        try {
            //TODO write your implementation code here:
           
            String id_task="";
            ConnectionClass con = new ConnectionClass();
            String sss="select * from tbl_task where task_type='"+tasktype+"'";
            ResultSet d=con.selectCommand(sss);
            if(d.next())
                id_task=d.getString("task_id");
            
            descr= descr.replace("'", "");
            
            String ins = "insert into tbl_assigntask(assign_date,emp_id,description,task_type,task_id) values('"+compdate+"','"+employeeid+"','"+descr+"','"+tasktype+"','"+id_task+"')";
            boolean b = con.executeCommand(ins);
            if(b){ 
                response="true";
            }
            else{
                response="false";
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TrackService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return ""+response;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "mytask")
    public String mytask(@WebParam(name = "empid") String empid) {
        //TODO write your implementation code here:
        String mid = null;
        String test= null;
        ConnectionClass con = new ConnectionClass();
        JSONArray arr = new JSONArray();
         try{
        String str="select * from tbl_assigntask where emp_id = '"+empid+"'and status=0";
        ResultSet rs = con.selectCommand(str);
        String sd2="";
        String sd="";
            String sd1="";
         sd="select * from tbl_employee where emp_id='"+empid+"'";
         ResultSet dd=con.selectCommand(sd);
         if (dd.next()) {
            sd1="select manager_id from tbl_department where dept_code='"+dd.getString("dept_code")+"'";
            ResultSet aa = con.selectCommand(sd1);
            if(aa.next()){
               test = aa.getString("manager_id");
            }
            sd2="select emp_name from tbl_employee where emp_id='"+test+"'";
            ResultSet bb = con.selectCommand(sd2);
            if(bb.next()){
                mid=bb.getString("emp_name");
            }
         }
       
            rs.last();
            JSONObject o[] = new JSONObject[rs.getRow()];
            int i=0;
            rs.beforeFirst();
            while(rs.next()){
                
               
                o[i] = new JSONObject();
                o[i].put("asid",rs.getString("assign_id"));
                o[i].put("task_type",rs.getString("task_type"));
                o[i].put("managerid",mid);
                arr.put(o[i]);
                 i++;
            }
        }
        catch(Exception e){
            
        }
         System.out.println(arr);
         return arr.toString();
       
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "taskactivity")
    public String taskactivity(@WebParam(name = "asid") String asid) {
        //TODO write your implementation code here:
        
        ConnectionClass con = new ConnectionClass();
        JSONArray ar = new JSONArray();
        String que="select * from tbl_assigntask where assign_id = '"+asid+"'";
        ResultSet rs = con.selectCommand(que);
        try{
            rs.last();
            JSONObject o[] = new JSONObject[rs.getRow()];
            int i=0;
            rs.beforeFirst();
            while(rs.next()){
                o[i] = new JSONObject();
                o[i].put("tasktype",rs.getString("task_type"));
                o[i].put("taskdesc",rs.getString("description"));
                ar.put(o[i]);
                 i++;
            }
        }
        catch(Exception e){
            
        }
         return ar.toString();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "report")
    public String report(@WebParam(name = "asgid") String asgid, @WebParam(name = "date") String date, @WebParam(name = "empid") String empid, @WebParam(name = "reports") String reports) {
        //TODO write your implementation code here:
        String response ="";
        ConnectionClass con = new ConnectionClass();
        String que = "insert into tbl_reports(assign_id,report_date,emp_id,report_details)values('"+asgid+"','"+date+"','"+empid+"','"+reports+"')";
        String que1 = "update tbl_assigntask set status=1 where assign_id='"+asgid+"'";
        con.executeCommand(que1);
        boolean b = con.executeCommand(que);
        if(b){
            response ="true";
        }
        else{
            response="false";
        }
        return ""+response;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "visitsReport")
    public String visitsReport(@WebParam(name = "mid") String mid) {
       ConnectionClass con = new ConnectionClass();
        JSONArray ar =new JSONArray();
        int d=0;
        String que="select dept_code from tbl_employee where emp_id='"+mid+"'";
        ResultSet rs = con.selectCommand(que);
        try {
            if(rs.next()){
                String sel="select * from tbl_employee where desig_code='emp' and dept_code='"+rs.getString("dept_code")+"'";
                ResultSet rr=con.selectCommand(sel);
                rr.last();
//                JSONObject o[] = new JSONObject[rr.getRow()];
                int i=0;
                rr.beforeFirst();
                while(rr.next()){
                    String sel1="select * from tbl_reports where emp_id='"+rr.getString("emp_id")+"'";
                    ResultSet rr1=con.selectCommand(sel1);
                    while(rr1.next()){
                        d++;
                    }
                }
            }
            
            
            
            
                        String ques="select dept_code from tbl_employee where emp_id='"+mid+"'";
        ResultSet rss = con.selectCommand(ques);
        
            if(rss.next()){
                String sel="select * from tbl_employee where desig_code='emp' and dept_code='"+rs.getString("dept_code")+"'";
                ResultSet rr=con.selectCommand(sel);
                rr.last();
                JSONObject o[] = new JSONObject[d];
                int i=0;
                rr.beforeFirst();
                while(rr.next()){
                    String sel1="select * from tbl_reports where emp_id='"+rr.getString("emp_id")+"'";
                    ResultSet rr1=con.selectCommand(sel1);
                    String sel2="select emp_name from tbl_employee where emp_id='"+rr.getString("emp_id")+"'";
                    ResultSet rr2=con.selectCommand(sel2);
                    rr2.next();
                    while(rr1.next()){
                   o[i] = new JSONObject();
                        o[i].put("repid",rr1.getString("report_id"));
                        o[i].put("empid",rr2.getString("emp_name"));
                        ar.put(o[i]);
                        i++;
                    }
                }
            }
        } catch (Exception ex) {
            
        }
        return ar.toString();
        
    }
    /**
     * Web service operation
     */
    @WebMethod(operationName = "attendance")
    public String goal(@WebParam(name = "sss") String sss) {
        String response="";
        try
        {
ConnectionClass con=new ConnectionClass();
        
        
        System.out.println("helloooooooooooooooo");
        
        
        boolean b=con.executeCommand(sss);
        System.out.println(sss);
        if(b){
           response="true"; 
        }
        else{
            response="false";
        }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return response;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "emp_attendance")
    public String emp_attendance(@WebParam(name = "check") String check,@WebParam(name = "eid") String eid, @WebParam(name = "time") String time, @WebParam(name = "date") String date) {    
        String id="";
        
        System.out.println("check"+check+" eid"+eid+" time"+time+" date"+date);
        boolean b=false;
        try
        {
ConnectionClass con=new ConnectionClass();                   
            if(!(check.equals("u")))
            {
                
        String h="select * from tbl_attendance where emp_id='"+eid+"'";
        ResultSet rs=con.selectCommand(h);
            while (rs.next()) 
            {                
                if(rs.getString("attnd_outtime").equals("no"))
                {
                    id=rs.getString("attnd_id");
                    System.out.println("outtime");
                    break;
                }
            }
        String query1="update tbl_attendance set attnd_outtime=CURTIME() where dates=CURDATE() and attnd_id='"+id+"'";
        b=con.executeCommand(query1);
            }
            else
            {
        String query="insert into tbl_attendance(emp_id,attnd_intime,dates,attnd_outtime)values('"+eid+"',CURTIME(),CURDATE(),'no')";
        b=con.executeCommand(query);
        
            }
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        String n="";
        if(b)
            n="true";
        else
            n="false";
        return n;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "reportdesc")
    public String reportdesc(@WebParam(name = "rid") String rid) {
        //TODO write your implementation code here:
        ConnectionClass con=new ConnectionClass();
        JSONArray ar=new JSONArray();
        String que="select * from tbl_reports where report_id='"+rid+"'";
        ResultSet rs=con.selectCommand(que);
        try{
            JSONObject o[]=new JSONObject[1];
            rs.next();
            o[0]=new JSONObject();
            o[0].put("date",rs.getString("report_date"));
            o[0].put("descr",rs.getString("report_details"));
            ar.put(o[0]);
            
            
        }catch(Exception e){
            
        }
        return ar.toString();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "myattendance")
    public String myattendance(@WebParam(name = "fdate") String fdate, @WebParam(name = "sdate") String sdate, @WebParam(name = "eid") String eid) {
//        //TODO write your implementation code here:
        
        System.out.println(fdate+" "+sdate+" "+eid);
        String ss="";
        try
        {
       dates d=new dates();
      long s=d.checks(fdate, sdate, eid);
      ss=""+s;
            System.out.println("sssssssssssssssssssss"+ss);
        }
        catch(Exception e)
        {
            
        }
        return ss;
    }
    
    @WebMethod(operationName = "loca")
    public String loca(@WebParam(name = "quer") String quer) {
    ConnectionClass con=new ConnectionClass();
   boolean b=con.executeCommand(quer);
   String sd="";
   if(b)
       sd="true";
   else
       sd="false";

        return sd;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "track")
    public String track(@WebParam(name = "id") String id, @WebParam(name = "date") String date) {
        //TODO write your implementation code here:
        ConnectionClass con=new ConnectionClass();
        JSONArray ar=new JSONArray();
         try{
       Date sec_date_date = new SimpleDateFormat("dd-MM-yyyy").parse(date);
       String sdat=new SimpleDateFormat("yyyy-MM-dd").format(sec_date_date);
        String query="select * from tbl_location where emp_id='"+id+"' and date='"+sdat+"'";
        ResultSet rs=con.selectCommand(query);
        
            rs.last();
            JSONObject o[] = new JSONObject[rs.getRow()];
            int i=0;
            rs.beforeFirst();
            while(rs.next()){
                o[i] = new JSONObject();
                o[i].put("latitude",rs.getString("latitude"));
                o[i].put("longitude",rs.getString("longitude"));
                ar.put(o[i]);
                 i++;
            }
    }catch(Exception e){
        
    }
        return ar.toString();
}
}
