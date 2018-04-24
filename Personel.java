package HW14_pharmacy_classes;

public class Personel extends Person {
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Personel(int id, String name, String surname, String username, String password) {
		setID(id);
		setName(name);
		setSurname(surname);
		setUsername(username);
		setPassword(password);
	}
	public Personel()
	{
		
	}

}
