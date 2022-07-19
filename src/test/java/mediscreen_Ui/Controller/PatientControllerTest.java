package mediscreen_Ui.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import mediscreen_Ui.Models.Note;
import mediscreen_Ui.Models.Patient;
import mediscreen_Ui.Services.Impl.NoteServiceImpl;
import mediscreen_Ui.Services.Impl.PatientServiceImpl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PatientController.class)
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientServiceImpl mockPatientProxy;
    @MockBean
    private NoteServiceImpl mockNoteProxy;
    

    final Patient patient = new Patient();
    final Patient patient1 = new Patient();
    final Note note = new Note();

    @BeforeEach
    void setUp() {
        patient.setId(0);
        patient.setFirstName("firstName");
        patient.setLastName("lastName");
        patient.setBirthdate(LocalDate.of(2020, 1, 1));
        patient.setSex("sex");
        patient.setAddress("address");
        patient.setPhone("phone");

        patient1.setId(0);
        patient1.setFirstName("firstName");
        patient1.setLastName("lastName");
        patient1.setBirthdate(LocalDate.of(2020, 1, 1));
        patient1.setSex("M");
        patient1.setAddress("address");
        patient1.setPhone("phone");

        note.setId("id");
        note.setPatientId(0);
        note.setNote("note");
    }

    @Test
    void testGetPatients() throws Exception {

        final List<Patient> patients = Collections.singletonList(patient);
        when(mockPatientProxy.getAllPatients()).thenReturn(patients);

        final MockHttpServletResponse response = mockMvc.perform(get("/patient/list")
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetPatients_PatientProxyServiceReturnsNoItems() throws Exception {
        when(mockPatientProxy.getAllPatients()).thenReturn(Collections.emptyList());

        final MockHttpServletResponse response = mockMvc.perform(get("/patient/list")
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetPatient() throws Exception {
        when(mockPatientProxy.getPatient(0)).thenReturn(patient);

        

        final List<Note> notes = Collections.singletonList(note);
        when(mockNoteProxy.getNotesByPatient(0)).thenReturn(notes);

        final MockHttpServletResponse response = mockMvc.perform(get("/patient/fiche/{id}", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetPatient_NoteProxyServiceReturnsNoItems() throws Exception {
        when(mockPatientProxy.getPatient(0)).thenReturn(patient);

        
        when(mockNoteProxy.getNotesByPatient(0)).thenReturn(Collections.emptyList());

        final MockHttpServletResponse response = mockMvc.perform(get("/patient/fiche/{id}", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testShowAddPatientForm() throws Exception {
        final MockHttpServletResponse response = mockMvc.perform(get("/patient/add")
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testSubmitAddPatientForm() throws Exception {
        when(mockPatientProxy.addPatient(any(Patient.class))).thenReturn(patient);

        final List<Patient> patients = Collections.singletonList(patient1);
        when(mockPatientProxy.getAllPatients()).thenReturn(patients);

        final MockHttpServletResponse response = mockMvc.perform(post("/patient/add")
                        .param("id", "0")
                        .param("firstName", "firstName")
                        .param("lastName", "lastName")
                        .param("birthdate", "2020-01-01")
                        .param("sex", "sex")
                        .param("address", "address")
                        .param("phone", "phone")
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
    }

    @Test
    void testSubmitAddPatientForm_PatientProxyServiceGetPatientsReturnsNoItems() throws Exception {
        when(mockPatientProxy.addPatient(any(Patient.class))).thenReturn(patient);

        when(mockPatientProxy.getAllPatients()).thenReturn(Collections.emptyList());

        final MockHttpServletResponse response = mockMvc.perform(post("/patient/add")
                        .param("id", "0")
                        .param("firstName", "firstName")
                        .param("lastName", "lastName")
                        .param("birthdate", "birthdate")
                        .param("sex", "sex")
                        .param("address", "address")
                        .param("phone", "phone")
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testUpdatePatientForm() throws Exception {
        when(mockPatientProxy.getPatient(0)).thenReturn(patient);

        final MockHttpServletResponse response = mockMvc.perform(get("/patient/update/{id}", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testUpdatePatient() throws Exception {
        when(mockPatientProxy.updatePatient(eq(0), any(Patient.class))).thenReturn(patient);

        final List<Patient> patients = Collections.singletonList(patient1);
        when(mockPatientProxy.getAllPatients()).thenReturn(patients);

        final MockHttpServletResponse response = mockMvc.perform(post("/patient/update/{id}", 0)
                        .param("id", "0")
                        .param("firstName", "firstName")
                        .param("lastName", "lastName")
                        .param("birthdate", "2020-01-01")
                        .param("sex", "M")
                        .param("address", "address")
                        .param("phone", "phone")
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
    }

    @Test
    void testUpdatePatient_PatientProxyServiceGetPatientsReturnsNoItems() throws Exception {

        when(mockPatientProxy.updatePatient(eq(0), any(Patient.class))).thenReturn(patient);

        when(mockPatientProxy.getAllPatients()).thenReturn(Collections.emptyList());

        final MockHttpServletResponse response = mockMvc.perform(post("/patient/update/{id}", 0)
                        .param("id", "0")
                        .param("firstName", "firstName")
                        .param("lastName", "lastName")
                        .param("birthdate", "2020-01-01")
                        .param("sex", "M")
                        .param("address", "address")
                        .param("phone", "phone")
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
    }

    @Test
    void testDeletePatient() throws Exception {
        final MockHttpServletResponse response = mockMvc.perform(get("/patient/delete/{id}", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
    }
}