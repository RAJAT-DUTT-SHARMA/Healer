package ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.ScrollPane;
import java.util.HashMap;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import beans.Person;
import services.DBOperations;
import services.HelperfunctionForDate;
import services.RuleRunningFunction;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PatientHistory extends JPanel {

	/**
	 * Create the panel.
	 */
	public PatientHistory() {
		setLayout(null);

		String[] person = { "RAM", "SHYAM", "SOHAN", "KIRTI", "DINESH", "AMIT" };

		for(String name: person) {
			RuleRunningFunction.insert(new Person(name));
		}
		
		RuleRunningFunction.runner();
		
		JScrollPane sp = new JScrollPane();
		add(sp);

		JList list = new JList<String>();
		list.setBounds(36, 46, 107, 147);
		add(list);

		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		list.setListData(person);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(168, 46, 342, 312);
		add(textArea);
		
		JLabel lblPatientName = new JLabel("Patient Name");
		lblPatientName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPatientName.setBounds(36, 31, 107, 14);
		add(lblPatientName);
		
		JLabel lblRelatedHistory = new JLabel("Related History");
		lblRelatedHistory.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelatedHistory.setBounds(168, 31, 342, 14);
		add(lblRelatedHistory);

		
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if (list.getSelectedValue() != null) {
					textArea.setText("");
					String selected_suggestion = list.getSelectedValue().toString();
					System.out.println(selected_suggestion);
					String text="";
					HashMap<String, String> details =  HelperfunctionForDate.getPersonToDate().get(selected_suggestion);
					text+="name: "+selected_suggestion+"\n";
					text+="address: "+details.get("address")+"\n";
					text+="disease: "+details.get("disease")+"\n";
					text+="rate: "+details.get("rate")+"\n";
					text+="date: "+details.get("date")+"\n";
					text+="doctor: "+details.get("doct")+"\n";
					
					textArea.setText(text);

				}
			}
		});
		
	}
}
