package ui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
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
import test.Person;

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
	
	public Diagnosis() {
		// TODO initialize window components
		dbOps=new DBOperations();
		symptoms=dbOps.getAllSymptom();
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

        mnuFile.setText("File");
        
        mniTest.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println( "Bootstrapping the Rule Engine ..." );
				  
		        KieServices ks = KieServices.Factory.get();
		        KieContainer kContainer = ks.getKieClasspathContainer();
		        KieSession kSession =  kContainer.newKieSession("ksession-rules");
		 
		        
		        Person p=new Person();
		        p.setTime(7);
		        p.setName("RAJAT");
		        kSession.insert(p);
		        int fired = kSession.fireAllRules();
		        System.out.println( "Number of Rules executed = " + fired );
		        System.out.println( "Message Status: " + p.getGreet());
		        
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
				
			}
		});
        
        lst_suggestions=new JList<String>();
        lst_suggestions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lst_suggestions.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				String selected_suggestion=lst_suggestions.getSelectedValue().toString();
				txt_symptom.setText(selected_suggestion);
				listScroller.setVisible(false);
			}
		});
        
        
        listScroller = new JScrollPane(lst_suggestions);
        lst_suggestions.setLayoutOrientation(JList.VERTICAL);
        listScroller.setVisible(false);
        listScroller.setBounds(50,150,150,200);
        
        pnl_symptomChecker.add(txt_symptom);
        pnl_symptomChecker.add(btn_add);
        pnl_symptomChecker.add(listScroller);
       
        
        add(pnl_symptomChecker);
        setLayout(null);
        setTitle("HEALER");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        pack();	
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
