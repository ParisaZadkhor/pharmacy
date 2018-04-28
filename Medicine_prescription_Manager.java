package DBManagers;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;

import persistanceClasses.Medicine_prescription;
import persistanceClasses.Personel;


public class Medicine_prescription_Manager {
	Connection conn;
	Statement stmt;
	ResultSet rs;
	
	public Medicine_prescription_Manager() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String Url = "jdbc:mysql://127.0.0.1/pharmacy?user=root&password=";
			conn = DriverManager.getConnection(Url);
			stmt = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void insert(Medicine_prescription mp) {
		try {
			stmt.executeUpdate(MessageFormat.format(
					"insert into medicine_prescription (ID,p_ID,m_ID,number) values(''{0}'',''{1}'',''{2}'',''{3}'');",
					mp.getID(),mp.getP_ID(),mp.getM_ID(),mp.getNumber()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void update(Medicine_prescription mp) {
		try {
			stmt.executeUpdate("update medicine_prescription set p_ID='" + mp.getP_ID() + "' ,m_ID='" + mp.getM_ID()
			+ "' ,number='" + mp.getNumber() + "' where ID = '" + mp.getID() + "'; ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Medicine_prescription select(int id) {
		Medicine_prescription mp = new Medicine_prescription();
		ResultSet rs;
		try {
			rs = stmt.executeQuery("select ID,p_ID,m_ID,number from medicine_prescription  where ID=" + id + ";");
			while (rs.next()) {
				mp.setID(rs.getInt(1));
				mp.setP_ID(rs.getInt(2));
				mp.setM_ID(rs.getInt(3));
				mp.setNumber(rs.getInt(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mp;
	}
	
	public ArrayList<Medicine_prescription> selectAll() {
		ArrayList<Medicine_prescription> arrayList = new ArrayList<>();
		try {
			ResultSet rs = stmt.executeQuery("select * from  medicine_prescription;");
			while (rs.next()) {
				Medicine_prescription mp = new Medicine_prescription();
				mp.setID(rs.getInt(1));
				mp.setP_ID(rs.getInt(2));
				mp.setM_ID(rs.getInt(3));
				mp.setNumber(rs.getInt(4));
				arrayList.add(mp);
			}
		} catch (Exception emp) {
			emp.printStackTrace();
		}
		return arrayList;

	}
	public void delete(int id) {
		try {
			stmt.executeUpdate("delete from medicine_prescription where ID=" + id + ";");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deleteAll() {
		try {
			stmt.executeUpdate("delete * from medicine_prescription; ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
