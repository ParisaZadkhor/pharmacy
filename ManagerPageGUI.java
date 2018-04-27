package HW14_pharmacy_GUI;
/*
 * this page created by zahra shayesteh
 * this is the first page that a manager will see after login page
 * he/she can manage insurances,patients and medicine from his/her page
 */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import HW14_pharmacy_classes.Personel;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.awt.Color;

public class ManagerPageGUI extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public ManagerPageGUI() {
		setTitle("Manager Managemet Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(188, 143, 143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_insuranceM = new JButton("insurances management");
		btn_insuranceM.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_insuranceM.setBackground(new Color(250, 240, 230));
		btn_insuranceM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				dispose(); 
				new ManagerInsuranceManagementGUI().setVisible(true);	//show the page of insurance management
			}
		});
		btn_insuranceM.setBounds(169, 101, 198, 36);
		contentPane.add(btn_insuranceM);
		
		JButton btn_patientM = new JButton("patients management");
		btn_patientM.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_patientM.setBackground(new Color(250, 240, 230));
		btn_patientM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				dispose(); 
				new ManagerPatientManagementGUI().setVisible(true);	//show the page of patient management
			}
		});
		btn_patientM.setBounds(169, 148, 198, 35);
		contentPane.add(btn_patientM);
		
		JButton btn_medicineM = new JButton("medicines management");
		btn_medicineM.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_medicineM.setBackground(new Color(250, 240, 230));
		btn_medicineM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				dispose(); 
				new ManagerMedicineManagementGUI().setVisible(true);//show the page of medicines management
			}
		});
		btn_medicineM.setBounds(169, 194, 198, 36);
		contentPane.add(btn_medicineM);
		
		JLabel lbl_name = new JLabel("");
		lbl_name.setForeground(new Color(255, 255, 255));
		lbl_name.setFont(new Font("Ravie", Font.ITALIC, 14));
		lbl_name.setBounds(52, 11, 107, 67);
		contentPane.add(lbl_name);
		
		JLabel lbl_welcome = new JLabel("Welcome to your management page");
		lbl_welcome.setForeground(new Color(255, 255, 255));
		lbl_welcome.setFont(new Font("Ravie", Font.ITALIC, 14));
		lbl_welcome.setBounds(169, 11, 350, 67);
		contentPane.add(lbl_welcome);
		
		JButton btnLogout = new JButton("logout");
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogout.setBackground(new Color(255, 228, 225));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				dispose(); 
				try {
					new LoginPageGUI().setVisible(true);//by clicking log out button manager will direct to the login page
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnLogout.setBounds(228, 252, 89, 29);
		contentPane.add(btnLogout);
		
		JLabel lblSelectAnAction = new JLabel("select an action to continue...");
		lblSelectAnAction.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSelectAnAction.setBackground(new Color(188, 143, 143));
		lblSelectAnAction.setBounds(189, 69, 179, 21);
		contentPane.add(lblSelectAnAction);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				lbl_name.setText(Personel.getUsername());
			}
		});
	}
}
