package HW14_pharmacy_classes;

public class Patient extends Person {
	private int prescription_ID;
	private Insurance insurance;

	public int getPrescription_ID() {
		return prescription_ID;
	}

	public void setPrescription_ID(int prescription_ID) {
		this.prescription_ID = prescription_ID;
	}

	public Insurance getInsurance() {
		return insurance;
	}

	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}

	public Patient() {

	}

	public Patient(int ID, String name, String surname, int prescription_ID, Insurance insurance) {
		setID(ID);
		setName(surname);
		setSurname(surname);
		setInsurance(insurance);
		setPrescription_ID(prescription_ID);
	}

	public Patient(int ID, String name, String surname, int prescription_ID) {
		setID(ID);
		setName(name);
		setSurname(surname);
		setPrescription_ID(prescription_ID);
	}
}
