package services;

import java.util.ArrayList;
import java.util.HashMap;

public class HelperFunctions {

	static HashMap<String, Integer> diseaseConfidenceMap;
	static HashMap<String, ArrayList<String>> symptomToDisease;
	static ArrayList<String> symptom_names;

	public static void initializeMap() {
		diseaseConfidenceMap = new HashMap<>();
		symptomToDisease = new HashMap<>();
	}

	public static void addSymptom(String disease, String symptom) {
		System.out.println("AddSymptomForDisease" + disease + " " + symptom);
		if (!symptomToDisease.containsKey(disease)) {
			symptom_names = new ArrayList<>();
			symptom_names.add(symptom);
			symptomToDisease.put(disease, symptom_names);
		} else {
			symptomToDisease.get(disease).add(symptom);
		}
	}

	public static void addDisease(String disease) {
		System.out.println("addDisease" + disease);

		if (diseaseConfidenceMap.containsKey(disease)) {
			int newConfidence = diseaseConfidenceMap.get(disease).intValue() + 1;
			diseaseConfidenceMap.replace(disease, new Integer(newConfidence));
		} else {
			diseaseConfidenceMap.put(disease, new Integer(1));
		}
	}

	public static HashMap<String, Integer> getDiseases() {
		return diseaseConfidenceMap;
	}

	public static HashMap<String, ArrayList<String>> getSymptomToDisease() {
		return symptomToDisease;
	}

}
