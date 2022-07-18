package mediscreen_Ui.Services;

import java.util.List;

import mediscreen_Ui.Models.Patient;

public interface PatientService {
	public List<Patient> getPatients();

	public Patient getPatient(int id);

	public Patient addPatient(Patient patient);

	public Patient updatePatient(int id, Patient patient);

	public void deletePatient(int id);

}
