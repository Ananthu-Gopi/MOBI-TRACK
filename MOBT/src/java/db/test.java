/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.PageContext;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author hp
 */
public class test {

public String goo()
{
    String id="";
//    String time="04:40:47";
    String sdate="12-04-2017";
    String fdate="12-04-2017";
   String eid="2";
     try
        {
       Date last_date_date = new SimpleDateFormat("dd-MM-yyyy").parse(fdate);
       String fdat=new SimpleDateFormat("yyyy-MM-dd").format(last_date_date);
       Date sec_date_date = new SimpleDateFormat("dd-MM-yyyy").parse(sdate);
       String sdat=new SimpleDateFormat("yyyy-MM-dd").format(sec_date_date);
       
       
       System.out.println("myattendance");
        ConnectionClass con=new ConnectionClass();
        String sd="SELECT * FROM tbl_attendance WHERE dates between '"+fdat+"' AND '"+sdat+"' AND emp_id='"+eid+"'";
//               
            System.out.println(">>>>>>>>>>>>>>>>>>>"+sd);
        ResultSet rs=con.selectCommand(sd);
        String date1="";
        String date2="";
       long diff=0;
        Date date1f ;
        Date date2f ;
       while(rs.next())
        {
        date1=rs.getString("attnd_intime");
        date2=rs.getString("attnd_outtime");
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        date1f = format.parse(date1);
        date2f = format.parse(date2);
        diff+=date1f.getTime()-date2f.getTime(); 
        }
//       
            System.out.println(">>>>>>>>>>>>>>>>>"+diff);
//        
        }
        catch(Exception e)
        {
            
        }
    
    
    return null;
    }
        
    
    
    public static void main(String[] args) throws ParseException {
        
        test t=new test();
       String ss= t.goo();
        System.out.println(ss);
//        
    
//        String fdate="22-04-2016";
     
    }
}
