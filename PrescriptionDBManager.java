package HW14_pharmacy_DBManagers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import HW14_pharmacy_classes.*;

public class PrescriptionDBManager {
	Statement stmt;

	public PrescriptionDBManager() {//constructor of the class which initialize mysql connector parameters
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

	public int create(Prescription p) throws SQLException {// create a Prescription which returns its ID
		int pID;
		if(p.getPatient_ID()!=-1)//if patient has insurance and his/her name should save int the system
			stmt.executeUpdate(MessageFormat.format("insert into prescription (visitDate,m_p_ID,patient_ID) values(''{0}'',{1},{2});",p.getVisitDate(),p.getM_p(),p.getPatient_ID()));
		else//if patient has not insurance and his/her name should not save in the system
			stmt.executeUpdate(MessageFormat.format("insert into prescription (visitDate,m_p_ID) values(''{0}'',{1});",p.getVisitDate(),p.getM_p()));
		ResultSet rs = stmt.executeQuery("select ID from prescription where visitDate='" + p.getVisitDate() + "';");//finding prescription's ID
		while(rs.next()){
			pID=rs.getInt(1);
			return pID;//id of prescription
		}
		return -1;//prescription does not create successfully
	}

	public Prescription read(int ID) throws SQLException {// read one prescription by its ID and returns its object
		int Prescription_ID;
		String date;
		int mp_ID;
		int p_ID;
		Prescription p;
		ResultSet rs = stmt.executeQuery("select * from prescription where ID=" + ID + ";");//finding prescription
		while (rs.next()) {
			Prescription_ID=rs.getInt(1);
			date = rs.getString(2);
			mp_ID=rs.getInt(3);
			p_ID=rs.getInt(4);
			p = new Prescription(Prescription_ID,date,mp_ID,p_ID);
			return p;//returning the patient object
		}
		return new Prescription();//if prescription does not found it will create a empty object of prescription
	}

	public ArrayList<Prescription> readAll() throws SQLException// read all prescriptions and returns their list
	{
		ArrayList<Prescription> prescriptions=new ArrayList<Prescription>();
		int Prescription_ID;
		String date;
		int mp_ID;
		int p_ID;
		Prescription p;
		ResultSet rs = stmt.executeQuery("select * from prescription;");//finding prescriptions
		while (rs.next()) {
			Prescription_ID=rs.getInt(1);
			date = rs.getString(2);
			mp_ID=rs.getInt(3);
			p_ID=rs.getInt(4);
			p = new Prescription(Prescription_ID,date,mp_ID,p_ID);
			prescriptions.add(p);
		}
		return prescriptions;//returning the list of prescriptions
	}

	public void update(Prescription p) throws SQLException {// update a prescription
		stmt.executeUpdate("update prescription set ID=" + p.getID() + " ,visitDate='" + p.getVisitDate()+ "' ,m_p_ID=" + p.getM_p() + " ,patient_ID=" + p.getPatient_ID() + ";");
	}

	public void delete(int ID) throws SQLException {// delete a prescription with its ID
		stmt.executeUpdate("delete from prescription where ID=" + ID + ";");
	}

	public void deleteAll() throws SQLException {// delete all prescriptions
		stmt.executeUpdate("truncate table prescription;");
	}
}
