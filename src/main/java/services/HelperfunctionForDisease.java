package services;

import java.util.HashMap;

public class HelperfunctionForDisease{

	static HashMap <String,Integer> diseaseConfidenceMap;

	public static void initializeMap() {
		diseaseConfidenceMap=new HashMap<>(); 
	}
	
	public static void addDiseaseForTreatment(String disease) {
		System.out.println("addDiseaseForTreatment "+disease);
		
		if(diseaseConfidenceMap.containsKey(disease)) {
			int newConfidence=diseaseConfidenceMap.get(disease).intValue()+1;
			diseaseConfidenceMap.replace(disease,new Integer(newConfidence) );
		}else {
			diseaseConfidenceMap.put(disease, new Integer(1));
		}
	}
	
	public static HashMap<String,Integer> getDiseases(){
		return diseaseConfidenceMap;
	}	
}