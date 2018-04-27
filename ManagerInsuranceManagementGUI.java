package HW14_pharmacy_GUI;
/*
 * this page has been created by zahra shayesteh
 * this is the insurance management page for manager
 * he/she can read,update,delete and create a insurance or a set of insurances.
 */
import HW14_pharmacy_DBManagers.*;
import HW14_pharmacy_classes.Insurance;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Font;

public class ManagerInsuranceManagementGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txt_ID;
	private JTextField txt_name;
	InsuranceDBManager iDBM;

	/**
	 * Create the frame.
	 */
	public ManagerInsuranceManagementGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(233, 150, 122));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(null);
		JLabel lbl_insuranceID = new JLabel("Insurance ID :");
		lbl_insuranceID.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_insuranceID.setBounds(27, 21, 83, 36);
		contentPane.add(lbl_insuranceID);

		txt_ID = new JTextField();
		txt_ID.setBounds(120, 24, 103, 31);
		contentPane.add(txt_ID);
		txt_ID.setColumns(10);

		JLabel lbl_insuranceName = new JLabel("Insurance name :");
		lbl_insuranceName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_insuranceName.setBounds(244, 21, 114, 36);
		contentPane.add(lbl_insuranceName);

		txt_name = new JTextField();
		txt_name.setBounds(357, 24, 103, 31);
		contentPane.add(txt_name);
		txt_name.setColumns(10);

		JButton btn_update = new JButton("update");
		btn_update.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_update.setBounds(265, 68, 103, 31);
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iDBM = new InsuranceDBManager();
				if(!txt_ID.getText().isEmpty() && !txt_name.getText().isEmpty()){// if both fields have values
					Insurance i=new Insurance(Integer.parseInt(txt_ID.getText()),txt_name.getText());
					try {
						iDBM.update(i);//updating an insurance
						JOptionPane.showMessageDialog(null,"Insurance with insurance ID= "+txt_ID.getText()+" updated successfully!"); 
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else// if one of the fileds or both of them are empty
					JOptionPane.showMessageDialog(null,"You must fill both fields to update an insurance!"); 
			}
		});

		JButton btn_create = new JButton("create");
		btn_create.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_create.setBounds(140, 68, 103, 31);
		btn_create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iDBM = new InsuranceDBManager();
				if(!txt_name.getText().isEmpty()){//if name field is not empty
					Insurance i=new Insurance(txt_name.getText());
					try {
						int iID=iDBM.create(i);//creating an insurance using its name
						JOptionPane.showMessageDialog(null,"The insurance "+i.getInsuranceName()+" with the insurance ID= "+iID+" created successfully!"); 
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else//  if one of the fileds or both of them are empty
					JOptionPane.showMessageDialog(null,"You should fill the insurance name field to create an insurance!"); 
			}
		});

		JButton btn_Show = new JButton("show");
		btn_Show.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_Show.setBounds(27, 68, 103, 31);
		btn_Show.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iDBM = new InsuranceDBManager();
				if(txt_ID.getText().isEmpty()){//if read all is selected by manager
					ArrayList<Insurance> insurances = new ArrayList<Insurance>();
					String col[] = {"Insurance ID","Insurance Name"};
					DefaultTableModel tableModel = new DefaultTableModel(col, 0);// The 0 argument is number rows.
					JTable table = new JTable(tableModel);
					try {
						insurances = iDBM.readAll();//read all the insurances
						for (int i = 0; i < insurances.size(); i++) {
							Object[] objs = {insurances.get(i).getInsuranceID(),insurances.get(i).getInsuranceName()};
							tableModel.addRow(objs);
						}
						JScrollPane sp = new JScrollPane(table);
						sp.setBounds(17, 110, 466, 130);
						contentPane.add(sp);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else//if reading one insurance by its ID is selected
				{
					Insurance insurance;
					String col[] = {"Insurance ID","Insurance Name"};
					DefaultTableModel tableModel = new DefaultTableModel(col, 0);// The 0 argument is number rows.
					JTable table = new JTable(tableModel);
					try {
						insurance = iDBM.read(Integer.parseInt(txt_ID.getText()));//reading an insurance using its ID
						Object[] objs = {insurance.getInsuranceID(),insurance.getInsuranceName()};
						tableModel.addRow(objs);
						JScrollPane sp = new JScrollPane(table);
						sp.setBounds(17, 110, 466, 130);
						contentPane.add(sp);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		contentPane.add(btn_Show);
		contentPane.add(btn_create);

		contentPane.add(btn_update);

		JButton btn_delete = new JButton("delete");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iDBM = new InsuranceDBManager();
				if(!txt_ID.getText().isEmpty()){//if delete an insurance by its ID is selected
					try {
						iDBM.delete(Integer.parseInt(txt_ID.getText()));//delete an insurance using its ID
						JOptionPane.showMessageDialog(null,"Insurance with insurance ID= "+txt_ID.getText()+" deleted successfully!"); 
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				} else{//if deleting all insurances is selected
					try {
						iDBM.deleteAll();//delete all insurances
						JOptionPane.showMessageDialog(null,"All of the insurances deleted successfully!"); 
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btn_delete.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_delete.setBounds(378, 68, 103, 31);
		contentPane.add(btn_delete);

	}
}
