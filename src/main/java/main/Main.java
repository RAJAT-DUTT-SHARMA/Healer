package main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import services.DBOperations;
import services.RuleGenerator;
import ui.Diagnosis;
import ui.DiseaseToDepartment;
import ui.HelpDesk;
import ui.Main_ui;
import ui.Treatment;

public class Main {
	
//	public static Main_ui main_ui = new Main_ui();
	
	public static HelpDesk helpDesk = new HelpDesk();
	public static DiseaseToDepartment diseasetodepartment = new DiseaseToDepartment();
	public static Treatment treatment = new Treatment();
	
	public static void main(String[] a) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					main_ui.setVisible(true);
			        DBOperations db = new DBOperations();
			        db.getAllSpecialist();
			        db.getRelatedSpecialist("measles");
			        new RuleGenerator().generateDiseaseToSpecialist();
			        new RuleGenerator().generateSymtomToDiseaseRules();
			        
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
}
