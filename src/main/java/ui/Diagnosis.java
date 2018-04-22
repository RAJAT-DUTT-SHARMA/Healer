package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import services.DBOperations;
import services.HelperFunctionForTest;
import services.HelperFunctions;
import services.HelperfunctionForDepartment;
import services.HelperfunctionForTreatment;
import services.RuleGenerator;
import services.RuleRunningFunction;
import beans.DiseaseSymptomConfidenceModel;
import beans.Person;
import beans.SymptomPojo;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import main.Main;

import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;

public class Diagnosis extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField txt_symptom;
	private JButton btn_add, btn_search;
	private JList lst_suggestions;
	private JScrollPane listScroller;

	private DBOperations dbOps;
	ArrayList<String> symptoms;
	ArrayList<String> symptomInput;
	private TextArea predictedDieseases;

	private ArrayList<String> top3diseases;
	private JTextArea textArea;

	private JLabel lblInformationRelatedTo;
	private JScrollPane scrollPane;
	

	public Diagnosis() {
		// TODO initialize window components
		dbOps = new DBOperations();
		symptoms = dbOps.getAllSymptom();
		symptomInput = new ArrayList<>();

		initComponents();
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		setBackground(new java.awt.Color(153, 153, 153));
		setBackground(Color.gray);

		txt_symptom = new JTextField();
		txt_symptom.setToolTipText("ENTER SYMPTOMS");
		txt_symptom.setBounds(50, 40, 475, 30);
		txt_symptom.setPreferredSize(new Dimension(200, 30));
		txt_symptom.setEditable(true);
		txt_symptom.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				String prefix = txt_symptom.getText();
				System.out.println(prefix);
				// now find symptoms matching these prefix in database

				ArrayList<String> suggestions = new ArrayList<>();

				ListIterator<String> iterator = symptoms.listIterator();
				while (iterator.hasNext()) {
					String cur = iterator.next();
					if (cur.contains(prefix)) {
						suggestions.add(cur);
					}
				}
				for (String string : suggestions) {
					System.out.println(string);
				}

				lst_suggestions.setListData(suggestions.toArray());

				lst_suggestions.setVisible(true);
				listScroller.setVisible(true);

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});

		btn_add = new JButton("Add");
		btn_add.setBounds(555, 40, 80, 30);
		btn_add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				symptomInput.add(txt_symptom.getText());
				txt_symptom.setText("");
				txt_symptom.requestFocus();
			}
		});

		lst_suggestions = new JList<String>();
		lst_suggestions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lst_suggestions.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if (lst_suggestions.getSelectedValue() != null) {
					String selected_suggestion = lst_suggestions.getSelectedValue().toString();
					txt_symptom.setText(selected_suggestion);
					listScroller.setVisible(false);
				}

			}
		});

		listScroller = new JScrollPane(lst_suggestions);
		lst_suggestions.setLayoutOrientation(JList.VERTICAL);
		listScroller.setVisible(false);
		listScroller.setBounds(50, 91, 250, 331);
		setLayout(null);

		add(txt_symptom);
		add(btn_add);

		btn_search = new JButton("Search");
		btn_search.setBounds(682, 40, 80, 30);
		btn_search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				System.out.println("Bootstrapping the Rule Engine ...");

				for (String string : symptomInput) {
					SymptomPojo pojo = new SymptomPojo(string);
					RuleRunningFunction.insert(pojo);
				}

				int fired = RuleRunningFunction.runner();
				System.out.println("Number of Rules executed = " + fired);

				symptomInput = new ArrayList<>();
				top3diseases = new ArrayList<>();
				printTop3Disease();

			}

		});
		add(btn_search);
		add(listScroller);

		predictedDieseases = new TextArea();
		predictedDieseases.setLocation(337, 91);
		predictedDieseases.setSize(425, 331);
		add(predictedDieseases);

		predictedDieseases.setEditable(false);
		predictedDieseases.setRows(10);
		predictedDieseases.setColumns(100);
		predictedDieseases.setText("Possible Diseases : \n\nDisease\t\tConfidence\n");

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBounds(778, 91, 528, 483);
//		add(textArea);

		lblInformationRelatedTo = new JLabel("Information Related to Detected Diseases");
		lblInformationRelatedTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformationRelatedTo.setBounds(790, 71, 528, 14);
		add(lblInformationRelatedTo);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setLocation(788, 91);
		scrollPane.setSize(530, 331);
