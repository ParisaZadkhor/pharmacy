package DBManagers;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;

import persistanceClasses.Medicine;
import persistanceClasses.Personel;

public class MedicineManager {
	Connection conn;
	Statement stmt;
	ResultSet rs;
	public MedicineManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String Url = "jdbc:mysql://127.0.0.1/pharmacy?user=root&password=";
			conn = DriverManager.getConnection(Url);
			stmt = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void insert(Medicine m) {
		try {
			stmt.executeUpdate(MessageFormat.format(
					"insert into medicine (name,price,number) values(''{0}'',''{1}'',''{2}'');",
					m.getName(),m.getPrice(),m.getNumber()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void update (Medicine m)
	{
		try {
			stmt.executeUpdate("update Medicine set name='" + m.getName() + "' ,price='" +m.getPrice()+ "' ,number='" + m.getNumber() + "' where id = '"+m.getId()+"'; ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Medicine select(int id)
	{
		Medicine m = new Medicine();
		ResultSet rs;
		try {
			rs = stmt.executeQuery("select id,name,price,number from medicine where id="+id+";");
			while (rs.next())
			{
				m.setId(rs.getInt(1));
				m.setName(rs.getString(2));
				m.setPrice(rs.getDouble(3));
				m.setNumber(rs.getInt(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}
	public ArrayList<Medicine> selectAll()
	{
		ArrayList <Medicine> medicines = new ArrayList<>();
		try{
			ResultSet rs = stmt.executeQuery("select * from medicine ;");
			while (rs.next())
			{
				Medicine m = new Medicine();
				m.setId(rs.getInt(1));
				m.setName(rs.getString(2));
				m.setPrice(rs.getDouble(3));
				m.setNumber(rs.getInt(4));
				medicines.add(m);
			}
		}catch(Exception emp)
		{
			emp.printStackTrace();
		}
		return medicines;
		
	}
	public void delete(int id){
		try {
			stmt.executeUpdate("delete from medicine where id="+id+";");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deleteAll()
	{
		try {
			stmt.executeUpdate("delete * from medicine");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
