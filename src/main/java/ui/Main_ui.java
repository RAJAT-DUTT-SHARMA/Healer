package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import beans.DiseaseSymptomConfidenceModel;
import beans.SymptomPojo;
import main.Main;
import services.DBOperations;
import services.HelperFunctions;
import services.RuleGenerator;
import services.RuleRunningFunction;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.ListIterator;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class Main_ui extends JFrame {

	private JPanel contentPane;
	
	private JTextField txt_symptom;
	private JButton btn_add,btn_search;
	private JList lst_suggestions;
	private JScrollPane listScroller;
	
	
	private DBOperations dbOps;
	ArrayList<String> symptoms;
	ArrayList<String> symptomInput;
	private TextArea predictedDieseases;
	private JPanel panel;
	private JButton btnGoForDepartment;
	private ArrayList<String> top3diseases;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_ui frame = new Main_ui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main_ui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblHealer = new JLabel("HEALER");
		lblHealer.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblHealer.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblHealer, BorderLayout.NORTH);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new Diagnosis();
		tabbedPane.addTab("Search through Symptom", null, panel, null);
		
		
		JPanel panel_1 = new SearchThroughDisease();
//		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Search through Disease", null, panel_1, null);
		
		JPanel panel_3 = new SearchThroughSpecialist();
		tabbedPane.addTab("Search through Specialist", null, panel_3, null);
	}
}
