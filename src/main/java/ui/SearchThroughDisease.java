package ui;

import javax.swing.JPanel;

import beans.DiseasePojo;
import services.DBOperations;
import services.HelperFunctionForTest;
import services.HelperFunctions;
import services.HelperfunctionForDepartment;
import services.HelperfunctionForTreatment;
import services.RuleRunningFunction;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.TextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SearchThroughDisease extends JPanel {

	List list;
	JTextArea  textField;
	JTextArea  textField_1;
	JTextArea  textArea;
	private JTextArea textArea_1;
	private JLabel lblProbableSymptom;

	public SearchThroughDisease() {
		setLayout(null);

		list = new List();
		list.setBounds(447, 37, 218, 114);
		add(list);

		textField = new JTextArea ();
		textField.setLineWrap(true);
		textField.setWrapStyleWord(true);
		textField.setBounds(23, 177, 239, 192);
		add(textField);

		JLabel lblSpecialist = new JLabel("Specialist");
		lblSpecialist.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpecialist.setBounds(23, 157, 239, 14);
		add(lblSpecialist);

		textField_1 = new JTextArea ();
		textField_1.setLineWrap(true);
		textField_1.setWrapStyleWord(true);
		textField_1.setBounds(23, 406, 239, 192);
		add(textField_1);

		JLabel lblTests = new JLabel("Tests");
		lblTests.setHorizontalAlignment(SwingConstants.CENTER);
		lblTests.setBounds(23, 386, 239, 14);
		add(lblTests);

		textArea = new JTextArea ();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBounds(328, 177, 490, 421);
		add(textArea);

		JLabel lblTreatmentRecommended = new JLabel("Treatment Recommended");
		lblTreatmentRecommended.setHorizontalAlignment(SwingConstants.CENTER);
		lblTreatmentRecommended.setBounds(328, 157, 490, 14);
		add(lblTreatmentRecommended);

		JLabel lblDiseaseNames = new JLabel("Disease Names");
		lblDiseaseNames.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiseaseNames.setBounds(447, 17, 218, 14);
		add(lblDiseaseNames);
		
		textArea_1 = new JTextArea();
		textArea_1.setWrapStyleWord(true);
		textArea_1.setLineWrap(true);
		textArea_1.setBounds(828, 177, 222, 420);
		add(textArea_1);
		
		lblProbableSymptom = new JLabel("Probable Symptom");
		lblProbableSymptom.setHorizontalAlignment(SwingConstants.CENTER);
		lblProbableSymptom.setBounds(828, 157, 222, 14);
		add(lblProbableSymptom);
		setData();

	}

	public void setData() {
		HelperfunctionForDepartment.initializeMap();
		HelperFunctionForTest.initializeMap();
		DBOperations db = new DBOperations();
		ArrayList<String> AllDiseases = db.getAllDiseases();
		for (String i : AllDiseases) {
			list.add(i);
			RuleRunningFunction.insert(new DiseasePojo(i));
		}
		System.out.println("calling runner");
		RuleRunningFunction.runner();

		list.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[] selected = list.getSelectedItems();

				System.out.println(selected[0]);

				SetSpecialistBox(selected);
				SetTestBox(selected);
				SetTreatmentBox(selected);
				SetSymptomBox(selected);
			}
		});
		// initializing important hashmap

	}

	public void SetSpecialistBox(String[] selected) {
		HashMap<String, ArrayList<String>> diseaseToSpecialist = HelperfunctionForDepartment.getDiseaseToDepartment();
		String text = "";
		for (String i : selected) {
			text += "" + i + ": ";
			System.out.println("value of i =>" + i);
			System.out.println("value of item =>" + diseaseToSpecialist.get(i));
			text += "" + diseaseToSpecialist.get(i).toString() + "\n";
		}
		System.out.println(text);
		textField.setText(text);

	}

	public void SetTestBox(String[] selected) {
		HashMap<String, ArrayList<String>> diseaseToTest = HelperFunctionForTest.getDiseaseToTest();
		String text = "";


		for (String i : selected) {
			text += "" + i + ": \n\n";
			ArrayList<String> tests = diseaseToTest.get(i);
			int count =1 ;
			for(String k: tests) {
				text += ""+count+".) "+k+"\n\n";
				count++;
			}
			
		}
		
		System.out.println(text);
//		textField_1.setLineWrap(true);
		textField_1.setText(text);
		
	}

	public void SetTreatmentBox(String[] selected) {
		HashMap<String, ArrayList<String>> diseaseToTreatment = HelperfunctionForTreatment.getTreatments();
		String text = "";

		for (String i : selected) {
			text += "" + i + ": \n\n";
			ArrayList<String> treatments = diseaseToTreatment.get(i);
			int count =1 ;
			for(String k: treatments) {
				text += ""+count+".) "+k+"\n\n";

				count++;
			}
		}

		System.out.println(text);
//		textArea.setLineWrap(true);
		textArea.setText(text);
	}
	
	public void SetSymptomBox(String[] selected) {
		HashMap<String, ArrayList<String>> diseaseToSymptom = HelperFunctions.getSymptomToDisease();
		String text = "";

		for (String i : selected) {
			text += "" + i + ": \n\n";
			ArrayList<String> symptoms = diseaseToSymptom.get(i);
			int count =1 ;
			for(String k: symptoms) {
				text += ""+count+".) "+k+"\n\n";

				count++;
			}
		}

		System.out.println(text);
//		textArea_1.setLineWrap(true);
		textArea_1.setText(text);
	}
}
