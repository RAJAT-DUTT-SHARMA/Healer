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
    
    public ArrayList<String> getAllTreatments() {
        String objBean;
        ArrayList<String> lst = null;
        
        try (Connection con = ConnectDB.connect()) {
        	PreparedStatement pstmt = con.prepareStatement("select * from treatment;");
            ResultSet rs = pstmt.executeQuery();
            lst = new ArrayList<>();
            int i=0;
            while (rs.next()) {
                objBean = rs.getString("treatment");
                lst.add(objBean);
                i++;
            }
            System.out.println("rows retrieved : "+i );
        } catch (Exception e) {
            System.out.println("in getAllTreatments()" + e);
        }
        return lst;
    }
    
    public ArrayList<String> getTreatmentForDisease(String disease) {
        String objBean;
        ArrayList<String> lst = null;
        
        try (Connection con = ConnectDB.connect()) {
        	String query="SELECT treatment,description "+
        				 "FROM treatment, "+
        				 	   "("+
        				 	      "SELECT treatment_id "+
        				 	      "FROM disease,disease_treatment_map "+
        				 	      "WHERE (disease.disease_id = disease_treatment_map.disease_id) AND  disease.disease='"+disease+"') AS t_id " +
        			     "WHERE t_id.treatment_id=treatment.treatment_id;";
        	PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            lst = new ArrayList<>();
            int i=0;
            while (rs.next()) {
                objBean = rs.getString("treatment");
                objBean = objBean + ": " + rs.getString("description");
                lst.add(objBean);
                i++;
            }
            System.out.println("rows retrieved : "+i );
        } catch (Exception e) {
            System.out.println("in getTreatmentForDisease()" + e);
        }
        return lst;
    }
    
    public ArrayList<String> getDiseaseForTreatment(String treatment) {
        String objBean;
        ArrayList<String> lst = null;
        
        try (Connection con = ConnectDB.connect()) {
        	String query="SELECT disease "+
        				 "FROM disease, "+
        				 	   "("+
        				 	      "SELECT disease_id "+
        				 	      "FROM treatment,disease_treatment_map "+
        				 	      "WHERE (treatment.treatment_id = disease_treatment_map.treatment_id) AND  treatment.treatment='"+treatment+"') AS d_id " +
        			     "WHERE d_id.disease_id=disease.disease_id;";
        	PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            lst = new ArrayList<>();
            int i=0;
            while (rs.next()) {
                objBean = rs.getString("disease");
                lst.add(objBean);
                i++;
            }
            System.out.println("rows retrieved : "+i );
        } catch (Exception e) {
            System.out.println("in getDiseaseForTreatment()" + e);
        }
        return lst;
    }
    
    public int addDisease(String disease) {
    	int id=-1;
    
    	try (Connection con = ConnectDB.connect()) {
        	
    		String query="insert into disease (disease) values(\""+disease+"\");";
        	PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            
            query="select count(*) from disease";
            pstmt=con.prepareStatement(query);
            rs=pstmt.executeQuery();
            rs.next();
            id=rs.getInt(1);
            
            System.out.println("id  : "+id );
        } catch (Exception e) {
            System.out.println("in addDisease()" + e);
        }
    	return id;
    }
    
    public int addSymptom(String symptom) {
    	int id=-1;
    
    	try (Connection con = ConnectDB.connect()) {
        	
    		String query="insert into symptom (symptom) values(\""+symptom+"\");";
        	PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            
            query="select count(*) from symptom";
            pstmt=con.prepareStatement(query);
            rs=pstmt.executeQuery();
            rs.next();
            id=rs.getInt(1);
            
            System.out.println("id  : "+id );
        } catch (Exception e) {
            System.out.println("in addSymptom()" + e);
        }
    	return id;
    }
    public int addTest(String test) {
    	int id=-1;
    
    	try (Connection con = ConnectDB.connect()) {
        	
    		String query="insert into tests (test) values(\""+test+"\");";
        	PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            
            query="select count(*) from tests";
            pstmt=con.prepareStatement(query);
            rs=pstmt.executeQuery();
            rs.next();
            id=rs.getInt(1);
            
            System.out.println("id  : "+id );
        } catch (Exception e) {
            System.out.println("in addTest()" + e);
        }
    	return id;
    }
    public int addSpecialist(String specialist) {
    	int id=-1;
    
    	try (Connection con = ConnectDB.connect()) {
        	
    		String query="insert into specialist (name) values(\""+specialist+"\");";
        	PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            
            query="select count(*) from specialist";
            pstmt=con.prepareStatement(query);
            rs=pstmt.executeQuery();
            rs.next();
            id=rs.getInt(1);
            
            System.out.println("id  : "+id );
        } catch (Exception e) {
            System.out.println("in addSpecialist()" + e);
        }
    	return id;
    }
    public int addTreatment(String name,String description) {
    	int id=-1;
    
    	try (Connection con = ConnectDB.connect()) {
        	
    		String query="insert into treatment (treatment,description) values(\""+name+"\",\""+description+"\");";
        	PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            
            query="select count(*) from treatment";
            pstmt=con.prepareStatement(query);
            rs=pstmt.executeQuery();
            rs.next();
            id=rs.getInt(1);
            
            System.out.println("id  : "+id );
        } catch (Exception e) {
            System.out.println("in addTreatment()" + e);
        }
    	return id;
    }
    
    public void addDiseaseSymptomMap(int dis,int sym) {
    
    	try (Connection con = ConnectDB.connect()) {
        	
    		String query="insert into symptom_disease_map (symptom_id,disease_id) values("+sym+","+dis+");";
        	PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            
        } catch (Exception e) {
            System.out.println("in addTreatment()" + e);
        }
    	
    }
    
    public void addDiseaseTreatmentMap(int dis,int treat) {
        
    	try (Connection con = ConnectDB.connect()) {
        	
    		String query="insert into disease_treatment_map (disease_id,treatment_id) values("+dis+","+treat+");";
        	PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
        } catch (Exception e) {
            System.out.println("in addTreatment()" + e);
        }
    	
    }
    
