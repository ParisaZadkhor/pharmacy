package HW14_pharmacy_GUI;
/*
 * this class is created by zahra shayesteh
 * this is the page that manager can manages patients by it
 * deleting,updating,creating and showing of the patients is available in this page for the manager.
 */
import HW14_pharmacy_classes.Insurance;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import HW14_pharmacy_DBManagers.InsuranceDBManager;
import HW14_pharmacy_DBManagers.PatientDBManager;
import HW14_pharmacy_classes.Patient;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ManagerPatientManagementGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txt_ID;
	private JTextField txt_Name;
	private JTextField txt_sname;
	private JTextField txt_iname;
	PatientDBManager pDBM;
	private JTextField txt_iID;

	/**
	 * Create the frame.
	 */
	public ManagerPatientManagementGUI() {
		setResizable(false);
		setTitle("Manager patients management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, -15, 812, 310);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(210, 180, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl_ID = new JLabel("ID :");
		lbl_ID.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_ID.setBounds(10, 29, 31, 14);
		contentPane.add(lbl_ID);

		JLabel lbl_name = new JLabel("Name :");
		lbl_name.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_name.setBounds(96, 29, 46, 14);
		contentPane.add(lbl_name);

		JLabel lbl_surname = new JLabel("Surname :");
		lbl_surname.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_surname.setBounds(253, 29, 69, 14);
		contentPane.add(lbl_surname);

		JLabel lbl_insuranceName = new JLabel("Insurance name :");
		lbl_insuranceName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_insuranceName.setBounds(574, 29, 103, 14);
		contentPane.add(lbl_insuranceName);

		txt_ID = new JTextField();
		txt_ID.setBackground(new Color(255, 255, 224));
		txt_ID.setBounds(40, 21, 46, 31);
		contentPane.add(txt_ID);
		txt_ID.setColumns(10);

		txt_Name = new JTextField();
		txt_Name.setBackground(new Color(255, 255, 224));
		txt_Name.setBounds(140, 21, 103, 31);
		contentPane.add(txt_Name);
		txt_Name.setColumns(10);

		txt_sname = new JTextField();
		txt_sname.setBackground(new Color(255, 255, 224));
		txt_sname.setBounds(319, 21, 103, 31);
		contentPane.add(txt_sname);
		txt_sname.setColumns(10);

		txt_iname = new JTextField();
		txt_iname.setBackground(new Color(255, 255, 224));
		txt_iname.setBounds(681, 21, 103, 31);
		contentPane.add(txt_iname);
		txt_iname.setColumns(10);

		JButton btn_show = new JButton("show");
		btn_show.setBackground(new Color(245, 222, 179));
		btn_show.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_show.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pDBM = new PatientDBManager();
				if(txt_ID.getText().isEmpty()){//if read all is selected by manager
					ArrayList<Patient> patients = new ArrayList<Patient>();
					String col[] = {"Patient ID","Patient name","Patient surname","Insurance ID","Insurance Name"};
					DefaultTableModel tableModel = new DefaultTableModel(col, 0);// The 0 argument is number rows.
					JTable table = new JTable(tableModel);
					try {
						patients = pDBM.readAll();//read all the patients
						for (int i = 0; i < patients.size(); i++) {
							Object[] objs = {patients.get(i).getId(),patients.get(i).getName(),patients.get(i).getSurname(),patients.get(i).getInsurance().getInsuranceID(),patients.get(i).getInsurance().getInsuranceName()};
							tableModel.addRow(objs);
						}
						JScrollPane sp = new JScrollPane(table);
						sp.setBounds(17, 110, 770, 130);
						contentPane.add(sp);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else//if reading one patient by its ID is selected
				{
					Patient patient;
					String col[] = {"Patient ID","Patient name","Patient surname","Insurance ID","Insurance Name"};
					DefaultTableModel tableModel = new DefaultTableModel(col, 0);// The 0 argument is number rows.
					JTable table = new JTable(tableModel);
					try {
						patient = pDBM.read(Integer.parseInt(txt_ID.getText()));//reading a patient using its ID
						Object[] objs = {patient.getId(),patient.getName(),patient.getSurname(),patient.getInsurance().getInsuranceID(),patient.getInsurance().getInsuranceName()};
						tableModel.addRow(objs);
						JScrollPane sp = new JScrollPane(table);
						sp.setBounds(17, 110, 770, 130);
						contentPane.add(sp);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btn_show.setBounds(20, 68, 160, 31);
		contentPane.add(btn_show);

		JButton btnCreate = new JButton("create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pDBM = new PatientDBManager();
				InsuranceDBManager iDBM=new InsuranceDBManager();
				if(!txt_Name.getText().isEmpty() && !txt_sname.getText().isEmpty() && !txt_iID.getText().isEmpty()){//if required fields are not empty
					try {
						Insurance i=iDBM.read(Integer.parseInt(txt_iID.getText()));
						Patient p=new Patient(txt_Name.getText(),txt_sname.getText(),i);
						int pID=pDBM.create(p);//creating an patient using its name
						JOptionPane.showMessageDialog(null,"The patient "+txt_Name.getText()+" "+txt_sname.getText()+" with the insurance : "+p.getInsurance().getInsuranceName()+" with patient ID= "+pID+" created successfully!"); 
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else//  if some of the fields are empty
					JOptionPane.showMessageDialog(null,"You should fill at least name,surname and insurance ID fields to create an insurance!"); 
			}
		});
		btnCreate.setBackground(new Color(245, 222, 179));
		btnCreate.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCreate.setBounds(190, 68, 160, 31);
		contentPane.add(btnCreate);

		JButton btnUpdate = new JButton("update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pDBM = new PatientDBManager();
				if(!txt_ID.getText().isEmpty() && (!txt_Name.getText().isEmpty() || !txt_sname.getText().isEmpty() || !txt_iname.getText().isEmpty() || !txt_iID.getText().isEmpty())){// if all fields have values
					try {
						Insurance i=new Insurance(Integer.parseInt(txt_iID.getText()), txt_iname.getText());
						Patient p=new Patient(Integer.parseInt(txt_ID.getText()),txt_Name.getText(),txt_sname.getText(),i);
						pDBM.update(p);//updating an Patient
						JOptionPane.showMessageDialog(null,"Patient with patient ID= "+txt_ID.getText()+" updated successfully!"); 
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else// if one of the fields are empty
					JOptionPane.showMessageDialog(null,"You must fill all fields to update a patient!"); 
			}
		});
		btnUpdate.setBackground(new Color(245, 222, 179));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnUpdate.setBounds(454, 68, 160, 31);
		contentPane.add(btnUpdate);

		JButton btnDelete = new JButton("delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pDBM = new PatientDBManager();
				if(!txt_ID.getText().isEmpty()){//if delete a Patient by its ID is selected
					try {
						pDBM.delete(Integer.parseInt(txt_ID.getText()));//delete a Patient using its ID
						JOptionPane.showMessageDialog(null,"Patient with patient ID= "+txt_ID.getText()+" deleted successfully!"); 
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				} else{//if deleting all Patients is selected
					try {
						pDBM.deleteAll();//delete all Patients
						JOptionPane.showMessageDialog(null,"All of the patients deleted successfully!"); 
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnDelete.setBackground(new Color(245, 222, 179));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDelete.setBounds(624, 68, 160, 31);
		contentPane.add(btnDelete);

		txt_iID = new JTextField();
		txt_iID.setColumns(10);
		txt_iID.setBackground(new Color(255, 255, 224));
		txt_iID.setBounds(518, 21, 46, 31);
		contentPane.add(txt_iID);

		JLabel lblInsuranceId = new JLabel("Insurance ID :");
		lblInsuranceId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInsuranceId.setBounds(432, 29, 85, 14);
		contentPane.add(lblInsuranceId);
		
		JButton btn_back = new JButton("Back");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				dispose(); 
				new ManagerPageGUI().setVisible(true);
			}
		});
		btn_back.setForeground(new Color(255, 255, 255));
		btn_back.setBackground(new Color(139, 69, 19));
		btn_back.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_back.setBounds(370, 70, 64, 27);
		contentPane.add(btn_back);
	}
}
