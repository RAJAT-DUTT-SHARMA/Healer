package main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import ui.Diagnosis;
import ui.DiseaseToDepartment;
import ui.HelpDesk;
import ui.Treatment;

public class Main {
	
	public static HelpDesk helpDesk = new HelpDesk();
	public static Diagnosis diagnosis = new Diagnosis();
	public static DiseaseToDepartment diseasetodepartment = new DiseaseToDepartment();
	public static Treatment treatment = new Treatment();
	
	public static void main(String[] a) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					helpDesk.setVisible(false);
					diagnosis.setVisible(true);
					diseasetodepartment.setVisible(false);
					treatment.setVisible(false);
					
			        
			        
			        
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
}
