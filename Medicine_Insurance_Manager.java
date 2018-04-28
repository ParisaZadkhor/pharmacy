package DBManagers;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;

import persistanceClasses.Insurance;
import persistanceClasses.Medicine;
import persistanceClasses.Medicine_Insurance;
import persistanceClasses.Personel;

public class Medicine_Insurance_Manager {
	Connection conn;
	Statement stmt;
	ResultSet rs;

	public Medicine_Insurance_Manager() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String Url = "jdbc:mysql://127.0.0.1/pharmacy?user=root&password=";
			conn = DriverManager.getConnection(Url);
			stmt = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insert(Medicine_Insurance mi) {
		try {
			stmt.executeUpdate(MessageFormat.format(
					"insert into medicine_insurance (ID,percentage,medicine_ID,insurance_id) values(''{0}'',''{1}'',''{2}'',''{3}'');",
					mi.getId(), mi.getPercentage(), mi.getMedicine().getId(), mi.getInsurance().getInsuranceID()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(Medicine_Insurance mi) {
		try {
			stmt.executeUpdate("update medicine_insurance  set percentage='" + mi.getPercentage() + "' ,medicine_ID='"
					+ mi.getMedicine().getId() + "' ,insurance_id='" + mi.getInsurance().getInsuranceID()
					+ "' where ID = '" + mi.getId() + "'; ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Medicine_Insurance select(int id) {
		Medicine_Insurance mi = new Medicine_Insurance();
		ResultSet rs, resultSet;
		try {
			rs = stmt.executeQuery(
					"select ID,percentage,,medicine_ID,insurance_id from medicine_insurance  where id=" + id + ";");
			while (rs.next()) {
				mi.setId(rs.getInt(1));
				mi.setPercentage(rs.getDouble(2));
				
				int m_id = rs.getInt(3); 
				Medicine medicine = new Medicine();  
				MedicineManager MedicineManager = new MedicineManager(); //select from medicine where id is  m_id 
				medicine = MedicineManager.select(m_id);
				mi.setMedicine(medicine);
				
				int i_id = rs.getInt(4);
				Insurance insurance = new Insurance(); //select from insurance where id is i_id
				InsuranceDBManager InsuranceDBManager = new InsuranceDBManager();
				insurance = InsuranceDBManager.read(i_id);
				mi.setInsurance(insurance);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mi;
	}
	public ArrayList<Medicine_Insurance> selectAll() {
		ArrayList<Medicine_Insurance> arrayList = new ArrayList<>();
		try {
			ResultSet rs = stmt.executeQuery("select * from medicine_insurance ;");
			while (rs.next()) {
				Medicine_Insurance mi = new Medicine_Insurance();
				mi.setId(rs.getInt(1));
				mi.setPercentage(rs.getDouble(2));
				
				int m_id = rs.getInt(3);
				Medicine medicine = new Medicine();
				MedicineManager MedicineManager = new MedicineManager();
				medicine = MedicineManager.select(m_id);
				mi.setMedicine(medicine);
				
				int i_id = rs.getInt(4);
				Insurance insurance = new Insurance();
				InsuranceDBManager InsuranceDBManager = new InsuranceDBManager();
				insurance = InsuranceDBManager.read(i_id);
				mi.setInsurance(insurance);
			
				arrayList.add(mi);
			}
		} catch (Exception emp) {
			emp.printStackTrace();
		}
		return arrayList;

	}
	public void delete(int id) {
		try {
			stmt.executeUpdate("delete from medicine_insurance where id=" + id + ";");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deleteAll() {
		try {
			stmt.executeUpdate("delete * from medicine_insurance");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
