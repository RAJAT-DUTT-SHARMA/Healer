package services;

import java.util.ArrayList;
import java.util.HashMap;

public class HelperfunctionForDepartment {
	static HashMap <String, ArrayList<String>> DiseaseToDepartment;
	static ArrayList<String> department_names;
	static HashMap <String, ArrayList<String>>	DepartmentToDisease;
	static ArrayList<String> disease_names;
	
	public static void initializeMap() {
		DiseaseToDepartment =new HashMap<>(); 
		DepartmentToDisease = new HashMap<>();
	}
	
	public static void addDepartmentForDisease(String disease, String department) {
		System.out.println("adddepartmentfordisease"+department+" "+disease);
		if(!DiseaseToDepartment.containsKey(disease)) {
			department_names = new ArrayList<>();
			department_names.add(department);
			DiseaseToDepartment.put(disease, department_names);
		}
		else {
			DiseaseToDepartment.get(disease).add(department);
		}
	}
	
	public static HashMap<String, ArrayList<String>> getDiseaseToDepartment(){
		
		return DiseaseToDepartment;
	}
	
	public static void addDiseaseForDepartment(String department, String disease) {
		System.out.println("adddiseasefordepartment"+department+" "+disease);
		if(!DiseaseToDepartment.containsKey(department)) {
			disease_names = new ArrayList<>();
			disease_names.add(disease);
			System.out.println(department+" "+disease);
			
			DiseaseToDepartment.put(department, disease_names);
		}
		else {
			System.out.println(department+" "+disease);
			
			DiseaseToDepartment.get(department).add(disease);
		}
	}
	
	public static HashMap<String, ArrayList<String>> getDepartmentToDisease(){
		
		return DepartmentToDisease;
	}
	
}
