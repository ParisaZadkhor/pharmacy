package HW14_pharmacy_DBManagers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import HW14_pharmacy_classes.Patient;
import HW14_pharmacy_classes.Insurance;
public class PatientDBManager {
	Statement stmt;

	public PatientDBManager() {//constructor of the class which initialize mysql connector parameters
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://127.0.0.1/pharmacy?user=root&password=";
			Connection conn = DriverManager.getConnection(url);
			if (conn == null) {
				System.out.println("errrrrrrrrrror connection");
			}
			stmt = conn.createStatement();
		} catch (Exception e) {
			System.out.println("exception " + e);
		}
	}

	public int create(Patient p) throws SQLException {// create a patient which returns its ID
		stmt.executeUpdate(MessageFormat.format("insert into patient (name,surname,insurance_ID) values(''{0}'',''{1}'',{2});",
				p.getName(), p.getSurname(),p.getInsurance().getInsuranceID()));
		ResultSet rs=stmt.executeQuery(MessageFormat.format("select ID from patient where name=''{0}'' and surname=''{1}'' and insurance_ID={3};",p.getName(),p.getSurname(),p.getInsurance().getInsuranceID()));
		while(rs.next())
			return rs.getInt(1);
		return -1;//if patient does not create successfully
	}

	public Patient read(int ID) throws SQLException {// read one patient by its ID and returns its object
		int InsuranceID,id;
		String name, surname;
		Patient p;
		Insurance i=null;
		ResultSet rs = stmt.executeQuery("select * from patient where ID=" + ID + ";");//finding patient
		while (rs.next()) {
			id=rs.getInt(1);
			name = rs.getString(2);
			surname = rs.getString(3);
			InsuranceID = rs.getInt(4);
			ResultSet rs1 = stmt.executeQuery("select * from insurance where insuranceID=" + InsuranceID + ";");//retrieving patient insurance
			while (rs1.next()) {
				int InID = rs1.getInt(1);
				String InName=rs1.getString(2);
				i=new Insurance(InID, InName);
			}
			p = new Patient(id,name,surname,i);
			return p;//returning the patient object
		}
		return new Patient();//if patient does not found, it will return a empty object of patient
	}

	public ArrayList<Patient> readAll() throws SQLException// read all patients and returns their list
	{
		ArrayList<Patient> patients = new ArrayList<Patient>();
		int InsuranceID,id;
		String name, surname;
		Patient p;
		Insurance i=null;
		ResultSet rs = stmt.executeQuery("select * from patient;");
		while (rs.next()) {
			id=rs.getInt(1);
			name = rs.getString(2);
			surname = rs.getString(3);
			InsuranceID = rs.getInt(4);
			ResultSet rs1 = stmt.executeQuery("select * from insurance where insurance_ID=" + InsuranceID + ";");//retrieving patient insurance
			while (rs1.next()) {
				int InID = rs1.getInt(1);
				String InName=rs1.getString(2);
				i=new Insurance(InID, InName);
			}
			p = new Patient(id,name,surname,i);
			patients.add(p);//adding patient into patients list
		}
		return patients;//returning the list of patients
	}

	public void update(Patient p) throws SQLException {// update a patient
		stmt.executeUpdate("update patient set name='" + p.getName() + "' ,surname='" + p.getSurname()+ "' , insurance_ID=" + p.getInsurance().getInsuranceID() + " where ID="+p.getId()+";");
	}

	public void delete(int ID) throws SQLException {// delete a patient with its ID
		stmt.executeUpdate("delete from patient where ID=" + ID + ";");
	}

	public void deleteAll() throws SQLException {// delete all patients
		stmt.executeUpdate("truncate table patient;");
	}
}
