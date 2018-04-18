package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.Main;
import services.HelperfunctionForTreatment;
import services.RuleRunningFunction;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import models.DiseaseTreatments;
import services.HelperfunctionForTreatment;
import java.util.ArrayList;
import java.util.HashMap;

public class Treatment extends JFrame {

	private JPanel contentPane;
	JTextArea txtrTreatment;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Treatment() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblTreatmentForYour = new JLabel("Treatment For Your Disease");
		lblTreatmentForYour.setHorizontalAlignment(SwingConstants.CENTER);
		lblTreatmentForYour.setVerticalAlignment(SwingConstants.TOP);
		lblTreatmentForYour.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblTreatmentForYour, BorderLayout.NORTH);
		
		txtrTreatment = new JTextArea();
		contentPane.add(txtrTreatment, BorderLayout.CENTER);
		txtrTreatment.setEditable(false);
		
		HelperfunctionForTreatment.initializeMap();	
	}

	public void SetStart() {
		this.setVisible(true);
		Main.diseasetodepartment.setVisible(false);
		Main.helpDesk.setVisible(false);
		String[] diseases = {"Dehydration","chicken pox","measles"};
		
		
		for(String s: diseases) {
			RuleRunningFunction.insert(new DiseaseTreatments(s));
		}
		
		RuleRunningFunction.runner();
		
		HashMap<String, ArrayList<String> > treatments = HelperfunctionForTreatment.getTreatments();
	
		String text = "";
		for(String s: diseases) {
			text = text+"--"+s+"--"+"\n";
			ArrayList<String> treatmentList = treatments.get(s);
			for(String t : treatmentList) {
				text = text + t +"\n";
			}
		}
		
		
		txtrTreatment.setText(text);
		
	}
	
}
