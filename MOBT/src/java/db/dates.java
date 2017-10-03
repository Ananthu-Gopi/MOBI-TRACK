/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author hp
 */
public class dates {
    
    public static void main(String[] args) {
        dates d=new dates();
        d.checks("s","s","s");
    }
    
    public long checks(String fdate,String sdate,String eid)
    {
//                 sdate="19-04-2017";
//                 fdate="18-04-2017";
//                   eid="2";
    
        System.out.println("fdate="+fdate+"sdate="+sdate+"eid="+eid);
         long diff=0;
        try
        {
       
            
       Date last_date_date = new SimpleDateFormat("dd-MM-yyyy").parse(fdate);
       String fdat=new SimpleDateFormat("yyyy-MM-dd").format(last_date_date);
       Date sec_date_date = new SimpleDateFormat("dd-MM-yyyy").parse(sdate);
       String sdat=new SimpleDateFormat("yyyy-MM-dd").format(sec_date_date);
       System.out.println("myattendance");
       
       
        ConnectionClass con=new ConnectionClass();
        String sd="SELECT * FROM tbl_attendance WHERE dates between '"+fdat+"' AND '"+sdat+"' AND emp_id='"+eid+"'";  
            System.out.println(">>>>>>>>>>>>>>>>>>>"+sd);
        ResultSet rs=con.selectCommand(sd);
        String date1="";
        String date2="";
      
        Date date1f ;
        Date date2f ;
       while(rs.next())
        {
        date1=rs.getString("attnd_intime");
        date2=rs.getString("attnd_outtime");
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        date1f = format.parse(date1);
        date2f = format.parse(date2);
        diff+=date2f.getTime()-date1f.getTime(); 
        }
//       
            System.out.println(">>>>>>>>>>>>>>>>>"+diff);
        }
        catch(Exception e)
        {
            
        }
        return diff;
        }
    
}
