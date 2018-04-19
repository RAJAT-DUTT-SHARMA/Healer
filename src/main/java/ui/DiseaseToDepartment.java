package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JFormattedTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Table;

import main.Main;
import models.Disease;
import services.HelperfunctionForDepartment;
import services.RuleRunningFunction;

public class DiseaseToDepartment extends JFrame {

	private JPanel contentPane;
	private JLabel label;
	private JLabel label_1;
	private JLabel lblWelcomeToDepartment;
	private JTable table;
	public ArrayList<String> diseases;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiseaseToDepartment frame = new DiseaseToDepartment();
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
	public DiseaseToDepartment() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 434, 0 };
		gridBagLayout.rowHeights = new int[] { 19, 237, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblWelcomeToDepartment_1 = new JLabel("Welcome To Department Checker");
		lblWelcomeToDepartment_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWelcomeToDepartment_1.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblWelcomeToDepartment_1 = new GridBagConstraints();
		gbc_lblWelcomeToDepartment_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblWelcomeToDepartment_1.gridx = 0;
		gbc_lblWelcomeToDepartment_1.gridy = 0;
		getContentPane().add(lblWelcomeToDepartment_1, gbc_lblWelcomeToDepartment_1);

		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		getContentPane().add(panel, gbc_panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		HelperfunctionForDepartment.initializeMap();

	}

	public void SetStart() {
		this.setVisible(true);
		Main.helpDesk.setVisible(false);
		for (String a : diseases) {
			RuleRunningFunction.insert(new Disease(a));
		}

		RuleRunningFunction.runner();

		HashMap<String, ArrayList<String>> abc = HelperfunctionForDepartment.getDiseaseToDepartment();

		if (!abc.isEmpty())
			System.out.println("done");

		table = new JTable();
		Object[][] row = new Object[3][2];
		for (int i = 0; i < diseases.size(); i++) {

			row[i][0] = diseases.get(i);
			row[i][1] = abc.get(diseases.get(i));

			System.out.println(row[i][1]);
		}
		
		
		// Object[][] row =
		// {{diseases.get(0),abc.get(diseases.get(0))},{diseases.get(1),abc.get(diseases.get(1))},{diseases.get(2),abc.get(diseases.get(2))}};

		table.setModel(new DefaultTableModel(row, new String[] { "Disease", "Department" }));

		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);

		panel.add(table);

	}

}