//		scrollPane.setBounds(1308, 91, 18, 483);
		add(scrollPane);

		predictedDieseases.setVisible(true);

	}

	public ArrayList<String> getTop3diseases() {
		return top3diseases;
	}

	public void setTop3diseases(String top3diseases) {
		this.top3diseases.add(top3diseases);
	}

	public void printTop3Disease() {
		// TODO Auto-generated method stub
		HashMap<String, Integer> predictions = HelperFunctions.getDiseases();

		// convert the prediction map to array list
		ArrayList<DiseaseSymptomConfidenceModel> resultList = new ArrayList<>();
		for (String string : predictions.keySet()) {
			DiseaseSymptomConfidenceModel ele = new DiseaseSymptomConfidenceModel();
			ele.setDisease(string);
			ele.setConfidence(predictions.get(string).intValue());
			resultList.add(ele);
		}

		// sort the array list
		Comparator<DiseaseSymptomConfidenceModel> comparator = new Comparator<DiseaseSymptomConfidenceModel>() {

			@Override
			public int compare(DiseaseSymptomConfidenceModel o1, DiseaseSymptomConfidenceModel o2) {
				// TODO Auto-generated method stub
				if (o1.getConfidence() > o2.getConfidence()) {
					return -1;
				} else if (o1.getConfidence() < o2.getConfidence()) {
					return 1;
				}
				return 0;
			}
		};

		resultList.sort(comparator);
		String text = "Possible Diseases : \n\nDisease\t\tConfidence\n";
		int count = 0;
		for (DiseaseSymptomConfidenceModel a : resultList) {
			if (count < 3) {
				text += a.getDisease() + "\t" + a.getConfidence();
				count++;
				this.setTop3diseases(a.getDisease());
			} else {
				break;
			}
			text += "\n";
		}

		// predictedDieseases.setText("Possible Diseases :
		// \n\nDisease\t\tConfidence\n"+resultList.get(0).getDisease()+"\t"+resultList.get(0).getConfidence()+"\n"+resultList.get(1).getDisease()+"\t"+resultList.get(1).getConfidence()+"\n"+resultList.get(2).getDisease()+"\t"+resultList.get(2).getConfidence());
		predictedDieseases.setText(text);
		SetDiseaseInformation();

	}

	public static void main(String args[]) {
		for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				try {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
		new Diagnosis().setVisible(true);
		;

	}

	public void SetDiseaseInformation() {
		HashMap<String, ArrayList<String>> diseaseToTreatment = HelperfunctionForTreatment.getTreatments();
		HashMap<String, ArrayList<String>> diseaseToTest = HelperFunctionForTest.getDiseaseToTest();
		HashMap<String, ArrayList<String>> diseaseToSpecialist = HelperfunctionForDepartment.getDiseaseToDepartment();

		String text = "";

		for (String disease : top3diseases) {
			text += disease + ": \n";
			int count = 1;
			for (String department : diseaseToSpecialist.get(disease)) {
				text += "Specialist To Discuss with: " + count + ")" + department + "\n";
				count++;
			}
			count = 1;
			text += "Treatments: \n";
			for (String treatment : diseaseToTreatment.get(disease)) {
				text += "" + count + ") " + treatment + "\n";
				count++;
			}
			count = 1;
			text += "Test Recommended: \n";
			for (String test : diseaseToTest.get(disease)) {
				text += "" + count + ") " + test + "\n";
				count++;
			}
			text += "\n\n";
		}
		
		System.out.println(text);
		
	
			
		textArea.setLineWrap(true);
		textArea.setText(text);
		scrollPane.setVisible(true);
//		textArea.setVisible(true);

	}
}
