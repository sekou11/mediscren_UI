package mediscreen_Ui.Proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import feign.Headers;
import feign.RequestLine;
import mediscreen_Ui.Models.Patient;

@FeignClient(name = "mspatient",url="localhost:8701")
public interface PatientProxy {
	 @GetMapping("/patient/list")
	    List<Patient> getAllPatients();

	    @GetMapping("/patient/{id}")
	    Patient getPatient(@PathVariable int id);
	    
	    @RequestLine("POST")
	    @Headers("Content-Type: application/json")
	    @PostMapping(value = "/patient/add", consumes = "application/json")
	    Patient addPatient(@RequestBody Patient patient);

	    @PostMapping("/patient/update/{id}")
	    Patient updatePatient(@RequestBody Patient patient, @PathVariable int id);

	    @GetMapping("/patient/delete/{id}")
	    void deletePatient(@PathVariable Integer id);

}
