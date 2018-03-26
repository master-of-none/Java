/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kishan
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class PrintJob implements Runnable {
 @Override
     public void run(){
         Connection con;
        Statement st;  
        ResultSet rs;
     
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/studentdb";
            con = DriverManager.getConnection(url,"root","");
            st = con.createStatement();
            System.out.println("Before Update");
            String query = "select * from result" ; 
            rs = st.executeQuery(query);
            System.out.println("Records of database:");
            
            while(rs.next())  
                System.out.println(rs.getString(1)+"  "+rs.getString(2));  
            con.close();  
        }
        catch(ClassNotFoundException | SQLException e) {
            System.out.println("Error"+e);
        }
    }
}

class UpdateJob implements Runnable {
     @Override
    public void run(){
         Connection con;
        Statement st;  
        int rs;
        ResultSet rs1;
     
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/studentdb";
            con = DriverManager.getConnection(url,"root","");
            st = con.createStatement();
            String query = "UPDATE result " +
                   "SET Grade = 'S' WHERE Name='sk'";
            rs = st.executeUpdate(query);
            
            System.out.println("After update");
            String query1 = "select * from result" ; 
            rs1 = st.executeQuery(query1);
            System.out.println("Records of database:");
            
            while(rs1.next())  
                System.out.println(rs1.getString(1)+"  "+rs1.getString(2));  
            con.close();  
        }
        catch(ClassNotFoundException | SQLException e) {
            System.out.println("Error"+e);
        }
    }
}
 
public class LostUpdate {
    public static void main(String[] args) {   
        PrintJob pj= new PrintJob();
        UpdateJob uj= new  UpdateJob();
        Thread a = new Thread(pj);
        Thread b = new Thread(uj);
        a.start();
        b.start();
    }
}