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
    
    
    public ArrayList<String> getAllTests() {
        String objBean;
        ArrayList<String> lst = null;
        
        try (Connection con = ConnectDB.connect()
            	) {
        	PreparedStatement pstmt = con.prepareStatement("select * from tests;");
            ResultSet rs = pstmt.executeQuery();
            lst = new ArrayList<>();
            //rs.first();
            int i=0;
            while (rs.next()) {
                objBean = rs.getString("test");
                lst.add(objBean);
                i++;
            }
            System.out.println("rows retrieved : "+i );
        } catch (Exception e) {
            System.out.println("in getAllTests()" + e);
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
    
    public ArrayList<String> getAllSpecialist(){
    	String objBean;
        ArrayList<String> lst = null;
        
        try (Connection con = ConnectDB.connect()
            	) {
        	PreparedStatement pstmt = con.prepareStatement("select * from specialist;");
            ResultSet rs = pstmt.executeQuery();
            lst = new ArrayList<>();
            //rs.first();
            int i=0;
            while (rs.next()) {
                objBean = rs.getString("name");
                lst.add(objBean);
                i++;
            }
            System.out.println("rows retrieved : "+i );
        } catch (Exception e) {
            System.out.println("in getAllDisease()" + e);
        }
        return lst;
    }
    
    public ArrayList<String> getRelatedSpecialist(String disease) {
        String objBean;
        ArrayList<String> lst = null;
        
        try (Connection con = ConnectDB.connect()
            	) {
        	PreparedStatement pstmt = con.prepareStatement("select * from disease_specialist_map,disease,specialist where disease.disease='"+disease+"' and disease.disease_id=disease_specialist_map.disease_id and specialist.specialist_id=disease_specialist_map.specialist_id;");
            ResultSet rs = pstmt.executeQuery();
            lst = new ArrayList<>();
            //rs.first();
            int i=0;
            while (rs.next()) {
                objBean = rs.getString("name");
                lst.add(objBean);
                i++;
            }
            System.out.println("rows retrieved : "+i + lst.toString());
        } catch (Exception e) {
            System.out.println("in getRelatedSymptom()" + e);
        }
        return lst;
    }
    
    public ArrayList<String> getDiseaseForSpecialist(String specialist) {
        String objBean;
        ArrayList<String> lst = null;
        
        try (Connection con = ConnectDB.connect()
            	) {
        	PreparedStatement pstmt = con.prepareStatement("select * from disease_specialist_map,disease,specialist where specialist.name='"+specialist+"' and disease.disease_id=disease_specialist_map.disease_id and specialist.specialist_id=disease_specialist_map.specialist_id;");
            ResultSet rs = pstmt.executeQuery();
            lst = new ArrayList<>();
            //rs.first();
            int i=0;
            while (rs.next()) {
                objBean = rs.getString("disease");
                lst.add(objBean);
                i++;
            }
            System.out.println("rows retrieved : "+i + lst.toString());
        } catch (Exception e) {
            System.out.println("in getDiseaseForSpecialist()" + e);
        }
        return lst;
    }
    
    public ArrayList<String> getTestForDisease(String disease) {
        String objBean;
        ArrayList<String> lst = null;
        
        try (Connection con = ConnectDB.connect()
            	) {
        	PreparedStatement pstmt = con.prepareStatement("select * from disease_to_test_map,disease,tests where disease.disease='"+disease+"' and disease.disease_id=disease_to_test_map.disease_id and tests.test_id=disease_to_test_map.test_id;");
            ResultSet rs = pstmt.executeQuery();
            lst = new ArrayList<>();
            //rs.first();
            int i=0;
            while (rs.next()) {
                objBean = rs.getString("test");
                lst.add(objBean);
                i++;
            }
            System.out.println("rows retrieved : "+i + lst.toString());
        } catch (Exception e) {
            System.out.println("in getTestForDisease()" + e);
        }
        return lst;
    }
    
    
}
