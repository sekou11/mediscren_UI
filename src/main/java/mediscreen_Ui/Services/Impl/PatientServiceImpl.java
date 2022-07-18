package mediscreen_Ui.Services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mediscreen_Ui.Models.Patient;
import mediscreen_Ui.Proxies.PatientProxy;
import mediscreen_Ui.Services.PatientService;

@Service
public class PatientServiceImpl implements PatientService {
	@Autowired
	PatientProxy patientProxy;

	@Override
	public List<Patient> getPatients() {

		return patientProxy.getAllPatients();
	}

	@Override
	public Patient getPatient(int id) {

		return patientProxy.getPatient(id);
	}

	@Override
	public Patient addPatient(Patient patient) {

		return patientProxy.addPatient(patient);
	}

	@Override
	public Patient updatePatient(int id, Patient patient) {

		return patientProxy.updatePatient(patient, id);
	}

	@Override
	public void deletePatient(int id) {
		patientProxy.deletePatient(id);

	}

}
