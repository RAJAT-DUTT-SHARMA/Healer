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
            //rs.first();
            int i=0;
            while (rs.next()) {
                objBean = rs.getString("symptom");
                lst.add(objBean);
                i++;
            }
            System.out.println("rows retrieved : "+i );
        } catch (Exception e) {
            System.out.println("in getAllSymptom()" + e);
        }
        return lst;
    }
    
    public ArrayList<String> getAllDiseases() {
        String objBean;
        ArrayList<String> lst = null;
        
        try (Connection con = ConnectDB.connect()
            	) {
        	PreparedStatement pstmt = con.prepareStatement("select * from disease;");
            ResultSet rs = pstmt.executeQuery();
            lst = new ArrayList<>();
            //rs.first();
            int i=0;
            while (rs.next()) {
                objBean = rs.getString("disease");
                lst.add(objBean);
                i++;
            }
            System.out.println("rows retrieved : "+i );
        } catch (Exception e) {
            System.out.println("in getAllDisease()" + e);
        }
        return lst;
    }
    
    public ArrayList<String> getRelatedSymptom(String disease) {
        String objBean;
        ArrayList<String> lst = null;
        
        try (Connection con = ConnectDB.connect()
            	) {
        	PreparedStatement pstmt = con.prepareStatement("select * from symptom_disease_map,disease,symptom where disease.disease='"+disease+"' and disease.disease_id=symptom_disease_map.disease_id and symptom.symp_id=symptom_disease_map.symptom_id;");
            ResultSet rs = pstmt.executeQuery();
            lst = new ArrayList<>();
            //rs.first();
            int i=0;
            while (rs.next()) {
                objBean = rs.getString("symptom");
                lst.add(objBean);
                i++;
            }
            System.out.println("rows retrieved : "+i );
        } catch (Exception e) {
            System.out.println("in getRelatedSymptom()" + e);
        }
        return lst;
    }
    
    public ArrayList<String> getRelatedDisease(String symptom) {
        String objBean;
        ArrayList<String> lst = null;
        
        try (Connection con = ConnectDB.connect()
            	) {
        	PreparedStatement pstmt = con.prepareStatement("select * from symptom_disease_map,disease,symptom where symptom.symptom='"+symptom+"' and symptom.symp_id=symptom_disease_map.symptom_id and disease.disease_id=symptom_disease_map.disease_id;");
            ResultSet rs = pstmt.executeQuery();
            lst = new ArrayList<>();
            //rs.first();
            int i=0;
            while (rs.next()) {
                objBean = rs.getString("disease");
                lst.add(objBean);
                i++;
            }
            System.out.println("rows retrieved : "+i );
        } catch (Exception e) {
            System.out.println("in getRelatedSymptom()" + e);
        }
        return lst;
    }
}
