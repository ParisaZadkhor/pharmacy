package HW14_pharmacy_DBManagers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import HW14_pharmacy_classes.Insurance;

public class InsuranceDBManager {
	Statement stmt;

	public InsuranceDBManager() {//constructor of the class which initialize mysql connector parameters
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

	public int create(Insurance i) throws SQLException {// create a Insurance which returns its ID
		stmt.executeUpdate(MessageFormat.format("insert into insurance (insuranceID,insuranceName) values({0},''{1}'');",
				i.getInsuranceID(),i.getInsuranceName()));
		ResultSet rs=stmt.executeQuery(MessageFormat.format("select insuranceID from insurance where insuranceName=''{0}'';",i.getInsuranceName()));
		while(rs.next())
			return rs.getInt(1);
		return -1;//if Insurance does not create successfully
	}

	public Insurance read(int ID) throws SQLException {// read one Insurance by its ID and returns its object
		String iname;
		int id;
		Insurance i;
		ResultSet rs = stmt.executeQuery("select * from insurance where insuranceID=" + ID + ";");//finding Insurance
		while (rs.next()) {
			id=rs.getInt(1);
			iname = rs.getString(2);
			i = new Insurance(id,iname);
			return i;//returning the Insurance object
		}
		return new Insurance();//if Insurance does not found it will create a empty object of Insurance
	}

	public ArrayList<Insurance> readAll() throws SQLException// read all Insurances and returns their list
	{
		ArrayList<Insurance> managers = new ArrayList<Insurance>();
		int id;
		String iname;
		Insurance i;
		ResultSet rs = stmt.executeQuery("select * from insurance;");
		while (rs.next()) {
			id=rs.getInt(1);
			iname = rs.getString(2);
			i = new Insurance(id,iname);
			managers.add(i);
		}
		return managers;//returning the list of managers
	}

	public void update(Insurance i) throws SQLException {// update a insurance
		stmt.executeUpdate("update insurance set insuranceName='" + i.getInsuranceName() + "' where insuranceID="+i.getInsuranceID()+";");
	}

	public void delete(int ID) throws SQLException {// delete a insurance with its ID
		stmt.executeUpdate("delete from insurance where insuranceID=" + ID + ";");
	}

	public void deleteAll() throws SQLException {// delete all insurances
		stmt.executeUpdate("truncate table insurance;");
	}
}
