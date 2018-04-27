package HW14_pharmacy_classes;

import java.util.ArrayList;
import java.util.Date;

public class Prescription {
	private int ID;//prescription ID
	private String visitDate;
	private int m_p;//medicine prescription ID
	private int patient_ID;//PAtient ID that a prescription related to

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}

	public int getM_p() {
		return m_p;
	}

	public void setM_p(int m_p) {
		this.m_p = m_p;
	}

	public int getPatient_ID() {
		return patient_ID;
	}

	public void setPatient_ID(int patient_ID) {
		this.patient_ID = patient_ID;
	}

	public Prescription(int iD, String visitDate, int mp, int patient_ID) {
		ID = iD;
		this.visitDate = visitDate;
		setM_p(mp);
		this.patient_ID = patient_ID;
	}

	public Prescription(int ID, String visitDate, int mp) {
		this.ID = ID;
		this.visitDate = visitDate;
		setM_p(mp);
		setPatient_ID(-1);
	}

	public Prescription() {

	}
}
