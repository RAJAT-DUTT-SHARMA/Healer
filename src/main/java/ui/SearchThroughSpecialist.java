package ui;

import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import services.DBOperations;
import services.HelperFunctionForTest;
import services.HelperfunctionForTreatment;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class SearchThroughSpecialist extends JPanel {

	/**
	 * Create the panel.
	 */
	public SearchThroughSpecialist() {
		setLayout(null);
		DBOperations db = new DBOperations();
		JList list = new JList<String>();
		JList list2 = new JList<String>();
		JTextArea list3 = new JTextArea();
		list3.setWrapStyleWord(true);
		list3.setLineWrap(true);
		JTextArea list4 = new JTextArea();
		list4.setLineWrap(true);
		list4.setWrapStyleWord(true);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (list.getSelectedValue() != null) {
					list3.setText("");
					list4.setText("");
					String selected_suggestion = list.getSelectedValue().toString();
					list2.setListData(db.getDiseaseForSpecialist(selected_suggestion).toArray());

				}
			}
		});
		list.setForeground(Color.DARK_GRAY);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(350, 40, 157, 87);
		add(scrollPane);

		list.setListData(db.getAllSpecialist().toArray());

		JLabel lblAllSpecialist = new JLabel("All Specialist");
		lblAllSpecialist.setHorizontalAlignment(SwingConstants.CENTER);
		lblAllSpecialist.setBounds(350, 15, 157, 14);
		add(lblAllSpecialist);

		list2.setForeground(Color.DARK_GRAY);
		list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane_1 = new JScrollPane(list2);
		scrollPane_1.setBounds(38, 193, 300, 141);
		add(scrollPane_1);

		JLabel lblDiseasesCheckedBy = new JLabel("Diseases checked by selected specialist");
		lblDiseasesCheckedBy.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiseasesCheckedBy.setBounds(38, 151, 237, 31);
		add(lblDiseasesCheckedBy);

		JScrollPane scrollPane_2 = new JScrollPane(list3);
		scrollPane_2.setBounds(372, 193, 473, 317);
		add(scrollPane_2);

		JLabel lblRecommendedTreatmentFor = new JLabel("Recommended Treatment for Selected Disease");
		lblRecommendedTreatmentFor.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecommendedTreatmentFor.setBounds(372, 159, 473, 14);
		add(lblRecommendedTreatmentFor);

		JScrollPane scrollPane_3 = new JScrollPane(list4);
		scrollPane_3.setBounds(38, 369, 300, 141);
		add(scrollPane_3);

		JLabel lblRecommendedTreatmentFor_1 = new JLabel("Recommended Test for Selected Disease");
		lblRecommendedTreatmentFor_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecommendedTreatmentFor_1.setBounds(38, 345, 300, 14);
		add(lblRecommendedTreatmentFor_1);

		list2.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if (list2.getSelectedValue() != null) {
					String selected_suggestion = list2.getSelectedValue().toString();
					list4.setText(HelperFunctionForTest.getDiseaseToTest().get(selected_suggestion).toString());

					HashMap<String, ArrayList<String>> diseaseToTreatment = HelperfunctionForTreatment.getTreatments();
					String text = "";

					text += "" + selected_suggestion + ": \n\n";
					ArrayList<String> treatments = diseaseToTreatment.get(selected_suggestion);
					int count = 1;
					for (String k : treatments) {
						text += "" + count + ".) " + k + "\n\n";

						count++;
					}

					list3.setText(text);

				}
			}
		});

	}
}
