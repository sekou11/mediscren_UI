package mediscreen_Ui.Controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import mediscreen_Ui.Models.Patient;
import mediscreen_Ui.Services.Impl.PatientServiceImpl;

@Controller
public class PatientController {
	 private static final Logger logger = LogManager.getLogger(PatientController.class);
	@Autowired
	private PatientServiceImpl patientServiceImpl;
	
	 @GetMapping("/patient/list")
    public String getAllPatients(Model model) {
		 logger.info("la liste des patients");
    	List<Patient>patients = patientServiceImpl.getAllPatients();
    	model.addAttribute("patients", patients);
    	return "patient/list";
    }
	 
	 
	    @GetMapping("/patient/fiche/{id}")
	    public String getPatient(@PathVariable Integer id, Model model) {
	        Patient patient = patientServiceImpl.getPatient(id);
	       

	        model.addAttribute("patient", patient);
	        

//	        List<Note> notes = noteProxy.getNotesByPatient(id);
//	        model.addAttribute("notes", notes);

	        return "patient/fichePatient";
	    }
	    
	    @GetMapping("/patient/add")
	    public String showAddPatientForm(Patient patient) {
	        return "patient/add";
	    }
	    
	    @PostMapping("/patient/add")
	    public String submitAddPatientForm(@Valid Patient patient, BindingResult result, Model model) {
	        if (!result.hasErrors()) {
	        	patientServiceImpl.addPatient(patient);
	            model.addAttribute("patients", patientServiceImpl.getAllPatients());
	            logger.info("POST /patient/validate : OK");
	            return "redirect:/patient/list";
	        }
	        logger.info("/patient/validate : KO");
	        return "patient/add";
	    }  
	    
	    @GetMapping("/patient/update/{id}")
	    public String updatePatientForm(@PathVariable(value = "id") int id, Model model) {
	        model.addAttribute("patient", patientServiceImpl.getPatient(id));
	        return "patient/update";
	    }
	    
	    @PostMapping("/patient/update/{id}")
	    public String updatePatient(@Valid Patient patient, @PathVariable("id") int id, BindingResult result, Model model) {
	        if (!result.hasErrors()) {
	        	patientServiceImpl.updatePatient(id, patient);
	            model.addAttribute("patients", patientServiceImpl.getAllPatients());
	            logger.info("POST /patient/update : OK");
	            return "redirect:/patient/fiche/{id}";
	        }
	        logger.info("/patient/update : KO");
	        return "patient/update";
	    }

	    @GetMapping("/patient/delete/{id}")
	    public String deletePatient(@PathVariable int id) {
	    	patientServiceImpl.deletePatient(id);
	        return "redirect:/patient/list";
	    }
}
