/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imart.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Bao Luan
 */
public class connect {
    private static Connection conn;
    private static ResultSet rs;
    private static  Statement stmt;
    private static String user,pass,url;
    private PreparedStatement ps;
    
    public connect(){
        connectSQL();
        rs=null;
        stmt=null;
        ps= null;
        user="root";
        pass="";
        url="jdbc:mysql://localhost:3306/imart";
    }
        
    public Connection connectSQL(){
        try {
            conn= DriverManager.getConnection(this.url,this.user,this.pass);
            return this.conn;
        } catch (SQLException ex) {
            return null;
        }           
    }
    
    public static ResultSet dataSQL(String statement){
        try {
            stmt = conn.createStatement();
            return stmt.executeQuery(statement);
        } catch (SQLException ex) { 
            System.out.println("Error at function dataSQL (line 47)");
            return null;
        }
    }
    
    public void setDataSQL(String statement){
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(statement);
        } catch (SQLException ex) { 
            System.out.println("Error at class connect (line 57)");
        }
    }
    
    public ResultSet getSQL(String statement, String us, String pw, String status){
        connectSQL();
        try {
            ps = conn.prepareStatement(statement);
            ps.setString(1, us);
            ps.setString(2, pw);
            ps.setString(3,status);
            return ps.executeQuery();
        } catch (SQLException ex) { 
            System.out.println("Error at function dataSQL (line 64)");
            return null;
        }
    }
    
    public void closeSQL(){
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error at line 72");
        }
    }
    
    public static void main(String[] args) {
        connect conn= new connect();
        conn.connectSQL();
        conn.setDataSQL("insert into hoa_don values ('1','NV01','KH01','2020-01-04','0.2','nope')");
    }
}