public void addDiseaseTestMap(int dis,int test) {
        
    	try (Connection con = ConnectDB.connect()) {
        	
    		String query="insert into disease_to_test_map (disease_id,test_id) values("+dis+","+test+");";
        	PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
        } catch (Exception e) {
            System.out.println("in addTreatment()" + e);
        }
    	
    }

public void addDiseaseSpecialisttMap(int dis,int spid) {
    
	try (Connection con = ConnectDB.connect()) {
    	
		String query="insert into disease_specialist_map (disease_id,specialist_id) values("+dis+","+spid+");";
    	PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
    } catch (Exception e) {
        System.out.println("in addTreatment()" + e);
    }
	
}


public int getDiseaseId(String disease) {
	int id=-1;
	try (Connection con = ConnectDB.connect()) {
    	
		String query="select disease_id from disease where disease=\""+disease+"\";";
    	PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        id=rs.getInt(1);
    } catch (Exception e) {
        System.out.println("in addTreatment()" + e);
    }
	return id;
}

public int getSymptomId(String symptom) {	
	int id=-1;
	try (Connection con = ConnectDB.connect()) {
    	
		String query="select symp_id from symptom where symptom=\""+symptom+"\";";
    	PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        id=rs.getInt(1);
    } catch (Exception e) {
        System.out.println("in addTreatment()" + e);
    }
	return id;
	
}public int getSpecialistId(String specialist) {
	int id=-1;
	try (Connection con = ConnectDB.connect()) {
    	
		String query="select specialist_id from specialist where name=\""+specialist+"\";";
    	PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        id=rs.getInt(1);
    } catch (Exception e) {
        System.out.println("in addTreatment()" + e);
    }
	return id;
}public int getTreatmentId(String treatment) {
	int id=-1;
	try (Connection con = ConnectDB.connect()) {
    	
		String query="select treatment_id from treatment where treatment=\""+treatment+"\";";
    	PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        id=rs.getInt(1);
    } catch (Exception e) {
        System.out.println("in addTreatment()" + e);
    }
	return id;
}
public int getTestId(String test) {
	int id=-1;
	try (Connection con = ConnectDB.connect()) {
    	
		String query="select test_id from tests where test=\""+test+"\";";
    	PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        id=rs.getInt(1);
    } catch (Exception e) {
        System.out.println("in addTreatment()" + e);
    }
	return id;
}
    
    
    
    
}

