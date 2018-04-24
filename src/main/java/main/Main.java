package main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import services.DBOperations;
import services.HelperFunctions;
import services.HelperfunctionForDate;
import services.HelperfunctionForDepartment;
import services.HelperfunctionForTreatment;
import services.HelperfunctionForDisease;
import services.RuleGenerator;
import ui.Diagnosis;
import ui.Main_ui;

public class Main {

	

	public static void main(String[] a) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RuleGenerator.generateDiseaseToSpecialist();
					RuleGenerator.generateDiseaseToSymtomRules();
					RuleGenerator.generateDiseaseToTest();
					RuleGenerator.generateSpecialistToDisease();
					RuleGenerator.generateSymtomToDiseaseRules();
					HelperFunctions.initializeMap();
					
					RuleGenerator.generateDiseaseToTreatmentRules();
					RuleGenerator.generateTreatmentToDiseaseRules();
					HelperfunctionForTreatment.initializeMap();
					HelperfunctionForDisease.initializeMap();
					HelperfunctionForDate.initializeMap();
					
					
					Main_ui main_ui = new Main_ui();
					main_ui.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
