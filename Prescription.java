package HW14_pharmacy_classes;

import java.util.ArrayList;
import java.util.Date;

public class Prescription {
	private int ID;
	private Date visitDate;
	private double price;
	private ArrayList<Medicine> medicine;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ArrayList<Medicine> getMedicine() {
		return medicine;
	}

	public void setMedicine(ArrayList<Medicine> medicine) {
		this.medicine = medicine;
	}

	public Prescription(int ID, Date visitDate, double price, ArrayList<Medicine> medicine) {
		this.ID = ID;
		this.visitDate = visitDate;
		this.price = price;
		this.medicine = new ArrayList<Medicine>();
		setMedicine(medicine);
	}

	public Prescription() {

	}
}
