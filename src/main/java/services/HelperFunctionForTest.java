package services;

import java.util.ArrayList;
import java.util.HashMap;

public class HelperFunctionForTest {
	
		static HashMap <String, ArrayList<String>> DiseaseToTest;
		static ArrayList<String> test_names;
		
		public static void initializeMap() {
			DiseaseToTest =new HashMap<>(); 
		}
		
		public static void addTestForDisease(String disease, String test) {
			System.out.println("addTestFordiseae"+test+" "+disease);
			
			if(!DiseaseToTest.containsKey(disease)) {
				
				test_names = new ArrayList<>();
				test_names.add(test);
				DiseaseToTest.put(disease, test_names);
				System.out.println(disease+" "+test);
			}
			else {
				DiseaseToTest.get(disease).add(test);

				System.out.println(disease+" "+test);
			}
		}
		
		public static HashMap<String, ArrayList<String>> getDiseaseToTest(){
			
			return DiseaseToTest;
		}
}
