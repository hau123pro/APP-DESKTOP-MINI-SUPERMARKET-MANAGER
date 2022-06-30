/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imart.DAO;

import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Bao Luan
 */
public class sanPhamDAO {
    connect conn= new connect();
    public sanPhamDAO(){
    }
    
    public ResultSet getDBSanPham(){
        conn.connectSQL();
        String statement="select * from san_pham";
        return connect.dataSQL(statement);
    }
    
    public void updateDBSanPham(String id, int soluong){
        conn.connectSQL();
        conn.setDataSQL("update san_pham set soluong='"+soluong+"' where id='"+id.trim()+"'");
        conn.closeSQL();
    }
    
    public void updateDBSanPham2(String id, double soluong){
        conn.connectSQL();
        conn.setDataSQL("update san_pham set dongia='"+soluong+"' where id='"+id.trim()+"'");
        conn.closeSQL();
    }
    
}
