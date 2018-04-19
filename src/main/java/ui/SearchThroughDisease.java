package ui;

import javax.swing.JPanel;



import beans.DiseasePojo;
import services.DBOperations;
import services.HelperFunctionForTest;
import services.HelperfunctionForDepartment;
import services.RuleRunningFunction;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.TextField;
import javax.swing.JLabel;

public class SearchThroughDisease extends JPanel {

	List list;
	TextField textField;
	TextField textField_1;

	public SearchThroughDisease() {
		setLayout(null);

		list = new List();
		list.setBounds(117, 10, 190, 114);
		add(list);

		textField = new TextField();
		textField.setBounds(23, 177, 240, 192);
		add(textField);

		JLabel lblSpecialist = new JLabel("Specialist");
		lblSpecialist.setBounds(100, 157, 46, 14);
		add(lblSpecialist);

		textField_1 = new TextField();
		textField_1.setBounds(307, 177, 184, 113);
		add(textField_1);

		JLabel lblTests = new JLabel("Tests");
		lblTests.setBounds(377, 157, 46, 14);
		add(lblTests);
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
			}
		});

		/*
		 * list.addItemListener(new ItemListener() {
		 * 
		 * @Override public void itemStateChanged(ItemEvent e) { // TODO Auto-generated
		 * method stub String[] selected = list.getSelectedItems(); for (String i :
		 * selected) { RuleRunningFunction.insert(i); } SetSpecialistBox(); } });
		 */

		// initializing important hashmap

	}

	public void SetSpecialistBox(String[] selected) {
		HashMap<String, ArrayList<String>> diseaseToSpecialist = HelperfunctionForDepartment.getDiseaseToDepartment();
		String text = "";
		
		for (String i : selected) {
			text += "" + i + ": ";
			System.out.println("value of i =>"+ i);
			System.out.println("value of item =>"+ diseaseToSpecialist.get(i));
		
			text += "" + diseaseToSpecialist.get(i).toString() + "\n";
		}

		/*
		 * Iterator<Entry<String, ArrayList<String>>> I =
		 * diseaseToSpecialist.entrySet().iterator(); while(I.hasNext()) {
		 * Map.Entry<String, ArrayList<String>> a = I.next();
		 * 
		 * text+=""+a.getKey()+": "; text+=""+a.getValue().toString()+"\n"; }
		 */
		System.out.println(text);
		textField.setText(text);

	}
	
	
	public void SetTestBox(String[] selected) {
		HashMap<String, ArrayList<String>> diseaseToTest = HelperFunctionForTest.getDiseaseToTest();
		String text = "";
		
		for (String i : selected) {
			text += "" + i + ": ";
			System.out.println("value of i =>"+ i);
			System.out.println("value of item =>"+ diseaseToTest.get(i));
		
			text += "" + diseaseToTest.get(i).toString() + "\n";
		}

		/*
		 * Iterator<Entry<String, ArrayList<String>>> I =
		 * diseaseToSpecialist.entrySet().iterator(); while(I.hasNext()) {
		 * Map.Entry<String, ArrayList<String>> a = I.next();
		 * 
		 * text+=""+a.getKey()+": "; text+=""+a.getValue().toString()+"\n"; }
		 */
		System.out.println(text);
		textField_1.setText(text);
	}
}
