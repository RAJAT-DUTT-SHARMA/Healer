package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import main.Main;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class HelpDesk extends JFrame {

	private JPanel contentPane;
	public JButton btnNewButton;
	private JButton btnDiagnosis;
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	
	
	
	public HelpDesk() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 127, 172, 2, 0 };
		gbl_contentPane.rowHeights = new int[] { 22, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblWelcomeToHealer = new JLabel("Welcome to Healer");
		lblWelcomeToHealer.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToHealer.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblWelcomeToHealer = new GridBagConstraints();
		gbc_lblWelcomeToHealer.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblWelcomeToHealer.insets = new Insets(0, 0, 5, 5);
		gbc_lblWelcomeToHealer.gridx = 1;
		gbc_lblWelcomeToHealer.gridy = 0;
		contentPane.add(lblWelcomeToHealer, gbc_lblWelcomeToHealer);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.anchor = GridBagConstraints.WEST;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 0;
		contentPane.add(scrollPane, gbc_scrollPane);

		JButton btnCheckUp = new JButton("Check up");
		btnCheckUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.helpDesk.setVisible(false);
				Main.diagnosis.setVisible(true);
				Main.diseasetodepartment.setVisible(false);

			}
		});
		GridBagConstraints gbc_btnCheckUp = new GridBagConstraints();
		gbc_btnCheckUp.insets = new Insets(0, 0, 5, 5);
		gbc_btnCheckUp.gridx = 1;
		gbc_btnCheckUp.gridy = 2;
		contentPane.add(btnCheckUp, gbc_btnCheckUp);

		btnDiagnosis = new JButton("Diagnosis");
		btnDiagnosis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.treatment.SetStart();
			}
		});
		GridBagConstraints gbc_btnDiagnosis = new GridBagConstraints();
		gbc_btnDiagnosis.insets = new Insets(0, 0, 5, 5);
		gbc_btnDiagnosis.gridx = 1;
		gbc_btnDiagnosis.gridy = 3;
		contentPane.add(btnDiagnosis, gbc_btnDiagnosis);
//		btnDiagnosis.setEnabled(false);
		
		btnNewButton = new JButton("Know where to Treat your Problem");
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.diseasetodepartment.SetStart();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 4;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
	}

	public void SetStart() {
		this.setVisible(true);
		Main.diagnosis.setVisible(false);
		Main.diseasetodepartment.setVisible(false);
	}
	
	
}