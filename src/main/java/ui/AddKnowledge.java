package ui;

import javax.swing.JPanel;

import services.DBOperations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.TextArea;

public class AddKnowledge extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextArea textArea;
	private JLabel lblError;

	public AddKnowledge() {
		setLayout(null);
		DBOperations ops=new DBOperations();
		
		JPanel add_disease = new JPanel();
		add_disease.setBorder(new LineBorder(Color.DARK_GRAY));
		add_disease.setBounds(12, 37, 179, 318);
		add(add_disease);
		add_disease.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(12, 59, 155, 24);
		comboBox.addItem("Add New");
		
		ArrayList<String> disease=ops.getAllDiseases();
		for(String d:disease) {
			comboBox.addItem(d);
		}
		comboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				
				if(e.getStateChange()==ItemEvent.SELECTED) {
					lblError.setVisible(false);
					lblError.setText("");
					if(comboBox.getSelectedIndex()==0){
						comboBox.setEditable(false);
						textField.setEditable(true);
						comboBox.setEnabled(false);
					}	
				}
			}
		});
		comboBox.setSelectedIndex(-1);
		
		add_disease.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Select/Add Disease");
		lblNewLabel.setBounds(12, 12, 155, 24);
		add_disease.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(12, 153, 155, 30);
		add_disease.add(textField);
		textField.setColumns(10);
		textField.setEditable(false);
		
		
		JLabel lblNewLabel_1 = new JLabel("Enter Disease");
		lblNewLabel_1.setBounds(12, 126, 155, 15);
		add_disease.add(lblNewLabel_1);
		
		
		
		
		
		JPanel add_symptom = new JPanel();
		add_symptom.setBorder(new LineBorder(Color.DARK_GRAY));
		add_symptom.setLayout(null);
		add_symptom.setBounds(203, 37, 179, 318);
		add(add_symptom);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(12, 59, 155, 24);
		comboBox_1.addItem("Add New");
		ArrayList<String> symptoms=ops.getAllSymptom();
		for(String s:symptoms) {
			comboBox_1.addItem(s);
		}
		comboBox_1.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED) {
					lblError.setVisible(false);
					lblError.setText("");
					
					if(comboBox_1.getSelectedIndex()==0){
						comboBox_1.setEditable(false);
						textField_1.setEditable(true);
						comboBox_1.setEnabled(false);
					}	
				}
			}
		});
		comboBox_1.setSelectedIndex(-1);
		
		add_symptom.add(comboBox_1);
		
		JLabel lblSelectOrAdd = new JLabel("Select /Add Symptom");
		lblSelectOrAdd.setBounds(12, 12, 155, 24);
		add_symptom.add(lblSelectOrAdd);
		
		JLabel lblEnterSymptom = new JLabel("Enter Symptom");
		lblEnterSymptom.setBounds(12, 126, 155, 15);
		add_symptom.add(lblEnterSymptom);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(12, 153, 155, 30);
		add_symptom.add(textField_1);
		
		
		
		
		JPanel add_test = new JPanel();
		add_test.setBorder(new LineBorder(Color.DARK_GRAY));
		add_test.setLayout(null);
		add_test.setBounds(392, 37, 179, 318);
		add(add_test);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(12, 59, 155, 24);
		comboBox_2.addItem("Add New");
		
		ArrayList<String> tests=ops.getAllTests();
		for(String t:tests) {
			comboBox_2.addItem(t);
		}
		comboBox_2.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED) {
					lblError.setVisible(false);
					lblError.setText("");
					
					if(comboBox_2.getSelectedIndex()==0){
						comboBox_2.setEditable(false);
						textField_2.setEditable(true);
						comboBox_2.setEnabled(false);
					}	
				}
			}
		});
		comboBox_2.setSelectedIndex(-1);
		
		add_test.add(comboBox_2);
		
		JLabel lblSelectaddTest = new JLabel("Select/Add Test");
		lblSelectaddTest.setBounds(12, 12, 155, 24);
		add_test.add(lblSelectaddTest);
		
		JLabel lblEnterTest = new JLabel("Enter Test");
		lblEnterTest.setBounds(12, 125, 155, 15);
		add_test.add(lblEnterTest);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(12, 152, 155, 30);
		textField_2.setEditable(false);
		add_test.add(textField_2);
		
		
		
		
		JPanel add_treatment = new JPanel();
		add_treatment.setBorder(new LineBorder(Color.DARK_GRAY));
		add_treatment.setLayout(null);
		add_treatment.setBounds(583, 37, 179, 318);
		add(add_treatment);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(12, 59, 155, 24);
		comboBox_3.addItem("Add New");
		
		ArrayList<String> treatments=ops.getAllTreatments();
		for(String t:treatments) {
			comboBox_3.addItem(t);
		}
		
		comboBox_3.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED) {
					lblError.setVisible(false);
					lblError.setText("");
					
					if(comboBox_3.getSelectedIndex()==0){
						comboBox_3.setEditable(false);
						textField_3.setEditable(true);
						textArea.setEditable(true);
						comboBox_3.setEnabled(false);
					}	
				}
			}
		});
		
		comboBox_3.setSelectedIndex(-1);
		
		add_treatment.add(comboBox_3);
		
		JLabel lblSelectaddTreatment = new JLabel("Select/Add Treatment");
		lblSelectaddTreatment.setBounds(12, 12, 155, 24);
		add_treatment.add(lblSelectaddTreatment);
		
		JLabel lblEnterTreatment = new JLabel("Enter Treatment");
		lblEnterTreatment.setBounds(12, 124, 155, 15);
		add_treatment.add(lblEnterTreatment);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(12, 151, 155, 30);
		add_treatment.add(textField_3);
		
		JLabel lblEnterDescription = new JLabel("Enter Description");
		lblEnterDescription.setBounds(12, 194, 155, 15);
		add_treatment.add(lblEnterDescription);
		
		textArea = new JTextArea();
		textArea.setBounds(12, 215, 155, 91);
		textArea.setEditable(false);
		add_treatment.add(textArea);
		
		
		
		
		
		JPanel add_specialist = new JPanel();
		add_specialist.setLayout(null);
		add_specialist.setBorder(new LineBorder(Color.DARK_GRAY));
		add_specialist.setBounds(774, 37, 179, 318);
		add(add_specialist);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(12, 59, 155, 24);
		comboBox_4.addItem("Add New");
		
		ArrayList<String> specialists=ops.getAllSpecialist();
		for(String s:specialists) {
			comboBox_4.addItem(s);
		}
		comboBox_4.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED) {
					lblError.setVisible(false);
					lblError.setText("");
					
					if(comboBox_4.getSelectedIndex()==0){
						comboBox_4.setEditable(false);
						textField_4.setEditable(true);
						comboBox_4.setEnabled(false);
					}	
				}
			}
		});
		
		comboBox_4.setSelectedIndex(-1);
			
		add_specialist.add(comboBox_4);
		
		JLabel lblSelectaddSpecialist = new JLabel("Select/Add Specialist");
		lblSelectaddSpecialist.setBounds(12, 12, 155, 24);
		add_specialist.add(lblSelectaddSpecialist);
		
		JLabel lblEnterSpecialist = new JLabel("Enter Specialist");
		lblEnterSpecialist.setBounds(12, 121, 155, 15);
		add_specialist.add(lblEnterSpecialist);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(12, 148, 155, 30);
		add_specialist.add(textField_4);
		
		JButton btnNewButton = new JButton("Add Knowledge");
		btnNewButton.setBounds(392, 386, 179, 25);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				lblError.setForeground(Color.RED);
				
				if(textField.isEditable()) {
					if(disease.contains(textField.getText()) || textField.getText().isEmpty()) {
						lblError.setText("Disease already exists or is empty");
						lblError.setVisible(true);
						textField.setText("");
						textField.setEditable(false);
						comboBox.setEnabled(true);
						comboBox.setSelectedIndex(-1);
						return;
					}
				}
				if(textField_1.isEditable()) {
					if(symptoms.contains(textField_1.getText()) || textField_1.getText().isEmpty()) {
						lblError.setText("Symptom already exists or is empty");
						lblError.setVisible(true);
						textField_1.setText("");
						textField_1.setEditable(false);
						comboBox_1.setEnabled(true);
						return ;
					}	
				}
				if(textField_2.isEditable()) {
					if(tests.contains(textField_2.getText()) || textField_2.getText().isEmpty()) {
						lblError.setText("Test already exists or is empty");
						lblError.setVisible(true);
						textField_2.setText("");
						textField_2.setEditable(false);
						comboBox_2.setEnabled(true);
						comboBox_2.setSelectedIndex(-1);
						return;
					}
				}if(textField_3.isEditable()) {
					if(treatments.contains(textField_3.getText()) || textField_3.getText().isEmpty()) {
						lblError.setText("Treatment already exists or is empty");
						lblError.setVisible(true);
						textField_3.setText("");
						textField_3.setEditable(false);
						textArea.setText("");
						textArea.setEditable(false);
						comboBox_3.setEnabled(true);
						comboBox_3.setSelectedIndex(-1);
						return;
					}else if(textArea.getText().isEmpty()) {
						lblError.setText("Treatment description is empty");
						lblError.setVisible(true);
						textField_3.setText("");
						textField_3.setEditable(false);
						textArea.setText("");
						textArea.setEditable(false);
						comboBox_3.setEnabled(true);
						comboBox_3.setSelectedIndex(-1);
						return;
						
					}
				}if(textField_4.isEditable()) {
					if(specialists.contains(textField_4.getText()) || textField_4.getText().isEmpty()) {
						lblError.setText("Specialist already exists or is empty");
						lblError.setVisible(true);
						textField_4.setText("");
						textField_4.setEditable(false);
						comboBox_4.setEnabled(true);
						comboBox_4.setSelectedIndex(-1);
						return;
					}
				}if(comboBox.getSelectedIndex()==-1) {
					lblError.setText("Please specify disease");
					lblError.setVisible(true);
					return;
				}if(comboBox_1.getSelectedIndex()==-1) {
					lblError.setText("Please specify symptom");
					lblError.setVisible(true);
					return;
				}if(comboBox_2.getSelectedIndex()==-1) {
					lblError.setText("Please specify test");
					lblError.setVisible(true);
					return;
				}if(comboBox_3.getSelectedIndex()==-1) {
					lblError.setText("Please specify treatment");
					lblError.setVisible(true);
					return;
				}if(comboBox_4.getSelectedIndex()==-1) {
					lblError.setText("Please specify specialist");
					lblError.setVisible(true);
					return;
				}else {
					
					int disease_id,symptom_id,test_id,treatment_id,specialist_id;
					String d=null;
					
					//add the disease knowledge
					if(comboBox.getSelectedIndex()==0) {
						d=textField.getText();
						disease_id=ops.addDisease(d);
					}else {
						d=comboBox.getSelectedItem().toString();
						disease_id=ops.getDiseaseId(d);
					}
					
					//add the symptom knowledge
					if(comboBox_1.getSelectedIndex()==0) {
						d=textField_1.getText();
						symptom_id=ops.addSymptom(d);
					}else {
						d=comboBox_1.getSelectedItem().toString();
						symptom_id=ops.getSymptomId(d);
					}
					
					//add test knowledge
					if(comboBox_2.getSelectedIndex()==0) {
						d=textField_2.getText();
						test_id=ops.addTest(d);
					}else {
						d=comboBox_2.getSelectedItem().toString();
						test_id=ops.getTestId(d);
					}
					
					//add treatment knowledge
					if(comboBox_3.getSelectedIndex()==0) {
						d=textField_3.getText();
						treatment_id=ops.addTreatment(d, textArea.getText());
					}else {
						d=comboBox_3.getSelectedItem().toString();
						treatment_id=ops.getTreatmentId(d);
					}
					
					//add specialist knowledge
					if(comboBox.getSelectedIndex()==0) {
						d=textField_4.getText();
						specialist_id=ops.addSpecialist(d);
					}else {
						d=comboBox_4.getSelectedItem().toString();
						specialist_id=ops.getSpecialistId(d);
					}
					
					//TODO Validate the ID's
					
					
					
					//add mapping knowledge
					
					//add disease_symptom_mapping
					ops.addDiseaseSymptomMap(disease_id, symptom_id);
					ops.addDiseaseTestMap(disease_id, test_id);
					ops.addDiseaseTreatmentMap(disease_id, treatment_id);
					ops.addDiseaseSpecialisttMap(disease_id, specialist_id);
					
					lblError.setText("successfully added");
					lblError.setForeground(Color.GREEN);
					lblError.setVisible(true);
				}
				
			}
		});
		add(btnNewButton);
		
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBounds(25, 439, 928, 78);
		lblError.setVisible(false);
		add(lblError);
		
		
	}
}
