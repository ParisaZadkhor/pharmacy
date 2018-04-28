package persistanceClasses;

public class Medicine {

	private int id;
	private String name;
	private double price;
	private int number; // number of drugs that existed in drugstore 

	public Medicine() {
	}

	public Medicine(int id,String name, double price,int number) {
		setId(id);
		setName(name);
		setPrice(price);
		setNumber(number);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	

}
