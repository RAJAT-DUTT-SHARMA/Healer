package services;

import java.util.HashMap;

public class HelperfunctionForDepartment {
	static HashMap <String, String> DiseaseToDepartment;
	
	public static void initializeMap() {
		DiseaseToDepartment =new HashMap<>(); 
	}
	
	public static void addDepartmentForDisease(String disease, String department) {
		System.out.println(disease+" "+department);
		DiseaseToDepartment.put(disease, department);
	}
	
	public static HashMap<String, String> getDiseaseToDepartment(){
		
		return DiseaseToDepartment;
	}
}
