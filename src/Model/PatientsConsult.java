/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import View.*;
import javax.swing.table.*;

/**
 *
 * @author Michael Cabusas
 */
public class PatientsConsult extends Conn{
    
    Conn conn = new Conn() {};
    readPanel r; 
    addPanel a;
    updatePanel u;
    deletePanel d;
    updateInfoPanel update;

    public void setUpdate(updateInfoPanel update) {
        this.update = update;
    }
    

    public void setD(deletePanel d) {
        this.d = d;
    }
   

    public void setU(updatePanel u) {
        this.u = u;
    }
    

    public void setA(addPanel a) {
        this.a = a;
    }
    
    

    public void setR(readPanel r) {
        this.r = r;
    }
    
    public boolean insert(Patients p) throws ClassNotFoundException, SQLException{
        
        PreparedStatement st;
        Connection con = conn.getConn();
        boolean ret = false;
        String query = "INSERT INTO patients(fName, lName, bloodType, gender, age) VALUES(?,?,?,?,?)";

            try {
                st = con.prepareStatement(query);
                st.setString(1, p.getfName());
                st.setString(2, p.getlName());
                st.setString(3, p.getBloodType());
                st.setString(4, p.getGender());
                st.setInt(5, p.getAge());
                st.execute();
                ret = true;
            } catch (SQLException e) {
                System.out.println(e);
            }
        return ret;
    }
    
    public boolean update(Patients p) throws ClassNotFoundException, SQLException{
        PreparedStatement st;
        Connection con = conn.getConn();
        boolean ret = false;
        String q = "UPDATE patients SET fName=?, lName=?, gender=?, age=?, bloodtype=? WHERE id=?";
        
        int x;

            try {
                st = con.prepareStatement(q);
                st.setString(1, p.getfName());
                st.setString(2, p.getlName());
                st.setString(3, p.getGender());
                st.setInt(4, p.getAge());
                st.setString(5, p.getBloodType());
                st.setInt(6, p.getId());
                int count = st.executeUpdate();
                if(count != 0){
                    ret = true;
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
           
            
        return ret;
    }
    
    public boolean delete(int id) throws ClassNotFoundException, SQLException{
        PreparedStatement st;
        Connection con = getConn();
        boolean ret = false;

        String query = "DELETE FROM patients WHERE id=?";

            try {
                st = con.prepareStatement(query);
                st.setInt(1, id);
                int count = st.executeUpdate();
                if(count >= 1 ){
                    ret = true;
                }
                
                System.out.println("return value: " + ret);
            } catch (SQLException e) {
                System.out.println(e);
                
            }
        return ret;
    }
   
     public void read() throws ClassNotFoundException, SQLException{
        
        //readPanel r = new readPanel();
        PreparedStatement st;
        Connection con = conn.getConn();
        ResultSet rs;

        String query = "SELECT * FROM patients";
            try {
                st = con.prepareStatement(query);
                rs = st.executeQuery();
                DefaultTableModel tm = (DefaultTableModel)r.tab.getModel();
                tm.setRowCount(0);
                while(rs.next()){
                    Object o[] = {rs.getInt("id"), rs.getString("fName"), rs.getString("lName"), rs.getString("bloodType"), rs.getString("gender"), rs.getInt(("age"))};
                    tm.addRow(o);
                    
                }
            } catch (SQLException e) {
                System.out.println(e);
            }  
    }
     
    public boolean search(int id) throws ClassNotFoundException, SQLException{
        PreparedStatement st;
        Connection con = getConn();
        Object obj[] = new Object[5];
        ResultSet rs;
        boolean ret = false;

        String query = "SELECT * "
                + "     FROM patients WHERE id = ?";

            try {
                st = con.prepareStatement(query);
                st.setInt(1, id);
                rs = st.executeQuery();
                if(rs.next()){
                   
                   update.fNameUpdate.setText(rs.getString("fName"));
                   update.lNameUpdate.setText(rs.getString("lName"));
                   update.ageUpdate.setText(Integer.toString(rs.getInt("age")));
                   update.bloodTypeUpdate.setSelectedItem((rs.getString("bloodType")));
                   update.genderUpdate.setSelectedItem(rs.getString(("gender")));
                   ret = true;
                }
            } catch (SQLException e) {
                System.out.println("exception: " + e);
            }
        return ret;
    }
    
}
