package HW14_pharmacy_classes;

public class Insurance {
	private int insuranceID;
	private String insuranceName;

	public int getInsuranceID() {
		return insuranceID;
	}

	public void setInsuranceID(int insuranceID) {
		this.insuranceID = insuranceID;
	}

	public String getInsuranceName() {
		return insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	public Insurance(int insuranceID, String insuranceName) {
		this.insuranceID = insuranceID;
		this.insuranceName = insuranceName;
	}

	public Insurance() {

	}
}
