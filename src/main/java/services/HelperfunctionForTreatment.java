package services;

import java.util.ArrayList;
import java.util.HashMap;

public class HelperfunctionForTreatment {
	public static HashMap<String, ArrayList<String>> DiseaseToTreatment;

	public static void initializeMap() {
		DiseaseToTreatment = new HashMap<>();
	}

	public static void addTreatmentForDisease(String disease, String treatment) {
		System.out.println("addTreatmentForDisease "+treatment+" "+disease);
		
		if (!DiseaseToTreatment.containsKey(disease)) {
			ArrayList<String> a = new ArrayList<String>();
			a.add(treatment);
			DiseaseToTreatment.put(disease, a);
		} else {
			DiseaseToTreatment.get(disease).add(treatment);
		}
	}

	public static HashMap<String, ArrayList<String> > getTreatments() {
		return DiseaseToTreatment;
	}

}
