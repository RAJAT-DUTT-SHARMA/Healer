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
import services.HelperFunctions;
import services.RuleGenerator;
import services.RuleRunningFunction;
import beans.DiseaseSymptomConfidenceModel;
import beans.Person;
import beans.SymptomPojo;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import main.Main;

import java.awt.GridLayout;

public class Diagnosis extends JFrame implements WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar mbarMainFrame;
	private JMenu mnuFile;
	private JMenuItem mniExit,mniTest;
	private JMenuItem mniSymptomChecker;
	private JMenu mnuServices;
	
	private JPanel pnl_symptomChecker;
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
	
	
	public Diagnosis() {
		// TODO initialize window components
		dbOps=new DBOperations();
		symptoms=dbOps.getAllSymptom();
		symptomInput=new ArrayList<>();
		initComponents();
	}
	
	private void initComponents() {
		// TODO Auto-generated method stub

		
		
        mbarMainFrame = new javax.swing.JMenuBar();
        
        mnuFile = new javax.swing.JMenu();
        mniExit = new javax.swing.JMenuItem();
        mniTest=new JMenuItem("test");
        
        mnuServices = new javax.swing.JMenu();
        mniSymptomChecker = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 153));
        RuleGenerator.generateSymtomToDiseaseRules();
        mnuFile.setText("File");
        
        mniTest.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println( "Bootstrapping the Rule Engine ..." );
				  
		        KieServices ks = KieServices.Factory.get();
		        KieContainer kContainer = ks.getKieClasspathContainer();
		        KieSession kSession =  kContainer.newKieSession("ksession-rules");
		        HelperFunctions.initializeMap();
		        
		        for (String string : symptomInput) {
		        	SymptomPojo pojo=new SymptomPojo(string);
			        kSession.insert(pojo);
			        int fired = kSession.fireAllRules();
			        System.out.println( "Number of Rules executed = " + fired );
				}
		            System.out.println(HelperFunctions.getDiseases().toString());
			}
		});

        mniExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        mniExit.setText("Exit");
        mniExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
        mnuFile.add(mniExit);

        mbarMainFrame.add(mnuFile);

        mnuServices.setText("Profile");

        mniSymptomChecker.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        mniSymptomChecker.setText("Symptom Checker");
        mniSymptomChecker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //mniProfileAccDetActionPerformed(evt);
             for (String string : symptoms) {
				System.out.println(string);
			}
            }
        });
        mnuServices.add(mniSymptomChecker);
        mnuServices.add(mniTest);
        mbarMainFrame.add(mnuServices);

        setJMenuBar(mbarMainFrame);
        
        pnl_symptomChecker=new JPanel();
        pnl_symptomChecker.setLayout(new FlowLayout());
        pnl_symptomChecker.setBounds(40,80,500,500);
        pnl_symptomChecker.setBackground(Color.gray);
        
        txt_symptom=new JTextField();
        txt_symptom.setBounds(50,90,150,30);
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
				String prefix=txt_symptom.getText();
				System.out.println(prefix);
				//now find symptoms matching these prefix in database
				
				ArrayList<String> suggestions=new ArrayList<>();
				
				ListIterator<String> iterator=symptoms.listIterator();
				while(iterator.hasNext()) {
					String cur=iterator.next();
					if(cur.contains(prefix)) {
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
        
        btn_add=new JButton("Add");
        btn_add.setBounds(210,90,80,30);
        btn_add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				symptomInput.add(txt_symptom.getText());
				txt_symptom.setText("");
				txt_symptom.requestFocus();
			}
		});
        
        lst_suggestions=new JList<String>();
        lst_suggestions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lst_suggestions.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(lst_suggestions.getSelectedValue()!=null) {
					String selected_suggestion=lst_suggestions.getSelectedValue().toString();
					txt_symptom.setText(selected_suggestion);
					listScroller.setVisible(false);	
				}
				
			}
		});
        
        btn_search=new JButton("Search");
        btn_search.setBounds(50,360,80,30);
        btn_search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				System.out.println( "Bootstrapping the Rule Engine ..." );
				  
		        KieServices ks = KieServices.Factory.get();
		        KieContainer kContainer = ks.getKieClasspathContainer();
		        KieSession kSession =  kContainer.newKieSession("ksession-rules");
		        HelperFunctions.initializeMap();
		        
		        for (String string : symptomInput) {
		        	SymptomPojo pojo=new SymptomPojo(string);
			        RuleRunningFunction.insert(pojo);
			        }
		    
		        int fired = RuleRunningFunction.runner();
		        System.out.println( "Number of Rules executed = " + fired );
			
		        symptomInput=new ArrayList<>();
		        top3diseases = new ArrayList<>();
		        printTop3Disease();
		        Main.diseasetodepartment.diseases = top3diseases;
		        btnGoForDepartment.setEnabled(true);
		    	
			}

			
		});
        
        listScroller = new JScrollPane(lst_suggestions);
        lst_suggestions.setLayoutOrientation(JList.VERTICAL);
        listScroller.setVisible(false);
        listScroller.setBounds(50,150,250,200);
        
        pnl_symptomChecker.add(txt_symptom);
        pnl_symptomChecker.add(btn_add);
        pnl_symptomChecker.add(listScroller);
        pnl_symptomChecker.add(btn_search);
        pnl_symptomChecker.setPreferredSize(new Dimension(400, 400));
        pnl_symptomChecker.setBounds(0, 0, 400, 400);
        pnl_symptomChecker.setVisible(true);
        
        getContentPane().add(pnl_symptomChecker);
        
        panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(new GridLayout(2, 1, 0, 0));
        
        predictedDieseases=new TextArea();
        panel.add(predictedDieseases);
        
        predictedDieseases.setEditable(false);
        predictedDieseases.setRows(10);
        predictedDieseases.setColumns(100);
        predictedDieseases.setText("Possible Diseases : \n\nDisease\t\tConfidence\n");
        
        btnGoForDepartment = new JButton("Go for Department Check");
        btnGoForDepartment.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Main.helpDesk.btnNewButton.setEnabled(true);
        		Main.helpDesk.setVisible(true);
				Main.diagnosis.setVisible(false);
				Main.diseasetodepartment.setVisible(false);
        	}
        });
        btnGoForDepartment.setEnabled(false);
        panel.add(btnGoForDepartment);
        //predictedDieseases.setBounds(310, 0, 350,300);
        //predictedDieseases.setPreferredSize(new Dimension(300, 300));
        predictedDieseases.setVisible(true);
        
        getContentPane().setLayout(new FlowLayout());
        setTitle("Probable Disease Check");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        pack();	
	}

	
	
	public ArrayList<String> getTop3diseases() {
		return top3diseases;
	}

	public void setTop3diseases(String top3diseases) {
		this.top3diseases.add(top3diseases);
	}
	
	

	public void printTop3Disease() {
		// TODO Auto-generated method stub
        HashMap<String, Integer> predictions=HelperFunctions.getDiseases();
		
        //convert the prediction map to array list
		ArrayList<DiseaseSymptomConfidenceModel> resultList=new ArrayList<>();
		for (String string : predictions.keySet()) {
			DiseaseSymptomConfidenceModel ele=new DiseaseSymptomConfidenceModel();
			ele.setDisease(string);
			ele.setConfidence(predictions.get(string).intValue());
			resultList.add(ele);
		}
		
		//sort the array list
		Comparator<DiseaseSymptomConfidenceModel> comparator=new Comparator<DiseaseSymptomConfidenceModel>() {
			
			@Override
			public int compare(DiseaseSymptomConfidenceModel o1, DiseaseSymptomConfidenceModel o2) {
				// TODO Auto-generated method stub
				if(o1.getConfidence()>o2.getConfidence()) {
					return -1;
				}else if(o1.getConfidence()<o2.getConfidence()) {
					return 1;
				}
				return 0;
			}
		};
		
		resultList.sort(comparator);
		String text="Possible Diseases : \n\nDisease\t\tConfidence\n";
		int count=0;
		for(DiseaseSymptomConfidenceModel a: resultList) {
			if(count<3) {
			text+=a.getDisease()+"\t"+a.getConfidence();
			count++;
			this.setTop3diseases(a.getDisease());
			}
			else {
				break;
			}
			text+="\n";
		}
		
//		predictedDieseases.setText("Possible Diseases : \n\nDisease\t\tConfidence\n"+resultList.get(0).getDisease()+"\t"+resultList.get(0).getConfidence()+"\n"+resultList.get(1).getDisease()+"\t"+resultList.get(1).getConfidence()+"\n"+resultList.get(2).getDisease()+"\t"+resultList.get(2).getConfidence());
		predictedDieseases.setText(text);

		
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
		  new Diagnosis().setVisible(true);;
		  
	}

	
	


	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
