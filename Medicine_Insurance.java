package persistanceClasses;

public class Medicine_Insurance {

	private int id;
	private double percentage;
	private Medicine medicine;
	private Insurance insurance;
	
	public Medicine_Insurance(){}
	public Medicine_Insurance(int id, double percentage, Medicine medicine, Insurance insurance) {
		setId(id);
		setPercentage(percentage);
		setMedicine(medicine);
		setInsurance(insurance);
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public Insurance getInsurance() {
		return insurance;
	}

	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
