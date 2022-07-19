package mediscreen_Ui.Proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mediscreen_Ui.Models.Patient;

@FeignClient(name = "mspatient",url="http://localhost:8701")
public interface PatientProxy {
	 @GetMapping("/patient/list")
	    List<Patient> getAllPatients();

	    @GetMapping("/patient/{id}")
	    Patient getPatient(@PathVariable int id);  
	    
	
	   
	    @RequestMapping(value = "/patient/save", method = RequestMethod.POST)
	    Patient addPatient(@RequestBody Patient patient);  

	    @PutMapping("/patient/update/{id}")
	    Patient updatePatient(@RequestBody Patient patient, @PathVariable int id);

	    @GetMapping("/patient/delete/{id}")
	    void deletePatient(@PathVariable Integer id);

}
