package DBManagers;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;

import persistanceClasses.Personel;


public class PersonelManager {
	Connection conn;
	Statement stmt;
	ResultSet rs;

	public PersonelManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String Url = "jdbc:mysql://127.0.0.1/pharmacy?user=root&password=";
			conn = DriverManager.getConnection(Url);
			stmt = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insert(Personel p) {
		try {
			stmt.executeUpdate(MessageFormat.format(
					"insert into personel (name,surname,username,password,role) values(''{0}'',''{1}'',''{2}'',''{3}'',''{4}'');",
					p.getName(), p.getSurname(), p.getUsername(), p.getPassword(), p.getRole()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(Personel p) {
		try {
			stmt.executeUpdate("update personel set name='" + p.getName() + "' ,surname='" + p.getSurname()
			+ "' ,username='" + p.getUsername() + "' ,password='" + p.getPassword() + "',role='" + p.getRole()
			+ "' where id = '" + p.getId() + "'; ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Personel select(int id) {
		Personel p = new Personel();
		ResultSet rs;
		try {
			rs = stmt.executeQuery("select id,name,surname,username,password,role from personel where id=" + id + ";");
			while (rs.next()) {
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setSurname(rs.getString(3));
				p.setUsername(rs.getString(4));
				p.setPassword(rs.getString(5));
				p.setRole(rs.getInt(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	public ArrayList<Personel> selectAll() {
		ArrayList<Personel> personels = new ArrayList<>();
		try {
			ResultSet rs = stmt.executeQuery("select * from personel ;");
			while (rs.next()) {
				Personel p = new Personel();
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setSurname(rs.getString(3));
				p.setUsername(rs.getString(4));
				p.setPassword(rs.getString(5));
				p.setRole(rs.getInt(5));
				personels.add(p);
			}
		} catch (Exception emp) {
			emp.printStackTrace();
		}
		return personels;

	}

	public ArrayList<Personel> selectRole(int role) {
		ArrayList<Personel> personels = new ArrayList<>();
		try {
			ResultSet rs = stmt.executeQuery("select * from personel where role=" + role + " ;");
			while (rs.next()) {
				Personel p = new Personel();
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setSurname(rs.getString(3));
				p.setUsername(rs.getString(4));
				p.setPassword(rs.getString(5));
				p.setRole(rs.getInt(5));
				personels.add(p);
			}
		} catch (Exception emp) {
			emp.printStackTrace();
		}
		return personels;
	}

	public void delete(int id) {
		try {
			stmt.executeUpdate("delete from personel where id=" + id + ";");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteAll() {
		try {
			stmt.executeUpdate("delete * from personel;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean authentication(Personel p) throws SQLException{
		ResultSet rs = stmt.executeQuery("select * from personel where username= '"+p.getUsername()+"' and password= '"+p.getPassword()+"' and role=" + p.getRole() + " ;");
		while (rs.next())
			return true;
		return false;
	}

}
