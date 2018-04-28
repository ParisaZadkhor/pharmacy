package persistanceClasses;

public class Personel extends Person {
	private static String username;
	private String password;
	private int role; // staff, salesperson, manager each access is different to other

	public Personel() {

	}

	public Personel(String username, String password, int role) {
		setUsername(username);
		setPassword(password);
		setRole(role);
	}

	public Personel(int id, String name, String surname, String username, String password, int role) {
		setId(id);
		setName(name);
		setSurname(surname);
		setUsername(username);
		setPassword(password);
		setRole(role);
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String un) {
		username = un;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

}
