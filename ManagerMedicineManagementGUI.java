package HW14_pharmacy_GUI;
/*
 * this page is created by zahra shayesteh
 * this is the medicine management page of the manager
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import HW14_pharmacy_DBManagers.InsuranceDBManager;
import HW14_pharmacy_DBManagers.MedicineManager;
import HW14_pharmacy_classes.Insurance;
import HW14_pharmacy_classes.Medicine;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ManagerMedicineManagementGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txt_ID;
	private JTextField txt_name;
	private JTextField txt_price;
	private JTextField txt_number;
	MedicineManager mDBM;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerMedicineManagementGUI frame = new ManagerMedicineManagementGUI();
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
	public ManagerMedicineManagementGUI() {
		setTitle("Manager medicine management");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(189, 183, 107));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblId = new JLabel("ID :");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblId.setBounds(23, 21, 23, 14);
		contentPane.add(lblId);

		JLabel lblName = new JLabel("name :");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblName.setBounds(89, 21, 46, 14);
		contentPane.add(lblName);

		JLabel lblPrice = new JLabel("price :");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrice.setBounds(231, 21, 46, 14);
		contentPane.add(lblPrice);

		JLabel lblNumber = new JLabel("number :");
		lblNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNumber.setBounds(340, 21, 59, 14);
		contentPane.add(lblNumber);

		txt_ID = new JTextField();
		txt_ID.setBackground(new Color(240, 248, 255));
		txt_ID.setBounds(45, 18, 34, 20);
		contentPane.add(txt_ID);
		txt_ID.setColumns(10);

		txt_name = new JTextField();
		txt_name.setBackground(new Color(240, 248, 255));
		txt_name.setText("");
		txt_name.setBounds(135, 18, 86, 20);
		contentPane.add(txt_name);
		txt_name.setColumns(10);

		txt_price = new JTextField();
		txt_price.setBackground(new Color(240, 248, 255));
		txt_price.setText("");
		txt_price.setBounds(271, 18, 59, 20);
		contentPane.add(txt_price);
		txt_price.setColumns(10);

		txt_number = new JTextField();
		txt_number.setBackground(new Color(240, 248, 255));
		txt_number.setBounds(401, 18, 86, 20);
		contentPane.add(txt_number);
		txt_number.setColumns(10);

		JButton btnShow = new JButton("Show");
		btnShow.setBackground(new Color(238, 232, 170));
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mDBM = new MedicineManager();
				if(txt_ID.getText().isEmpty()){//if read all is selected
					ArrayList<Medicine> medicines = new ArrayList<Medicine>();
					String col[] = {"Medicine ID","Name","Price","Number"};
					DefaultTableModel tableModel = new DefaultTableModel(col, 0);// The 0 argument is number rows.
					JTable table = new JTable(tableModel);
					medicines = mDBM.selectAll();//read all the Medicines
					for (int i = 0; i < medicines.size(); i++) {
						Object[] objs = {medicines.get(i).getId(),medicines.get(i).getName(),medicines.get(i).getPrice(),medicines.get(i).getNumber()};
						tableModel.addRow(objs);
					}
					JScrollPane sp = new JScrollPane(table);
					sp.setBounds(17, 110, 466, 130);
					contentPane.add(sp);
				}
				else//if reading one medicine by its ID is selected
				{
					Medicine medicine;
					String col[] = {"Medicine ID","Name","Price","Number"};
					DefaultTableModel tableModel = new DefaultTableModel(col, 0);// The 0 argument is number rows.
					JTable table = new JTable(tableModel);
					medicine = mDBM.select(Integer.parseInt(txt_ID.getText()));//reading a Medicine using its ID
					Object[] objs = {medicine.getId(),medicine.getName(),medicine.getPrice(),medicine.getNumber()};
					tableModel.addRow(objs);
					JScrollPane sp = new JScrollPane(table);
					sp.setBounds(17, 110, 466, 130);
					contentPane.add(sp);
				}
			}
		});
		btnShow.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnShow.setBounds(23, 59, 89, 23);
		contentPane.add(btnShow);

		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mDBM = new MedicineManager();
				if(!txt_name.getText().isEmpty() && !txt_price.getText().isEmpty() && !txt_number.getText().isEmpty()){//if name,price and number field is not empty
					Medicine m=new Medicine(txt_name.getText(),Double.parseDouble(txt_price.getText()),Integer.parseInt(txt_number.getText()));
					mDBM.insert(m);//creating an Medicine using its name
					JOptionPane.showMessageDialog(null,"The medicine "+m.getName()+" created successfully!"); 
				}
				else//  if essential fields are empty
					JOptionPane.showMessageDialog(null,"You should fill the medicine name,price and number fields to create an medicine!"); 
			}
		});
		btnCreate.setBackground(new Color(238, 232, 170));
		btnCreate.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCreate.setBounds(126, 59, 89, 23);
		contentPane.add(btnCreate);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mDBM = new MedicineManager();
				if(!txt_ID.getText().isEmpty() && !txt_name.getText().isEmpty() && !txt_number.getText().isEmpty() && !txt_price.getText().isEmpty()){// if all fields have values
					Medicine m=new Medicine(Integer.parseInt(txt_ID.getText()),txt_name.getText(),Double.parseDouble(txt_price.getText()),Integer.parseInt(txt_number.getText()));
					mDBM.update(m);//updating a Medicine
					JOptionPane.showMessageDialog(null,"Medicine with Medicine ID= "+txt_ID.getText()+" updated successfully!"); 
				}
				else// if even one of the fields are empty
					JOptionPane.showMessageDialog(null,"You must fill all fields to update a medicine!"); 
			}
		});
		btnUpdate.setBackground(new Color(238, 232, 170));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnUpdate.setBounds(299, 59, 89, 23);
		contentPane.add(btnUpdate);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mDBM = new MedicineManager();
				if(!txt_ID.getText().isEmpty()){//if delete an Medicine by its ID is selected
					mDBM.delete(Integer.parseInt(txt_ID.getText()));//delete an Medicine using its ID
					JOptionPane.showMessageDialog(null,"Medicine with Medicine ID= "+txt_ID.getText()+" deleted successfully!"); 	
				} else{//if deleting all Medicines is selected
					mDBM.deleteAll();//delete all Medicines
					JOptionPane.showMessageDialog(null,"All of the medicines deleted successfully!");
				}
			}
		});
		btnDelete.setBackground(new Color(238, 232, 170));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDelete.setBounds(398, 59, 89, 23);
		contentPane.add(btnDelete);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				dispose(); 
				new ManagerPageGUI().setVisible(true);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(107, 142, 35));
		btnBack.setBounds(225, 59, 64, 23);
		contentPane.add(btnBack);
	}
}
