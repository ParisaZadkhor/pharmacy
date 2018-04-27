package HW14_pharmacy_GUI;
/*
 * this page created by zahra shayesteh
 * this is the first page of pharmacy system that a user will see.
 * he/she must specify her/his role to login with her/his username and password
 * according to their role they will direct to their own pages.
 */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import HW14_pharmacy_DBManagers.PersonelManager;
import HW14_pharmacy_classes.Personel;
import javax.swing.border.BevelBorder;
import javax.swing.JRadioButton;

public class LoginPageGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField_user;
	private JPasswordField passwordField_pass;
	private PersonelManager pDBM;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {// main method of the whole program
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPageGUI frame = new LoginPageGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public LoginPageGUI() throws IOException {
		setTitle("Login Page");
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		dispose();
		setBounds(100, 100, 667, 567);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, new Color(32, 178, 170), null));
		panel.setBackground(new Color(0, 206, 209));
		panel.setBounds(196, 178, 272, 298);
		contentPane.add(panel);
		panel.setLayout(null);
		JLabel lbl_username = new JLabel("username :");
		lbl_username.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_username.setBounds(10, 19, 97, 31);
		panel.add(lbl_username);
		JLabel lbl_password = new JLabel("password :");
		lbl_password.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_password.setBounds(10, 71, 97, 28);
		panel.add(lbl_password);

		textField_user = new JTextField();
		textField_user.setBounds(117, 21, 145, 31);
		panel.add(textField_user);
		textField_user.setColumns(10);

		JRadioButton rdbtn_manager = new JRadioButton("login as a manager");
		rdbtn_manager.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtn_manager.setBackground(new Color(0, 206, 209));
		rdbtn_manager.setBounds(67, 131, 138, 23);
		panel.add(rdbtn_manager);

		JRadioButton rdbtn_salesperson = new JRadioButton("login as a salesperson");
		rdbtn_salesperson.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtn_salesperson.setBackground(new Color(0, 206, 209));
		rdbtn_salesperson.setBounds(67, 168, 149, 23);
		panel.add(rdbtn_salesperson);

		JRadioButton rdbtn_staff = new JRadioButton("login as a staff");
		rdbtn_staff.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtn_staff.setBackground(new Color(0, 206, 209));
		rdbtn_staff.setBounds(67, 206, 138, 23);
		panel.add(rdbtn_staff);


		ButtonGroup bg=new ButtonGroup();
		bg.add(rdbtn_manager);
		bg.add(rdbtn_salesperson);
		bg.add(rdbtn_staff);

		JButton btn_login = new JButton("Login");
		btn_login.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(bg.getSelection()!=null){//if a role has been selected from radio buttons
					if (!textField_user.getText().trim().isEmpty() & passwordField_pass.getPassword().length != 0) {//if username & password fields have been filled
						String passText = new String(passwordField_pass.getPassword());
						String userText = new String(textField_user.getText());
						int role;
						if(rdbtn_manager.isSelected())
							role=1;//role of manager
						else if(rdbtn_salesperson.isSelected())
							role=2;//role of salesperson
						else
							role=3;//role of staff
						Personel p = new Personel(userText, passText,role); 
						try { 
							pDBM= new PersonelManager(); 
							if(pDBM.authentication(p)) {//check the authentication of the entered fields
								setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
								dispose(); 
								if(role==1){
									new ManagerPageGUI().setVisible(true);	//directing the manager to his page
								}
								else if(role==2)
								{
									//direct be page salesperson//directing the salesperson to his page
								}
								else{
									//direct be page staff//directing the staff to their page
									//new DrawPage().setVisible(true); 
								}
							} else//if the authentication of the username & password have been failed
								JOptionPane.showMessageDialog(null,
										"The username or password you entered doesn't/dont match!"); 
							} catch (HeadlessException | SQLException e) { }
					} else {//if one of or both of the fields are empty
						JOptionPane.showMessageDialog(null,
								"You must fill both fields of username and password to continue!");
					}
				}else{//if the role has not been selected yet
					JOptionPane.showMessageDialog(null,
							"You must select your role to continue!");
				}
			}
		});
		btn_login.setBounds(86, 242, 99, 34);
		panel.add(btn_login);

		passwordField_pass = new JPasswordField();
		passwordField_pass.setBounds(117, 72, 145, 31);
		panel.add(passwordField_pass);
		passwordField_pass.setToolTipText("Password must contain at least 4 characters");

		JLabel lbl_welcome = new JLabel(
				"Welcome to the pharmacy system");
		lbl_welcome.setForeground(new Color(75, 0, 130));
		lbl_welcome.setFont(new Font("Juice ITC", Font.BOLD | Font.ITALIC, 48));
		lbl_welcome.setBounds(60, 20, 569, 116);
		contentPane.add(lbl_welcome);

		JLabel lbl_direct = new JLabel(
				"please login first to direct into your page...");
		lbl_direct.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_direct.setBounds(165, 133, 335, 34);
		contentPane.add(lbl_direct);
	}
}
