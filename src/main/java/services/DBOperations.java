/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 *
 * @author Er.RajatDS
 */
public class DBOperations {
    
    
    public ArrayList<String> getAllSymptom() {
        String objBean;
        ArrayList<String> lst = null;
        
        try (Connection con = ConnectDB.connect()
            	) {
        	PreparedStatement pstmt = con.prepareStatement("select * from symptom;");
            ResultSet rs = pstmt.executeQuery();
            lst = new ArrayList<>();
            rs.first();
            while (rs.next()) {
                objBean = rs.getString("symptom");
                lst.add(objBean);
            }
        } catch (Exception e) {
            System.out.println("in getAllSymptom()" + e);
        }
        return lst;
    }
       
}
