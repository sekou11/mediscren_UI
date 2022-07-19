package mediscreen_Ui.Services.Impl;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import mediscreen_Ui.Models.Patient;
import mediscreen_Ui.Proxies.PatientProxy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {
	   private PatientServiceImpl patientProxyServiceImplUnderTest;

	    final Patient patient = new Patient();
	    final Patient patient1 = new Patient();

	    @BeforeEach
	    void setUp() {
	        patientProxyServiceImplUnderTest = new PatientServiceImpl();
	        patientProxyServiceImplUnderTest.patientProxy = mock(PatientProxy.class);

	        patient.setId(1);
	        patient.setFirstName("firstName");
	        patient.setLastName("lastName");
	        patient.setBirthdate(LocalDate.of(2020, 1, 1));
	        patient.setSex("M");
	        patient.setAddress("address");
	        patient.setPhone("phone");


	        patient1.setId(2);
	        patient1.setFirstName("firstName");
	        patient1.setLastName("lastName");
	        patient1.setBirthdate(LocalDate.of(2020, 1, 1));
	        patient1.setSex("sex");
	        patient1.setAddress("address");
	        patient1.setPhone("phone");

	    }

	    @Test
	    void testGetPatients() {
	        final List<Patient> patients = Collections.singletonList(patient);
	        when(patientProxyServiceImplUnderTest.patientProxy.getAllPatients()).thenReturn(patients);

	        final List<Patient> result = patientProxyServiceImplUnderTest.getAllPatients();

	        verify(patientProxyServiceImplUnderTest.patientProxy).getAllPatients();

	    }

	    @Test
	    void testGetPatients_PatientProxyReturnsNoItems() {
	        when(patientProxyServiceImplUnderTest.patientProxy.getAllPatients()).thenReturn(Collections.emptyList());

	        final List<Patient> result = patientProxyServiceImplUnderTest.getAllPatients();

	        assertThat(result).isEqualTo(Collections.emptyList());
	    }

	    @Test
	    void testGetPatient() {
	        when(patientProxyServiceImplUnderTest.patientProxy.getPatient(1)).thenReturn(patient);

	        final Patient result = patientProxyServiceImplUnderTest.getPatient(1);

	        verify(patientProxyServiceImplUnderTest.patientProxy).getPatient(1);

	    }

	    @Test
	    void testAddPatient() {

	        when(patientProxyServiceImplUnderTest.patientProxy.addPatient(any(Patient.class))).thenReturn(patient1);

	        final Patient result = patientProxyServiceImplUnderTest.addPatient(patient);

	        verify(patientProxyServiceImplUnderTest.patientProxy).addPatient(any(Patient.class));
	    }



	    @Test
	    void testDeletePatient() {
	        patientProxyServiceImplUnderTest.deletePatient(1);

	        verify(patientProxyServiceImplUnderTest.patientProxy).deletePatient(1);
	    }
	
}